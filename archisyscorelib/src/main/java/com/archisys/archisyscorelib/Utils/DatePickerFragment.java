package com.archisys.archisyscorelib.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.archisys.archisyscorelib.Utils.Listener.ICallback;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    private Activity activity;
    private Date selectedDate;
    private String inputDate="";
    private ICallback callback;
    private String formate="dd/MM/yyyy hh:mm a";
    private String minDate="";
    private String maxDate="";

    public DatePickerFragment(Activity activity, String inputDate, String minDate,String maxDate, String formate, ICallback callback) {

        this.activity = activity;
        this.selectedDate = new Date();
        this.inputDate=inputDate;
        this.formate=formate;
        this.minDate=minDate;
        this.maxDate=maxDate;
        try {
            this.selectedDate = CommonUtils.parseDateSimple(inputDate, formate);
        } catch (ParseException e) {
        }

        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        c.setTime(selectedDate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),this,year,month,day);
        if(!minDate.equals("")){
            datePickerDialog.getDatePicker().setMinDate(CommonUtils.getMillisForStringDate(minDate,formate));
        }
        if(!maxDate.equals("")){
            datePickerDialog.getDatePicker().setMaxDate(CommonUtils.getMillisForStringDate(maxDate,formate));
        }

        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        if (view.isShown()) {
            final Calendar c = Calendar.getInstance();
            //c.setTime(selectedDate);
            c.set(year,month,day);
            String returnDate = "";
            try {
                returnDate = CommonUtils.formateDate(c.getTime(), formate);
            } catch (Exception e) {

            }
            callback.onSuccess(returnDate);
            //callback.onSuccess( (month+1) + "/" + day + "/" + year );
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        callback.onCancel();
    }
}