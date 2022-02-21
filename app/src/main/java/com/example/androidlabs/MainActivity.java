package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private static final String DOMAIN = "https://cataas.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        CatImages request = new CatImages(MainActivity.this);
        request.execute(DOMAIN + "/cat?json=true");
    }

    private class CatImages extends AsyncTask<String, Integer, String> {
        private Context context;

        public CatImages(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... args) {
            InputStream response = null;
            OutputStream output = null;
            try {
                // Send first Web request
                URL url = new URL(args[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                response = urlConnection.getInputStream();

                // Read JSON
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                String result = stringBuilder.toString();

                JSONObject catImage = new JSONObject(result);
                String imageUrl = catImage.getString("url");
                String imageId = catImage.getString("id");

                // Send second request for cat image
                url = new URL(DOMAIN + imageUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                response = urlConnection.getInputStream();

                Bitmap currentPicture = BitmapFactory.decodeStream(response);

                File file = new File(context.getFilesDir(), imageId);
                output = new BufferedOutputStream(new FileOutputStream(file));
                currentPicture.compress(Bitmap.CompressFormat.JPEG, 100, output);
                output.close();

                ((MainActivity)context).runOnUiThread(() -> imageView.setImageBitmap(currentPicture));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}