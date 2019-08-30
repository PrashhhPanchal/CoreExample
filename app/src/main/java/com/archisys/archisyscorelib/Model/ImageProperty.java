package com.archisys.archisyscorelib.Model;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageProperty {

    private Uri imgaeUri;

    private String mime;

    private String imageName;

    private String imagePath;

    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Uri getImgaeUri() {
        return imgaeUri;
    }

    public void setImgaeUri(Uri imgaeUri) {
        this.imgaeUri = imgaeUri;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
