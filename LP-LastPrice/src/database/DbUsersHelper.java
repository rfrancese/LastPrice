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
	   private static final String TABLE_USERS = "utenti";
	   private static final String TABLE_SELLERS = "venditori";
	   private static final String TABLE_ADMIN = "amministratori";
	   private static final String TABLE_TRANSACTION = "vendite";
	   private static final String TABLE_REFUND = "rimborsi";
	   private static final String TABLE_LP = "asta";
	   private static final String TABLE_OFFERS= "offerte";
	   
	   
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LastPrice3";
 
    public DbUsersHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_USER_TABLE = "CREATE TABLE utenti ( " +
                "user TEXT PRIMARY KEY , " + 
                "nome TEXT, "+"cognome TEXT,"+"sesso TEXT,"+"data_nascita TEXT,"+"password TEXT," + "numero_carta LONG)";
        String CREATE_SELLERS_TABLE = "CREATE TABLE venditori ( " +
                "user TEXT PRIMARY KEY , " + 
                "nome TEXT, "+"cognome TEXT,"+ "sesso TEXT," + "data_nascita TEXT,"+"password TEXT,"+"partita_iva TEXT,"+"nome_attività TEXT,"+"numero_offerte INTEGER," + "numero_carta LONG)";
        String CREATE_ADMIN_TABLE="CREATE TABLE amministratori (" +
                "user TEXT PRIMARY KEY , " +
        		"nome TEXT," + "cognome TEXT, " + "sesso TEXT," + "data_nascita TEXT," + "password TEXT," + "numero_carta LONG)";
        String CREATE_PAYMENTS_TABLE="CREATE TABLE vendite (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"descr_offerta TEXT," + "user_utente TEXT, " + "user_venditore TEXT," + "data_acquisto TEXT," + "prenotato TEXT, " + "pagato TEXT)";
        String CREATE_REFUND_TABLE="CREATE TABLE rimborsi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"user_utente TEXT," + "user_venditore TEXT, " + "causale TEXT," + "user_amministratore TEXT)";
        String CREATE_LASTPRICE_TABLE="CREATE TABLE asta (" +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT," +
        		"user_venditore TEXT," + "user_offerta TEXT," + "scadenza TEXT," + "prezzo_base FLOAT)";
        String CREATE_OFFERTS_TABLE="CREATE TABLE offerte (" +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT," +
        		"descrizione TEXT," + "luogo TEXT," + "user_venditore TEXT," + "data_scadenza TEXT," + "asta TEXT)";
        // create books table
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SELLERS_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(CREATE_OFFERTS_TABLE);
        db.execSQL(CREATE_PAYMENTS_TABLE);
        db.execSQL(CREATE_REFUND_TABLE);
        db.execSQL(CREATE_LASTPRICE_TABLE);
        
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_USERS);
      
        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------
 
    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
 
    // Books table name
 
   
    // Books Table Columns names
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
    
    private static final String[] COLUMNS_USERS = {KEY_USER,KEY_NAME,KEY_LNAME,KEY_SEX,KEY_BIRTH,KEY_PASSWORD,KEY_CARD};
    private static final String[] COLUMNS_SELLERS = {KEY_USER,KEY_NAME,KEY_LNAME,KEY_BIRTH,KEY_PASSWORD,KEY_IVA,KEY_NAME_SHOP,KEY_NUM_OFF};
    private static final String[] COLUMNS_ADMIN = {KEY_USER,KEY_NAME,KEY_LNAME,KEY_BIRTH,KEY_PASSWORD,KEY_CARD};
    
    public void addUser(Utente u,int n){
        Log.d("addUser", u.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       
        // 2. create ContentValues to add key "column"/value
        
        values.put(KEY_NAME, u.getName());
        values.put(KEY_LNAME, u.getLastName());
        values.put(KEY_SEX, u.getSex());
        values.put(KEY_BIRTH, u.getBirth());
        values.put(KEY_USER, u.getUser());
        values.put(KEY_PASSWORD, u.getPw());
       
       // get descrizione
       
     if(n==1){
    	 values.put(KEY_CARD, u.getCarta());// se devo inserire nel db Utente
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
     }
     if(n==2){
    	 values.put(KEY_IVA, u.getCarta());// se devo inserire nel db Utente
         db.insert(TABLE_SELLERS, // table
                 null, //nullColumnHack
                 values); // key/value -> keys = column names/ values = column values
      }
     if(n==3){
    	 values.put(KEY_CARD, u.getCarta());// se devo inserire nel db Utente
         db.insert(TABLE_ADMIN, // table
                 null, //nullColumnHack
                 values); // key/value -> keys = column names/ values = column values
      }
        // 4. close
      
        db.close(); 
    }
    public void insertOffer(Offerta of,String asta){
    	 SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
        
         // 2. create ContentValues to add key "column"/value
         
         values.put(KEY_DESCRIPTION, of.getDesc());
         values.put(KEY_LUOGO, of.getDesc());
         values.put(KEY_USR_SEL, of.getVenditore());
         values.put(KEY_SCADENZA, of.getExpire());
         values.put(KEY_ASTA, asta );
        
        
        // get descrizione
        
      
     	
         db.insert(TABLE_OFFERS, // table
                 null, //nullColumnHack
                 values); // key/value -> keys = column names/ values = column values
      
    }
    public Utente getUser(String user){
 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor =  db.query(TABLE_USERS, COLUMNS_USERS, // b. column names
                " user = ?", // c. selections 
                new String[] { String.valueOf(user) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null)
        {   cursor.moveToFirst();
 
        // 4. build book object
       Utente utente = new Utente();
       utente.setId(Integer.parseInt(cursor.getString(0)));
        utente.setName(cursor.getString(1));
        utente.setLastName(cursor.getString(2));
        utente.setBirth(cursor.getString(3));
        utente.setUser(cursor.getString(4));
        utente.setPw(cursor.getString(5));
      
 
        // 5. return book
        return utente;
        }
        else return null;
    }
 
    // Get All Books
   
    public int updateUser(Utente u) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, u.getName());
        values.put(KEY_LNAME, u.getLastName());
        values.put(KEY_SEX, u.getSex());
        values.put(KEY_BIRTH, u.getBirth());
        values.put(KEY_USER, u.getUser());
        values.put(KEY_PASSWORD, u.getPw());
  
 
        // 3. updating row
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(u.getUser()) }); //selection args
 
        // 4. close
        db.close();
 
        return i;
 
    }
 
    // Deleting single book
    public void deleteUser(Utente u) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_USERS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(u.getId()) });
 
        // 3. close
        db.close();
 
        Log.d("deleteBook", u.toString());
 
    }
   public boolean searchUser(String usr,String pw,int n){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor=null;
	   String data[]={usr,pw};
	   if (n==1) {
	  
	   cursor= db.rawQuery("SELECT * FROM utenti WHERE user=? AND password=?", data);}
	   if(cursor.getCount()==1)
		   return true;
		   else return false;
   }
   public void insertTable(String create){//metodo per inserire tabella nuova nel db da activity
	   SQLiteDatabase db = this.getWritableDatabase();
	   db.execSQL(create);
   }
}