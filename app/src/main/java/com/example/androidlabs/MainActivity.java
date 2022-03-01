package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "name";
    public static final String HEIGHT = "height";
    public static final String MASS = "mass";
    public static final String BASE_URL = "https://swapi.dev/api/";
    ArrayAdapter<String> theAdapter;

    ArrayList<String> source = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        boolean isTablet = findViewById(R.id.fragment) != null;

        StarWarsApiConsumer request = new StarWarsApiConsumer();
        request.execute(BASE_URL + "people/?format=json");

        theAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, source);
        listView.setAdapter(theAdapter);
        listView.setOnItemClickListener((list, item, position, id) -> {
            Bundle dataToSend = new Bundle();
            dataToSend.putString(NAME, source.get(position));
            dataToSend.putString(HEIGHT, "123");
            dataToSend.putString(MASS, "11");

            if(isTablet) {
                DetailsFragment detailFragment = new DetailsFragment();
                detailFragment.setArguments(dataToSend);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, detailFragment)
                        .commit();
            } else {
                Intent nextActivity = new Intent(MainActivity.this, EmptyActivity.class);
                nextActivity.putExtras(dataToSend);
                startActivity(nextActivity);
            }
        });
    }

    private class StarWarsApiConsumer extends AsyncTask <String, Integer, Map<String, SWCharacter>> {

        @Override
        protected Map<String, SWCharacter> doInBackground(String... args) {
            Map<String, SWCharacter> characterMap = new HashMap<>();
            try {
                InputStream response = makeHttpRequest(args[0]);
                String result = parseJson(response);
                JSONArray characters = new JSONObject(result).getJSONArray("results");

                for (int i = 0; i < characters.length(); i++) {
                    String name = characters.getJSONObject(i).getString(NAME);
                    String height = characters.getJSONObject(i).getString(HEIGHT);
                    String mass = characters.getJSONObject(i).getString(MASS);

                    SWCharacter character = new SWCharacter(name, height, mass);
                    characterMap.put(name, character);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return characterMap;
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