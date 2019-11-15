package com.example.catabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private ArrayList<String> breedToAdapt;

    public FavouriteAdapter(ArrayList<String> breedToAdapt)
    {
        this.breedToAdapt=breedToAdapt;
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public TextView breedName;


        public FavouriteViewHolder(View v)
        {
            super(v);
            view = v;
            breedName = v.findViewById(R.id.fav_cat_result);


        }
        public void bind (final String cat_name) {
            breedName.setText(cat_name);
        }
    }
    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fav_recycler, parent, false);
        FavouriteViewHolder favViewHolder = new FavouriteViewHolder(view);
        return favViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.FavouriteViewHolder holder, int position) {
        String breedAtPosition = breedToAdapt.get(position);
     holder.breedName.setText(breedAtPosition+" ");

    }
    @Override
    public int getItemCount() {
        return breedToAdapt.size();
    }

    //set data method
    public void setData(ArrayList<String> namesToAdapt)

    {
        this.breedToAdapt=namesToAdapt;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
