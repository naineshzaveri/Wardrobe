package wardrobe.nainesh.com.wardrobeapp.models;

public class ImageModel implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	android.graphics.Bitmap bitmap = null;
	String path, url;

	android.net.Uri imageUri;
	String imageName;

	// internal use..type is image or button
	int imageType; 	// camera or gallery

	private int id;

	private int type; // trouser or shirt


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public android.graphics.Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(android.graphics.Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public android.net.Uri getImageUri() {
		return imageUri;
	}

	public void setImageUri(android.net.Uri imageUri) {
		this.imageUri = imageUri;
	}

	public int getImageType() {
		return imageType;
	}

	public void setImageType(int imageType) {
		this.imageType = imageType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
