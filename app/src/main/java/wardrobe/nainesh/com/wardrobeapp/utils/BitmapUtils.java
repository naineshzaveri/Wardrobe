package wardrobe.nainesh.com.wardrobeapp.utils;

/**
 * Created by naineshzaveri on 19/07/16.
 */
public class BitmapUtils  {

    public static void setUrlImageUsingPicasso(android.content.Context activity, String imagePath, android.widget.ImageView imgPrescription) {

        try {
            com.squareup.picasso.Picasso.with(activity).load(imagePath).noFade().into(imgPrescription);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
