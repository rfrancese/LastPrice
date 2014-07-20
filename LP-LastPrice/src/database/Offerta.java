package database;

public class Offerta {
		private String Nome;
		private String descrizione;
		private String prezzo;
		private String venditore;
		private String scadenza;
		private String asta;
		private String luogo;
		private String categoria;
		private int id;
		private String lat;
		private String lng;
		private double voto;
		
		public Offerta(){}
		public Offerta(String x, String y , String p, String v , String sc,String place,String cat){
			Nome=x;
			descrizione=y;
			prezzo=p;
			venditore=v;
			scadenza=sc;
			asta="no";
			luogo=place;
			categoria=cat;
			voto=0;
		}
		public double calculate(double v){
			return (voto+v)/2;
		}
		public void setLat(String s){
			lat=s;
		}
		public void setLng(String s){
			lng=s;
		}
		public String getLat(){
			return lat;
		}
		public String getLng(){
			return lng;
		}
		public void setId(int x){
			id=x;
		}
		public void setName(String x){
			Nome=x;
		}
		public void setDesc(String x){
			descrizione=x;
		}
		public void setPrice(String x){
			prezzo=x;
		}
		public void setSeller(String x){
			venditore=x;
		}
		public void setScadenza(String x){
			scadenza=x;
		}
		public void setPlace(String x){
			luogo=x;
		}
		public void setCategoria(String x){
			categoria=x;
		}
		public String getPlace(){
			return luogo;
		}
		
		public String getName(){
			return Nome;
		}
		public String getDesc(){
			return descrizione;
		}
		public String getPrice(){
			return prezzo;
		}
		public String getVenditore(){
			return venditore;
		}
		
		public String getExpire(){
			return scadenza;
		}
		public void lastPrice(){
			asta="si";
		}
		public String getCategoria(){
			return categoria;
		}
		public int getId(){
			return id;
		}
		public void setAsta(String s){
			asta=s;
		}
}
