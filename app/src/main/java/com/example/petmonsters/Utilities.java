package com.example.petmonsters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.Random;

public class Utilities {

    public static void ShowShortToast(Context context, String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static String md5(String s) throws Exception {

        MessageDigest digest = java.security.MessageDigest.getInstance(Constants.hash_algorithm);
        digest.update(s.getBytes());
        byte messageDigest[] = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i=0; i<messageDigest.length; i++)
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        return hexString.toString();
    }

    public static Boolean showAdd(){
        int result = new Random().nextInt(3-0);
        return result == 1;
    }

    public static void changeActivity(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void changeActivityAndFinish(Context context, Class<?> cls, Activity activity){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        activity.finish();
    }

    public static void changeActivityWithAdd(Context context, Class<?> cls){
        if (showAdd()){
            Intent a = new Intent(context, AddActivity.class);
            a.putExtra(Constants.next_activity_key, cls);
            context.startActivity(a);
        } else {
            Intent intent = new Intent(context, cls);
            context.startActivity(intent);
        }
    }
}
