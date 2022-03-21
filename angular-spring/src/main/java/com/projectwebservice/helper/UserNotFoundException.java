package com.projectwebservice.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User with this Username not found is database !!");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
