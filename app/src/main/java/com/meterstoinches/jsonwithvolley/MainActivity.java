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
