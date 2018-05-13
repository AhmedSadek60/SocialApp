package com.example.zeina.data;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable{

    Bitmap picture;
    String context;
    public String PersonName;
    String PersonPic;
    int picTest;

    public  Post(){}
    protected Post(Parcel in) {
//        picture = in.readParcelable(Bitmap.class.getClassLoader());
        context = in.readString();
        PersonName = in.readString();
        PersonPic = in.readString();
        picTest = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    void Post(Bitmap pic, String cont, String name, String profile_pic){
        picture=pic;
        context=cont;
        PersonName=name;
        PersonPic=profile_pic;
    }

    Bitmap getPicture(){
        return picture;
    }
    String getContext(){
        return context;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeParcelable(picture, i);
        parcel.writeString(context);
        parcel.writeString(PersonName);
        parcel.writeString(PersonPic);
        parcel.writeInt(picTest);
    }
}
