// Khoa Tran
// 11/26/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #8: GeoLocation

/* This class stores information of the name, address, tag, latitude, and logitutde of a place on Earth. 
The class also stores information of a location
and the distance between this place and another using the method in the GeoLocation object 
*/
public class PlaceInformation {
         
    private String name;
    private String address;
    private String tag;
    private double latitude;
    private double longitude;
    private GeoLocation location;
    

    // constructs a PlaceInformation object with the name, address, tag, latitude, and longitude 
    public PlaceInformation(String name, String address, String tag, 
                            double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.tag = tag;
        this.latitude = latitude;
        this.longitude = longitude;
        location = new GeoLocation(latitude, longitude);
    }

    // returns the name of the place
    public String getName() {
        return name;
    }

    // returns the address of the place
    public String getAddress() {
        return address;
    }
    
    // returns the tag of the place
    public String getTag() {
      return tag;
    }

    // returns a string with the name along with the latitude and longitutude of the place
    public String toString() {
      return name + " (latitude: " + latitude + ", longitude: " + longitude +")";
    }
    
    // returns the location referencing to the GeoLocation object 
    public GeoLocation getLocation() {
      return location;
    }

    /* returns the distance in miles between this object and the parameter spot calculated 
    by the distanceFrom method in the GeoLocation class
    */
    // spot = geo location of the other spot
    public double distanceFrom(GeoLocation spot) {
      return location.distanceFrom(spot);  
    }
}