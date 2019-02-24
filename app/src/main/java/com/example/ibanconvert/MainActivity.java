package com.example.ibanconvert;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button convert;
    EditText ibantxt;
    TextView txt;
    static String myresponse="";
    private static final String api_key="1495a04c7966388b7d8fc15b98667e1a";
    private static  final String USER_AGENT = "API Client/1.0";
   // private static String url = "https://api.bank.codes/iban/json";

    String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyBmSXUzVZBKQv9FJkTpZXn0dObKgEQOIFU&cx=014099860786446192319:t5mr0xnusiy&q=AndroidDev&alt=json&searchType=image";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convert=findViewById(R.id.convertbtn);
        ibantxt=findViewById(R.id.ibantext);
        txt=findViewById(R.id.resulttxt);

        queue = Volley.newRequestQueue(this);

        if(control())
        {
            try {
                response();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }

    boolean control()
    {
        if(ibantxt.getText().equals("")) return false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected==false) {
            Toast.makeText(getBaseContext(),"Not connected internet!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }

   protected void response()
   {
       StringRequest strReq = new StringRequest(Request.Method.GET,
               "https://api.bank.codes/iban/json/9fc53b3db09ca830488d19546a4fc2a1/BE68539007547034/", new Response.Listener<String>() {

           @Override
           public void onResponse(String response) {
               Log.d("warning", response.toString());
               txt.setText(response.toString());

           }
       }, new Response.ErrorListener() {

           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.d("Error: " + error.getMessage());
           }
       });

      queue.add(strReq);
   }

}
