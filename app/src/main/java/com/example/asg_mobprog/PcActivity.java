package com.example.asg_mobprog;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asg_mobprog.PC.PC;
import com.example.asg_mobprog.adapter.PokemonAdapter;
import com.example.asg_mobprog.model.Pokemon;

import java.util.ArrayList;

public class PcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc);
        ArrayList<Pokemon> pokemons= PC.getPokemons(this);
        RecyclerView recyclerView = findViewById(R.id.rvPokemon);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new PokemonAdapter(this, pokemons));


    }
}

