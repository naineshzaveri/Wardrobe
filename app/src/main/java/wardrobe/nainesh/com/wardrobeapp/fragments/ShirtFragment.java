package wardrobe.nainesh.com.wardrobeapp.fragments;

/**
 * Created by naineshzaveri on 17/07/16.
 */
public class ShirtFragment extends wardrobe.nainesh.com.wardrobeapp.base.BaseFragment implements wardrobe.nainesh.com.wardrobeapp.adapter.PhotoGalleryAdapter.PhotoSelectListener {
    android.content.Context context;
    java.util.ArrayList<wardrobe.nainesh.com.wardrobeapp.models.ImageModel> arrayShirtst = new java.util.ArrayList<>();
    private android.support.v4.view.ViewPager viewPager;
    wardrobe.nainesh.com.wardrobeapp.adapter.PhotoGalleryAdapter photoGalleryAdapter;
    android.widget.ImageView ivAddPhotos;
    @android.support.annotation.Nullable
    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(wardrobe.nainesh.com.wardrobeapp.R.layout.fragment_shirt, null);
        init(view);

        setPhotoGalleryAdapter();

        ivAddPhotos.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                wardrobe.nainesh.com.wardrobeapp.utils.Utility.imagePickerDialog(ShirtFragment.this);
            }
        });
        return view;

    }

    @Override
    public void onAttach(android.content.Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void init(android.view.View view){
        viewPager = (android.support.v4.view.ViewPager) view.findViewById(wardrobe.nainesh.com.wardrobeapp.R.id.vpPagerShirt);
        ivAddPhotos = (android.widget.ImageView)view.findViewById(wardrobe.nainesh.com.wardrobeapp.R.id.ivAddPics);

    }

    private void setPhotoGalleryAdapter(){

        if(photoGalleryAdapter == null){
            photoGalleryAdapter = new wardrobe.nainesh.com.wardrobeapp.adapter.PhotoGalleryAdapter(context, wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.TYPE_SHIRT,arrayShirtst,this);
            viewPager.setAdapter(photoGalleryAdapter);
        }else{
            photoGalleryAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onAddPhoto(int wardrobeType) {

        wardrobe.nainesh.com.wardrobeapp.utils.Utility.imagePickerDialog(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        // After the selection of image you will retun on the main activity with bitmap image
        wardrobe.nainesh.com.wardrobeapp.models.ImageModel imageModel = wardrobe.nainesh.com.wardrobeapp.utils.Utility.Image_Selecting_Task(this,data,requestCode);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == wardrobe.nainesh.com.wardrobeapp.utils.Utility.GALLERY_PICTURE) {
            // data contains result
            // Do some task
            wardrobe.nainesh.com.wardrobeapp.utils.Utility.createImageModel(imageModel,wardrobe.nainesh.com.wardrobeapp.utils.Utility.GALLERY_PICTURE, wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.TYPE_SHIRT, 1);
        } else if (requestCode == wardrobe.nainesh.com.wardrobeapp.utils.Utility.CAMERA_PICTURE) {
            wardrobe.nainesh.com.wardrobeapp.utils.Utility.createImageModel(imageModel,wardrobe.nainesh.com.wardrobeapp.utils.Utility.CAMERA_PICTURE, wardrobe.nainesh.com.wardrobeapp.database.tables.WardrobeTable.TYPE_SHIRT, 1);

        }
        if (imageModel != null)
            arrayShirtst.add(imageModel);

        setPhotoGalleryAdapter();
    }
}
