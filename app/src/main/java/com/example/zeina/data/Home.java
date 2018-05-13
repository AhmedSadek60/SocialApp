package com.amma.projectds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    String id;
    TextView shit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();

        id = intent.getStringExtra("_id").toString();
        shit = findViewById(R.id.shit);
        shit.setText(id);
        Log.d("2:", "onResponse: "+id);
        Intent yallabeena = new Intent(Home.this,AllMembers.class);
        startActivity(yallabeena);

    }
}
