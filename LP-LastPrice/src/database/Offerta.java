package database;

public class Offerta {
		private String Nome;
		private String descrizione;
		private double prezzo;
		private String venditore;
		private String scadenza;
		private String asta;
		private String luogo;
		private String categoria;
		
		public Offerta(String x, String y , double p, String v , String sc,String place,String cat){
			Nome=x;
			descrizione=y;
			prezzo=p;
			venditore=v;
			scadenza=sc;
			asta="no";
			luogo=place;
			categoria=cat;
		}
		public void setName(String x){
			Nome=x;
		}
		public void setDesc(String x){
			descrizione=x;
		}
		public void setPrice(double x){
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
		public double getPrice(){
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
}
