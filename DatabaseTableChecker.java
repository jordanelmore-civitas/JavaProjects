import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class DatabaseTableChecker {

    /*
    Used to check required peoplesoft tables v.s the actual tables available in
    peoplesoft. To use the function enter the list of tables available and it will
    automatically compare them against what we need and return the tables needed
    to confirm data access.
     */
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
