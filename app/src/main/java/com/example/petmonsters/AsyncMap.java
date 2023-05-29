package com.example.petmonsters;

import android.os.AsyncTask;

import java.util.ArrayList;

public class AsyncMap extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params){
        String[] parts = params[0].split(Constants.coma);

        ArrayList<ArrayList<String>> strings = new ArrayList<>();

        for (String s: parts) {
            int flag = 0;

            for(int i = 0; i < strings.size(); i++){
                if(strings.get(i).contains(s)){
                    strings.get(i).add(s);
                    flag=1;
                }
            }

            if(flag == 0){
                ArrayList<String> tempString = new ArrayList<>();
                tempString.add(s);
                strings.add(tempString);
            }
        }

        String output = "";

        for(int i = 0; i < strings.size(); i++){
            String listString = String.join(Constants.coma, strings.get(i));
            output += listString;
            if(i < strings.size()-1) output += Constants.slash;
        }

        return output;

    }

}
