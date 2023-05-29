package com.example.petmonsters;
import android.os.AsyncTask;

public class AsyncShuffle extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params){
        String[] parts = params[0].split(Constants.slash);
        String output = "";
        for (String s: parts){
            String[] words = s.split(Constants.coma);
            String wordCount = words[0];
            int count = 0;
            for (String w: words){
                count += 1;
            }
            wordCount += Constants.coma + count;
            output += wordCount + Constants.slash;
        }

        output = output.replaceFirst(Constants.delete_last_character_regex,"");
        return output;
    }
}
