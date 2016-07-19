package wardrobe.nainesh.com.wardrobeapp.database;

public class WardrobeDatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {

    public static final String DATABASE_NAME = "wardrobe.db";


    public static final int DATABASE_VERSION = 1; //

    private android.database.sqlite.SQLiteDatabase mDatabaseManipulator;

    public android.database.sqlite.SQLiteDatabase getDataManipulator() {

        mDatabaseManipulator = getWritableDatabase();
        return mDatabaseManipulator;
    }

    public WardrobeDatabaseHelper(android.content.Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        createAppTables(db);
    }

    private void createAppTables(android.database.sqlite.SQLiteDatabase db) {
        try {
            android.util.Log.i("Chat DB"," started");
            db.execSQL(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.CREATE_STATEMENT);
            android.util.Log.i("Chat DB", "created");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
