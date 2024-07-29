package com.example.asg_mobprog.viewholder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asg_mobprog.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView width;
    public TextView height;
    public ImageView iv;
    public Button buttonExample;
    public PokemonViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViewName);
        width = itemView.findViewById(R.id.textViewWidth);
        height = itemView.findViewById(R.id.textViewHeight);
        iv = itemView.findViewById(R.id.imageViewPokemon);
        buttonExample = itemView.findViewById(R.id.buttonExample);
    }
}
