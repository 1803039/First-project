package com.example.foody.model;

public class user {
    private String Name;
    private String password;
     public user()
     {

     }

     public user(String name,String password1)
     {
         Name = name;
         password = password1;
     }
     public String getName()
     {
         return Name;
     }
     public void setName(String name)
     {
       Name = name;
     }
     public String getPassword()
     {
         return password;
     }

}
