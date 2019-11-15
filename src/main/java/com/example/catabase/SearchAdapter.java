package com.example.catabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    //SearchViewHolder manipulates what happens inside each item in the recycler view
    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView breedName;
        public Button add_me_button;

        //Constructor
        public SearchViewHolder(View v){
            super(v);
            view = v;
            breedName = v.findViewById(R.id.cat_name_result);
            add_me_button = v.findViewById(R.id.add_me_button);




        }

        public void bind (final SearchResultBy_Name searchresult){
            breedName.setText(searchresult.getName());
            breedName.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {

                    Context context = view.getContext();
                    Toast.makeText(context, searchresult.getId(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context,DetailedCatActivity.class);
                    context.startActivity(intent);

                    Intent intentx = new Intent("custom-message");

                    intent.putExtra("id",searchresult.getId());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentx);

                }

            });

            add_me_button.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {

                    Context context = view.getContext();
                    DataHolder.getInstance().cat_breeds.add(searchresult.getName());
                    Toast.makeText(context, searchresult.getName(), Toast.LENGTH_SHORT).show();


                }

            });


        }

    }
    //This method just creates the ViewHolder
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.search_result_view, parent, false);

        SearchViewHolder breedViewHolder = new SearchViewHolder(view);
        return breedViewHolder;


    }


    //class variable that holds the Objects from the API data that we want to adapt
    private List<SearchResultBy_Name> breedsToAdapt;



    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        //It is getting the breed from the ArrayList of breed at allocating it to breedAtPosition
        final SearchResultBy_Name breedAtPosition = breedsToAdapt.get(position);
        //It calls the bind method of the holder object which sets the breed Name to the textView
        holder.bind(breedAtPosition);
    }

    //returns the size of the breeds arraylist
    @Override
    public int getItemCount(){return breedsToAdapt.size();}

    //Sets the List input into the method and attaches it to the arraylist of breeds to be adapted
    public void setData(List<SearchResultBy_Name> breedsToAdapt) {
        this.breedsToAdapt = breedsToAdapt;
    }



}