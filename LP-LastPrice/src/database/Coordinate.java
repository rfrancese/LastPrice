package database;

public class Coordinate {
		private double lat;
		private double lng;
		private String city;
		public Coordinate(double l, double lg, String c){
			lat=l;
			lng=lg;
			city=c;
		}
		
		public void setLat(double x){
			lat=x;
		}
		public void setLng(double y){
			lng=y;
		}
		public String getCity () {
			return city;
		}
		public double getLat()
		{
			return lat;
		}
		public double getLng(){
			return lng;
		}
		

}
