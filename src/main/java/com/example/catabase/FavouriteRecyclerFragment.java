package com.example.catabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteRecyclerFragment extends Fragment {

    public FavouriteRecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflating the RecyclerFragment
        View view = inflater.inflate(R.layout.fragment_fav_recycler,container,false);
        //Recycler View
        //recycler view for order selection
        RecyclerView favourite_recycler = view.findViewById(R.id.rv_fav);
        //Layout Manager declaration
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        //Setting the layout manager to the recycler view
        favourite_recycler.setLayoutManager(layoutManager);
        //Adapter
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(DataHolder.getInstance().cat_breeds);


       // favourite_recycler.setAdapter(favouriteAdapter);

        return view;
    }
}
