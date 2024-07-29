package com.example.asg_mobprog.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asg_mobprog.DetailPage;
import com.example.asg_mobprog.R;
import com.example.asg_mobprog.model.Pokemon;
import com.example.asg_mobprog.viewholder.PokemonViewHolder;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
        Context ctx;
        LayoutInflater layoutInflater;
        ArrayList<Pokemon> pokemons;

public PokemonAdapter(Context context, ArrayList<Pokemon> pokemons){
        this.layoutInflater = LayoutInflater.from(context);
        this.pokemons = pokemons;
        this.ctx = context;
        }


public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycle_view_holder, parent, false);
        return new PokemonViewHolder(view);
        }

@Override
public void onBindViewHolder(PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        String imageUrl = pokemon.getSprites();
        Glide.with(ctx)
        .load(imageUrl)
        .into(holder.iv);
        holder.name.setText("Name: " + pokemon.getName());
        holder.width.setText("Width: " + pokemon.getWeight());
        holder.height.setText("Height: " + pokemon.getHeight());

        holder.buttonExample.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        // Handle button click event here
                        onButtonClick(pokemon);
                }
        });

        }

@Override
public int getItemCount() {
        return pokemons.size();
        }

private void onButtonClick(Pokemon pokemon) {
        // Add your logic here when the button is clicked
        Log.d("PokemonAdapter", "Button clicked for " + pokemon.getName());

        // Create an Intent to navigate to another page (replace YourTargetActivity.class with the actual class)
        Intent intent = new Intent(ctx, DetailPage.class);

        // Pass data to the next activity if needed
        intent.putExtra("pokemonName", pokemon.getName());

        // Start the new activity
        ctx.startActivity(intent);
        }

}


