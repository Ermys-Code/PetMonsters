package com.example.petmonsters;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AsyncConnectWithApi  extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"collection\":\"user\",\n    \"database\":\"ClubNoPenguin\",\n    \"dataSource\":\"ClubNoPenguin\",\n    \"filter\": {\"username\": \"ddd\"}\n\n}");
            Request request = new Request.Builder()
                    .url("https://eu-west-2.aws.data.mongodb-api.com/app/data-okkbs/endpoint/data/v1/action/findOne")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Access-Control-Request-Headers", "*")
                    .addHeader("api-key", "sucE6UfBivnhG4Iwg6sNPhluANRUqrDWApBAxpV7hG0SKOYmWWBXBlRcni1ehqOU")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }

            String jsonData = response.body().string();
            JSONObject Jobject = new JSONObject(jsonData);

            /*JSONArray Jarray = Jobject.getJSONArray("employees");

            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object = Jarray.getJSONObject(i);
            }*/

            return Jobject.getJSONObject("document").getString("password");

        } catch (JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
