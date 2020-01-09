package com.example.starwars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Details extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView aff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        image=(ImageView)findViewById(R.id.image);
        name=findViewById(R.id.name);
        aff=findViewById(R.id.affiliations);

        Intent intent=getIntent();
       name.setText(intent.getStringExtra("name"));
        List<String> mList=intent.getStringArrayListExtra("aff");
        String affi="";
        for(int i=0;i<mList.size();i++){

            affi=affi+mList.get(i)+"\n";
        }
        aff.setText(affi);
    }

}
