package com.example.zeina.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostDetails  extends AppCompatActivity {

    TextView userNameTV, postContentTV;
    ImageView userImg, postImg;

    String TAG = "Failed";
    ArrayList<Comment> comm = new ArrayList<Comment>();
    Post post = new Post();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        RecyclerView comment = findViewById(R.id.comment_area);
        post = (Post) getIntent().getParcelableExtra("post");
        comm = post.post_comments;

        userNameTV =findViewById(R.id.user_Name);
        userImg =findViewById(R.id.Pro_Pic);
        postContentTV =findViewById(R.id.Post_Text);
        postImg = findViewById(R.id.Post_Picture);

        userNameTV.setText(post.getUserName());
        Picasso.get().load( post.getUserPic()).placeholder(R.mipmap.like).into(userImg);
        postContentTV.setText(post.getContext());
        Picasso.get().load( post.getPostPic()).placeholder(R.mipmap.comment).into(postImg);


        LinearLayoutManager x = new LinearLayoutManager(this);
        x.setOrientation(LinearLayoutManager.VERTICAL);
        comment.setLayoutManager(x);
        CommentAdapter commentAdapter = new CommentAdapter(this, comm);
        comment.setAdapter(commentAdapter);

        Button Comment_Button = findViewById(R.id.Comment_Button);
        Comment_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post_data();
            }
        });
    }


    protected String Post_data() {

        EditText comment_text = findViewById(R.id.comment_field);
        String Comment_txt = comment_text.getText().toString();

        return null;
    }
}
