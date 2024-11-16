package com.example.cis183_final;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public String getUnitsDbName()
    {
        return units_table_name;
    }

    //Function to initialize all tables with dummy data
    public void initAllTables()
    {
        initUsers();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(users_table_name));
        initPantries();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(pantry_table_name));
        initRecipes();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(recipes_table_name));
        initMealTimes();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(mealTime_table_name));
        initIngredients();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(ingredients_table_name));
        initCategories();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(category_table_name));
        initRecipeIngredients();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(recipeIngredients_table_name));
        initUnits();
        Log.d("initAllTables: ", "Total users in db: " + countTableRecords(units_table_name));
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
            db.execSQL("INSERT INTO " + users_table_name + "(pantryID, username, password, fName, lName, email) VALUES ('1', 'tmcbride', 'password123', 'Tyler', 'McBride', 'tylersemail@email.com');");

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
            db.execSQL("INSERT INTO " + pantry_table_name + "(ingredientID, reocurring, stockQuantity, buyTrigger, unitID) VALUES ('1', '0', '12', '6', 'each');");

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
            db.execSQL("INSERT INTO " + recipes_table_name + "(mealTimeID, recipeName, instructions, makeCount) VALUES ('1', 'Scrambled Eggs', 'Pretend directions are here', '0');");

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
            db.execSQL("INSERT INTO " + mealTime_table_name + "(meaTime) VALUES ('Breakfast');");
            db.execSQL("INSERT INTO " + mealTime_table_name + "(meaTime) VALUES ('Lunch');");
            db.execSQL("INSERT INTO " + mealTime_table_name + "(meaTime) VALUES ('Dinner');");

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

            //Insert dummy data
            db.execSQL("INSERT INTO " + ingredients_table_name + "(ingredientName, categoryID) VALUES ('Eggs', '1');");
            db.execSQL("INSERT INTO " + ingredients_table_name + "(ingredientName, categoryID) VALUES ('Milk', '2');");

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

            //Insert dummy data
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Misc');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Dairy');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Fruits');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Vegetables');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Poultry');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Red Meat');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Fish');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Grains');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Spices');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Condiments');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Beverages');");
            db.execSQL("INSERT INTO " + category_table_name + "(categoryName) VALUES ('Baking');");

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
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('1', '1', '4', '1');");
            db.execSQL("INSERT INTO " + recipeIngredients_table_name + "(recipeID, ingredientID, recipeIngredientQuantity, recipeIngredientUnitID) VALUES ('1', '2', '1', '2');");

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

            //Insert dummy data
            db.execSQL("INSERT INTO " + units_table_name + "(unitName, unitAbbv) VALUES ('Each', 'ea');");
            db.execSQL("INSERT INTO " + units_table_name + "(unitName, unitAbbv) VALUES ('Teaspoon', 'tsp');");
            db.execSQL("INSERT INTO " + units_table_name + "(unitName, unitAbbv) VALUES ('Tablespoon', 'tbsp');");
            db.execSQL("INSERT INTO " + units_table_name + "(unitName, unitAbbv) VALUES ('Cup', 'cup');");
            db.execSQL("INSERT INTO " + units_table_name + "(unitName, unitAbbv) VALUES ('Ounce', 'oz');");
            db.execSQL("INSERT INTO " + units_table_name + "(unitName, unitAbbv) VALUES ('Pound', 'lb');");

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
}
