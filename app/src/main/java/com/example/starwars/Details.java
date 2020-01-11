package com.example.starwars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.example.starwars.SaveActivity.mypref;

public class Details extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView aff;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencess;
    Button save;
    Button retrieve;
    TextView gender;
    TextView height;
    TextView birthPlace;
    TextView eyeColor;
    TextView app;
    TextView masters;
    TextView destroyed;
    TextView created;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        image = (ImageView) findViewById(R.id.image1);
        name = findViewById(R.id.name);
        aff = findViewById(R.id.affiliations);
        gender = findViewById(R.id.gender);
        height = findViewById(R.id.height);
        birthPlace = findViewById(R.id.birth);
        eyeColor = findViewById(R.id.eyeColor);
        app = findViewById(R.id.apprentices);
        masters = findViewById(R.id.masters);
        destroyed = findViewById(R.id.destroyed);
        created = findViewById(R.id.created);


        save = findViewById(R.id.save);
        final Intent intent = getIntent();
        name.setText("Name:" + intent.getStringExtra("name"));
        if (intent.getStringExtra("name") != null)
            gender.setText("Gender:" + intent.getStringExtra("gender"));
        if (intent.getStringExtra("height") != null)
            height.setText("Height:" + intent.getStringExtra("height") + "m");
        if (intent.getStringExtra("eyeColor") != null)
            eyeColor.setText("Eye Color:" + intent.getStringExtra("eyeColor"));
        if (intent.getStringExtra("birthPlace") != null)
            birthPlace.setText("BirthPlace:" + intent.getStringExtra("birthPlace"));
        if (intent.getStringExtra("wiki") != null)
            created.setText("Wiki:" + intent.getStringExtra("wiki"));
        if (intent.getStringExtra("homeWorld") != null)
            destroyed.setText("Home world:" + intent.getStringExtra("homeWorld"));
        List<String> mList = intent.getStringArrayListExtra("aff");
        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .into(image);
        if (mList != null) {
            String affil = "Affiliations:" + "\n";
            for (int i = 0; i < mList.size(); i++) {

                affil = affil + mList.get(i) + "\n";
            }
            aff.setText(affil);
        }

        List<String> mList1 = intent.getStringArrayListExtra("app");
        if (mList1 != null) {

            String apprentices = "Apprentices:" + "\n";
            for (int i = 0; i < mList1.size(); i++) {

                apprentices = apprentices + mList1.get(i) + "\n";
            }
            app.setText(apprentices);
        }
        List<String> mList2 = intent.getStringArrayListExtra("mast");
        if (mList2 != null) {


            String master = "Masters:" + "\n";
            for (int i = 0; i < mList2.size(); i++) {

                master = master + mList2.get(i) + "\n";
            }
            masters.setText(master);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("com.example.starwar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int i = 0, j = 0;
                String[] saved = new String[2111];
                Map<String, ?> keys = sharedPreferences.getAll();
                for (Map.Entry<String, ?> entry : keys.entrySet()) {

                    saved[i++] = entry.getValue().toString();
                    System.out.println(saved[j++]);
                }

                sharedPreferencess = getSharedPreferences("other", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferencess.edit();
                Set<String> set = new HashSet<>(Arrays.asList(saved));
                set.remove(null);
                System.out.println(set);
                edit.putStringSet("arraySaved", set);

                edit.commit();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                String currentTime = sdf.format(new Date());

                editor.putString(currentTime, intent.getStringExtra("name"));
                editor.commit();

            }


        });

    }



}
