package database;

public class Offerta {
		private String Nome;
		private String descrizione;
		private double prezzo;
		private String venditore;
		private String inizio;
		private String fine;
		
		private Offerta(String x, String y , double p, String v , String di,String df){
			Nome=x;
			descrizione=y;
			prezzo=p;
			venditore=v;
			inizio=di;
			fine=df;
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
		public void setInizio(String x){
			inizio=x;
		}
		public void setFine(String x){
			fine=x;
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
		public String getStart(){
			return inizio;
		}
		public String getExpire(){
			return fine;
		}
		
}
