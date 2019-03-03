import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

/*
Used to check required tables v.s the actual tables available in a database.
To utilize on the command line, call the script and give two file paths as arguments
    
Example:
java DatabaseTableChecker.java <required_tables_file_path> <database_tables_file_path>
java DatabaseTableChecker.java /Users/jordaneelmore/Documents/Doc2/test_ps_checker_file.txt /Users/jordaneelmore/Documents/Doc2/database_tables.txt.
    
Separate each table name by a space or update the regex in createSetList to look for another delimiter
*/

public class DatabaseTableChecker {

    public static String getTables(String filepath) throws Exception {
        //In file, separate tables by a space
        File file = new File(filepath);
        Scanner in = new Scanner(file);

        return in.nextLine().toLowerCase();

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

    public static void main(String[] args) throws Exception {

        String requiredTablesFilePath = args[0];
        String databaseTablesFilePath = args[1];

        Set<String> requiredTables = createSetList(getTables(requiredTablesFilePath));
        Set<String> databaseTables = createSetList(getTables(databaseTablesFilePath));

        checkForMissingTables(requiredTables,databaseTables);

    }

}
