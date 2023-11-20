package Domain;

public class Poi {
    public int poid;

    public Double latitude;

    public Double longitude;

    public String country;
    public String city;
    public String description;
    public String updated;

    public Poi(int poid, Double latitude, Double longitude, String country, String city, String description, String updated) {
        this.poid = poid;
        if (latitude == null) {
            this.latitude = 0.0;
        } else {
            this.latitude = latitude;
        }
        if (longitude == null) {
            this.longitude = 0.0;
        } else {
            this.longitude = longitude;
        }
        this.country = country;
        this.city = city;
        this.description = description;
        this.updated = updated;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) throws Exception {
        this.poid = poid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Poi[" +
                "poid=" + poid +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
