package com.example.myapplication.model.levels;

public  class ScoreUpdate {
    private static int score;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        ScoreUpdate.score = score;
    }

    public static void updateScore() {
        ScoreUpdate.score++;
    }
}
