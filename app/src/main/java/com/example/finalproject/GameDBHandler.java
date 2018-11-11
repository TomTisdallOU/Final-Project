package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GameDBHandler extends SQLiteOpenHelper {
    //DB Info
    private static final int Database_Version = 1;
    private static final String Database_Name =  Resources.getSystem().getString(R.string.DB_Name);
    private static final String Table_Game = Resources.getSystem().getString(R.string.Game_Table_Name);
    private static final String Game_Column_ID = "GameID";
    private static final String Game_Column_HomeTeam = "GameHomeTeam";
    private static final String Game_Column_AwayTeam = "GameAwayTeam";
    private static final String Game_Column_GameDate = "GameGameDate";



    public GameDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_Name, factory, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Game_Table = "Create table " + Table_Game + "(" + Game_Column_ID + " Integer Primary Key ," +
                Game_Column_HomeTeam + " TEXT, " + Game_Column_AwayTeam + " TEXT, " + Game_Column_GameDate + " TEXT)";
        db.execSQL(Create_Game_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String loadGameHandler(){
        String result = "";
        String query = "Select * From " + Table_Game;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            int gameID = cursor.getInt(0);
            String homeTeam = cursor.getString(1);
            String awayTeam = cursor.getString(2);
            String gameDate = cursor.getString(3);
            result = gameID + " " + homeTeam + " " + awayTeam + " " + gameDate + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addGame_Handler(Game game){
        ContentValues values = new ContentValues();
        values.put(Game_Column_ID, game.getGameID());
        values.put(Game_Column_HomeTeam, game.getHomeTeam());
        values.put(Game_Column_AwayTeam, game.getAwayTeam());
        values.put(Game_Column_GameDate, game.getGameDate().toString());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Table_Game, null, values);
        db.close();

    }
    public Game findGameHandler(int gameID){
        String query = "Select * From " + Table_Game + " Where " + Game_Column_ID + " = " + gameID;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            game.setGameID(cursor.getInt(0));
            game.setHomeTeam(cursor.getString(1));
            game.setAwayTeam(cursor.getString(2));
            String gameDate = cursor.getString(3);
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                date = format.parse(gameDate);
                //System.out.println(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            game.setGameDate(date);
            cursor.close();
        } else{
            game = null;
        }
        db.close();
        return game;
    }

    public boolean deleteGameHandler(int gameID){
        boolean result = false;
        String query = "Select * From " + Table_Game + " Where " + Game_Column_ID + " = "  + gameID;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            game.setGameID(cursor.getInt(0));
            db.delete(Table_Game, Game_Column_ID + " = " , new String[] {String.valueOf(gameID)});
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }
    public boolean updateGameHandler(Game game){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(Game_Column_ID, game.getGameID());
        args.put(Game_Column_HomeTeam, game.getHomeTeam());
        args.put(Game_Column_AwayTeam, game.getAwayTeam());
        args.put(Game_Column_GameDate, game.getGameDate().toString());
        return db.update(Table_Game, args, Game_Column_ID + " = " + game.getGameID(), null) > 0;

    }





}
