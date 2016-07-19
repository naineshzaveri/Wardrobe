package wardrobe.nainesh.com.wardrobeapp;

/**
 * Created by naineshzaveri on 17/07/16.
 */
public class Wardrobe extends android.app.Application {
    private static Wardrobe mThis;

    @Override
    public void onCreate() {
        super.onCreate();
        // app instance
        mThis = this;
       // wardrobe.nainesh.com.wardrobeapp.database.WardrobeDbManager.getInstance().setContext(getApplicationContext());
    }
    public static Wardrobe getInstance() {
        return mThis;
    }



}