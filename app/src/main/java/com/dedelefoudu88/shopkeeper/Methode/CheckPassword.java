package com.dedelefoudu88.shopkeeper.Methode;

import java.util.Hashtable;

/**
 * Created by dedel on 22/02/2017.
 */

public class CheckPassword  {

    private Hashtable<String,Userarg> user = new Hashtable();

    public CheckPassword()
    {
        user.put("admin",new Userarg("admin","admin@gmail.com"));
    }

    private class Userarg
    {
        public Userarg(String password,String email)
        {
            this.email = email;
            this.password = password;
        }
        public String password;
        public String email;
    }


    public boolean creatUser(String name, String mail, String password)
    {
        if (user.containsKey(name) == false)
        {
            return false;
        }
        user.put(name,new Userarg(mail,password));
        return true;
    }

    public boolean modifyUserName(String precName, String newName)
    {
        if (user.containsKey(precName) == false)
        {
            return false;
        }
        Userarg userarg;
        userarg = user.remove(precName);
        user.put(newName,userarg);

        return true;
    }

    public boolean modifyUserPassword(String Name, String password)
    {
        if (user.containsKey(Name) == false)
        {
            return false;
        }
        Userarg userarg;
        userarg = user.get(Name);
        userarg.password = password;
        return true;
    }

    public boolean modifyUserEmail(String Name, String email)
    {
        if (user.containsKey(Name) == false)
        {
            return false;
        }
        Userarg userarg;
        userarg = user.get(Name);
        userarg.email = email;
        return true;
    }

    public boolean checkPassword(String Name, String password)
    {

        if (user.containsKey(Name) == false)
        {
            return false;
        }
        Userarg userarg;
        userarg = user.get(Name);
        if (userarg.password.equals(password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
