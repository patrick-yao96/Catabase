package com.example.catabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class DetailedCatActivity extends AppCompatActivity {
    private RequestQueue mmRequestQueue;
    private String getCatURL = "https://api.thecatapi.com/v1/images/search?breed_ids=tang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_cat);



        final TextView cat_name = findViewById(R.id.cat_name_value);
        final TextView cat_description = findViewById(R.id.cat_description_value);
        final TextView cat_weight = findViewById(R.id.cat_weight_value);
        final TextView cat_temperament = findViewById(R.id.cat_temperament_value);
        final TextView cat_origin = findViewById(R.id.cat_origin_value);
        final TextView cat_lifespan = findViewById(R.id.cat_lifespan_value);
        final TextView cat_wiki_link = findViewById(R.id.cat_wikilink_value);
        final TextView cat_dog_friendliness = findViewById(R.id.cat_friend_value);
        Button fav_button = findViewById(R.id.fav_button);
        ImageView cat_image = findViewById(R.id.cat_image);

        //Receiving catId from main activity
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        String catID_string = "tang";

        //Volley Request for search by catID
        mmRequestQueue = Volley.newRequestQueue(getApplicationContext());
        Response.Listener<String> mresponseListener = new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                Gson gson = new Gson();

                SearchResultBy_ID[] enums = gson.fromJson(response, SearchResultBy_ID[].class);

                //Image url
              //  String image_url=enums[0].getUrl();

                List<SearchResultBy_ID> resultList = Arrays.asList(enums);

                SearchResultBy_ID resultInstance = resultList.get(1);

                List<Breed> breedList = Arrays.asList(resultInstance.getBreedsArray());

                Breed user_cat = breedList.get(1);




                cat_name.setText(user_cat.getName());
                cat_description.setText(user_cat.getDescription());
                //cat_weight.setText(user_cat.weight.getMetric());
                cat_temperament.setText(user_cat.getTemperament());
                cat_origin.setText(user_cat.getOrigin());
                cat_lifespan.setText(user_cat.getLife_span());
                cat_wiki_link.setText(user_cat.getWikipedia_url());
              cat_dog_friendliness.setText(user_cat.getDog_friendly()+" ");




                //Stopping the queue
                mmRequestQueue.stop();

            }
        };

        Response.ErrorListener merrorListener = new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                mmRequestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET,getCatURL,mresponseListener,merrorListener);
        mmRequestQueue.add(stringRequest);

    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             String catID_string = intent.getStringExtra("id");


        }
    };
}
