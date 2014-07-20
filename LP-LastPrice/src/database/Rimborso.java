package database;

public class Rimborso {
	private String id;
	private String user;
	private String seller;
	private String causale;
	public Rimborso(){}
	public Rimborso (String s, String t, String u){
		user=s;
		seller=t;
		causale=u;
	}
	public void setId(String x){
		id=x;
	}
	public String getId(){
		return id;
	}
	public String getUser(){
		return user;
	}
	public String getSeller(){
		return seller;
	}
	public String getCaus(){
		return causale;
	}
	public void setUser(String s){
		user=s;
	}
public void setSeller(String s){
		seller=s;
	}
public void setCaus(String s){
	causale=s;
}
}
