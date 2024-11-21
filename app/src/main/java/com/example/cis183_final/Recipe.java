package com.example.cis183_final;

import java.io.Serializable;

public class Recipe implements Serializable
{
    int mealTimeId;
    String recipeName;
    String instructions;
    int makeCount;

    public Recipe()
    {

    }

    public Recipe(int mt, String n, String i, int mc)
    {
        mealTimeId = mt;
        recipeName = n;
        instructions = i;
        makeCount = mc;
    }

    //====================================================
    //      GETTERS
    //====================================================

    public int getMealTimeId()
    {
        return mealTimeId;
    }

    public String getRecipeName()
    {
        return recipeName;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public int getMakeCount()
    {
        return makeCount;
    }

    //====================================================
    //      SETTERS
    //====================================================

    public void setMealTimeId(int mealTimeId)
    {
        this.mealTimeId = mealTimeId;
    }

    public void setRecipeName(String recipeName)
    {
        this.recipeName = recipeName;
    }

    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }

    public void setMakeCount(int makeCount)
    {
        this.makeCount = makeCount;
    }
}
