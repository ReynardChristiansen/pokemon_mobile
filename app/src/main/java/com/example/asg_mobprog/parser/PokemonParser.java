package com.example.asg_mobprog.parser;

import com.example.asg_mobprog.model.Pokemon;
import com.google.gson.Gson;

public class PokemonParser {
    private static final Gson gson = new Gson();
    public static Pokemon parsePokemon(String json) {
        return gson.fromJson(json, Pokemon.class);
    }
}
