package com.example.petmonsters;

import android.content.Context;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Utilities {

    public static void ShowShortToast(Context context, String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static String md5(String s) throws NoSuchAlgorithmException {

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

}
