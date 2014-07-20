package database;

public class City {
		Coordinate c;
		private String nome;
		public City(String s){
			nome = s;
		}
		public void setCity(Coordinate x){
			c=x;
		}
		public Coordinate getValues(){
			return c;
		}
		public String getName(){
			return nome;
		}
}
