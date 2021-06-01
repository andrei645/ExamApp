package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int id;
    private String enunt;
    private String variante;
    private String variantaCorecta;

    @SerializedName("body")
    private String problem;



    public int getId() {
        return id;
    }

    public String getEnunt() {
        return enunt;
    }

    public String getVariante() {
        return variante;
    }

    public String getVarianta_corecta() {
        return variantaCorecta;
    }

    public String getProblem() {
        return problem;
    }
}
