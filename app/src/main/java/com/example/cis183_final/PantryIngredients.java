package com.example.cis183_final;

import java.io.Serializable;

public class PantryIngredients implements Serializable
{
    int pantryId;
    int ingredientId;
    int pantryIngredientStock;
    int pantryIngredientBuyTrigger;
    int pantryIngredientUnitId;
    
    public PantryIngredients()
    {

    }

    public PantryIngredients(int pid, int iid, int s, int bt, int uid)
    {
        pantryId = pid;
        ingredientId = iid;
        pantryIngredientStock = s;
        pantryIngredientBuyTrigger = bt;
        pantryIngredientUnitId = uid;
    }

    //====================================================
    //      GETTERS
    //====================================================

    public int getPantryId()
    {
        return pantryId;
    }

    public int getIngredientId()
    {
        return ingredientId;
    }

    public int getPantryIngredientStock()
    {
        return pantryIngredientStock;
    }

    public int getPantryIngredientBuyTrigger()
    {
        return pantryIngredientBuyTrigger;
    }

    public int getPantryIngredientUnitId()
    {
        return pantryIngredientUnitId;
    }

    //====================================================
    //      SETTERS
    //====================================================

    public void setPantryId(int pantryId)
    {
        this.pantryId = pantryId;
    }

    public void setIngredientId(int ingredientId)
    {
        this.ingredientId = ingredientId;
    }

    public void setPantryIngredientStock(int pantryIngredientStock)
    {
        this.pantryIngredientStock = pantryIngredientStock;
    }

    public void setPantryIngredientBuyTrigger(int pantryIngredientBuyTrigger)
    {
        this.pantryIngredientBuyTrigger = pantryIngredientBuyTrigger;
    }

    public void setPantryIngredientUnitId(int pantryIngredientUnitId)
    {
        this.pantryIngredientUnitId = pantryIngredientUnitId;
    }
}
