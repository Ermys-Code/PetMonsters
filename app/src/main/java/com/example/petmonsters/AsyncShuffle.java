package com.example.petmonsters;
import android.os.AsyncTask;

public class AsyncShuffle extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params){
        String[] parts = params[0].split("/");
        String output = "";
        for (String s: parts){
            String[] words = s.split(",");
            String wordCount = words[0];
            int count = 0;
            for (String w: words){
                count += 1;
            }
            wordCount += "," + Integer.toString(count);
            output += wordCount + "/";
        }

        output = output.replaceFirst(".$","");
        return output;
    }
}
