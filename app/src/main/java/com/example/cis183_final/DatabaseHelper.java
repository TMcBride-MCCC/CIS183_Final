package com.example.cis183_final;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
    private static final String pantryIngredients_table_name = "PantryIngredients";
    private static final String units_table_name = "Units";

    public DatabaseHelper(Context c)
    {
        super (c, database_name, null, 11);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + users_table_name + " (" +
                "userID integer primary key autoincrement not null, " +
                "pantryID integer, " +
                "username varchar(50), " +
                "password varchar(50), " +
                "fName varchar(50), " +
                "lName varchar(50), " +
                "email varchar(50), " +
                "foreign key (pantryID) references " + pantry_table_name + " (pantryID)" +
                ");");
        db.execSQL("CREATE TABLE " + pantry_table_name + " (" +
                "pantryID integer primary key autoincrement not null, " +
                "houseName " +
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

        db.execSQL("CREATE TABLE " + pantryIngredients_table_name + " (" +
                "pantryIngredientID integer primary key autoincrement not null, " +
                "pantryID integer, " +
                "ingredientID integer, " +
                "pantryIngredientStock integer, " +
                "pantryIngredientBuyTrigger integer, " +
                "pantryIngredientUnitID integer, " +
                "foreign key (pantryID) references " + pantry_table_name + " (pantryID), " +
                "foreign key (ingredientID) references " + ingredients_table_name + " (ingredientID), " +
                "foreign key (pantryIngredientUnitID) references " + units_table_name + " (unitID) " +
                ");");

        db.execSQL("CREATE TABLE " + units_table_name + " (" +
                "unitID integer primary key autoincrement not null, " +
                "unitName varchar(50), " +
                "unitAbbv varchar(10)" +
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
        db.execSQL("DROP TABLE IF EXISTS " + pantryIngredients_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + units_table_name + ";");

        //Recreate Tables
        onCreate(db);
    }

    //Getter functions for table names
    public String getUserDbName()
    {
        return users_table_name;
    }

    public String getPantryDbName()
    {
        return pantry_table_name;
    }

    public String getRecipeDbName()
    {
        return recipes_table_name;
    }

    public String getMealTimeDbName()
    {
        return mealTime_table_name;
    }

    public String getIngredientsDbName()
    {
        return ingredients_table_name;
    }

    public String getCategoryDbName()
    {
        return category_table_name;
    }

    public String getRecipeIngredientsDbName()
    {
        return recipeIngredients_table_name;
    }

    public String getPantryIngredientsDbName()
    {
        return pantryIngredients_table_name;
    }

    public String getUnitsDbName()
    {
        return units_table_name;
    }

    //Function to initialize all tables with dummy data
    public void initAllTables()
    {
        initUsers();
        //Log.d("initAllTables: ", "Total users in db: " + countTableRecords(users_table_name));
        initPantries();
        //Log.d("initAllTables: ", "Total pantries in db: " + countTableRecords(pantry_table_name));
        initRecipes();
        //Log.d("initAllTables: ", "Total recipes in db: " + countTableRecords(recipes_table_name));
        initMealTimes();
        //Log.d("initAllTables: ", "Total mealTimes in db: " + countTableRecords(mealTime_table_name));
        initIngredients();
        //Log.d("initAllTables: ", "Total ingredients in db: " + countTableRecords(ingredients_table_name));
        initCategories();
        //Log.d("initAllTables: ", "Total categories in db: " + countTableRecords(category_table_name));
        initRecipeIngredients();
        //Log.d("initAllTables: ", "Total recipeIngredients in db: " + countTableRecords(recipeIngredients_table_name));
        initPantryIngredients();
        //Log.d("initAllTables: ", "Total pantryIngredients in db: " + countTableRecords(pantryIngredients_table_name));
        initUnits();
        //Log.d("initAllTables: ", "Total units in db: " + countTableRecords(units_table_name));
    }

    //Initialize user table with dummy data
    private void initUsers()
    {
        //If there are no records in the table currently....
        if (countTableRecords(users_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data
            db.execSQL("INSERT INTO " + users_table_name + " (pantryID, username, password, fName, lName, email) VALUES ('1', 'tmcbride', 'password1', 'Tyler', 'McBride', 'tyler@email.com');");
            db.execSQL("INSERT INTO " + users_table_name + " (pantryID, username, password, fName, lName, email) VALUES ('1', 'mphillips', 'password2', 'Mara', 'Phillips', 'mara@email.com');");
            db.execSQL("INSERT INTO " + users_table_name + " (pantryID, username, password, fName, lName, email) VALUES ('2', 'GoodBoy', 'password3', 'Bear', 'Doggo', 'bear@email.com');");
            db.execSQL("INSERT INTO " + users_table_name + " (pantryID, username, password, fName, lName, email) VALUES ('2', 'NotSoGoodBoy', 'password4', 'Apollo', 'Doggo', 'apollo@email.com');");

            //Close the database
            db.close();
        }
    }

    //Initialize pantry table with dummy data
    private void initPantries()
    {
        //If there are no records in the table currently....
        if (countTableRecords(pantry_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data
            db.execSQL("INSERT INTO " + pantry_table_name + " (houseName) VALUES ('McBride');");
            db.execSQL("INSERT INTO " + pantry_table_name + " (houseName) VALUES ('Pittie Committee');");

            //Close the database
            db.close();
        }
    }

    //Initialize recipe table with dummy data
    private void initRecipes()
    {
        //If there are no records in the table currently....
        if (countTableRecords(recipes_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data
            //1 = breakfast | 2= lunch | 3 = dinner
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('1', 'Scrambled Eggs', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('1', 'Jumbo Scrambled Eggs', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('1', 'Pancakes', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('1', 'Breakfast Burritos (Bacon)', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('1', 'Breakfast Burritos (Sausage)', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('2', 'Grilled Cheese Sandwich', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('2', 'Chicken & Salami Sandwich', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('2', 'Chicken Sandwich', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('2', 'Chili Cheese Hotdogs', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('2', 'Chili Cheese Turkey Hotdogs', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('3', 'Tacos (Beef)', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('3', 'Tacos (Turkey)', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('3', 'Garlic Fried Chicken', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('3', 'Cheese Burgers (Beef)', 'Pretend directions are here', '0');");
            db.execSQL("INSERT INTO " + recipes_table_name + " (mealTimeID, recipeName, instructions, makeCount) VALUES ('3', 'Cheese Burgers (Turkey)', 'Pretend directions are here', '0');");


            //Close the database
            db.close();
        }
    }

    //Initialize meal time table with dummy data
    private void initMealTimes()
    {
        //If there are no records in the table currently....
        if (countTableRecords(mealTime_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data
            db.execSQL("INSERT INTO " + mealTime_table_name + " (mealTime) VALUES ('Breakfast');");
            db.execSQL("INSERT INTO " + mealTime_table_name + " (mealTime) VALUES ('Lunch');");
            db.execSQL("INSERT INTO " + mealTime_table_name + " (mealTime) VALUES ('Dinner');");

            //Close the database
            db.close();
        }
    }

    //Initialize ingredients table with dummy data
    private void initIngredients()
    {
        //If there are no records in the table currently....
        if (countTableRecords(ingredients_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data                                                                                                                     //ID
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Eggs', '1');");                            //1
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Milk', '2');");                            //2
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Flour', '12');");                          //3
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Baking Powder', '12');");                  //4
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Salt', '9');");                            //5
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('White Sugar', '9');");                     //6
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Brown Sugar', '9');");                     //7
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Butter', '2');");                          //8
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Bacon', '6');");                           //9
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Sausage', '6');");                         //10
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Vegetable Oil', '12');");                  //11
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Flour Tortillas', '8');");                 //12
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Salsa', '10');");                          //13
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Black Pepper', '9');");                    //14
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Shredded Cheddar', '2');");                //15
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Garlic Salt', '9');");                     //16
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Frozen Hash Browns', '4');");              //17
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Breakfast Sausage', '4');");               //18
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Bread', '8');");                           //19
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('American Cheese Slice', '2');");           //20
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Butter', '2');");                          //21
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Deli Chicken Slice', '5');");              //22
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Deli Salami Slice', '6');");               //23
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('PepperJack Cheese Slice', '2');");         //24
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Hotdog Bun', '8');");                      //25
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Pork Hotdog', '6');");                     //26
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Hormel Chilli (Beef)', '6');");            //27
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Turkey Hotdog', '5');");                   //28
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Hormel Chilli (Turkey)', '5');");          //29
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Ground Beef', '6');");                     //30
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Ground Turkey', '6');");                   //31
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Taco Seasoning', '9');");                  //32
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Shredded Mexican Cheese', '6');");         //33
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Lettuce (Head)', '4');");                  //34
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Taco Sauce', '10');");                     //35
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Garlic Powder', '9');");                   //36
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Paprika', '9');");                         //37
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Bread Crumbs', '8');");                    //38
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Chicken Breast', '5');");                  //39
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Hamburger Bun', '8');");                   //40
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Hamburger Seasoning', '9');");             //41
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Dog Food', '1');");                        //42
            db.execSQL("INSERT INTO " + ingredients_table_name + " (ingredientName, categoryID) VALUES ('Dog Treats', '1');");                      //43

            //Close the database
            db.close();
        }
    }

    //Initialize category table with dummy data
    private void initCategories()
    {
        //If there are no records in the table currently....
        if (countTableRecords(category_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data                                                                                                         //ID
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Misc');");                                       //1
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Dairy');");                                      //2
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Fruits');");                                     //3
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Vegetables');");                                 //4
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Poultry');");                                    //5
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Red Meat');");                                   //6
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Fish');");                                       //7
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Grains');");                                     //8
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Spices');");                                     //9
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Condiments');");                                 //10
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Beverages');");                                  //11
            db.execSQL("INSERT INTO " + category_table_name + " (categoryName) VALUES ('Baking');");                                     //12

            //Close the database
            db.close();
        }
    }

    //Initialize recipe ingredients table with dummy data
    private void initRecipeIngredients()
    {
        //If there are no records in the table currently....
        if (countTableRecords(recipeIngredients_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data
            //1-5 = Breakfast
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('1', '1', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('1', '2', '1', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('2', '1', '16', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('2', '2', '4', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '3', '1 1/2', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '4', '3 1/2', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '6', '1', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '5', '1/4', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '2', '1 1/4', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '8', '3', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('3', '1', '1', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '1', '10', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '9', '12', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '2', '3', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '5', '1/4', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '11', '3', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '17', '4', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '16', '1/2', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '14', '1/4', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '12', '10', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '15', '1 1/2', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('4', '13', '1', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '1', '10', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '18', '12', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '2', '3', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '5', '1/4', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '11', '3', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '17', '4', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '16', '1/2', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '14', '1/4', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '12', '10', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '15', '1 1/2', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + " (recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('5', '13', '1', '4');");
            //6-10 = Lunch
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('6', '19', '2', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('6', '20', '2', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('6', '21', '3', '3');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('7', '22', '8', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('7', '23', '8', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('7', '24', '1', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('8', '22', '8', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('8', '24', '1', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('9', '25', '2', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('9', '26', '2', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('9', '27', '8', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('9', '15', '2', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('10', '25', '2', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('10', '28', '2', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('10', '29', '8', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('10', '15', '2', '5');");
            //11-15 = Dinner
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('11', '30', '1', '6');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('11', '32', '1', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('11', '12', '8', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('11', '33', '8', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('11', '34', '1', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('11', '35', '8', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('12', '31', '1', '6');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('12', '32', '1', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('12', '12', '8', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('12', '33', '8', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('12', '34', '1', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('12', '35', '8', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '36', '2', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '14', '1', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '5', '1', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '37', '1', '2');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '38', '1/2', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '3', '1', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '2', '1/2', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '1', '1', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '39', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('13', '1', '1', '4');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('14', '40', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('14', '30', '1', '6');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('14', '20', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('14', '41', '.2', '5');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('15', '40', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('15', '31', '1', '6');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('15', '20', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('15', '41', '.2', '5');");


            //Close the database
            db.close();
        }
    }

    //Initialize pantry ingredients table with dummy data
    private void initPantryIngredients()
    {
        //If there are no records in the table currently....
        if (countTableRecords(pantryIngredients_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '1', '12', '6', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '2', '16', '8', '4');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '3', '16', '8', '4');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '5', '4', '4', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '6', '181', '36', '3');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '7', '33', '8', '3');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '8', '8', '8', '3');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '11', '32', '16', '3');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '12', '10', '4', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '14', '4', '4', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '15', '16', '4', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '17', '16', '4', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '18', '16', '12', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '19', '20', '5', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '20', '16', '4', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '22', '24', '8', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '23', '4', '4', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '24', '10', '8', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '25', '2', '0', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '28', '10', '4', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '29', '15', '0', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '31', '5', '1', '6');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '32', '2', '1', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '40', '8', '4', '1');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('1', '41', '2', '.5', '5');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('2', '42', '4', '2', '6');");
            db.execSQL("INSERT INTO " + pantryIngredients_table_name + " (pantryID, ingredientID, pantryIngredientStock, pantryIngredientBuyTrigger, pantryIngredientUnitID) VALUES ('2', '43', '10', '20', '1');");

            //Close the database
            db.close();
        }
    }

    //Initialize units table with dummy data
    private void initUnits()
    {
        //If there are no records in the table currently....
        if (countTableRecords(units_table_name) == 0)
        {
            //Get a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert dummy data                                                                                             //ID
            db.execSQL("INSERT INTO " + units_table_name + " (unitName, unitAbbv) VALUES ('Each', 'ea');");                  //1
            db.execSQL("INSERT INTO " + units_table_name + " (unitName, unitAbbv) VALUES ('Teaspoon', 'tsp');");             //2
            db.execSQL("INSERT INTO " + units_table_name + " (unitName, unitAbbv) VALUES ('Tablespoon', 'tbsp');");          //3
            db.execSQL("INSERT INTO " + units_table_name + " (unitName, unitAbbv) VALUES ('Cup', 'cup');");                  //4
            db.execSQL("INSERT INTO " + units_table_name + " (unitName, unitAbbv) VALUES ('Ounce', 'oz');");                 //5
            db.execSQL("INSERT INTO " + units_table_name + " (unitName, unitAbbv) VALUES ('Pound', 'lb');");                 //6

            //Close the database
            db.close();
        }
    }

    //Function used to count the records in a table
    public int countTableRecords(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        db.close();

        return numRows;
    }

    //==============================================================================================================================================
    //                                                                  Pantries
    //==============================================================================================================================================

    //Function to get all household names to fill sign up spinner
    public ArrayList<String> getHouseNames()
    {
        //Make a new array list
        ArrayList<String> households = new ArrayList<>();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query to run
        String selectHouseName = "SELECT houseName FROM " + pantry_table_name;

        Cursor cursor = db.rawQuery(selectHouseName,null);

        if (cursor != null)
        {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++)
            {
                households.add(cursor.getString(0));

                cursor.moveToNext();
            }
        }

        db.close();
        return households;
    }

    //Function to create a new pantry if the user does not join an existing household
    public void createNewPantry(String houseNameThatWasPassed)
    {
        //Get a writeable version of the database
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + pantry_table_name + " (houseName) VALUES ('" + houseNameThatWasPassed + "');");

        db.close();

        Log.d("House created", "Housename: " + houseNameThatWasPassed);
    }

    public int getPantryId(String houseNameThatWasPassed)
    {
        int pantryId = -1;

        if(houseNameExists(houseNameThatWasPassed))
        {
            //Get readable database copy
            SQLiteDatabase db = this.getReadableDatabase();

            //Query to run
            String selectPantryId = "SELECT pantryID FROM " + pantry_table_name + " WHERE houseName = '" + houseNameThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectPantryId,null);

            if (cursor !=null)
            {
                cursor.moveToFirst();
                pantryId = cursor.getInt(0);
            }

            db.close();
        }
        else
        {
            Log.d("DATABASE getPantryId", "There is no houseName matching this name: " + houseNameThatWasPassed);
        }

        return pantryId;
    }

    public boolean houseNameExists(String houseNameThatWasPassed)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkHouseName = "SELECT count(houseName) FROM " + pantry_table_name + " WHERE houseName = '" + houseNameThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkHouseName, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //==============================================================================================================================================
    //                                                                  Users
    //==============================================================================================================================================

    //Function used to sign up a user
    public void signUpUser(User userThatWasPassed)
    {
        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Insert statement to insert the user into the database
        db.execSQL("INSERT INTO " + users_table_name + " (username, password, fName, lname, email)" +
                    "VALUES ('" + userThatWasPassed.getUsername() + "','" + userThatWasPassed.getPassword() + "','" + userThatWasPassed.getfName() + "','" + userThatWasPassed.getlName() + "','" + userThatWasPassed.getEmail() + "');");

        db.close();
    }

    //Function used to log in user
    public String verifyUsersPassword(String usernameThatWasPassed, String passwordThatWasPassed)
    {
        String password = "";

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectPassword = "SELECT password FROM " + users_table_name + " WHERE username = '" + usernameThatWasPassed + "';";

        Cursor cursor = db.rawQuery(selectPassword,null);

        if (cursor !=null && cursor.moveToFirst())
        {
            cursor.moveToFirst();
            password = cursor.getString(0);
        }
        else
        {
            Log.d("DATABASE verifyUsersPassword()","Cursor came back null");
        }

        return password;
    }

    public ArrayList<String> getAllUsernames()
    {
        //Make a new array list
        ArrayList<String> listOfUsernames = new ArrayList<>();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query to run
        String selectUsername = "SELECT username FROM " + users_table_name;

        Cursor cursor = db.rawQuery(selectUsername,null);

        if (cursor != null)
        {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++)
            {
                listOfUsernames.add(cursor.getString(0));

                cursor.moveToNext();
            }
        }

        db.close();
        return listOfUsernames;
    }

    public ArrayList<String> getAllEmails()
    {
        ArrayList<String> listOfEmails = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectEmail = "SELECT email FROM " + users_table_name;

        Cursor cursor = db.rawQuery(selectEmail,null);

        if (cursor != null)
        {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++)
            {
                listOfEmails.add(cursor.getString(0));

                cursor.moveToNext();
            }
        }

        db.close();
        return listOfEmails;
    }

    public String getUserFName(String usernameThatWasPassed)
    {
        String fName = "";

        //Get readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectFName = "SELECT fName FROM " + users_table_name + " WHERE username = '" + usernameThatWasPassed + "';";

        //Run query
        Cursor cursor = db.rawQuery(selectFName,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            fName = cursor.getString(0);
        }
        else
        {
            Log.d("DATABASE getUserFName()","Cursor is null");
        }

        //Close db
        db.close();

        return fName;
    }

    public String getUserLName(String usernameThatWasPassed)
    {
        String lName = "";

        //Get readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectLName = "SELECT lName FROM " + users_table_name + " WHERE username = '" + usernameThatWasPassed + "';";

        //Run query
        Cursor cursor = db.rawQuery(selectLName,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            lName = cursor.getString(0);
        }
        else
        {
            Log.d("DATABASE getUserLName()","Cursor is null");
        }

        //Close db
        db.close();

        return lName;
    }

    public String getUserEmail(String usernameThatWasPassed)
    {
        String email = "";

        //Get readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectEmail = "SELECT email FROM " + users_table_name + " WHERE username = '" + usernameThatWasPassed + "';";

        //Run query
        Cursor cursor = db.rawQuery(selectEmail,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            email = cursor.getString(0);
        }
        else
        {
            Log.d("DATABASE getUserEmail()","Cursor is null");
        }

        //Close db
        db.close();

        return email;
    }

    public int getUserPantryId(String usernameThatWasPassed)
    {
        int pantryId = -1;

        //Get readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectPantryId = "SELECT pantryID FROM " + users_table_name + " WHERE username = '" + usernameThatWasPassed + "';";

        //Run query
        Cursor cursor = db.rawQuery(selectPantryId,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            pantryId = cursor.getInt(0);
        }
        else
        {
            Log.d("DATABASE getUserPantryId()","Cursor is null");
        }

        //Close db
        db.close();

        return pantryId;
    }

    public String getUserPantryHouseName(int pantryIdThatWasPassed)
    {
        String houseName = "";

        //Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Query string
        String selectHouseName = "SELECT houseName FROM " + pantry_table_name + " WHERE pantryID = '" + pantryIdThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(selectHouseName,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            houseName = cursor.getString(0);
        }
        else
        {
            Log.d("DATABASE getUserPantryHouseName()","Cursor came back null");
        }

        return houseName;
    }

    //==============================================================================================================================================
    //                                                                  RECIPES
    //==============================================================================================================================================

    //Function used to copy recipe data from database to ArrayList
    public void fillRecipeArrayList()
    {
        //Delete the recipes from the ArrayList to prevent duplications
        RecipeList.getInstance().getRecipes().clear();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query to pull all recipes from recipe table
        String selectQuery = "SELECT * FROM " + recipes_table_name;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor !=null)
        {
            //Move to first row
            cursor.moveToFirst();

            //For loop to move through the entire table
            for (int i = 0; i < cursor.getCount(); i++)
            {
                //Make a new recipe memory chunk
                Recipe recipe = new Recipe();

                //Set the info
                recipe.setMealTimeId(cursor.getInt(1));
                recipe.setRecipeName(cursor.getString(2));
                recipe.setInstructions(cursor.getString(3));
                recipe.setMakeCount(cursor.getInt(4));

                //Add the recipe data to RecipeList
                RecipeList.getInstance().addRecipe(recipe);

                //Debugging message
                //Log.d("DATABASE fillRecipeArrayList()", "Recipe " + (i +1) + ": " + recipe.getRecipeName());

                //Move the cursor
                cursor.moveToNext();
            }

            //Close the db
            db.close();
        }
        //Log for total added
        Log.d("fillRecipeArrayList() FINISHED", "Total Recipes added: " + RecipeList.getInstance().getRecipes().size());
    }

    public boolean recipeNameExists(String recipeNameThatWasPassed)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkRecipeName = "SELECT count(recipeName) FROM " + recipes_table_name + " WHERE recipeName = '" + recipeNameThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkRecipeName, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getRecipeId(String recipeNameThatWasPassed)
    {
        int recipeId = -1;

        if (recipeNameExists(recipeNameThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectRecipeId = "SELECT recipeID FROM " + recipes_table_name + " WHERE recipeName = '" + recipeNameThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectRecipeId,null);

            if (cursor !=null)
            {
                cursor.moveToFirst();
                recipeId = cursor.getInt(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getRecipeId(): ", "There is no recipeName matching this name: " + recipeNameThatWasPassed);
        }

        return recipeId;
    }

    public boolean recipeIdExists(int recipeIdThatWasPassed)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkRecipeId = "SELECT count(recipeID) FROM " + recipes_table_name + " WHERE recipeID = '" + recipeIdThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkRecipeId, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count !=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getNumRecipeIngredients(int recipeIdThatWasPassed)
    {
        int numIngredients = -1;

        if (recipeIdExists(recipeIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String countIngredients = "SELECT count(recipeIngredientID) FROM " + recipeIngredients_table_name + " WHERE recipeID = '" + recipeIdThatWasPassed + "';";

            Cursor cursor = db.rawQuery(countIngredients,null);

            cursor.moveToFirst();

            numIngredients = cursor.getInt(0);
        }
        else
        {
            Log.d("ERROR IN DATABASE getNumRecipeIngredients(): ", "There is no recipeID matching this ID: " + recipeIdThatWasPassed);
        }

        return numIngredients;
    }

    public int getNumRecipeIngredientsInPantry(int recipeIdThatWasPassed)
    {
        int numIngredients = -1;

        if (recipeIdExists(recipeIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String countIngredients = "SELECT count(*) FROM " + recipeIngredients_table_name +
                    " LEFT JOIN " + pantryIngredients_table_name +
                    " ON " + recipeIngredients_table_name + ".ingredientID = " + pantryIngredients_table_name + ".ingredientID" +
                    " WHERE " + recipeIngredients_table_name + ".recipeID = " + recipeIdThatWasPassed +
                    " AND " + pantryIngredients_table_name + ".pantryIngredientStock >= " + recipeIngredients_table_name + ".recipeIngredientQuantity;";

            Cursor cursor = db.rawQuery(countIngredients,null);

            cursor.moveToFirst();

            numIngredients = cursor.getInt(0);
        }
        else
        {
            Log.d("ERROR IN DATABASE getNumRecipeIngredients(): ", "There is no recipeID matching this ID: " + recipeIdThatWasPassed);
        }

        return numIngredients;
    }

    public String getMealTime(int mealTimeId)
    {
        String mealTime ="";

        if (mealTimeExists(mealTimeId))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectMealTime = "SELECT mealTime FROM " + mealTime_table_name + " WHERE mealTimeID = '" + mealTimeId + "';";

            Cursor cursor = db.rawQuery(selectMealTime, null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                mealTime = cursor.getString(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getMealTime(): ", "There is no mealTime matching this ID: " + mealTime);
        }

        return mealTime;
    }

    public boolean mealTimeExists(int mealTimeId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkMealTimeId = "SELECT count(mealTimeID) FROM " + mealTime_table_name + " WHERE mealTimeID = '" + mealTimeId + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkMealTimeId, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count !=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //==============================================================================================================================================
    //                                                                  Pantry Ingredients
    //==============================================================================================================================================

    //Function used to copy all pantryIngredient data from database to ArrayList
    public void fillPantryIngredientsArrayList()
    {
        //Delete the recipes from the ArrayList to prevent duplications
        PantryIngredientList.getInstance().getPantryIngredients().clear();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query to pull all pantry ingredients from PantryIngredients table
        String selectQuery = "SELECT * FROM " + pantryIngredients_table_name;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor !=null)
        {
            //Move to first row
            cursor.moveToFirst();

            //For loop to move through the entire table
            for (int i = 0; i < cursor.getCount(); i++)
            {
                //Make a new recipe memory chunk
                PantryIngredient ingredient = new PantryIngredient();

                //Set the info
                ingredient.setPantryId(cursor.getInt(1));
                ingredient.setIngredientId(cursor.getInt(2));
                ingredient.setPantryIngredientStock(cursor.getInt(3));
                ingredient.setPantryIngredientBuyTrigger(cursor.getInt(4));
                ingredient.setPantryIngredientUnitId(cursor.getInt(5));

                //Add the ingredient data to PantryIngredientList
                PantryIngredientList.getInstance().addPantryIngredient(ingredient);

                //Debugging message
                //Log.d("DATABASE fillPantryIngredientsArrayList()", "Ingredient " + (i +1) + ": " + .getRecipeName());
                //Log.d("DATABASE fillPantryIngredientsArrayList()", "Ingredient " + (i +1) + " added");

                //Move the cursor
                cursor.moveToNext();
            }

            //Close the db
            db.close();
        }
        //Log for total added
        Log.d("fillPantryIngredientsArrayList() FINISHED", "Total Recipes added: " + PantryIngredientList.getInstance().getPantryIngredients().size());
    }

    //Function used to copy pantryIngredient data from database to ArrayList based on the users pantryId
    public void fillPantryIngredientsArrayListGivenUserPantryId(int pantryIdThatWasPassed)
    {
        //Delete the recipes from the ArrayList to prevent duplications
        PantryIngredientList.getInstance().getPantryIngredients().clear();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query to pull all pantry ingredients from PantryIngredients table
        String selectQuery = "SELECT * FROM " + pantryIngredients_table_name + " WHERE pantryID = '" + pantryIdThatWasPassed + "';";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor !=null)
        {
            //Move to first row
            cursor.moveToFirst();

            //For loop to move through the entire table
            for (int i = 0; i < cursor.getCount(); i++)
            {
                //Make a new recipe memory chunk
                PantryIngredient ingredient = new PantryIngredient();

                //Set the info
                ingredient.setPantryId(cursor.getInt(1));
                ingredient.setIngredientId(cursor.getInt(2));
                ingredient.setPantryIngredientStock(cursor.getInt(3));
                ingredient.setPantryIngredientBuyTrigger(cursor.getInt(4));
                ingredient.setPantryIngredientUnitId(cursor.getInt(5));

                //Add the ingredient data to PantryIngredientList
                PantryIngredientList.getInstance().addPantryIngredient(ingredient);

                //Debugging message
                //Log.d("DATABASE fillPantryIngredientsArrayList()", "Ingredient " + (i +1) + ": " + .getRecipeName());
                //Log.d("DATABASE fillPantryIngredientsArrayList()", "Ingredient " + (i +1) + " added");

                //Move the cursor
                cursor.moveToNext();
            }

            //Close the db
            db.close();
        }
        //Log for total added
        Log.d("fillPantryIngredientsArrayList() FINISHED", "Total Recipes added: " + PantryIngredientList.getInstance().getPantryIngredients().size());
    }

    public boolean ingredientIdExists(int ingredientIdThatWasPassed)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkIngredientId = "SELECT count(ingredientID) FROM " + ingredients_table_name + " WHERE ingredientID = '" + ingredientIdThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkIngredientId, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count !=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getIngredientName(int ingredientIdThatWasPassed)
    {
        String ingredientName ="";

        if (ingredientIdExists(ingredientIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectIngredientName = "SELECT ingredientName FROM " + ingredients_table_name + " WHERE ingredientID = '" + ingredientIdThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectIngredientName, null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                ingredientName = cursor.getString(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getIngredientName(): ", "There is no ingredientID matching this ID: " + ingredientIdThatWasPassed);
        }

        return ingredientName;
    }

    public int getIngredientCategoryID(int ingredientIdThatWasPassed)
    {
        int categoryId = 10;

        if (ingredientIdExists(ingredientIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectCategoryId = "SELECT categoryID FROM " + ingredients_table_name + " WHERE ingredientID = '" + ingredientIdThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectCategoryId, null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                categoryId = cursor.getInt(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getIngredientCategoryID(): ", "There is no ingredientID matching this ID: " + ingredientIdThatWasPassed);
        }

        return categoryId;
    }

    public boolean categoryIdExists(int categoryIdThatWasPassed)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkCategoryId = "SELECT count(categoryID) FROM " + category_table_name + " WHERE categoryID = '" + categoryIdThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkCategoryId, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count !=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getIngredientCategoryName(int categoryIdThatWasPassed)
    {
        String categoryName = "";

        if (categoryIdExists(categoryIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectCategoryName = "SELECT categoryName FROM " + category_table_name + " WHERE categoryID = '" + categoryIdThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectCategoryName, null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                categoryName = cursor.getString(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getIngredientCategoryName(): ", "There is no categoryID matching this ID: " + categoryIdThatWasPassed);
        }

        return categoryName;
    }

    public int getIngredientStock(int ingredientIdThatWasPassed)
    {
        int stock = 999;

        if (ingredientIdExists(ingredientIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectStock = "SELECT pantryIngredientStock FROM " + pantryIngredients_table_name + " WHERE ingredientID = '" + ingredientIdThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectStock, null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                stock = cursor.getInt(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getIngredientStock(): ", "There is no ingredientID matching this ID: " + ingredientIdThatWasPassed);
        }
        return stock;
    }

    public boolean unitIdExists(int unitIdThatWasPassed)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //String for the database query
        String checkUnitId = "SELECT count(unitID) FROM " + units_table_name + " WHERE unitID = '" + unitIdThatWasPassed + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkUnitId, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        if (count !=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getUnitAbbv(int unitIdThatWasPassed)
    {
        String unitAbbv = "";

        if (unitIdExists(unitIdThatWasPassed))
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectUnitAbbv = "SELECT unitAbbv FROM " + units_table_name + " WHERE unitID = '" + unitIdThatWasPassed + "';";

            Cursor cursor = db.rawQuery(selectUnitAbbv, null);

            if (cursor != null)
            {
                cursor.moveToFirst();
                unitAbbv = cursor.getString(0);
            }

            db.close();
        }
        else
        {
            Log.d("ERROR IN DATABASE getUnitAbbv(): ", "There is no unitID matching this ID: " + unitIdThatWasPassed);
        }

        return unitAbbv;
    }
}
