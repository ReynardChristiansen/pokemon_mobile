package com.example.asg_mobprog.model;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;


class PokemonSprites {

    @SerializedName("front_default")
    public String frontDefault;

    // Add more sprite URLs as needed

    public String getFrontDefault() {
        return frontDefault;
    }
}

public class Pokemon {
    @SerializedName("name")
    public String name;

    @SerializedName("height")
    public int height;

    @SerializedName("sprites")
    public PokemonSprites sprites; // Assume you have a PokemonSprites class

    @SerializedName("weight")
    public int weight;

    // No-argument constructor for Gson
    public Pokemon() {
    }


    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getSprites() {
        return sprites.getFrontDefault();
    }

    // Add getter methods for other fields as needed
}
