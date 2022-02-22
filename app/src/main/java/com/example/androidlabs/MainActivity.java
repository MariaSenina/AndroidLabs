package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar progressBar;

    private static final String DOMAIN = "https://cataas.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);

        CatImages request = new CatImages(MainActivity.this);
        request.execute(DOMAIN + "/cat?json=true");
    }

    private class CatImages extends AsyncTask<String, Integer, String> {
        private Context context;
        private Bitmap currentPicture;
        private boolean newImageSelected;

        public CatImages(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... args) {
            while(true) {
                try {
                    // Send first Web request
                    InputStream response = makeHttpRequest(args[0]);

                    // Read JSON
                    String result = parseJson(response);

                    JSONObject catImage = new JSONObject(result);
                    String imageUrl = catImage.getString("url");
                    String imageId = catImage.getString("id");

                    File file = new File(context.getFilesDir(), imageId);

                    if (file.exists()) {
                        currentPicture = BitmapFactory.decodeFile(file.getPath());
                    } else {
                        response = makeHttpRequest(DOMAIN + imageUrl);

                        currentPicture = BitmapFactory.decodeStream(response);

                        OutputStream output = new BufferedOutputStream(new FileOutputStream(file));
                        currentPicture.compress(Bitmap.CompressFormat.JPEG, 100, output);
                        output.close();
                    }

                    newImageSelected = true;
                    for (int i = 0; i < 100; i++) {
                        try {
                            publishProgress(i);
                            Thread.sleep(20);
                            newImageSelected = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (newImageSelected) {
                imageView.setImageBitmap(currentPicture);
            }

            progressBar.setProgress(values[0]);
        }

        protected InputStream makeHttpRequest(String address) throws IOException {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            return urlConnection.getInputStream();
        }

        protected String parseJson(InputStream response) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        }
    }
}