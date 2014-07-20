package database;
import java.util.LinkedList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DbUsersHelper extends SQLiteOpenHelper {
		// Dichiarazione tabelle database
	   private static final String TABLE_USERS = "utenti";
	   private static final String TABLE_SELLERS = "venditori";
	   private static final String TABLE_ADMIN = "amministratori";
	   private static final String TABLE_TRANSACTION = "vendite";
	   private static final String TABLE_REFUND = "rimborsi";
	   private static final String TABLE_OFFERS= "offerte";
	   
	   
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Nome database
    private static final String DATABASE_NAME = "LastPrice15";
 
    public DbUsersHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL create
    	String CREATE_VOTO_TABLE="CREATE TABLE voto ("+"voto DOUBLE PRIMARY KEY)";
        String CREATE_USER_TABLE = "CREATE TABLE utenti ( " +
                "user TEXT , " + 
                "nome TEXT, "+"cognome TEXT,"+"sesso TEXT,"+"data_nascita TEXT,"+"password TEXT," + "numero_carta TEXT,"+"numero_telefono TEXT,"+"email TEXT,"+"indirizzo TEXT,"+"credito DOUBLE)";
        String CREATE_SELLERS_TABLE = "CREATE TABLE venditori ( " +
                "user TEXT PRIMARY KEY , " + 
                "nome TEXT, "+"cognome TEXT,"+ "sesso TEXT," + "data_nascita TEXT,"+"password TEXT,"+"partita_iva TEXT,"+"nome_attività TEXT,"+"numero_offerte INTEGER," + "numero_carta TEXT,"+"numero_telefono TEXT,"+"email TEXT,"+"indirizzo TEXT)";
        String CREATE_ADMIN_TABLE="CREATE TABLE amministratori (" +
                "user TEXT PRIMARY KEY , " +
        		"nome TEXT," + "cognome TEXT, " + "sesso TEXT," + "data_nascita TEXT," + "password TEXT," + "numero_carta TEXT,"+"numero_telefono TEXT,"+"email TEXT,"+"indirizzo TEXT)";
        String CREATE_PAYMENTS_TABLE="CREATE TABLE vendite (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"descr_offerta TEXT," + "user_utente TEXT, " + "user_venditore TEXT," + "data_acquisto TEXT)";
        String CREATE_REFUND_TABLE="CREATE TABLE rimborsi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"user_utente TEXT," + "user_venditore TEXT, " + "causale TEXT)";
        
        String CREATE_OFFERTS_TABLE="CREATE TABLE offerte (" +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT," + "titolo TEXT,"+
        		"descrizione TEXT," + "Prezzo TEXT, "+"luogo TEXT," + "user_venditore TEXT," + "data_scadenza TEXT," + "asta TEXT, "+"categoria TEXT,"+"latitudine TEXT,"+"longitudine TEXT,"+"voto DOUBLE)";
        // create books table
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SELLERS_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(CREATE_OFFERTS_TABLE);
        db.execSQL(CREATE_PAYMENTS_TABLE);
        db.execSQL(CREATE_REFUND_TABLE);
        db.execSQL(CREATE_VOTO_TABLE);
      
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Elimina i vecchi utenti
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_USERS);
      
        
        this.onCreate(db);
    }
   
    // Nome colonne tabelle
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nome";
    private static final String KEY_LNAME = "cognome";
    private static final String KEY_SEX = "sesso";
    private static final String KEY_BIRTH = "data_nascita";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IVA = "partita_iva";
    private static final String KEY_CARD ="numero_carta";
    private static final String KEY_NAME_SHOP = "shop";
    private static final String KEY_NUM_OFF = "offerte attive";
    private static final String KEY_DESCRIPTION="descrizione";
    private static final String KEY_LUOGO="luogo";
    private static final String KEY_SCADENZA="data_scadenza";
    private static final String KEY_ASTA="asta";
    private static final String KEY_USR_SEL="user_venditore";
    private static final String KEY_CAT="categoria";
    private static final String KEY_TITLE="titolo";
    private static final String KEY_PRICE="prezzo";
    private static final String KEY_TELEPHONE="numero_telefono";
    private static final String KEY_MAIL="email";
    private static final String KEY_ADDRESS="indirizzo";
    private static final String KEY_CREDIT="credito";
    private static final String KEY_LAT="latitudine";
    private static final String KEY_LNG="longitudine";
    private static final String KEY_USR_RFN="user_utente";
    private static final String KEY_SEL_RFN="user_venditore";
    private static final String KEY_CSL="causale";
    private static final String KEY_DSCR="descr_offerta";
    private static final String KEY_DATE="data_acquisto";
    private static final String KEY_VOTO="voto";
    
    // Colonne tabelle
    private static final String[] COLUMNS_USERS = {KEY_USER,KEY_NAME,KEY_LNAME,KEY_SEX,KEY_BIRTH,KEY_PASSWORD,KEY_CARD,KEY_TELEPHONE,KEY_MAIL,KEY_ADDRESS,KEY_CREDIT};
    private static final String[] COLUMNS_TRANSACTION = {KEY_ID,KEY_DSCR,KEY_USR_RFN,KEY_SEL_RFN,KEY_DATE};
    private static final String[] COLUMNS_REFUND = {KEY_ID,KEY_USR_RFN,KEY_SEL_RFN,KEY_CSL};
    private static final String[] COLUMNS_VOTO = {"voto"};
    private static final String[] COLUMNS_OFFERS = {KEY_ID,KEY_TITLE,KEY_DESCRIPTION,KEY_PRICE,KEY_LUOGO,KEY_USR_SEL,KEY_SCADENZA,KEY_CAT,KEY_LAT,KEY_LNG,KEY_VOTO};
    // Carica voto medio se ancora non ha votato nessun utente
    public void carica(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
        values.put(KEY_VOTO, 2.5);
        db.insert("voto", null, values);
        db.close();
        
    }
    // Verifica se non ci è stato nessun voto
    public boolean isEmptyVote(){
    	SQLiteDatabase db = this.getReadableDatabase();
    	 Cursor cursor =  db.query("voto", COLUMNS_VOTO, // b. column names
                 null, // c. selections 
                 		// d. selections args
                 null, // e. group by
                 null, // f. having
                 null, // g. order by
                 null); // h. limit
  
         // Conta gli elementi
         if (cursor.getCount()==0) return true;
         else return false;
    }
    // Metodo aggiunta utente a seconda del tipo account
    public long addUser(Utente u,int n){
        Log.d("addUser", u.toString());
        // Db scrivibile
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_NAME, u.getName());
        values.put(KEY_LNAME, u.getLastName());
        values.put(KEY_SEX, u.getSex());
        values.put(KEY_BIRTH, u.getBirth());
        values.put(KEY_USER, u.getUser());
        values.put(KEY_PASSWORD, u.getPw());
        values.put(KEY_TELEPHONE,u.getTel());
        values.put(KEY_MAIL, u.getMail());
        values.put(KEY_ADDRESS, u.getAddress());
       
       // get descrizione
       long i=-2;
     if(n==1){
    	 values.put(KEY_CARD, u.getCarta()); // se devo inserire nel db Utente
        i=db.insert(TABLE_USERS, null, values); 
     }
     if(n==2){
    	 values.put(KEY_IVA, u.getCarta());// se devo inserire nel db venditori
        i= db.insert(TABLE_SELLERS, null, values); 
      }
     if(n==3){
    	 values.put(KEY_CARD, u.getCarta());// se devo inserire nel db amministratori
        i= db.insert(TABLE_ADMIN, null, values); 
      }
        // Chiudi
      
        db.close(); 
        return i;
    }
    // Elimina offerta
    public long deleteOffer(String x){
    	String descrizione=x;
    	  
        SQLiteDatabase db = this.getWritableDatabase();
        String data[]={descrizione};
       
       long i= db.delete(TABLE_OFFERS,
                KEY_DESCRIPTION+" = ?",
                data);
 
       
        db.close();
 
      return i;
 
    }
    //Inserisci offerta
    public long insertOffer(Offerta of,String asta){
    	 SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
        
         // Crea ContentValues to add key "column"/value
         values.put(KEY_TITLE, of.getName());
         values.put(KEY_DESCRIPTION, of.getDesc());
         values.put(KEY_PRICE, of.getPrice());
         values.put(KEY_LUOGO, of.getPlace());
         values.put(KEY_USR_SEL, of.getVenditore());
         values.put(KEY_SCADENZA, of.getExpire());
         values.put(KEY_ASTA, asta );
         values.put(KEY_CAT, of.getCategoria());
         values.put(KEY_LAT, of.getLat());
         values.put(KEY_LNG, of.getLng());
        
        
        // get descrizione
        
      long i;
     
        i= db.insert(TABLE_OFFERS, null, values); 
      return i;
    }
    //Cerca l'utente
    public Utente getUser(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.query(TABLE_USERS, COLUMNS_USERS, // b. column names
                KEY_USER +"= ?", // c. selections 
                new String[] { String.valueOf(user) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor != null)
        {   cursor.moveToFirst();
 
        // Costruisci l'oggetto utente
       Utente utente = new Utente();
       utente.setId(Integer.parseInt(cursor.getString(0)));
        utente.setName(cursor.getString(1));
        utente.setLastName(cursor.getString(2));
        utente.setBirth(cursor.getString(3));
        utente.setUser(cursor.getString(4));
        utente.setPw(cursor.getString(5));
      
        return utente;
        }
        else return null;
    }
    // Prendi il voto medio
    public double getVoto(){
    	 SQLiteDatabase db=this.getReadableDatabase();
    	 double voto=0;
    	 Cursor cursor = db.query("voto",COLUMNS_VOTO,null,null,null,null,null);
    	 cursor.moveToFirst();
    	 voto=cursor.getDouble(0);
    	 return voto;
    	 
    }
    // Vota LastPrice
 public double votaLP(double x){
	   SQLiteDatabase db = this.getWritableDatabase();
	 Double media;
	 media=this.getVoto();
	 ContentValues values=new ContentValues();
	 Double voto=(media+x)/2;
	 values.put(KEY_VOTO,voto);
	long i= db.update(KEY_VOTO, //table
             values, // column/value
           null,null); //selection args
	db.close();
		if(i>0) return voto;
		else return 0.3;
 }
 // Voto alle offerte se si volesse aggiungere nell'app
 public double getOfferVoto(String descr){
	  SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor =  db.query(TABLE_OFFERS, COLUMNS_OFFERS, // b. column names
              KEY_DESCRIPTION +"= ?", // c. selections 
              new String[] { descr }, // d. selections args
              null, // e. group by
              null, // f. having
              null, // g. order by
              null); // h. limit
      	double voto=0;
      if (cursor != null)
      {   cursor.moveToFirst();
      	voto=cursor.getDouble(10);
      }
      return voto;
  }
 // Vota l'offerta
 public double vota(double x,String descr){
	  SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
    
     Double p= x;
     Double media=this.getOfferVoto(descr);
     media=(media+p)/2;
     values.put(KEY_VOTO, media);
      int i = db.update(TABLE_OFFERS, //table
              values, // column/value
              KEY_DESCRIPTION+" = ?", // selections
              new String[] { descr }); //selection args
      db.close();
      
      if(i>=0){
      	return media;
      }
      else return 0;

 }
    // Restituisce il credito del cliente
   public double getCredito(String user){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor =  db.query(TABLE_USERS, COLUMNS_USERS, // b. column names
               KEY_USER +"= ?", // c. selections 
               new String[] { user }, // d. selections args
               null, // e. group by
               null, // f. having
               null, // g. order by
               null); // h. limit
       	double credito=0;
       if (cursor != null)
       {   cursor.moveToFirst();
       	credito=cursor.getDouble(10);
       }
       return credito;
   }
   // Aggiorna il credito dell'utente
    public double updateUser(String user,String prezzo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CREDIT,prezzo);
       Double p= Double.parseDouble(prezzo);
       Double credito=this.getCredito(user);
       credito+=p;
       values.put(KEY_CREDIT, credito);
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_USER+" = ?", // selections
                new String[] { user }); //selection args
        db.close();
        
        if(i>=0){
        	return credito;
        }
        else return 0;
 
    }
    // Aggiorna il credito
    public double updateCredit(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       
       values.put(KEY_CREDIT, 0);

        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_USER+" = ?", // selections
                new String[] { user }); //selection args
        db.close();
        
        if(i>=0){
        	return 1;
        }
        else return 0;
 
    }
    // Cancello l'utente
    public long deleteUser(String user) {
 
        
        SQLiteDatabase db = this.getWritableDatabase();
        String data[]={user};
       
       long i= db.delete(TABLE_USERS,
                KEY_USER+" = ?",
                data);
 
       
        db.close();
 
      return i;
 
    }
    // Cancella una vendita, funzionalità implementabile
   public long deleteSales(String id) {
 
        
        SQLiteDatabase db = this.getWritableDatabase();
        String data[]={id};
      db.rawQuery("DELETE FROM TABLE_TRANSACTION WHERE id = ?", data);
     long i=1;
  
       
        db.close();
 
      return i;
 
    }
   // Trova il numero di telefono di un venditore
   public String getTelephone(String seller){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor;
	   String data[]={seller};
	   String telefono="1";
	   cursor= db.rawQuery("SELECT * FROM venditori WHERE user=? ", data);
	   if(cursor!=null){
		   cursor.moveToFirst();
		   telefono=cursor.getString(10);
	   }
	   return telefono;
   }
   // Controllo se esistono altri user al momento dell'iscrizione
   public boolean validate(String usr,int n){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor=null;
	   String data[]={usr};
	   if (n==1) {
	  
	   cursor= db.rawQuery("SELECT * FROM utenti WHERE user=? ", data);}
	   if(n==2){
		   cursor= db.rawQuery("SELECT * FROM venditori WHERE user=? ", data);
	   }
	   if(n==3){
		   cursor= db.rawQuery("SELECT * FROM amministratori WHERE user=? ", data);
	   }
	   if(cursor.getCount()==1)
		   return true;
		   else return false;
   }
   // Controllo il login
   public boolean searchUser(String usr,String pw,int n){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor=null;
	   String data[]={usr,pw};
	   if (n==1) {
	  
	   cursor= db.rawQuery("SELECT * FROM utenti WHERE user=? AND password=?", data);}
	   if(n==2){
		   cursor= db.rawQuery("SELECT * FROM venditori WHERE user=? AND password=?", data);
	   }
	   if(n==3){
		   cursor= db.rawQuery("SELECT * FROM amministratori WHERE user=? AND password=?", data);
	   }
	   if(cursor.getCount()==1)
		   return true;
		   else return false;
   }
   // Torna la lista di offerte attive di una determinata categoria
   public Offerta[] getOffer(String cat){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor=null;
	   String data[]={cat};
	  
			   int i=0;
	   cursor= db.rawQuery("SELECT * FROM offerte WHERE categoria=?", data);
	   if(cursor != null){
		   
		   Offerta[] finale=new Offerta[cursor.getCount()];
		   cursor.moveToFirst();
		   while (!cursor.isAfterLast()){
			   Offerta off=new Offerta();
		   off.setId(Integer.parseInt(cursor.getString(0)));
		   off.setName(cursor.getString(1));
		   off.setDesc(cursor.getString(2));
		   off.setPrice(cursor.getString(3));
		   off.setPlace(cursor.getString(4));
		   off.setSeller(cursor.getString(5));
		   off.setScadenza(cursor.getString(6));
		   off.setAsta(cursor.getString(7));
		   off.setCategoria(cursor.getString(8));
		   off.setLat(cursor.getString(9));
		   off.setLng(cursor.getString(10));
		   finale[i]=off;
		   i++;
		   cursor.moveToNext();
		   
		   
		   
		   }
		   return finale;
	   }
	   else return null ;
		   
   }
   // Ritorna le offerte attive di uno specifico utente
   public Offerta[] getUserOffer(String user){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor=null;
	   String data[]={user};
	  
			   int i=0;
	   cursor= db.rawQuery("SELECT * FROM offerte WHERE user_venditore=?", data);
	   if(cursor != null){
		   
		   Offerta[] finale=new Offerta[cursor.getCount()];
		   cursor.moveToFirst();
		   while (!cursor.isAfterLast()){
			   Offerta off=new Offerta();
		   off.setId(Integer.parseInt(cursor.getString(0)));
		   off.setName(cursor.getString(1));
		   off.setDesc(cursor.getString(2));
		   off.setPrice(cursor.getString(3));
		   off.setPlace(cursor.getString(4));
		   off.setSeller(cursor.getString(5));
		   off.setScadenza(cursor.getString(6));
		   off.setAsta(cursor.getString(7));
		   off.setCategoria(cursor.getString(8));
		   off.setLat(cursor.getString(9));
		   off.setLng(cursor.getString(10));
		   finale[i]=off;
		   i++;
		   cursor.moveToNext();
		   
		   
		   
		   }
		   return finale;
	   }
	   else return null ;
		   
   }
   // Aggiunge il rimborso
   public long addRefund(Rimborso r){
	   SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(KEY_USR_RFN, r.getUser());
       values.put(KEY_SEL_RFN, r.getSeller());
       values.put(KEY_CSL, r.getCaus());
       long i;
       
       i= db.insert(TABLE_REFUND, null, values);
     return i;
   }
   // Inserisci una vendita
   public long insertSale(Vendita v){
	   SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(KEY_USR_RFN, v.getUser());
       values.put(KEY_SEL_RFN, v.getSeller());
       values.put(KEY_DSCR, v.getDescr());
       long i;
       
       i= db.insert(TABLE_TRANSACTION, null, values); 
     return i;
   }
   // Restituisce la lista di utenti attivi dell'applicazione
   public Utente[] getUsers(){
       SQLiteDatabase db = this.getReadableDatabase();
       int i=0;
       Cursor cursor =  db.query(TABLE_USERS, COLUMNS_USERS, // b. column names
              null , // d. selections args
               null, // e. group by
               null, // f. having
               null, // g. order by
               null); // h. limit
      
       if (cursor != null){
    	   
		   Utente[] finale=new Utente[cursor.getCount()];
		   cursor.moveToFirst();
		   while (!cursor.isAfterLast()){
			
       
			   Utente utente = new Utente();
			   utente.setUser(cursor.getString(0));
			   	finale[i]=utente;
			   	i++;
			   	cursor.moveToNext();
		   }
       
       return finale;
       }
       else return null;
   }
   // Restituisce lista richieste rimborso
   public Rimborso[] getRefunds(){
       SQLiteDatabase db = this.getReadableDatabase();
       int i=0;
       Cursor cursor =  db.query(TABLE_REFUND, COLUMNS_REFUND, // b. column names
              null , // d. selections args
               null, // e. group by
               null, // f. having
               null, // g. order by
               null); // h. limit
      
       if (cursor != null){
    	   
		   Rimborso[] finale=new Rimborso[cursor.getCount()];
		   cursor.moveToFirst();
		   while (!cursor.isAfterLast()){
			
       
			   Rimborso refund = new Rimborso();
			refund.setId(cursor.getString(0));
			refund.setUser(cursor.getString(1));
			refund.setSeller(cursor.getString(2));
			refund.setCaus(cursor.getString(3));
			   	finale[i]=refund;
			   	i++;
			   	cursor.moveToNext();
		   }
       
       return finale;
       }
       else return null;
   }
   // Restituisce le vendite di un utente
   public int getUserSale(String descrizione){
	   SQLiteDatabase db = this.getReadableDatabase();
       int i=0;
       String [] data={descrizione};
       String utente[]=new String[1000];
      
       Cursor cursor =  db.rawQuery("SELECT * FROM vendite WHERE descr_offerta= ? " , data);
       if (cursor != null){
    	   
		   
		   cursor.moveToFirst();
		   while (!cursor.isAfterLast()){
			
       
			
			 utente[i]=cursor.getString(3);
			
			   	i++;
			   	cursor.moveToNext();
		   }
       
       return i;
       }
       else return 0;
   }
   // Restituisce le vendite 
   public String getSellerSale(String descrizione){
	   SQLiteDatabase db = this.getReadableDatabase();
       String [] data={descrizione};
       String seller="";
      
       Cursor cursor =  db.rawQuery("SELECT * FROM vendite WHERE descr_offerta= ? " , data);
       if (cursor != null){
    	  
		   cursor.moveToFirst();
		
			seller=cursor.getString(2);
		
       return seller;
       }
       else return "niente";
   }
   // Restituisce lista vendite
   public Vendita[] getSales(){
       SQLiteDatabase db = this.getReadableDatabase();
       int i=0;
       Cursor cursor =  db.query(true , TABLE_TRANSACTION, COLUMNS_TRANSACTION, // b. column names
              null , // d. selections args
               null, // e. group by
               KEY_DSCR, // f. having
               null, // g. order by
               null,
               null); // h. limit
      
       if (cursor != null){
    	   
		   Vendita[] finale=new Vendita[cursor.getCount()];
		   cursor.moveToFirst();
		   while (!cursor.isAfterLast()){
			
       
			  Vendita sale=new Vendita();
			  sale.setId(cursor.getString(0));
			  sale.setDesc(cursor.getString(1));
			  sale.setUser(cursor.getString(2));
			  sale.setSeller(cursor.getString(3));
			  sale.setDate(cursor.getString(4));
			   	finale[i]=sale;
			   	i++;
			   	cursor.moveToNext();
		   }
       
       return finale;
       }
       else return null;
   }
   public void insertTable(String create){//metodo per inserire tabella nuova nel db da activity
	   SQLiteDatabase db = this.getWritableDatabase();
	   db.execSQL(create);
   }
}