package com.example.cis183_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "Fridge.db";
    private static final String users_table_name = "Users";
    private static final String pantry_table_name = "Pantry";
    private static final String recipes_table_name = "Recipes";
    private static final String mealTime_table_name = "MealTime";
    private static final String ingredients_table_name = "Ingredients";
    private static final String category_table_name = "Category";
    private static final String recipeIngredients_table_name = "RecipeIngredients";
    private static final String units_table_name = "Units";

    public DatabaseHelper(Context c)
    {
        super (c, database_name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + users_table_name + " (" +
                "userID integer primary key autoincrement not null, " +
                "pantryID integer, username varchar(50), " +
                "password varchar(50), fname varchar(50), " +
                "lname varchar(50), " +
                "email varchar(50), " +
                "foreign key (pantryID) references " + pantry_table_name + " (pantryID)" +
                ");");
        db.execSQL("CREATE TABLE " + pantry_table_name + " (" +
                "pantryID integer primary key autoincrement not null, " +
                "ingredientID integer, " +
                "reoccuring integer, " +
                "stockQuantity real, " +
                "buyTrigger integer, " +
                "reqAmount real, " +
                "unitID integer, " +
                "foreign key (ingredientID) references " + ingredients_table_name + " (ingredientID), " +
                "foreign key (unitID) references " + units_table_name + " (unitID)" +
                ");");
        db.execSQL("CREATE TABLE " + recipes_table_name + " (" +
                "recipeID integer primary key autoincrement not null, " +
                "mealTimeID integer, " +
                "recipeName varchar(50), " +
                "instructions text, " +
                "makeCount integer, " +
                "foreign key (mealTimeID) references " + mealTime_table_name + " (mealTimeID)" +
                ");");
        db.execSQL("CREATE TABLE " + mealTime_table_name + " (" +
                "mealTimeID integer primary key autoincrement not null, " +
                "mealTime varchar(50)" +
                ");");
        db.execSQL("CREATE TABLE " + ingredients_table_name + " (" +
                "ingredientID integer primary key autoincrement not null, " +
                "ingredientName varchar(50), " +
                "categoryID integer, " +
                "foreign key (categoryID) references " + category_table_name + " (categoryID)" +
                ");");
        db.execSQL("CREATE TABLE " + category_table_name + " (" +
                "categoryID integer primary key autoincrement not null, " +
                "categoryName varchar(50)" +
                ");");
        db.execSQL("CREATE TABLE " + recipeIngredients_table_name + " (" +
                "recipeIngredientID integer primary key autoincrement not null, " +
                "recipeID integer, " +
                "ingredientID integer, " +
                "recipeIngredientQuantity real, " +
                "recipeIngredientUnitID integer, " +
                "foreign key (recipeID) references " + recipes_table_name + " (recipeID), " +
                "foreign key (ingredientID) references " + ingredients_table_name + " (ingredientID), " +
                "foreign key (recipeIngredientUnitID) references " + units_table_name + " (unitID)" +
                ");");
        db.execSQL("CREATE TABLE " + units_table_name + " (" +
                "unitID integer primary key autoincrement not null, " +
                "unitName varchar(50), " +
                "abbv varchar(10)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //Delete Tables
        db.execSQL("DROP TABLE IF EXISTS " + users_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + pantry_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + recipes_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + mealTime_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ingredients_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + category_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + recipeIngredients_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + units_table_name + ";");

        //Recreate Tables
        onCreate(db);
    }
}
