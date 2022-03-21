package com.projectwebservice.helper;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super("User with this Username is already there in DB !! Try with again");
    }
    public UserFoundException(String msg){
        super(msg);
    }
}
