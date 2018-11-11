package com.example.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO -- see exmample of how to add a game in the addGame function.
        //TODO Tom to update user class to save users -- similar to how game works.


    }

    public void addGame(){
        GameDBHandler dbHandler = new GameDBHandler(v.getContext(), null, null, 1);
        ++ID;
        Game game = new Game();
        game.setGameID(ID);
        game.setHomeTeam("Detroit");
        game.setAwayTeam("Pittsburgh");
        Date date = new Date();
        game.setGameDate(date);
        dbHandler.addGame_Handler(game);
    }
}
