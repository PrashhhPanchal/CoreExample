package com.archisys.archisyscorelib.Utils.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.archisys.archisyscorelib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean isConnected;
    public static String getTodayDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        long todaydate = System.currentTimeMillis();
        return sdf.format(todaydate);
    }
    public static long getTodayDateOnly()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long todaydate = System.currentTimeMillis();
        String date=sdf.format(todaydate);
        try {
            Date date1=sdf.parse(date);
            return date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todaydate;
    }
    public static String getTodayDate(String formate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        long todaydate = System.currentTimeMillis();
        return sdf.format(todaydate);
    }
    public static String formateDate(Date date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date parseDateSimple(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(formatter.parse(date));
        return calendar.getTime();
    }
    public static long getMillisForStringDate(String datestring,String formate){
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        Date date = null;
        try {
            date = sdf.parse(datestring);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startdate = date.getTime();
        return startdate;
    }

    public static long getDifferenceMillis(String start,String end){
        return getMillisForStringDate(end,"dd/MM/yyyy hh:mm a") - getMillisForStringDate(start,"dd/MM/yyyy hh:mm a");
    }
    public static String getDateformatedString(String datestring, String inputformate, String outputformate, TimeZone timeZone){
        SimpleDateFormat inputFormate = new SimpleDateFormat(inputformate);
        SimpleDateFormat outputFormate = new SimpleDateFormat(outputformate);
        Date date = null;
        try {
            date = inputFormate.parse(datestring);
            if(timeZone==null){
                timeZone=TimeZone.getDefault();
            }
            outputFormate.setTimeZone(timeZone);
            return outputFormate.format(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Validation
    public static boolean validateLetters(String txt) {
        boolean check;
        if (txt.length() < 3) {
            check = false;
        } else {
            String regx = "[a-zA-Z]+\\.?";
            Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(txt);
            check = matcher.find();
        }
        return check;
    }

    public static boolean isEmailValid(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else {
            return false;
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public static boolean isValidMobile(String phone2) {
        boolean check;
        if (phone2.length() < 10 || phone2.length() > 10) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        if (!isConnected) {
                            isConnected = true;
                        }
                        return true;
                    }
                }
            }
        }
        isConnected = false;
        return false;
    }



    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static long getCurrentdate() {

        Long unixdate = null;
        try {
            Date d = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
            sdf1.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            sdf1.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
            String dateString = sdf1.format(d);//Sat, 21 Jun 2014 18:37:31 GMT

            System.out.println(dateString + " 1");
            DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
            Date date1 = dateFormat.parse(dateString);
            System.out.println(date1 + " 2");
            System.out.println(date1.getTime() + " 3");
            long unixTime = (long) date1.getTime() / 1000;
            System.out.println(unixTime + " 4");
            unixdate = unixTime;
        } catch (Exception e) {
        }
        return unixdate;
    }


    public static void downloadFile(String uRl, Activity context) {
        Toast.makeText(context, "Start Download Image", Toast.LENGTH_SHORT).show();
        DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Image Downloading...")
                .setDestinationInExternalPublicDir("/StockAppsImages", "IMG" + getCurrentdate() + ".jpg");

        mgr.enqueue(request);
    }

    public static void downloadPDFFile(String uRl, Context context) {
        Toast.makeText(context, "Start Download PDF", Toast.LENGTH_SHORT).show();
        DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("PDF Downloading...")
                .setDestinationInExternalPublicDir("/StockAppsPDF", "PDF" + getCurrentdate() + ".pdf");

        mgr.enqueue(request);
    }



    //android convert date to milliseconds
    public String parseDate(String timeAtMiliseconds) {
        if (timeAtMiliseconds.equalsIgnoreCase("")) {
            return "";
        }
        Log.d("Roshani", "Time at milli second " + timeAtMiliseconds);
        String result = "now";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss aa");
        formatter.setTimeZone(TimeZone.getDefault());
        String dataSot = formatter.format(new Date());
        Calendar calendar = Calendar.getInstance();

        long dayagolong = Long.valueOf(timeAtMiliseconds) * 1000;
        calendar.setTimeInMillis(dayagolong);
        String agoformater = formatter.format(calendar.getTime());

        Date CurrentDate = null;
        Date CreateDate = null;
        try {
            CurrentDate = formatter.parse(dataSot);
            CreateDate = formatter.parse(agoformater);
            Log.d("Roshani", "Current===" + dataSot + "=-Create==" + agoformater);
            long different = Math.abs(CurrentDate.getTime() - CreateDate.getTime());

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;
            different = different % secondsInMilli;

            if (elapsedDays == 0) {
                if (elapsedHours == 0) {
                    if (elapsedMinutes == 0) {
                        if (elapsedSeconds < 0) {
                            return "0" + " Second";
                        } else {
                            if (elapsedDays > 0 && elapsedSeconds < 59) {
                                return "now";
                            }
                        }
                    } else {
                        String m = elapsedMinutes > 1 ? " Minutes ago" : " Minute ago";
                        return String.valueOf(elapsedMinutes) + m;
                    }
                } else {
                    String m = elapsedHours > 1 ? " Hours ago" : " Hour ago";
                    return String.valueOf(elapsedHours) + m;
                }

            } else {
                if (elapsedDays <= 30) {
                    String m = elapsedDays > 1 ? " Days ago" : " Day ago";
                    return String.valueOf(elapsedDays) + m;
                }
                if (elapsedDays > 30 && elapsedDays <= 58) {
                    return "1 Month ago";
                }
                if (elapsedDays > 58 && elapsedDays <= 87) {
                    return "2 Months ago";
                }
                if (elapsedDays > 87 && elapsedDays <= 116) {
                    return "3 Months ago";
                }
                if (elapsedDays > 116 && elapsedDays <= 145) {
                    return "4 Months ago";
                }
                if (elapsedDays > 145 && elapsedDays <= 174) {
                    return "5 Months ago";
                }
                if (elapsedDays > 174 && elapsedDays <= 203) {
                    return "6 Months ago";
                }
                if (elapsedDays > 203 && elapsedDays <= 232) {
                    return "7Mth ago";
                }
                if (elapsedDays > 232 && elapsedDays <= 261) {
                    return "8 Months ago";
                }
                if (elapsedDays > 261 && elapsedDays <= 290) {
                    return "9 Months ago";
                }
                if (elapsedDays > 290 && elapsedDays <= 319) {
                    return "10 Months ago";
                }
                if (elapsedDays > 319 && elapsedDays <= 348) {
                    return "11 Months ago";
                }
                if (elapsedDays > 348 && elapsedDays <= 360) {
                    return "12 Months ago";
                }

                if (elapsedDays > 360 && elapsedDays <= 720) {
                    return "1 year ago";
                }

                if (elapsedDays > 720) {
                    SimpleDateFormat formatterYear = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar calendarYear = Calendar.getInstance();
                    calendarYear.setTimeInMillis(dayagolong);
                    return formatterYear.format(calendarYear.getTime()) + "";
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isMyServiceRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if ("com.services.demo.NotificationServices".equals(service.service
                    .getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void jsonToFile(JSONObject object) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
            file.createNewFile();
            if (file.exists()) {
                FileOutputStream fOut = new FileOutputStream(file);
                OutputStreamWriter myOutWriter =
                        new OutputStreamWriter(fOut);
                myOutWriter.append(object.toString());
                myOutWriter.close();

                System.out.println("file created: " + file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String loadJSONFromAsset(Context context,String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    // *********   FOr Srceen Size by prakash jadav  START *************
    private static final int WIDTH_INDEX = 0;
    private static final int HEIGHT_INDEX = 1;

    public static int[] getScreenSize(Context context) {
        int[] widthHeight = new int[2];
        widthHeight[WIDTH_INDEX] = 0;
        widthHeight[HEIGHT_INDEX] = 0;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        widthHeight[WIDTH_INDEX] = size.x;
        widthHeight[HEIGHT_INDEX] = size.y;

        if (!isScreenSizeRetrieved(widthHeight)) {
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            widthHeight[0] = metrics.widthPixels;
            widthHeight[1] = metrics.heightPixels;
        }

        // Last defense. Use deprecated API that was introduced in lower than API 13
        if (!isScreenSizeRetrieved(widthHeight)) {
            widthHeight[0] = display.getWidth(); // deprecated
            widthHeight[1] = display.getHeight(); // deprecated
        }

        return widthHeight;
    }


    private static boolean isScreenSizeRetrieved(int[] widthHeight) {
        return widthHeight[WIDTH_INDEX] != 0 && widthHeight[HEIGHT_INDEX] != 0;
    }
    // *********   FOr Srceen Size by prakash jadav  END *************



    // created by prakash jadav   ,,, Universal Image Loader.
    public static void setImageView(String uri,ImageView imageView,boolean isCached,int defaultImage){
        if(isCached) {
            setImageViewCached(uri,imageView,defaultImage);
        }else{
            setImageViewNotCached(uri,imageView,defaultImage);
        }
    }
    public static void setImageViewCircular(String uri, ImageView imageView, boolean isCached, int radius,int defaultImage){
        if(isCached) {
            setImageViewCircularCached(uri,imageView,radius,defaultImage);
        }else{
            setImageViewCircularNotCached(uri,imageView,radius,defaultImage);
        }
    }
    private static void setImageViewNotCached(String uri, ImageView imageView,int defaultImage){
        try {
            ImageLoader imageLoader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false)
                    .cacheOnDisk(false)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage)
                    .showImageOnLoading(defaultImage).build();
            imageLoader.displayImage(uri, imageView, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void setImageViewCached(String uri, ImageView imageView,int defaultImage){
        try {
            ImageLoader imageLoader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage)
                    .showImageOnLoading(defaultImage).build();
            imageLoader.displayImage(uri, imageView, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setImageViewCircularNotCached(String uri, ImageView imageView, int radius,int defaultImage){
        try {
            ImageLoader imageLoader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false)
                    .displayer(new RoundedBitmapDisplayer(radius))
                    .cacheOnDisk(false)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage)
                    .showImageOnLoading(defaultImage).build();
            imageLoader.displayImage(uri, imageView, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void setImageViewCircularCached(String uri, ImageView imageView, int radius,int defaultImage){
        try {
            ImageLoader imageLoader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .displayer(new RoundedBitmapDisplayer(radius))
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage)
                    .showImageOnLoading(defaultImage).build();
            imageLoader.displayImage(uri, imageView, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }












}
