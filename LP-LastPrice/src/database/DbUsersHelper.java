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
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LastPrice2";
 
    public DbUsersHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_USER_TABLE = "CREATE TABLE utenti ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "nome TEXT, "+"cognome TEXT,"+"sesso TEXT,"+"data_nascita TEXT,"+"user TEXT,"+"password TEXT," + "numero_carta LONG)";
        String CREATE_SELLERS_TABLE = "CREATE TABLE venditori ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "nome TEXT, "+"cognome TEXT,"+ "sesso TEXT," + "data_nascita TEXT,"+"user TEXT,"+"password TEXT,"+"partita_iva TEXT,"+"nome_attività TEXT,"+"numero_offerte INTEGER," + "numero_carta LONG)";
        String CREATE_ADMIN_TABLE="CREATE TABLE amministratori (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"nome TEXT," + "cognome TEXT, " + "sesso TEXT," + "data_nascita TEXT," + "user TEXT, " + "password TEXT," + "numero_carta LONG)";
        String CREATE_PAYMENTS_TABLE="CREATE TABLE vendite (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"descr_offerta TEXT," + "id_utente INTEGER, " + "id_venditore INTEGER," + "data_acquisto TEXT," + "prenotato TEXT, " + "pagato TEXT)";
        String CREATE_REFUND_TABLE="CREATE TABLE rimborsi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"id_utente INTEGER," + "id_venditore INTEGER, " + "causale TEXT," + "id_amministratore INTEGER)";
        String CREATE_LASTPRICE_TABLE="CREATE TABLE asta (" +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT," +
        		"id_venditore INTEGER," + "id_offerta INTEGER," + "scadenza TEXT," + "prezzo_base FLOAT)";
        String CREATE_OFFERTS_TABLE="CREATE TABLE offerte (" +
        		"id INTEGER PRIMARY KEY AUTOINCREMENT," +
        		"descrizione TEXT," + "luogo TEXT," + "id_venditore INTEGER," + "data_scadenza TEXT," + "asta TEXT)";
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
 
    private static final String TABLE_SELLERS = "venditori";
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nome";
    private static final String KEY_LNAME = "cognome";
    private static final String KEY_SEX = "sesso";
    private static final String KEY_BIRTH = "data_nascita";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IVA = "p.iva";
    private static final String KEY_NAME_SHOP = "shop";
    private static final String KEY_NUM_OFF = "offerte attive";
 
    private static final String[] COLUMNS_USERS = {KEY_ID,KEY_NAME,KEY_LNAME,KEY_SEX,KEY_BIRTH,KEY_USER,KEY_PASSWORD};
    private static final String[] COLUMNS_SELLERS = {KEY_ID,KEY_NAME,KEY_LNAME,KEY_BIRTH,KEY_USER,KEY_PASSWORD,KEY_IVA,KEY_NAME_SHOP,KEY_NUM_OFF};
    
    public void addUser(Utente u){
        Log.d("addTurno", u.toString());
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
        
       // get descrizione
        
 
        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
        // 4. close
      
        db.close(); 
    }
 
    public Utente getUser(int id){
 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor =  db.query(TABLE_USERS, COLUMNS_USERS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build book object
       Utente utente = new Utente();
       utente.setId(Integer.parseInt(cursor.getString(0)));
        utente.setName(cursor.getString(1));
        utente.setLastName(cursor.getString(2));
        utente.setBirth(cursor.getString(3));
        utente.setUser(cursor.getString(4));
        utente.setPw(cursor.getString(5));
        Log.d("getBook("+id+")", utente.toString());
 
        // 5. return book
        return utente;
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
                new String[] { String.valueOf(u.getId()) }); //selection args
 
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