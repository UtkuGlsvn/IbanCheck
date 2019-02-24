package com.example.ibanconvert;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.android.volley.toolbox.Volley;
import com.example.ibanconvert.model.IbanObject;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button convert;
    EditText ibantxt;
    TextView txt;
    RequestQueue queue;
    ProgressDialog pDialog;
    IbanObject myobject=new IbanObject();
    IbanApi myapi = new IbanApi();

    private String jsonResponse = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convert=findViewById(R.id.convertbtn);
        ibantxt=findViewById(R.id.ibantext);
        txt=findViewById(R.id.resulttxt);

        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Lütfen Bekleyin!");


        queue = Volley.newRequestQueue(this);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control())
                {
                    try {
                        response();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });


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


    private void response() {

    showDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                myapi.myurl(ibantxt.getText().toString()), null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());

                try {
                    JSONObject result = response.getJSONObject("result");
                    JSONObject data = result.getJSONObject("data");

                    myobject.setIban(data.getString("iban"));
                    myobject.setCountry(data.getString("country"));
                    myobject.setCountrycode(data.getString("countrycode"));

                    jsonResponse += "Iban No: " + myobject.getIban() + "\n";
                    jsonResponse += "Ulke kod: " + myobject.getCountrycode()+ "\n";
                    jsonResponse += "Ulke:: " + myobject.getCountry() + "\n";

                    txt.setText(jsonResponse);
                    hideDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        queue.add(jsonObjReq);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
