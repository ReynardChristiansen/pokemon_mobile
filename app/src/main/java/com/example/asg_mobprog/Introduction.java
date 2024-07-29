package com.example.asg_mobprog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Introduction extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_pokemon);

        Button b = findViewById(R.id.btnNavigateToPokemonList);

        b.setOnClickListener(e->{
            Intent navigate = new Intent(this, CatchPokemon.class);
            startActivity(navigate);
        });
    }
}
