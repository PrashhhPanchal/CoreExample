package com.archisys.archisyscorelib.Utils.Base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.archisys.archisyscorelib.BuildConfig;
import com.archisys.archisyscorelib.R;
import com.archisys.archisyscorelib.Utils.Broadcast.InternetConnectorReceiver;
import com.archisys.archisyscorelib.Utils.Listener.ActivityResult;
import com.archisys.archisyscorelib.Utils.Listener.IActivityResultListener;
import com.archisys.archisyscorelib.Utils.Listener.IPermissionResultListener;
import com.archisys.archisyscorelib.Utils.Listener.NetworkChangedListener;
import com.archisys.archisyscorelib.Utils.Listener.PermissionResult;
import com.archisys.archisyscorelib.Utils.Model.ImageProperty;
import com.archisys.archisyscorelib.Utils.Utils.Const;
import com.archisys.archisyscorelib.Utils.Utils.ImageFilePath;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import static com.archisys.archisyscorelib.Utils.PathUtil.getFileName;


public abstract class BaseActivity extends AppCompatActivity implements BaseFragment.FragmentCallback,
        NetworkChangedListener, IPermissionResultListener, IActivityResultListener {

    // since its going to be common for all the activities
    SharedPreferences sharedPreferences;
    public BaseActivity activity;

    File parentFile;
    File fileFolder;
    File cameraImage;

    public File myImageFile;

    public Integer Camera_Request_Code = 1001;
    public Integer Gallery_Request_Code = 1002;
    public Integer Document_Request_Code = 1003;

    InternetConnectorReceiver internetConnectorReceiver;

    public Resources.Theme themes;
    public TypedValue storedValueInTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        sharedPreferences = this.getSharedPreferences("FindMyTradePrefs", Context.MODE_PRIVATE);

        initBase();

    }

    public void initBase()
    {
        activity= BaseActivity.this;
        internetConnectorReceiver = new InternetConnectorReceiver(this);

        themes = activity.getTheme();
        storedValueInTheme = new TypedValue();
    }

    // for Internet Changed event
    public void registerInternetBroadcast(){
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(internetConnectorReceiver, intentFilter);
    }

    public boolean isContextAlive()
    {
        if (getBaseActivity() == null)
        {
            return false;
        }
        return true;
    }

    public void userProfilePic()
    {
        ContextWrapper cw = new ContextWrapper(activity);
        File directory = cw.getDir("Download", Context.MODE_PRIVATE);
        myImageFile = new File(directory, "profilePicture.png");
    }

    public BaseActivity getBaseActivity(){
        return activity;
    }

    public void callIntent(Class clazz){
        Intent intent=new Intent(activity,clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


    public Dialog createDialog(Integer layout)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.setCancelable(true);
        return dialog;
    }

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String[] permission) {
        for (int i = 0; i < permission.length; i++)
        {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            checkSelfPermission(permission[i]) == PackageManager.PERMISSION_DENIED)
            {
                return false;
            }
        }
        return true;
    }

    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * check internet connectivity
     * @return
     */
    public  boolean isNetworkConnected() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) this
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }

        } catch (Exception e) {
            Toast.makeText(this,  e.getMessage() , Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++)
        {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED)
            {
                Toast.makeText(activity, "Grant permission for better app performance.!", Toast.LENGTH_SHORT).show();
            }
        }
        new PermissionResult(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        new ActivityResult(this, requestCode, resultCode, data);
    }

    public abstract void AutoRefresh();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onPause() {

        super.onPause();
        BaseApplication.activityPaused();// On Pause notify the Application
        this.unregisterReceiver(internetConnectorReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //this.unregisterReceiver(internetConnectorReceiver);
    }

    @Override
    protected void onResume() {

        super.onResume();
        BaseApplication.activityResumed();// On Resume notify the Application
        registerInternetBroadcast();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
        finish();
    }

    public void ChooseMedia(int code)
    {
        //this method requires provider
        String[] permissions = {Const.Permission_Camera, Const.Permission_Write_External_Storage, Const.Permission_Read_External_Storage};

        if (!hasPermission(permissions))
        {
            requestPermissionsSafely(permissions, 1);
            return;
        }

        parentFile = Environment.getExternalStorageDirectory();
        fileFolder = new File(parentFile.getAbsolutePath() + "/MobileApps");
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }

        if (code == Camera_Request_Code)
        {
            cameraImage = new File(fileFolder, UUID.randomUUID() + ".jpeg");
            Intent intentFirst = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                intentFirst.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", cameraImage));
            } else {
                intentFirst.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraImage));
            }
            startActivityForResult(intentFirst, Camera_Request_Code);
        }

        if (code == Gallery_Request_Code)
        {
            startActivityForResult(Intent.createChooser(new Intent(Intent.ACTION_GET_CONTENT)
                    .setType("image/*"), "Choose an image"),Gallery_Request_Code);
        }

        if (code == Document_Request_Code)
        {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("*/*");
            startActivityForResult(intent.createChooser(intent, "Select File"), Document_Request_Code);
        }

    }

    public ImageProperty SaveMediaFile(int code, Intent data) {
        ImageProperty imageProperty = new ImageProperty();
        String extension = "";
        String type = "";
        String path = "";
        Uri file1Uri = null;
        Bitmap bitmapImage = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;

            if (code == Camera_Request_Code)
            {
                bitmapImage = BitmapFactory.decodeFile(cameraImage.getAbsolutePath(), options);
                FileOutputStream fileOutputStream = new FileOutputStream(cameraImage);
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                file1Uri = Uri.fromFile(new File(cameraImage.getPath()));
                path = ImageFilePath.getPath(this, file1Uri);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            if (code == Gallery_Request_Code)
            {
                file1Uri = data.getData();
                path = ImageFilePath.getPath(this, file1Uri);
                if (path == null)
                {
                    path = getFilePathFromURI(file1Uri);
                }
                if (new File(path).length() > 4096*1024)
                {
                    Toast.makeText(this, "Image size is too high", Toast.LENGTH_SHORT).show();
                    return null;
                }
                bitmapImage = BitmapFactory.decodeFile(new File(path).getAbsolutePath(), options);
            }
            if (code == Document_Request_Code)
            {
                file1Uri = data.getData();
                path = getFilePathFromURI(file1Uri);
            }


            extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(file1Uri));
            if (extension != null && !extension.isEmpty()) {
                type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            }
            else {
                type = getApplicationContext().getContentResolver().getType(file1Uri);
            }

            imageProperty.setImageName(new File(path).getName());
            imageProperty.setImgaeUri(file1Uri);
            imageProperty.setMime(type);
            imageProperty.setBitmap(bitmapImage);
            imageProperty.setImagePath(path);

        } catch (Exception e) {
            Toast.makeText(activity, "Attachment Failed. Please try again..", Toast.LENGTH_LONG).show();
            try {
                if (cameraImage.exists()) {
                    cameraImage.delete();
                }
            } catch (Exception ex) {
            }
        }
        return imageProperty;
    }

    public static String getFilePathFromURI(Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
            //copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }


    @Override
    public void NetworkChanged() {
        if(BaseApplication.isActivityVisible()) {
            if (isNetworkConnected()) {
                Log.d("NetworkChanged", "NetworkChanged Internet connected");
                Const.isInternetConnected=true;
                Toast.makeText(activity, "Back online", Toast.LENGTH_SHORT).show();
            } else {
                Const.isInternetConnected=false;
                Toast.makeText(activity, "You are offline !", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void HideCompleteSystemUI()
    {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }
}
