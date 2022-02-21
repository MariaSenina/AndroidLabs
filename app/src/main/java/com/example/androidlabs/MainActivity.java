package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CatImages request = new CatImages();
        request.execute("https://cataas.com/cat?json=true");
    }

    class CatImages extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... args) {

//            try {
//                URL url = new URL(args[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream response = urlConnection.getInputStream();
//
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
//                StringBuilder stringBuilder = new StringBuilder();
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line + "\n");
//                }
//
//                String result = stringBuilder.toString();
//
//                JSONObject catImage = new JSONObject(result);
//
//                Bitmap currentPicture = BitmapFactory.decodeStream(response);
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }

            return null;
        }
    }
}