package com.example.starwars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
public RecyclerView recyclerView;
public HeroAdapter mAdapter;
public ArrayList<Hero> mList;
public   String URL_DATA="https://awesome-star-wars-api.herokuapp.com/characters";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchData pro=new FetchData();
        pro.execute();
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mList=new ArrayList<>();






//        loadRecyclerViewData();
















    }

//    public void loadRecyclerViewData(){
//        ProgressDialog progressDialog=new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    JSONArray
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//
//    });
//        RequestQueue  requestQueue= Volley.newRequestQueue(this);
//
//
//
//
//    }




     public class FetchData extends AsyncTask<Void,Void,Void>{
         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);
             mAdapter=new HeroAdapter(mList,getApplicationContext());
             recyclerView.setAdapter(mAdapter);
         }

         private String mString;

         @Override
         protected Void doInBackground(Void... voids) {
             HttpURLConnection urlConnection=null;
             BufferedReader reader=null;
             Uri  uri=Uri.parse(getString(R.string.war_api));
             URL url;
             System.out.println("aaaaa");
             try{

                 url=new URL(uri.toString());
                 urlConnection=(HttpURLConnection)url.openConnection();
                 urlConnection.setRequestMethod("GET");
                 urlConnection.setRequestProperty("user-key","");
                 urlConnection.connect();

                 InputStream inputStream=urlConnection.getInputStream();
                 StringBuffer buffer=new StringBuffer();
                 if(inputStream==null){
                     return null;

                 }
                 reader=new BufferedReader(new InputStreamReader(inputStream));

                 String line;
                 while((line=reader.readLine())!=null){
                     buffer.append(line+ "\n");
                 }
                 if(buffer.length()==0){
                     return null;
                 }

                 JSONObject jsonObject=new JSONObject(buffer.toString());

                 System.out.println(jsonObject.toString());
                 JSONArray array=jsonObject.getJSONArray("data");


                 for(int i=0;i<array.length();i++){
                     JSONObject o=array.getJSONObject(i);
                     Hero hero=new Hero(o.getString("name"),o.getString("id"),o.getString("image"));

System.out.println(o.getString("name"));
                     mList.add(hero);

                 }









             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             finally {


             }
             return null;
         }
     }

}
