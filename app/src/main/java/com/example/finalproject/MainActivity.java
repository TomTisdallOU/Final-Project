package com.example.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO -- see example of how to add a game in the addGame function.
        //TODO Tom to update user class to save users -- similar to how game works.


    }

    public void addGame(){
        GameDBHandler dbHandler = new GameDBHandler(this, null, null, 1);

        //I have to figure out how to properly increment ID -- should be unique
        int ID = 1;
        Game game = new Game();
        game.setGameID(ID);
        game.setHomeTeam("Detroit");
        game.setAwayTeam("Pittsburgh");
        Date date = new Date();
        game.setGameDate(date);
        dbHandler.addGame_Handler(game);
    }
}
