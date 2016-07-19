package wardrobe.nainesh.com.wardrobeapp.adapter;

/**
 * Created by naineshzaveri on 17/07/16.
 */
public class PhotoGalleryAdapter extends android.support.v4.view.PagerAdapter {

    public interface PhotoSelectListener {
        void onAddPhoto(int wardrobeType);
    }

    private android.content.Context mContext;
    private PhotoSelectListener mPhotoSelectListener;
    private java.util.ArrayList<wardrobe.nainesh.com.wardrobeapp.models.ImageModel> arrPhotos;
    int type; // shirt or trouser

    public PhotoGalleryAdapter(android.content.Context mContext, int type, java.util.ArrayList<wardrobe.nainesh.com.wardrobeapp.models.ImageModel> arrPhotos, PhotoSelectListener photoSelectListener) {
        this.mContext = mContext;
        this.arrPhotos = arrPhotos;
        this.type = type;
        this.mPhotoSelectListener = photoSelectListener;
    }

    @Override
    public int getCount() {
        return arrPhotos.size();
    }

    @Override
    public boolean isViewFromObject(android.view.View view, Object object) {
        return view == ((android.widget.RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(android.view.ViewGroup container, int position) {
        android.view.View itemView = android.view.LayoutInflater.from(mContext).inflate(wardrobe.nainesh.com.wardrobeapp.R.layout.row_wardrobe, container, false);
        wardrobe.nainesh.com.wardrobeapp.models.ImageModel imageModel = arrPhotos.get(position);
        android.widget.ImageView imageView = (android.widget.ImageView) itemView.findViewById(wardrobe.nainesh.com.wardrobeapp.R.id.ivPhoto);
        android.widget.ImageView ivAddPhotos = (android.widget.ImageView) itemView.findViewById(wardrobe.nainesh.com.wardrobeapp.R.id.ivAddPics);

        wardrobe.nainesh.com.wardrobeapp.utils.BitmapUtils.setUrlImageUsingPicasso(mContext,imageModel.getUrl(),imageView);
        ivAddPhotos.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if (mPhotoSelectListener != null)
                    mPhotoSelectListener.onAddPhoto(type);
            }
        });
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(android.view.ViewGroup container, int position, Object object) {
        container.removeView((android.widget.RelativeLayout) object);
    }

}

