package com.example.zeina.data;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class NewsFeed extends AppCompatActivity {

    public  ArrayList<Post> dataarr = new ArrayList<Post>();
    Bitmap[] pic_src = new Bitmap[5];
    int[] txt_src=new int[5];
    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    String TAG = "Success";
    String user_id="5afa2cc88024b6bbc5d01fad";
    //String user_id="5afa2de18024b6bbc5d01fb1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed);
        RecyclerView posts = findViewById(R.id.List_Of_Posts);
        Log.i("Entered news feed page", "onCreate: ");
        Button makepost = findViewById(R.id.makePost);
        Button pick_image = findViewById(R.id.getPic);
        Button home = findViewById(R.id.Home_Button);
        Button profile = findViewById(R.id.Profile_Button);
        Button Chat = findViewById(R.id.Chat_Button);
        posts_data(user_id);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        posts.setLayoutManager(llm);
        PostsAdapter adapter = new PostsAdapter(this,dataarr);
        posts.setAdapter(adapter);

        pick_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               pickImage();
            }
        });
        makepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePost();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posts_data(user_id);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), Chat.class);
                startActivityForResult(intent, 0);
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream inputStream=new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                inputStream = getBaseContext().getContentResolver().openInputStream(data.getData());
                //ImageView imageView = findViewById(R.id.imageView);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
               // imageView.setImageBitmap(bmp);
               //postimage.setImageBitmap(bmp);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
            File newfile = null;
            try {
                newfile = whenConvertingInProgressToFile_thenCorrect(inputStream);
            } catch (IOException e) {
                Log.i("File Convertion", "onActivityResult: " + e.toString());
            }
            //write here the code of posting image
            }
        }

    public void makePost(){
        EditText Post_text = findViewById(R.id.Post_Text);
        String Post_txt = Post_text.getText().toString();
        //send text
    }
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }


    public File whenConvertingInProgressToFile_thenCorrect(InputStream inputStream)
            throws IOException {
        File targetFile = new File("src/main/resources/targetFile.tmp");
        OutputStream outStream = new FileOutputStream(targetFile);

        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        return targetFile;
    }



    void posts_data(String user_id){
        AndroidNetworking.get("http://178.62.119.179:5000/api/post/getNewsFeed/"+user_id)
                .setTag("newsfeed")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            JSONObject arr = new JSONObject(response.toString());
                            String count= arr.getString("count");
                            int num_of_posts = Integer.parseInt(count);
                            if(num_of_posts==0){
                                String mess =response.getString("message");
                                Toast.makeText(getApplicationContext(),mess, LENGTH_SHORT).show();
                                return;
                            }

                            Log.i("postsResp", response.toString());
                            Log.i("posts_num", "onResponse: "+num_of_posts);

                            JSONArray posts = arr.getJSONArray("posts");
                            for (int i = 0; i < num_of_posts; i++)
                            {
                                Post post = new Post();
                                JSONObject postObj = posts.getJSONObject(i);
                                post.postID = postObj.getString("_id");
                               // post.userID = postObj.getString("user");
                                post.context = postObj.getString("text");
                                post.postPic = postObj.getString("image");

                                JSONObject userObj = postObj.getJSONObject("user");
                                post.userID=userObj.getString("_id");
                                post.userName=userObj.getString("name");
                                post.userPic=userObj.getString("profileImage");

                                JSONArray likes = postObj.getJSONArray("likes");
                                int num_of_likes = likes.length();
                                post.numOfLikes=num_of_likes;

                                for (int y=0;y<num_of_likes;y++){
                                    JSONObject likeObj = likes.getJSONObject(y);
                                    String liker_id = likeObj.getString("_id");
                                    post.likers_id.add(liker_id);
                                }

                                JSONArray comments = postObj.getJSONArray("comments");
                                int num_of_comments = comments.length();

                                Log.i("commentsNum", String.valueOf(num_of_comments));

                                for (int y=0;y<num_of_comments;y++){
                                    Comment comment =  new  Comment();
                                    JSONObject commentObj = comments.getJSONObject(y);
                                    comment.commentID= commentObj.getString("_id");
                                    comment.commentText = commentObj.getString("text");

                                    Log.i("comment", comment.commentText);
                                    JSONObject commentList = commentObj.getJSONObject("user");

                                    comment.commenterID = commentList.getString("_id");
                                    comment.commenterName = commentList.getString("name");
                                    comment.commenterProfilePic= commentList.getString("profileImage");

                                    post.post_comments.add(comment);
                                }
                                dataarr.add(post);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.i(TAG, "onError: "+error.toString());
                    }
                });
    }
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}




 /* void fill_txt(){
        int txt=R.string.Post_Content;
        for(int x=0;x<5;x++){
            txt_src[x]=txt;
        }
    }

    void fill_pic(){
            Bitmap d =drawableToBitmap(getResources().getDrawable(R.mipmap.comment));
            pic_src[0]=d;
            d =drawableToBitmap(getResources().getDrawable(R.mipmap.like));
            pic_src[1]=d;
            d =drawableToBitmap(getResources().getDrawable(R.mipmap.comment));
            pic_src[2]=d;
            d =drawableToBitmap(getResources().getDrawable(R.mipmap.like));
            pic_src[3]=d;
            d =drawableToBitmap(getResources().getDrawable(R.mipmap.comment));
            pic_src[4]=d;
    }

    void fill_Data(){
        for(int x=0;x<5;x++){

            Post post=new Post();
            post.picture=pic_src[x];
            post.context=getResources().getString(txt_src[x]);
            dat.add(post);

        }
    }*/