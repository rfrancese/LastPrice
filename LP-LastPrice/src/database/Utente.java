package database;

public class Utente {
			private String nome;
			private String cognome;
			private String data_nascita;
			private String user;
			private String password;
			private String sesso;
			private long carta;
			private int id;
			public Utente(){}
			public Utente(String n, String c, String date,String sex, String us, String pw, long nc) {
				super();
				nome=n;
				cognome=c;
				data_nascita=date;
				user=us;
				password=pw;
				sesso=sex;
				carta=nc;
			}
			public void setId(int x){
				id=x;
			}
			public void setUser(String x){
				user=x;
			}
			public void setPw(String x){
				password=x;
			}
			public void setSex(String x){
				sesso=x;
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
			public void setCard(long x){
				carta=x;
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
			public int getId(){
				return id;
			}
			public String getSex(){
				return sesso;
			}
			public long getCarta() {
				return carta;
			}
}
