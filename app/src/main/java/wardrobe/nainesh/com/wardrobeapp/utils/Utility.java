package wardrobe.nainesh.com.wardrobeapp.utils;

/**
 * Created by naineshzaveri on 11/07/16.
 */
public class Utility {

    public static java.io.File Copy_sourceLocation;
    public static java.io.File Paste_Target_Location;
    public static java.io.File MY_IMG_DIR, Default_DIR;
    public static android.net.Uri uri;
    public static android.content.Intent pictureActionIntent = null;
    public static final int CAMERA_PICTURE = 1;
    public static final int GALLERY_PICTURE = 2;
    public static final String IMAGE_FILE_PATH = android.os.Environment.getExternalStorageDirectory() + "/Wardrobe";
    public static String tempImageName;
    public static void imagePickerDialog(final android.support.v4.app.Fragment context)
    {

        android.app.AlertDialog.Builder myAlertDialog = new android.app.AlertDialog.Builder(context.getActivity());
        myAlertDialog.setTitle("Pictures Option");
        myAlertDialog.setMessage("Select Picture Mode");

        myAlertDialog.setPositiveButton("Gallery", new android.content.DialogInterface.OnClickListener() {
            public void onClick(android.content.DialogInterface arg0, int arg1) {
                Utility.pictureActionIntent = new android.content.Intent(android.content.Intent.ACTION_GET_CONTENT, null);
                Utility.pictureActionIntent.setType("image/*");
                Utility.pictureActionIntent.putExtra("return-data", true);
                context.startActivityForResult(Utility.pictureActionIntent, Utility.GALLERY_PICTURE);
            }
        });

        myAlertDialog.setNegativeButton("Camera", new android.content.DialogInterface.OnClickListener() {
            public void onClick(android.content.DialogInterface arg0, int arg1) {
                Utility.pictureActionIntent = new android.content.Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                context.startActivityForResult(Utility.pictureActionIntent, Utility.CAMERA_PICTURE);
            }
        });
        myAlertDialog.show();

    }



    public static String Get_Random_File_Name()
    {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        int myYear = c.get(java.util.Calendar.YEAR);
        int myMonth = c.get(java.util.Calendar.MONTH);
        int myDay = c.get(java.util.Calendar.DAY_OF_MONTH);
        String Random_Image_Text = "" + myDay + myMonth + myYear + "_" + Math.random();
        return Random_Image_Text;
    }

    // Copy your image into specific folder
    public static java.io.File copyFile(java.io.File current_location, java.io.File destination_location)
    {
        Copy_sourceLocation = new java.io.File("" + current_location);
        Paste_Target_Location = new java.io.File("" + destination_location + "/" + Utility.Get_Random_File_Name() + ".jpg");

        android.util.Log.v("Purchase-File", "sourceLocation: " + Copy_sourceLocation);
        android.util.Log.v("Purchase-File", "targetLocation: " + Paste_Target_Location);
        try
        {
            // 1 = move the file, 2 = copy the file
            int actionChoice = 2;
            // moving the file to another directory
            if (actionChoice == 1)
            {
                if (Copy_sourceLocation.renameTo(Paste_Target_Location))
                {
                    android.util.Log.i("Purchase-File", "Move file successful.");
                } else
                {
                    android.util.Log.i("Purchase-File", "Move file failed.");
                }
            }

            // we will copy the file
            else
            {
                // make sure the target file exists
                if (Copy_sourceLocation.exists())
                {

                    java.io.InputStream in = new java.io.FileInputStream(Copy_sourceLocation);
                    java.io.OutputStream out = new java.io.FileOutputStream(Paste_Target_Location);

                    // Copy the bits from instream to outstream
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0)
                    {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();

                    android.util.Log.i("copyFile", "Copy file successful.");

                } else
                {
                    android.util.Log.i("copyFile", "Copy file failed. Source file missing.");
                }
            }

        } catch (NullPointerException e)
        {
            android.util.Log.i("copyFile", "" + e);

        } catch (Exception e)
        {
            android.util.Log.i("copyFile", "" + e);
        }
        return Paste_Target_Location;
    }

    // 	decode your image into bitmap format
    public static android.graphics.Bitmap decodeFile(java.io.File f)
    {
        try
        {
            android.graphics.BitmapFactory.Options o = new android.graphics.BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            android.graphics.BitmapFactory.decodeStream(new java.io.FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true)
            {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale++;
            }

            android.graphics.BitmapFactory.Options o2 = new android.graphics.BitmapFactory.Options();
            o2.inSampleSize = scale;
            return android.graphics.BitmapFactory.decodeStream(new java.io.FileInputStream(f), null, o2);
        } catch (java.io.FileNotFoundException e)
        {
            android.util.Log.e("decodeFile", "" + e);
        }
        return null;
    }

    // Create New Dir (folder) if not exist
    public static java.io.File Create_MY_IMAGES_DIR()
    {
        try
        {
            // Get SD Card path & your folder name
            MY_IMG_DIR = new java.io.File(android.os.Environment.getExternalStorageDirectory(), "/My_Image/");

            // check if exist
            if (!MY_IMG_DIR.exists())
            {
                // Create New folder
                MY_IMG_DIR.mkdirs();
                android.util.Log.i("path", ">>.." + MY_IMG_DIR);
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            android.util.Log.e("Create_MY_IMAGES_DIR", "" + e);
        }
        return MY_IMG_DIR;
    }
   /* public static String Image_Selecting_Task(android.content.Intent data, android.content.Context context)
    {
        try
        {
            Utility.uri = data.getData();
            if (Utility.uri != null)
            {
                // User had pick an image.
                android.database.Cursor cursor = context.getContentResolver().query(Utility.uri, new String[]
                        { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
                cursor.moveToFirst();
                // Link to the image
                final String imageFilePath = cursor.getString(0);

                //Assign string path to File
                Utility.Default_DIR = new java.io.File(imageFilePath);

                // Create new dir MY_IMAGES_DIR if not created and copy image into that dir and store that image path in valid_photo
                Utility.Create_MY_IMAGES_DIR();

                // Copy your image
                Utility.copyFile(Utility.Default_DIR, Utility.MY_IMG_DIR);

                // Get new image path and decode it
                //android.graphics.Bitmap b = Utility.decodeFile(Utility.Paste_Target_Location);

                // use new copied path and use anywhere
                String valid_photo = Utility.Paste_Target_Location.toString();
               // b = android.graphics.Bitmap.createScaledBitmap(b, 150, 150, true);

                //set your selected image in image view
                //user_photo.setImageBitmap(b);
                cursor.close();
                return valid_photo;

            } else
            {
                android.widget.Toast toast = android.widget.Toast.makeText(context, "Sorry!!! You haven't selecet any image.", android.widget.Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (Exception e)
        {
            // you get this when you will not select any single image
            android.util.Log.e("onActivityResult", "" + e);

        }
        return null;
    }*/
    public static  wardrobe.nainesh.com.wardrobeapp.models.ImageModel Image_Selecting_Task(android.support.v4.app.Fragment fragment, android.content.Intent data,
                                              int requestCode)
    {


        String url = null;
        android.net.Uri imageUri = null;
        wardrobe.nainesh.com.wardrobeapp.models.ImageModel myClass = new wardrobe.nainesh.com.wardrobeapp.models.ImageModel();

        String picturePath = null;
        String imageName = null;

        if (requestCode == Utility.CAMERA_PICTURE) {

            try {
                java.io.File f = new java.io.File(IMAGE_FILE_PATH);

                for (java.io.File temp : f.listFiles()) {
                    if (temp.getName().equals(tempImageName)) {
                        sendNewFileBroadcast(fragment.getActivity(), new java.io.File(IMAGE_FILE_PATH, tempImageName));
                        f = temp;
                        imageName = temp.getName();
                        break;
                    }
                }

                picturePath = f.getAbsolutePath();
                url = picturePath.toString();
                myClass.setImageName(imageName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == Utility.GALLERY_PICTURE) {

            try {

                android.net.Uri selectedImage = data.getData();
                String[] filePath = {android.provider.MediaStore.Images.Media.DATA};

                imageUri = selectedImage;
                url = selectedImage.toString();

                android.database.Cursor c = fragment.getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();

                if (picturePath == null) {
                    if (data.getData().toString().startsWith("content://com.google.android.apps.photos.content")
                            || data.getData().toString().startsWith("content://com.android.providers.media.documents/")) {
                        java.io.InputStream is = fragment.getActivity().getContentResolver().openInputStream(android.net.Uri.parse(url));
                        picturePath = getDriveFileAbsolutePath(fragment.getContext(), writeToTempImageAndGetPathUri(fragment.getContext(), android.graphics.BitmapFactory.decodeStream(is)));

                    }
                } else {
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        myClass.setUrl(url);

        myClass.setPath(picturePath);
        myClass.setImageUri(imageUri);


        return myClass;
    }

    public static android.net.Uri writeToTempImageAndGetPathUri(android.content.Context inContext, android.graphics.Bitmap inImage) {
        java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
        inImage.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = android.provider.MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return android.net.Uri.parse(path);
    }
    public static String getDriveFileAbsolutePath(android.content.Context context, android.net.Uri uri) throws java.io.IOException {

        java.io.FileInputStream input = null;
        java.io.FileOutputStream output = null;
        String fileName = "";
        final String[] projection = {
                android.provider.MediaStore.MediaColumns.DISPLAY_NAME
        };
        android.content.ContentResolver cr = context.getApplicationContext().getContentResolver();
        android.database.Cursor metaCursor = cr.query(uri, projection, null, null, null);
        if (metaCursor != null) {
            try {
                if (metaCursor.moveToFirst()) {
                    fileName = metaCursor.getString(0);
                }
            } finally {
                metaCursor.close();
            }
        }

        if (uri == null) return null;
        android.content.ContentResolver resolver = context.getContentResolver();

        String outputFilePath = new java.io.File(context.getCacheDir(), fileName).getAbsolutePath();
        try {
            android.os.ParcelFileDescriptor pfd = resolver.openFileDescriptor(uri, "r");
            java.io.FileDescriptor fd = pfd.getFileDescriptor();
            input = new java.io.FileInputStream(fd);
            output = new java.io.FileOutputStream(outputFilePath);
            int read = 0;
            byte[] bytes = new byte[4096];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            return new java.io.File(outputFilePath).getAbsolutePath();
        } catch (java.io.IOException ignored) {
            // nothing we can do
        } catch (Exception e) {

        } finally {
            if (input != null)
                input.close();
            try {
                if (output != null)
                    output.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void sendNewFileBroadcast(final android.app.Activity context, final java.io.File file) {

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    android.net.Uri uri = android.net.Uri.fromFile(file);
                    android.content.Intent scanFileIntent = new android.content.Intent(
                            android.content.Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                    context.sendBroadcast(scanFileIntent);
                } catch (Exception e) {
                    android.util.Log.i("ExternalStorage", " prescrp  file not scanned");
                }
            }
        });


    }

    public static wardrobe.nainesh.com.wardrobeapp.models.ImageModel createImageModel(wardrobe.nainesh.com.wardrobeapp.models.ImageModel imageModel, int imageType, int type, int id){

        imageModel.setImageType(imageType);
        imageModel.setType(type);
        imageModel.setId(id);
        return imageModel;
    }

}
