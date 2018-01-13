package com.volkangurbuz.marvelheroesjsonparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //the URL having the json data
    private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";

    ListView listView;
    List<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        heroes = new ArrayList<>();
        loadHeroList();

    }


    private void loadHeroList() {

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        //progressBar.setVisibility(View.VISIBLE);

        //create a string request to send request to the url

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //getting the whole json object from the response
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray heroArray = obj.getJSONArray("heroes");

                    for (int i = 0; i < heroArray.length(); i++) {

                        JSONObject heroObject = heroArray.getJSONObject(i);
                        String name = heroObject.getString("name");
                        String imageUrl = heroObject.getString("imageurl");
                        Hero hero = new Hero(name, imageUrl);
                        heroes.add(hero);
                    }

                    ListViewAdapter listViewAdapter = new ListViewAdapter(heroes, getApplicationContext());
                    listView.setAdapter(listViewAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
