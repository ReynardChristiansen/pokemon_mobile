package com.example.asg_mobprog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.asg_mobprog.PC.PC;
import com.example.asg_mobprog.model.Pokemon;
import com.example.asg_mobprog.parser.PokemonParser;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CatchPokemon extends AppCompatActivity {
    int captureAttempts = 0;
    Context ctx;
    Pokemon pokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catch_pokemon);
        ctx=this;
        Button b = findViewById(R.id.btnCatchPokemon);
        Button y = findViewById(R.id.btnRandomPokemon);
        Button m = findViewById(R.id.btnNavigate);
        PokemonRequest d = new PokemonRequest();
        d.execute();

        b.setOnClickListener(e->{
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;

            if(randomNumber >= 6){
                ArrayList<Pokemon> pokemons = PC.getPokemons(this);
                pokemons.add(pokemon);
                PC.savePokemons(this, pokemons);

                Toast.makeText(this, "Catch Success", Toast.LENGTH_SHORT).show();
                PokemonRequest h = new PokemonRequest();
                h.execute();
                captureAttempts = 0;
            }

            else{
                captureAttempts++;
                if (captureAttempts >= 3) {
                    Toast.makeText(this, "Pokemon fled away!", Toast.LENGTH_SHORT).show();
                    captureAttempts = 0;
                    PokemonRequest h = new PokemonRequest();
                    h.execute();
                } else {
                    Toast.makeText(this, "Catch Failed. Attempts left: " + (3 - captureAttempts), Toast.LENGTH_SHORT).show();
                }

            }


        });

        y.setOnClickListener(e->{
            PokemonRequest h = new PokemonRequest();
            h.execute();
        });

        m.setOnClickListener(e->{
            Intent navigate = new Intent(this, PcActivity.class);
            startActivity(navigate);
        });
    }
    public class PokemonRequest extends AsyncTask<Void,Void,String> {
        private String readStream(InputStream is){
            try{
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int i = is.read();
                while(i!=-1){
                    buffer.write(i);
                    i= is.read();
                }
                return buffer.toString();
            }
            catch(Exception e){
                return "";
            }
        };
        @Override
        protected String doInBackground(Void... urls) {
            Random random = new Random();
            int randomNumber = random.nextInt(1000) + 1;

            String result = "";
            try{
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + randomNumber);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = readStream(in);
                urlConnection.disconnect();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }

        //        protected void onProgressUpdate (Void... values){
//
//        }
//
        protected void onPostExecute(String result){

            Log.v("server response",result);
            try {
                pokemon = PokemonParser.parsePokemon(result);
                // Print Pokemon details
                Log.v("Pokemon Details", "Name: " + pokemon.getName());
                Log.v("Pokemon Details", "Height: " + pokemon.getHeight());
                Log.v("Pokemon Details", "Weight: " + pokemon.getWeight());
                Log.v("Pokemon Details", "Image: " + pokemon.getSprites());  // Assuming 'image' is an int
                TextView name = findViewById(R.id.textViewName);
                TextView width = findViewById(R.id.textViewWidth);
                TextView height = findViewById(R.id.textViewHeight);
                ImageView iv = findViewById(R.id.imageViewPokemon);
                String imageUrl = pokemon.getSprites();
                Glide.with(ctx)
                        .load(imageUrl)
                        .into(iv);
                name.setText("Name: " + pokemon.getName());
                width.setText("Width: " + pokemon.getWeight());  // Assuming 'weight' is the width
                height.setText("Height: " + pokemon.getHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
