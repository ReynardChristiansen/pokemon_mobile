package com.example.asg_mobprog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.asg_mobprog.model.Pokemon;
import com.example.asg_mobprog.parser.PokemonParser;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailPage extends AppCompatActivity {
    String names;
    Context ctx;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        ctx=this;

        Intent intent = getIntent();
        names = intent.getStringExtra("pokemonName");

        Toast.makeText(this, names, Toast.LENGTH_SHORT).show();

        PokemonRequest d = new PokemonRequest();
        d.execute();
    }

    public class PokemonRequest extends AsyncTask<Void, Void, String> {
        private String readStream(InputStream is) {
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int i = is.read();
                while (i != -1) {
                    buffer.write(i);
                    i = is.read();
                }
                return buffer.toString();
            } catch (Exception e) {
                return "";
            }
        }

        ;

        @Override
        protected String doInBackground(Void... urls) {
            String result = "";
            try {
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + names);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = readStream(in);
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String result) {
            Log.v("server response", result);
            try {
                Pokemon pokemon = PokemonParser.parsePokemon(result);
                // Print Pokemon details
                Log.v("Pokemon Details", "Name: " + pokemon.getName());
                Log.v("Pokemon Details", "Height: " + pokemon.getHeight());
                Log.v("Pokemon Details", "Weight: " + pokemon.getWeight());
                Log.v("Pokemon Details", "Image: " + pokemon.getSprites());  // Assuming 'image' is an int
                TextView name = findViewById(R.id.textViewName);
                TextView width = findViewById(R.id.textViewWidth);
                TextView height = findViewById(R.id.textViewHeight);
                ImageView iv = findViewById(R.id.imageViewPokemon);
                name.setText("Name: " + pokemon.getName());
                width.setText("Width: " + pokemon.getWeight());  // Assuming 'weight' is the width
                height.setText("Height: " + pokemon.getHeight());
                String imageUrl = pokemon.getSprites();
                Glide.with(ctx)
                        .load(imageUrl)
                        .into(iv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
