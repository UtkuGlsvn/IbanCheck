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
    //https://api.bank.codes/iban/json/9fc53b3db09ca830488d19546a4fc2a1/BE68539007547034/ -> example
    private final String api_key="your key";//https://bank.codes/api-iban/
    private final String USER_AGENT = "API Client/1.0";
    String url = "https://api.bank.codes/iban/json/";
    String myresponse;
    protected String myurl(String iban)
    {///TR330006100519786457841326
        return  url+api_key+"/"+iban;
    }
}
