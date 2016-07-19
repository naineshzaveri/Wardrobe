package wardrobe.nainesh.com.wardrobeapp.database.tables;

public interface WardrobeTable extends BaseTable {
        int TYPE_SHIRT = 1;
        int TYPE_TROUSER = 2;

    public interface Wardrobe {
        String TABLE_NAME = "wardrobe";
        String COLUMN_AUTOINCREMENTID = "auto_id";
        String COLUMN_USER_IMAGE_URL = "image_url";
        String COLUMN_TYPE = "image_type"; // camera or gallery
        String COLUMN_PAIR_ID = "pair_id";
        String COLUMN_FAVORITE = "fav_apparel";
        String COLUMN_IMAGE_TYPE = "image_type";    // shirt or trouser
        String COLUMN_MESSAGE_TIME = "message_time";

        String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_AUTOINCREMENTID + " integer primary key autoincrement, "
                + COLUMN_USER_IMAGE_URL + " text, "
                + COLUMN_FAVORITE + " text, "
                + COLUMN_TYPE + " integer, "
                + COLUMN_PAIR_ID + " integer, "
                + COLUMN_IMAGE_TYPE + " integer, "
                + COLUMN_MESSAGE_TIME + " text);";

        String BASE_PATH = "Chat";
        android.net.Uri CONTENT_URI = android.net.Uri.parse(BASE_CONTENT_URI + BASE_PATH);
    }

    String URI_WARDROBE = BASE_CONTENT_URI + WardrobeTable.Wardrobe.BASE_PATH;


}
