package com.amma.projectds;

import java.util.ArrayList;

public class User {
    String name, email, picture, mobile, ID, bio;
    ArrayList<User> following, followers;

    public User(String _name, String _email, String _picture, String _mobile
                , String _ID, String _bio, ArrayList<User> _following, ArrayList<User> _followers)
    {
        name = _name;
        email = _email;
        picture = _picture;
        mobile = _mobile;
        ID = _ID;
        bio = _bio;
        followers = _followers;
        following = _following;
    }

    public User(){}
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPicture() {
        return picture;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public String getID() {
        return ID;
    }

}
