// Khoa Tran
// 11/26/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #8: GeoLocation

/* Processes GeoLocation class and uses methods from the object class
to output the latitude and longitude of each location and the distance in between locations.
*/

public class GeoLocationClient {
   
   public static void main(String[] args) {
     
      GeoLocation stash = new GeoLocation(34.988889,-106.614444);
      GeoLocation studios = new GeoLocation(34.989978, -106.614357);
      GeoLocation building = new GeoLocation(35.131281, -106.61263); 
      
      System.out.println("the stash is at " + stash.toString());
      System.out.println("ABQ studio is at " + studios.toString()); 
      System.out.println("FBI building is at " + building.toString());
      
      System.out.println("distance in miles between:");
      System.out.println("    stash/studio = " + stash.distanceFrom(studios));
      System.out.println("    stash/fbi    = " + stash.distanceFrom(building));
   }
}      
