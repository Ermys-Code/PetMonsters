package com.example.petmonsters;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AsyncConnectWithApi  extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            //MediaType mediaType = MediaType.parse("application/json");
            //RequestBody reqBody = RequestBody.create(a.getBytes(StandardCharsets.UTF_8));
            MediaType mediaType = MediaType.parse("");
            RequestBody body = RequestBody.create(mediaType, params[0]);
            Request request = new Request.Builder()
                    .url("https://eu-west-2.aws.data.mongodb-api.com/app/data-okkbs/endpoint/data/v1/action/" + params[1])
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Access-Control-Request-Headers", "*")
                    .addHeader("api-key", "sucE6UfBivnhG4Iwg6sNPhluANRUqrDWApBAxpV7hG0SKOYmWWBXBlRcni1ehqOU")
                    .build();
            Response response;

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                return e.getMessage();
            }

            return response.body().string();

        } catch (IOException e) {
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
