package com.example.zeina.data;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

public class Messages extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void adapter(){
        DialogsList dialogsListView= findViewById(R.id.dialogsList);
       // DialogsList dialogs = findViewById(R.id.dialogsList);
   /*    DialogsListAdapter dialogsListAdapter = new DialogsListAdapter<>(dialogs, new ImageLoader() {
           @Override
           public void loadImage(ImageView imageView, String url) {
               //If you using another library - write here your way to load image
               Picasso.get().load(url).into(imageView);
           }
       });

       dialogsListView.setAdapter(dialogsListAdapter);*/
   }
}
