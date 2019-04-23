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
    RecyclerView eventrecycler;
    TestAdaptor eventAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventrecycler = findViewById(R.id.rcv);
        eventrecycler.setHasFixedSize(true);
        eventrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        eventlist = new ArrayList<>();


        //this method will fetch and parse json
        //to display it in recyclerview
        loadUsers();


    }



    private void loadUsers() {
        String fetch_upcoming_events="http://pivotnet.co.in/SocietyManagement/Android/fetchupcomingevents.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, fetch_upcoming_events,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            // Toast.makeText(getActivity(),"entered"+array.length(),Toast.LENGTH_SHORT).show();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject user = array.getJSONObject(i);

                                //adding the product to product list
                                //String visitor_id,visitor_name,visitor_phone_num,visitor_vechile_num,
                                // mem_name,mem_flat_num,mem_phone_num,in_time_date,visitor_img;
                                eventlist.add(0, new Data(
                                        user.getString("update_upcoming_events_id")

                                       /* user.getString("mem_name"),
                                        user.getString("mem_flat_num"),
                                        user.getString("mem_phone_num")
                                        , user.getString("in_time_date"),
                                        user.getString("visitor_img")*/

                                ));


                            }


                            //creating adapter object and setting it to recyclerview
                            eventAdaptor = new TestAdaptor(getApplicationContext(), eventlist);
                            eventrecycler.setAdapter(eventAdaptor);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}