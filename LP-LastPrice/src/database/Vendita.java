package database;

public class Vendita {
		String id;
		String descr;
		String user;
		String sell;
		String data;
		public Vendita(){}
		public Vendita(String d ,String u, String s, String dt){
			descr=d;
			user=u;
			sell=s;
			data=d;
		}
		public String getUser(){return user;}
		
		public String getSeller(){return sell;}
		
		public String getDescr(){return descr;}
		
		public String getDate(){return data;}
		public void setUser(String s){
			user=s;
		}
		public String getId(){return id;}
	public void setSeller(String s){
			sell=s;
		}
	public void setDesc(String s){
		descr=s;
	}
	public void setDate(String s){
		data=s;
	}
	public void setId(String s){
		id=s;
	}
}
