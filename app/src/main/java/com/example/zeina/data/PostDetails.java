package com.example.zeina.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class PostDetails  extends AppCompatActivity {

    ArrayList<String> comm = new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        RecyclerView comment = findViewById(R.id.comment_area);
        fill_comment();
        LinearLayoutManager x = new LinearLayoutManager(this);
        x.setOrientation(LinearLayoutManager.VERTICAL);
        comment.setLayoutManager(x);
        CommentAdapter commentAdapter = new CommentAdapter(this, comm);
        comment.setAdapter(commentAdapter);
    }

    void fill_comment(){
        String txt="Hello";
        for(int x=0;x<5;x++){
            comm.add(txt);
        }
    }
}
