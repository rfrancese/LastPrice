package database;

public class Utente {
			private String nome;
			private String cognome;
			private String data_nascita;
			private String user;
			private String password;
			private String sesso;
			private String carta;
			private int id;
			private String telefono;
			private String email;
			private String indirizzo;
			private String credito;
			public Utente(){}
			public Utente(String n, String c, String date,String sex, String us, String pw, String nc,String tel,String mail,String address) {
				super();
				nome=n;
				cognome=c;
				data_nascita=date;
				user=us;
				password=pw;
				sesso=sex;
				carta=nc;
				telefono=tel;
				email=mail;
				indirizzo=address;
				credito="0";
			}
			public void addCredito(double x){
				credito+=x;
			}
			public String getCredito(){
				return credito;
			}
			public void setAddress(String x){
				indirizzo=x;
			}
			public String getAddress(){
				return indirizzo;
			}
			public void setMail(String x){
				email=x;
			}
			public void setTelephone(String x){
				telefono =x;
			}
			public String getMail(){
				return email;
			}
			public String getTel(){
				return telefono;
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
			public void setCard(String x){
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
			public String getCarta() {
				return carta;
			}
}
