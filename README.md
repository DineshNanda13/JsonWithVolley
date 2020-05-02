# JsonWithVolley
Android Studio

//dependency: implementation 'com.android.volley:volley:1.1.1'

//activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/result_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20sp"
        android:textColor="@android:color/black"/>

    <Button
        android:id="@+id/button_parse"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="68dp"
        android:text="Parse" />

</RelativeLayout>

//MainActivity.java

package com.meterstoinches.jsonwithvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result_tv);
        Button parse = (Button) findViewById(R.id.button_parse);

        requestQueue = Volley.newRequestQueue(this);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url = "https://www.json-generator.com/api/json/get/cgCDNhpToy?indent=2";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");
                            for(int i = 0; i < jsonArray.length(); i++){

                                JSONObject jsonObject_employee = jsonArray.getJSONObject(i);

                                String name = jsonObject_employee.getString("name");
                                int age = jsonObject_employee.getInt("age");
                                String password = jsonObject_employee.getString("password");
                                String country = jsonObject_employee.getString("country");
                                String contact = jsonObject_employee.getString("contact");

                                result.append(name+", "+String.valueOf(age)+", "+password+", "+country+", "
                                        +contact+ "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
