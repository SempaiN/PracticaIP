package Data;

import Domain.Poi;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import iesserpis.practicaip.Tools;
import org.bson.Document;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class
PoiDAOSQL {
    private static final String SELECT = "SELECT poid,latitude,longitude,country,city,description,updated FROM pois_np21";
    private static final String INSERT = "insert into pois_np21 (poid,latitude,longitude,country,city,description,updated) values (?, ?,?,?,?,?,?)";
    private static final String selectOne = "SELECT poid,latitude,longitude,country,city,description, updated from pois_np21 where poid = ?";
    private static final String deleteAll = "Delete from pois_np21";
    private static final String deleteOne = "Delete from pois_np21 where poid = ?";

    public List<Poi> getTable() {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        List<Poi> pois = new ArrayList<>();
        try {
            conexion = ConnectionSQL.getConnection();
            stmt = conexion.prepareStatement(SELECT);
            result = stmt.executeQuery();
            while (result.next()) {
                int poid = result.getInt("poid");
                double latitude = result.getDouble("latitude");
                double longitude = result.getDouble("longitude");
                String country = result.getString("country");
                String city = result.getString("city");
                String description = result.getString("description");
                String updated = result.getString("updated");
                Poi poi = new Poi(poid, latitude, longitude, country, city, description, updated);
                pois.add(poi);
            }
            ConnectionSQL.close(conexion);
            ConnectionSQL.close(result);
            ConnectionSQL.close(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return pois;
    }


    public int insert(Poi poi) {
        Date date;
        java.sql.Date date1;
        Connection connection = null;
        PreparedStatement stmt = null;
        int insert = 0;
        try {
            connection = ConnectionSQL.getConnection();
            stmt = connection.prepareStatement(INSERT);
            stmt.setInt(1, poi.getPoid());
            stmt.setDouble(2, poi.getLatitude());
            stmt.setDouble(3, poi.getLongitude());
            stmt.setString(4, poi.getCountry());
            stmt.setString(5, poi.getCity());
            stmt.setString(6, poi.getDescription());
            if (poi.getUpdated() == "") {
                stmt.setNull(7, Types.DATE);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = simpleDateFormat.parse(poi.getUpdated());
                date1 = new java.sql.Date(date.getTime());
                stmt.setDate(7, date1);
            }
            insert = stmt.executeUpdate();
            System.out.println("Inserted item ====> ");
            System.out.println(poi);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ParseException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                ConnectionSQL.close(stmt);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            try {
                ConnectionSQL.close(connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return insert;
    }

    public void insertMany(List<Poi> poiList) {
        try {
            for (int i = 0; i < poiList.size(); i++) {
                insert(poiList.get(i));
            }
        } catch (Exception e) {
            System.out.println("An error has ocurred!!!");
            e.printStackTrace(System.err);
        }
    }

    public List<Poi> getTable(int poidFilter) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        List<Poi> poiList = new ArrayList<>();
        try {
            connection = ConnectionSQL.getConnection();
            stmt = connection.prepareStatement(selectOne);
            stmt.setInt(1, poidFilter);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int poid = resultSet.getInt("poid");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String description = resultSet.getString("description");
                String updated = resultSet.getString("updated");
                Poi poi = new Poi(poid, latitude, longitude, country, city, description, updated);
                poiList.add(poi);
            }
        } catch (Exception ex) {
            System.out.println("An error has ocurred");
            ex.printStackTrace(System.err);
        }
        return poiList;
    }

    public void delete() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionSQL.getConnection();
            stmt = connection.prepareStatement(deleteAll);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("An error has ocurred");
            ex.printStackTrace(System.err);
        } finally {
            try {
                ConnectionSQL.close(connection);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            try {
                ConnectionSQL.close(stmt);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void delete(int poidFilter) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionSQL.getConnection();
            stmt = connection.prepareStatement(deleteOne);
            stmt.setInt(1, poidFilter);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("An error has ocurred");
            ex.printStackTrace(System.err);
        } finally {
            try {
                ConnectionSQL.close(connection);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            try {
                ConnectionSQL.close(stmt);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
