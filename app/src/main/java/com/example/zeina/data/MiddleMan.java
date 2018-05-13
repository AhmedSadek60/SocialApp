package com.amma.projectds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MiddleMan extends AppCompatActivity {

    TextView Test;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_man);
        Intent ahlnYaOrtega = getIntent();
        userid = ahlnYaOrtega.getStringExtra("_id").toString();
        Test = findViewById(R.id.Test);
        Test.setText(userid);
    }
}
