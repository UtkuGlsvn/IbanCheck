package com.example.ibanconvert;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public  class IbanApi {
    //https://api.bank.codes/iban/json/9fc53b3db09ca830488d19546a4fc2a1/BE68539007547034/
    private final String api_key="/1495a04c7966388b7d8fc15b98667e1a";
    private final String USER_AGENT = "API Client/1.0";
    String url = "https://api.bank.codes/iban/json/";
    String myresponse;
    protected String myurl()
    {
        return  url+api_key+"/TR330006100519786457841326";
    }
}
