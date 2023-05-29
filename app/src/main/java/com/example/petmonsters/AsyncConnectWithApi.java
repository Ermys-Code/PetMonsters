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
            MediaType mediaType = MediaType.parse("");
            RequestBody body = RequestBody.create(mediaType, params[0]);
            Request request = new Request.Builder()
                    .url(Constants.api_url + params[1])
                    .method(Constants.method_post, body)
                    .addHeader(Constants.header_content_type, Constants.header_application_json)
                    .addHeader(Constants.header_access_control, Constants.header_all)
                    .addHeader(Constants.header_api_key, Constants.api_key)
                    .build();
            Response response;

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                return e.getMessage();
            }

            return response.body().string();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}