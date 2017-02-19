package com.honjane.imviewdemo;

import com.honjane.imviewdemo.Model.MessageWrapper;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tanghongjian on 2017/2/19.
 */

public class CommUtils {

    public static String formatTimeInMillis(long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String fmt = dateFormat.format(date);

        return fmt;
    }



}
