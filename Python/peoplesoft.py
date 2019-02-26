def peoplesoft_table_check():
    '''Used to check required peoplesoft tables v.s the actual tables available in \
    peoplesoft. To use the function enter the list of tables available and it will \
    automatically compare them against what we need and return the tables needed\
    to confirm data access. '''

    print "Type in the current DB tables provides in a string format"
    DB_tables = raw_input()

    required_list = 'ps_acad_degr ps_acad_degr_plan ps_acad_group_tbl ps_acad_org_tbl \
                    ps_acad_plan ps_acad_plan_tbl ps_acad_prog ps_acad_prog_tbl \
                    ps_acad_subplan ps_acad_subpln_tbl ps_degree_tbl ps_class_instr \
                    ps_class_tbl ps_class_tbl_se_vw ps_grade_tbl ps_stdnt_enrl \
                    ps_item_type_fa ps_stdnt_awrd_actv ps_stdnt_awrd_disb \
                    ps_acad_degr_spln ps_campus_tbl ps_crse_attributes ps_crse_catalog \
                    ps_crse_offer ps_ethnic_grp_tbl ps_ext_acad_data ps_ext_acad_sum \
                    ps_ext_degree ps_ext_org_tbl_adm ps_honor_award_cs ps_honor_award_tbl \
                    ps_institution_tbl ps_instruct_mode ps_personal_data ps_repeat_code_tbl \
                    ps_rqmnt_desig_tbl ps_sa_tcmp_rel_tbl ps_sa_test_tbl ps_srvc_ind_cd_tbl \
                    ps_srvc_ind_data ps_ssr_crse_typoff ps_stdnt_advr_hist ps_stdnt_group_tbl \
                    ps_stdnt_grps ps_transcript_req ps_transcript_type ps_trns_crse_sch \
                    psxlatitem ps_addresses ps_email_addresses ps_ethnicity_dtl ps_names \
                    ps_pers_data_effdt ps_personal_phone ps_clst_detl_tbl ps_rq_grp_detl_tbl \
                    ps_rq_grp_tbl ps_rq_line_tbl ps_rq_ln_detl_tbl ps_rq_main_tbl \
                    ps_session_tbl ps_term_tbl ps_adm_appl_data ps_adm_appl_prog \
                    ps_admit_type_tbl ps_acad_car_tbl ps_stdnt_car_term \
                    ps_acad_stactn_tbl ps_acad_stdng_actn ps_stdnt_test_comp \
                    ps_ext_org_tbl ps_trns_crse_dtl ps_trns_crse_term ps_trns_test_dtl'

    required_tables = required_list.lower()
    DB_tables = DB_tables.lower()

    required_set =  set(required_tables.split())
    DB_tables_set = set(DB_tables.split())

    tables_needed = required_set.difference(DB_tables_set)

    if len(required_set.difference(DB_tables_set)) == 0:
        print ""
        print "You have all the requried tables. Make sure you have data in all the tables."
        print ""
    else:
        print ""
        print ("%r tables are needed to move forward and confirm data access." % tables_needed)
        print ""

peoplesoft_table_check()
