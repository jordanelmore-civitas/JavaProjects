#! /usr/bin/env python

import json
import sys
import requests
import dateutil.parser
import datetime
import pytz
import argparse

def processArgs():
  global opts

  a = argparse.ArgumentParser()
  a.add_argument('-c', '--config', help='default config')
  a.add_argument('--json', action='store_true',  help='indicates that output should be json')
  a.add_argument('--stats', action='store_true', help='indicates that you want details for the institutions')
  a.add_argument('institutions', nargs='*', help='list of institutions')
  
  opts = a.parse_args()

def get_inst_node(inst):
  for node in etcd_state['node']['nodes']:
    if node['key'].split('/')[-1] == inst:
      return node
  return None

def check_flags(inst):
  etcd_j = get_inst_node(inst)
  flags = {}
  #etcd_j = requests.get(ETCD.format(inst)).json()
  if not etcd_j or not etcd_j.get('nodes'):
    return {}
  for nodes in etcd_j['nodes']:
    key = nodes['key'].split('/')[-1]
    flags[key] = nodes['value']
  return flags

def ago(dt):
  dt = dt.total_seconds()
  if dt < 3600:
    return '%.1fm' % (dt/60.0)
  elif dt < 86400:
    return '%.1fh' % (dt/3600.0)
  else:
    return '%.1fd' % (dt/86400.0)

def estimate_eta(extract, dt):
  extract_tables = extract.get('extracts')
  if not extract_tables:
    return
  total_progress = 0
  total_slices = 0
  for table, ti in extract_tables.iteritems():
    total_progress += ti.get('slice_num', 0)
    total_slices += ti.get('slice_count', 0)
  if total_slices:
    if total_progress:
      rate = dt.total_seconds() / total_progress
      remain = (total_slices - total_progress) * rate
      return '(%d/%d ETA +%.1fh)' % (total_progress,
                                   total_slices,
                                   remain / 3600.0)
    else:
      return '(%d/%d)' % (total_progress, total_slices)
  else:
    return None

def system_overview():
  if opts.json:
    istats = []
    for inst in sorted(current['institutions'].keys()):
      instinfo = current['institutions'][inst]
      istats.append(InstitutionStatus(inst, instinfo).info)
    print json.dumps(istats, indent=2, sort_keys=True)
  else: 
    InstitutionStatus.showsummaryheader()
  
    for inst in sorted(current['institutions'].keys()):
      instinfo = current['institutions'][inst]
      istat = InstitutionStatus(inst, instinfo)
      istat.show()

def institution_details(lst):
  if opts.json:
    istats = []
    for inst in lst:
      if inst in current['institutions']:
        istats.append(InstitutionStatus(inst, current['institutions'][inst]).info)
    print json.dumps(istats, indent=2, sort_keys=True)
    
  else:
    InstitutionStatus.showdetailsheader()
  
    for inst in lst:
      if inst in current['institutions']:
        istat = InstitutionStatus(inst, current['institutions'][inst])
        istat.showdetails('last_extract_work')

class InstitutionStatus(object):
  SUMMARY_FORMAT='{:18} | {:15} | {}'
  DETAILS_FORMAT='{:18} | {:40} | {:10} | {:9} | {} / {}'

  def __init__(self, name, info):
    self.name = name
    self.annot = ''
    self.info = info

    flags = check_flags(name)
    annot = ''
    st = info['status']
    if flags.get('disable-extract'):
      st = 'EX.DISABLED'
    if flags.get('disable'):
      st = 'DISABLED'

    last_sync = info.get('last_sync')
    if last_sync and (('Invalid api key' in last_sync.get('disposition', ''))
                      or ('client error 401' in last_sync.get('disposition', ''))):
      self.overview = 'BAD KEY'
      return

    if last_sync and last_sync.get('artifacts', []):
      canvas_sync = last_sync.get('artifacts')[-1]['params']['canvas']
      self.sync_overview = '#{}'.format(canvas_sync['sequence'])

    last_extract = info.get('last_extract')
    if last_extract:
      start_ts = dateutil.parser.parse(last_extract['start_ts'])
      finish_ts = last_extract.get('finish_ts')
      if finish_ts:
        finish_ts = dateutil.parser.parse(finish_ts)
        dt = finish_ts - start_ts
        suffix = ' in %.1f hours' % (dt.total_seconds() / 3600)
      else:
        dt = datetime.datetime.now(pytz.utc) - start_ts
        suffix = ' for %.1f hours' % (dt.total_seconds() / 3600)
        eta = estimate_eta(last_extract, dt)
        if eta:
          suffix = suffix + ' ' + eta
      if 'nothing' in last_extract.get('disposition', ''):
        suffix = ''
      annot = last_extract.get('disposition', '?') + suffix

    lx_work = info.get('last_extract_work')
    if lx_work and annot == 'nothing to extract':
      xs = lx_work.get('extracts',{})
      finish_ts = dateutil.parser.parse(lx_work['finish_ts'])
      dt = datetime.datetime.now(pytz.utc) - finish_ts
      annot = 'last extracted {} tables with {} slices {} ago'.format(
        len(xs),
        sum([tbl.get('slice_count',0) for tbl in xs.values()]),
        ago(dt))

    self.overview = st
    self.annot = annot

  @staticmethod
  def showsummaryheader():
    print InstitutionStatus.SUMMARY_FORMAT.format(
      'Institution', 'Status','Notes')

  @staticmethod
  def showdetailsheader():
    print InstitutionStatus.DETAILS_FORMAT.format(
      'Institution', 'Table', 'Status', 'Artifat', 'Slice', 'Total'
    )

  def show(self):
    print self.SUMMARY_FORMAT.format(self.name,
                                      self.overview,
                                      self.annot)

  def showdetails(self, mode='last_extract'):
    extracts = self.info[mode]['extracts']
    for table in sorted(extracts.keys()):
      ti = extracts[table]
      print InstitutionStatus.DETAILS_FORMAT.format(
        self.name,
        table,
        ti.get('status', '--'),
        ti.get('artifact', {}).get('_id', '--'),
        ti.get('slice_num', '--'),
        ti.get('slice_count', '--'),
      )

def main():
  global etcd_state
  global current
  processArgs()

  ETCD_QUERY = 'http://config-etcd-prod-node1.private.civitaslearning.com:2379/v2/keys/service/canvas-integration/institution/?recursive=true'
  CI = 'http://canvas-integration-prod.private.civitaslearning.com:8080/canvas/v1/status'

  sys.stderr.write('Gathering control flags.\n')
  etcd_state = requests.get(ETCD_QUERY).json()

  sys.stderr.write('Reading current state.\n')
  current = requests.get(CI).json()

  if len(opts.institutions) == 0:
    system_overview()
  else:
    institution_details(opts.institutions)

if __name__ == '__main__':
  main()
