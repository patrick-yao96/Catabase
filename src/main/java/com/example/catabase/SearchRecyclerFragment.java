package com.example.catabase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class SearchRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private RequestQueue mRequestQueue;
    private String result;
    //Json request from the cat api
    private String catURL = "https://api.thecatapi.com/v1/breeds/search?q=";

    private String userInput;


    //Providing an empty constructor
    public SearchRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflating the RecyclerFragment
        View view = inflater.inflate(R.layout.fragment_main_recycler,container,false);
        //Declaring the recycler view and linking from the layout
        recyclerView = view.findViewById(R.id.rv_main);
        //Layout Manager declaration
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        //Setting the layout manager to the recycler view
        recyclerView.setLayoutManager(layoutManager);

        final SearchAdapter searchAdapter = new SearchAdapter();
        //SearchView
         SearchView catSearchView = view.findViewById(R.id.search_bar);
        catSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                //Volley Request for search by name
                mRequestQueue = Volley.newRequestQueue(getActivity());
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse (String response){
                        Gson gson = new Gson();

                        SearchResultBy_Name[] enums = gson.fromJson(response, SearchResultBy_Name[].class);


                        List<SearchResultBy_Name> resultList = Arrays.asList(enums);
                        //Feeding the result list of cat objects into the adapter
                        searchAdapter.setData(resultList);
                        //Setting the adapter to the recycler view
                        recyclerView.setAdapter(searchAdapter);
                        //Stopping the queue
                        mRequestQueue.stop();

                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        mRequestQueue.stop();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.GET,catURL+query,responseListener,errorListener);
                mRequestQueue.add(stringRequest);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String text) {

                //Volley Request for search by name
                mRequestQueue = Volley.newRequestQueue(getActivity());
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse (String response){
                        Gson gson = new Gson();

                        SearchResultBy_Name[] enums = gson.fromJson(response, SearchResultBy_Name[].class);


                        List<SearchResultBy_Name> resultList = Arrays.asList(enums);
                        //Feeding the result list of cat objects into the adapter
                        searchAdapter.setData(resultList);
                        //Setting the adapter to the recycler view
                        recyclerView.setAdapter(searchAdapter);
                        //Stopping the queue
                        mRequestQueue.stop();

                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        mRequestQueue.stop();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.GET,catURL+text,responseListener,errorListener);
                mRequestQueue.add(stringRequest);
                return true;
            }
        });



        return view;


    }
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(String string);
    }



}
