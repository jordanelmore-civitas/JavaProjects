import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class PeopleSoftTableCheck {

    /*
    Used to check required peoplesoft tables v.s the actual tables available in
    peoplesoft. To use the function enter the list of tables available and it will
    automatically compare them against what we need and return the tables needed
    to confirm data access.
     */
    public static String getRequiredTables() {

        return "ps_acad_degr ps_acad_degr_plan ps_acad_group_tbl " +
                "ps_acad_org_tbl ps_acad_plan ps_acad_plan_tbl ps_acad_prog " +
                "ps_acad_prog_tbl ps_acad_subplan ps_acad_subpln_tbl ps_degree_tbl " +
                "ps_class_instr ps_class_tbl ps_class_tbl_se_vw ps_grade_tbl ps_stdnt_enrl " +
                "ps_item_type_fa ps_stdnt_awrd_actv ps_stdnt_awrd_disb ps_acad_degr_spln " +
                "ps_campus_tbl ps_crse_attributes ps_crse_catalog ps_crse_offer ps_ethnic_grp_tbl " +
                "ps_ext_acad_data ps_ext_acad_sum ps_ext_degree ps_ext_org_tbl_adm " +
                "ps_honor_award_cs ps_honor_award_tbl ps_institution_tbl ps_instruct_mode ps_personal_data " +
                "ps_repeat_code_tbl ps_rqmnt_desig_tbl ps_sa_tcmp_rel_tbl ps_sa_test_tbl ps_srvc_ind_cd_tbl " +
                "ps_srvc_ind_data ps_ssr_crse_typoff ps_stdnt_advr_hist ps_stdnt_group_tbl ps_stdnt_grps " +
                "ps_transcript_req ps_transcript_type ps_trns_crse_sch psxlatitem ps_addresses ps_email_addresses " +
                "ps_ethnicity_dtl ps_names ps_pers_data_effdt ps_personal_phone ps_clst_detl_tbl ps_rq_grp_detl_tbl " +
                "ps_rq_grp_tbl ps_rq_line_tbl ps_rq_ln_detl_tbl ps_rq_main_tbl ps_session_tbl ps_term_tbl " +
                "ps_adm_appl_data ps_adm_appl_prog ps_admit_type_tbl ps_acad_car_tbl ps_stdnt_car_term " +
                "ps_acad_stactn_tbl ps_acad_stdng_actn ps_stdnt_test_compps_ext_org_tbl ps_trns_crse_dtl " +
                "ps_trns_crse_term ps_trns_test_dtl".toLowerCase();
    }

    public static String getDatabaseTables(){
        System.out.println("Enter the current DB tables in a string format separated by a space");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine().toLowerCase();
    }

    public static Set<String> createSetList(String tables) {
        Set<String> list = new HashSet();
        String[] SplitValues = tables.split(" ");

        for (int value = 0; value < SplitValues.length; value++) {
            list.add(SplitValues[value]);
        }

        //To check the list in values
        //System.out.println("tables: " + list);
        return list;
    }

    public static void checkForMissingTables(Set<String> requiredTables, Set<String> databaseTables){

        System.out.println("Missing tables: ");
        for (String table : requiredTables){
            if (!databaseTables.contains(table)) {
                System.out.println(table);
            }
        }
    }

    public static void main(String[] args){

        Set<String> requiredTables = createSetList(getRequiredTables());
        Set<String> databaseTables = createSetList(getDatabaseTables());

        checkForMissingTables(requiredTables,databaseTables);


    }

}
