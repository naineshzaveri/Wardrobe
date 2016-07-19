package wardrobe.nainesh.com.wardrobeapp.database;

public class WardrobeContentProvider extends android.content.ContentProvider {

	// database
	private WardrobeDatabaseHelper database;

	// used for the UriMacher

	private static final int WARDROBE = 10;

	private static final android.content.UriMatcher sURIMatcher = new android.content.UriMatcher(
			android.content.UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.AUTHORITY,
				wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.BASE_PATH, WARDROBE);
	}

	@Override
	public boolean onCreate() {
		database = new WardrobeDatabaseHelper(getContext());
		return false;
	}

	@Override
	public android.database.Cursor query(android.net.Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		android.util.Log.i("ContentProvider"," query");
		int uriType = sURIMatcher.match(uri);
		android.database.sqlite.SQLiteDatabase db = database.getWritableDatabase();
		android.database.Cursor cursor = null;

		if (uriType == WARDROBE) {
			cursor = db
					.query(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.TABLE_NAME,
							projection, selection, selectionArgs, null, null,
							sortOrder);
		}
		// make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public android.net.Uri insert(android.net.Uri uri, android.content.ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		android.util.Log.i("ContentProvider"," insert");
		android.database.sqlite.SQLiteDatabase sqlDB = database.getWritableDatabase();
		long id = 0;
		if (uriType == WARDROBE) {
			id = sqlDB.insert(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.TABLE_NAME, null,
					values);
			return android.net.Uri
					.parse(wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.BASE_PATH + "/" + id);
		}
		getContext().getContentResolver().notifyChange(uri, null);

		return android.net.Uri.parse("");
	}

	@Override
	public int delete(android.net.Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		android.util.Log.i("ContentProvider"," delete");
		android.database.sqlite.SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;

		if (uriType == WARDROBE) {
			rowsDeleted = sqlDB.delete(
					wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.TABLE_NAME, selection,
					selectionArgs);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public int update(android.net.Uri uri, android.content.ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		android.util.Log.i("ContentProvider"," update");
		android.database.sqlite.SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsUpdated = 0;

		if (uriType == WARDROBE) {
			rowsUpdated = sqlDB.update(
					wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.Wardrobe.TABLE_NAME, values,
					selection, selectionArgs);
		}
		getContext().getContentResolver().notifyChange(uri, null);

		return rowsUpdated;
	}

	@Override
	public String getType(android.net.Uri uri) {
		return null;
	}

}
