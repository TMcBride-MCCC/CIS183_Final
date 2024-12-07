package com.example.cis183_final;

public class User
{
    private int pantryId;
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String email;

    public User()
    {

    }

    public User(int pid, String u, String p, String f, String l, String e)
    {
        pantryId = pid;
        username = u;
        password = p;
        lName = l;
        email = e;
    }

    //===================================
    //             GETTERS
    //===================================

    public int getPantryId()
    {
        return pantryId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getfName()
    {
        return fName;
    }

    public String getlName()
    {
        return lName;
    }

    public String getEmail()
    {
        return email;
    }

    //===================================
    //             SETTERS
    //===================================

    public void setPantryId(int pantryId)
    {
        this.pantryId = pantryId;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}

