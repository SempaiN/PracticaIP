package Data;

import Domain.Poi;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PoiDAOMongo {
    public List<Poi> getCollection() {
        Poi poi;
        int poid;
        Double latitude;
        Double longitude;
        String city;
        String country;
        String description;
        String updated;
        List<Poi> pois = new ArrayList<>();
        MongoClient conn = ConnectionMongo.connectToMongoClient();
        try {
            MongoDatabase database = conn.getDatabase("poidb");
            MongoCollection<Document> collection = database.getCollection("pois_np21");
            MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

            while (cursor.hasNext()) {
                Document item = cursor.next();
                poid = item.getInteger("poid");
                latitude = item.getDouble("latitude");
                longitude = item.getDouble("longitude");
                city = item.getString("city");
                country = item.getString("country");
                description = item.getString("description");
                updated = item.getString("updated");
                poi = new Poi(poid, latitude, longitude, country, city, description, updated);
                pois.add(poi);
            }

        } catch (Exception exe) {
            System.out.println("An error has ocurred");
            exe.printStackTrace(System.err);
        }
        ConnectionMongo.closeMongoClient(conn);
        return pois;
    }

    public void insert(List<Poi> pois) {
        MongoClient conn = ConnectionMongo.connectToMongoClient();
        MongoDatabase database = conn.getDatabase("poidb");
        MongoCollection<Document> collection = database.getCollection("pois_np21");
        Document item = new Document();
        System.out.println("Inserting " + pois.size() + " elements in the database");
        for (int i = 0; i < pois.size(); i++) {
            item.put("poid", pois.get(i).getPoid());
            item.put("latitude", pois.get(i).getLatitude());
            item.put("longitude", pois.get(i).getLongitude());
            item.put("country", pois.get(i).getCountry());
            item.put("city", pois.get(i).getCity());
            item.put("description", pois.get(i).getDescription());
            item.put("updated", pois.get(i).getUpdated());
            collection.insertOne(item);
        }
        ConnectionMongo.closeMongoClient(conn);
    }

    public List<Poi> getCollection(int poidFilter) {
        Poi poi;
        List<Poi> pois = new ArrayList<>();
        MongoClient conn = ConnectionMongo.connectToMongoClient();
        try {
            MongoDatabase database = conn.getDatabase("poidb");
            MongoCollection<Document> collection = database.getCollection("pois_np21");
            MongoCursor<Document> cursor = collection.find(eq("poid", poidFilter)).projection(Projections.excludeId()).iterator();
            while (cursor.hasNext()) {

                Document item = cursor.next();
                int poid = item.getInteger("poid");
                Double latitude = item.getDouble("latitude");
                Double longitude = item.getDouble("longitude");
                String city = item.getString("city");
                String country = item.getString("country");
                String description = item.getString("description");
                String updated = item.getString("updated");
                poi = new Poi(poid, latitude, longitude, country, city, description, updated);
                pois.add(poi);
            }

        } catch (Exception exe) {
            System.out.println("An error has ocurred");
            exe.printStackTrace(System.err);
        }
        ConnectionMongo.closeMongoClient(conn);
        return pois;
    }

    public void delete() {
        MongoClient conn = ConnectionMongo.connectToMongoClient();
        MongoDatabase database = conn.getDatabase("poidb");
        MongoCollection<Document> collection = database.getCollection("pois_np21");
        try {
            collection.deleteMany(new Document());

        } catch (Exception ex) {
            System.out.println("An error has ocurred");
            ex.printStackTrace(System.err);
        }
        ConnectionMongo.closeMongoClient(conn);
    }

    public void delete(int poid) {
        MongoClient conn = ConnectionMongo.connectToMongoClient();
        MongoDatabase database = conn.getDatabase("poidb");
        MongoCollection<Document> collection = database.getCollection("pois_np21");
        try {
            collection.deleteOne(eq("poid", poid));

        } catch (Exception ex) {
            System.out.println("An error has ocurred");
            ex.printStackTrace(System.err);
        }
        ConnectionMongo.closeMongoClient(conn);
    }

    public void upsertPoi(int poid, double latitude, double longitude, String country, String city, String description, String updated) {
        MongoClient conn = ConnectionMongo.connectToMongoClient();
        MongoDatabase database = conn.getDatabase("poidb");
        MongoCollection<Document> collection = database.getCollection("pois_np21");
        try  {
            // Crear un documento para el filtro basado en el poid
            Document filter = new Document("poid", poid);

            // Crear un documento de actualización usando $set
            Document updateFields = new Document();
            updateFields.append("latitude", latitude);
            updateFields.append("longitude", longitude);
            updateFields.append("country", country);
            updateFields.append("city", city);
            updateFields.append("description", description);
            updateFields.append("updated", updated);

            Document updateOperationDocument = new Document("$set", updateFields);

            // Configurar las opciones de upsert
            UpdateOptions options = new UpdateOptions().upsert(true);

            // Realizar la operación de upsert
            collection.updateOne(filter, updateOperationDocument, options);

            System.out.println("Upsert completado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
