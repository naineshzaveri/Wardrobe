package wardrobe.nainesh.com.wardrobeapp.database;

/**
 * Created by naineshzaveri on 09/03/16.
 */
public class WardrobeDbManager {

    public interface DashBoardChangeListener {
        void onDashBoardStatusChange();
    }

    private android.content.ContentResolver contentResolver;
    private android.content.Context mContext;
    private static final String TAG = WardrobeDbManager.class.getName();
    private android.content.Context context;
    private static WardrobeDbManager theSingleton;

    public static WardrobeDbManager getInstance() {
        if (theSingleton == null) {
            theSingleton = new WardrobeDbManager();
        }
        return theSingleton;
    }

    public void setContext(android.content.Context context) {
        this.context = context;

        contentResolver = context.getContentResolver();
    }

    public android.content.Context getContext() {
        return context;
    }

    public int getLatestWardrobeFavoriteId() {

        try {
            String columnName = "MAX(" + wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.COLUMN_PAIR_ID + ")";

            android.database.Cursor mCursor = contentResolver.query(android.net.Uri.parse(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.URI_WARDROBE)
                    , new String[]{columnName}, null, null, null);

            mCursor.moveToFirst();
            if (mCursor != null && mCursor.getCount() > 0) {
                return mCursor.getInt(mCursor.getColumnIndex(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.COLUMN_PAIR_ID));
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLatestShirtWardrobeId() {

        try {
            String columnName = "MAX(" + wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.COLUMN_PAIR_ID + ")";

            android.database.Cursor mCursor = contentResolver.query(android.net.Uri.parse(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.URI_WARDROBE)
                    , new String[]{columnName}, null, null, null);

            mCursor.moveToFirst();
            if (mCursor != null && mCursor.getCount() > 0) {
                return mCursor.getInt(mCursor.getColumnIndex(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.COLUMN_PAIR_ID));
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void saveFavoriteCollectionInDB(String autoId, boolean isFav) {
        android.content.ContentValues values = new android.content.ContentValues();

        values.put(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.COLUMN_AUTOINCREMENTID, "Y"); //whatever column you want to update
        contentResolver.update(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.CONTENT_URI, values, wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.COLUMN_AUTOINCREMENTID + "=?", new String[]{autoId}); //id is the id of the row you wan to update

    }

}
