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
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class NewsFeed extends AppCompatActivity {

    ArrayList<Post> dat = new ArrayList<Post>();
    Bitmap[] pic_src = new Bitmap[5];
    int[] txt_src=new int[5];
    private static final int PICK_PHOTO_FOR_AVATAR = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed);
        RecyclerView posts = findViewById(R.id.List_Of_Posts);
        Log.i("Entered news feed page", "onCreate: ");
        ImageView postimage = findViewById(R.id.postimage);
        Button makepost = findViewById(R.id.makePost);
        fill_txt();
        fill_pic();
        fill_Data();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        posts.setLayoutManager(llm);
        PostsAdapter adapter = new PostsAdapter(this,dat);
        posts.setAdapter(adapter);

        makepost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               pickImage();
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

    void fill_txt(){
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
