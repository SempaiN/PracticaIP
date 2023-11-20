
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package iesserpis.practicaip;

import Data.ConnectionMongo;
import Data.PoiDAOMongo;
import Data.PoiDAOSQL;
import Domain.Menu;
import Domain.Poi;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author troll
 */
public class PracticaIP {
    public static PoiDAOSQL poiDAOSQL = new PoiDAOSQL();
    public static PoiDAOMongo poiDAOMongo = new PoiDAOMongo();
    public static boolean MongoDB = false;
    public static boolean MySQL = false;

    public static void main(String[] args) {
        Tools.welcome();
        ConnectionMongo.disableMongoLogging();
        try {
            upsert();
            //            selectorDatabaseMenu(Menu.selectDB());
//            if (MongoDB) {
//                mainMenuMongo(Menu.mainMenuMongo(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
//            } else {
//                mainMenuSQL(Menu.mainMenuSQL(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
//            }
//            showDatabase(poiDAOMongo.getCollection(12));

//            poiDAOSQL.insertMany(createListPois());
        } catch (Exception ex) {
            System.out.println("Algo ha pasao");
        }

    }

    public static List<Poi> createListPois(boolean mySQL) {
        int poid;
        Double latitude;
        Double longitude;
        String city;
        String country;
        String description;
        String updated;
        List<Poi> pois = new ArrayList<>();
        try {
            while (true) {
                System.out.println("ID: (zero to exit)");
                poid = Tools.getInt();
                if (poid == 0) {
                    break;
                }
                if (mySQL) {
                    if (!Tools.isValid(poiDAOSQL.getTable(), poid)) {
                        System.out.println("POI already exists in the database");
                        break;
                    }
                } else {
                    if (!Tools.isValid(poiDAOMongo.getCollection(), poid)) {
                        System.out.println("POI already exists in the collection");
                        break;
                    }
                }
                System.out.println("Latitude: [enter for blank]");
                latitude = Tools.getDouble(true);
                System.out.println("Longitude: [enter for blank]");
                longitude = Tools.getDouble(true);
                System.out.println("City: [enter for blank]");
                city = Tools.getString();
                System.out.println("Country: [enter for blank]");
                country = Tools.getString();
                System.out.println("Description: [enter for blank]");
                description = Tools.getString();
                System.out.println("Updated: [enter for blank]");
                updated = Tools.readDate();
                Poi poi = new Poi(poid, latitude, longitude, country, city, description, updated);
                pois.add(poi);
                System.out.println("Inserted item ====> ");
                System.out.println(poi);
            }
        } catch (Exception ex) {
            System.out.println("An error has ocurred");
            ex.printStackTrace(System.err);
        }
        return pois;
    }

    public static void showDatabase(List<Poi> poiList) {
        poiList.forEach(poi -> System.out.println(poi));
    }

    public static boolean checkElement(List<Poi> poisMongo, List<Poi> poisSQL) {
        if (poisMongo.size() > poisSQL.size()) {
            return true;
        }
        if (poisSQL.size() > poisMongo.size()) {
            return true;
        }
        return false;
    }

    public static void selectorDatabaseMenu(int option) {
        switch (option) {
            case 1:
                MongoDB = true;
                break;
            case 2:
                MySQL = true;
                break;
        }
    }

    public static void mainMenuSQL(int option) {
        switch (option) {
            case 1:
                insertData(MySQL);
                mainMenuSQL(Menu.mainMenuSQL(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
                break;
            case 2:
                viewDataMenu(MySQL, Menu.viewDataMenu());
                break;
            case 3:
                //deleteDataMenu();
                break;
            case 4:
                if (checkElement(poiDAOMongo.getCollection(), poiDAOSQL.getTable())) {
                    synchronizeElementsSQL();
                } else {
                    System.out.println("Option not available");
                }
                break;
            case 0:
                MySQL = false;
                MongoDB = false;
                selectorDatabaseMenu(Menu.selectDB());
                if (MySQL) {
                    mainMenuSQL(Menu.mainMenuSQL(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
                } else {
                    mainMenuMongo(Menu.mainMenuMongo(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));

                }
        }
    }

    public static void mainMenuMongo(int option) {
        switch (option) {
            case 1:
                insertData(MySQL);
                mainMenuMongo(Menu.mainMenuMongo(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
                break;
            case 2:
                viewDataMenu(MySQL, Menu.viewDataMenu());
                break;
            case 3:
                //deleteDataMenu();
                break;
            case 4:
                //upsert()
                break;
            case 5:
                if (checkElement(poiDAOMongo.getCollection(), poiDAOSQL.getTable())) {
                    synchronizeElementsMongo();
                } else {
                    System.out.println("Option not available");
                }
            case 0:
                MySQL = false;
                MongoDB = false;
                selectorDatabaseMenu(Menu.selectDB());
                if (MySQL) {
                    mainMenuSQL(Menu.mainMenuSQL(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
                } else {
                    mainMenuMongo(Menu.mainMenuMongo(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));

                }

        }
    }

    public static void viewDataMenu(boolean MySQL, int option) {
        switch (option) {
            case 1:
                if (MySQL) {
                    showDatabase(poiDAOSQL.getTable());
                } else {
                    showDatabase(poiDAOMongo.getCollection());
                }
                viewDataMenu(MySQL, Menu.viewDataMenu());
                break;
            case 2:
                System.out.println("Type the poid you want to search for");
                int poid = Tools.getInt();
                if (MySQL) {
                    List<Poi> filtredList = poiDAOSQL.getTable(poid);
                    if (filtredList.isEmpty()) {
                        System.out.println("Item does not exist in the list");
                    } else {
                        showDatabase(filtredList);
                    }
                } else {
                    List<Poi> filtredList = poiDAOMongo.getCollection(poid);
                    if (filtredList.isEmpty()) {
                        System.out.println("Item does not exist in the list");
                    } else {
                        showDatabase(filtredList);
                    }
//                   showDatabase( poiDAOMongo.getCollection(poid));
                }
                viewDataMenu(MySQL, Menu.viewDataMenu());
                break;
            case 3:
                // two filters
            case 0:
                if (MySQL) {
                    mainMenuSQL(Menu.mainMenuSQL(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
                } else {
                    mainMenuMongo(Menu.mainMenuMongo(poiDAOMongo.getCollection(), poiDAOSQL.getTable()));
                }
                break;
        }
    }

    public static void insertData(boolean mySQL) {
        List<Poi> poiList = createListPois(mySQL);
        if (mySQL) {
            poiDAOSQL.insertMany(poiList);
        } else {
            poiDAOMongo.insert(poiList);
        }
    }

    public static String managedDatabase(boolean MySQL) {
        if (MySQL) {
            return "You are managing the Mysql bbdd";
        } else {
            return "You are managing the Mongo bbdd";
        }
    }

    public static void synchronizeElementsSQL() {
        poiDAOMongo.delete();
        poiDAOMongo.insert(poiDAOSQL.getTable());
    }

    public static void synchronizeElementsMongo() {
        poiDAOSQL.delete();
        poiDAOSQL.insertMany(poiDAOMongo.getCollection());
    }

    public static void deleteMenu(int option, boolean mySQL) {
        switch (option) {
            case 1:
                System.out.println("===============================================");
                System.out.println("  WARNING: Delete All Data Confirmation");
                System.out.println("===============================================");
                System.out.println("Are you sure you want to delete all data?");
                System.out.println("This action cannot be undone!");
                System.out.println();
                System.out.print("Type 'yes' to confirm or any other key to cancel: ");
                String confirm = Tools.getString();
                if (confirm.toLowerCase().equals("yes")) {
                    if (mySQL) {
                        poiDAOSQL.delete();
                    } else {
                        poiDAOMongo.delete();
                    }
                }
                deleteMenu(Menu.deleteDataMenu(), mySQL);
                break;
            case 2:
                System.out.println("Enter the poid you want to delete");
                int poid = Tools.getInt();
        }
    }

    public static void upsert() {
        System.out.println("Enter a POID");

        int poid = Tools.getInt();

        System.out.println("Latitude: [press Enter for blank]");
        Double latitude = Tools.getDouble(true);

        System.out.println("Longitude: [press Enter for blank]");
        Double longitude = Tools.getDouble(true);

        System.out.println("City: [press Enter for blank]");
        String city = Tools.getString();

        System.out.println("Country: [press Enter for blank]");
        String country = Tools.getString();

        System.out.println("Description: [press Enter for blank]");
        String description = Tools.getString();

        System.out.println("Updated: [press Enter for blank]");
        String updated = Tools.readDate();

        poiDAOMongo.upsertPoi(poid, latitude, longitude, country, city, description, updated);
    }
}
