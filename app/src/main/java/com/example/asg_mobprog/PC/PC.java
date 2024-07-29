package com.example.asg_mobprog.PC;

import com.example.asg_mobprog.model.Pokemon;
import com.google.gson.reflect.TypeToken;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PC {

    private static final String PREFERENCES_NAME = "PokemonStorage";
    private static final String POKEMON_KEY = "Trainer";

    public static void savePokemons(Context context, ArrayList<Pokemon> pokemons) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String pokemonJson = gson.toJson(pokemons);
        editor.putString(POKEMON_KEY, pokemonJson);
        editor.apply();
    }

    public static ArrayList<Pokemon> getPokemons(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String pokemonJson = preferences.getString(POKEMON_KEY, null);

        if (pokemonJson != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Pokemon>>() {}.getType();
            return gson.fromJson(pokemonJson, listType);
        } else {
            return new ArrayList<>();
        }
    }
}
