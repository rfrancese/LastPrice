package database;

public class Venditore {
	
		private String nome;
		private String cognome;
		private String data_nascita;
		private String user;
		private String password;
		private String p_iva;
		private String sesso;
		private int num_off;
		
		
		public Venditore(String n, String c, String date, String ss, String us, String pw,String iva) {
			nome=n;
			cognome=c;
			data_nascita=date;
			user=us;
			password=pw;
			p_iva=iva;
			sesso=ss;
			num_off=0;
		}
		public void setUser(String x){
			user=x;
		}
		public void setPw(String x){
			password=x;
		}
		public void setBirth(String x){
			data_nascita=x;
		}
		public void setName(String x){
			nome=x;
		}
		public void setLastName(String x){
			cognome=x;
		}
		public void setIva(String x){
			p_iva=x;
		}
		public void setSex(String x){
			sesso=x;
		}
		public String getName(){
			return nome;
		}
		public String getLastName(){
			return cognome;
		}
		public String getUser(){
			return user;
		}
		public String getPw(){
			return password;
		}
		public String getBirth(){
			return data_nascita;
		}
		public String getIva(){
			return p_iva;
		}
		public String getSex(){
			return sesso;
		}
		public void Insert(){
			num_off++;
		}
		public int getNumOff(){
			return num_off;
		}
}


