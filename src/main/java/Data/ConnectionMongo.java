package Data;

import com.mongodb.MongoClient;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;


public class ConnectionMongo {
    public static MongoClient connectToMongoClient() {
        try {
            MongoClient dbClient = new MongoClient();
            return dbClient;
        } catch (Exception ex) {
            System.out.println("Something wrong connecting");
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public static void closeMongoClient(MongoClient mongoClient) {
        try {
            mongoClient.close();
        } catch (Exception ex) {
            System.out.println("Something wrong closing!!");
            ex.printStackTrace(System.out);
        }
    }

    public static void disableMongoLogging() {
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
    }
}
