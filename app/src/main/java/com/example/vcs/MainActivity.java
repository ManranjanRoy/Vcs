package com.example.vcs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vcs.Mannu.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     List<Data> eventlist;
     TestAdaptor eventAdaptor;
     RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventlist=new ArrayList<>();


        //this method will fetch and parse json
        //to display it in recyclerview
        loadUsers();


    }



    private void loadUsers() {
        String fetch_upcoming_events="http://pivotnet.co.in/SocietyManagement/Android/fetchupcomingevents.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, fetch_upcoming_events, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);

                    for (int i=0;i<=array.length();i++){

                        JSONObject object=array.getJSONObject(i);

                        eventlist.add(new Data(object.getString("update_upcoming_events_id"))
                        );

                        eventAdaptor=new TestAdaptor(getApplicationContext(),eventlist);
                        recyclerView.setAdapter(eventAdaptor);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}