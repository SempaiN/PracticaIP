package Domain;

import iesserpis.practicaip.PracticaIP;
import iesserpis.practicaip.Tools;

import java.util.List;

public class Menu {

    public static int selectDB() {
        System.out.println("=======================================");
        System.out.println("          SELECT DATABASE");
        System.out.println("=======================================");
        System.out.println("1. Mongo DB");
        System.out.println("2. MySQL");
        System.out.println("0. Exit");
        System.out.println("=======================================");
        System.out.print("Please enter your choice: ");

        return Tools.getInt();
    }

    public static int mainMenuSQL(List<Poi> poisMongo, List<Poi> poisSQL) {
        System.out.println(PracticaIP.managedDatabase(PracticaIP.MySQL));
        System.out.println("=============================================");
        System.out.println("                MAIN MENU - SQL");
        System.out.println("=============================================");
        System.out.println("1. Insert Data");
        System.out.println("2. View Data");
        System.out.println("3. Delete Data");

        if (PracticaIP.checkElement(poisMongo, poisSQL)) {
            System.out.println("4. Synchronize Elements");
        }

        System.out.println("0. Select Database");
        System.out.println("=============================================");
        System.out.print("Please enter your choice: ");

        return Tools.getInt();
    }

    public static int mainMenuMongo(List<Poi> poisMongo, List<Poi> poisSQL) {
        System.out.println(PracticaIP.managedDatabase(PracticaIP.MySQL));
        System.out.println("=============================================");
        System.out.println("              MAIN MENU - MongoDB");
        System.out.println("=============================================");
        System.out.println("1. Insert Data");
        System.out.println("2. View Data");
        System.out.println("3. Delete Data");
        System.out.println("4. Update Data");

        if (PracticaIP.checkElement(poisMongo, poisSQL)) {
            System.out.println("5. Synchronize Elements");
        }

        System.out.println("0. Select Database");
        System.out.println("=============================================");
        System.out.print("Please enter your choice: ");
        return Tools.getInt();
    }


    public static int viewDataMenu() {
        System.out.println(PracticaIP.managedDatabase(PracticaIP.MySQL));
        System.out.println("=======================================");
        System.out.println("          VIEW DATA MENU");
        System.out.println("=======================================");
        System.out.println("1. View All Data");
        System.out.println("2. View Data with One Filter");
        System.out.println("3. View Data with Two Filters");
        System.out.println("0. Exit");
        System.out.println("=======================================");
        System.out.print("Please enter your choice: ");

        return Tools.getInt();
    }

    public static int deleteDataMenu() {
        System.out.println(PracticaIP.managedDatabase(PracticaIP.MySQL));
        System.out.println("=======================================");
        System.out.println("          DELETE DATA MENU");
        System.out.println("=======================================");
        System.out.println("1. Delete All Data !!!!");
        System.out.println("2. Delete One Element");
        System.out.println("3. Delete Two Elements");
        System.out.println("0. Exit");
        System.out.println("=======================================");
        System.out.print("Please enter your choice: ");

        return Tools.getInt();
    }
}
