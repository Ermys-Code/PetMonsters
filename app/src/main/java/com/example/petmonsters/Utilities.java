package com.example.petmonsters;

import android.content.Context;
import android.widget.Toast;

public class Utilities {

    public static void ShowShortToast(Context context, String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
