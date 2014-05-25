package example.myapplication.movelife;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.sql.SQLException;

/**
 * Created by Thomas on 22-5-2014.
 */
public class DatabaseTable {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "bedrijfs_naam";

    private static final String DATABASE_NAME = "MoveLifedb";
    private static final String DATABASE_TABLE = "bedrijfTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;







    public long createEntry(String name) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);

    }

    public String getData() {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + "\n";

        }


        return result;
    }

    public Cursor getWordMatches(String query, String[] columns) {
        String selection = KEY_NAME + " MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DATABASE_TABLE);

        Cursor cursor = builder.query(ourHelper.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }


    DatabaseTable db  = new DatabaseTable(this);

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor c = db.getWordMatches(query, null);
            //process Cursor and display results
        }
    }


    private static class DbHelper extends SQLiteOpenHelper{


        public DbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL); "

            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public DatabaseTable(Context c){
        ourContext = c;

    }



    public DatabaseTable open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();

    }
}
