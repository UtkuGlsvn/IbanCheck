package com.example.ibanconvert;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button convert;
    EditText ibantxt,txt;

   // Ibanapi api=new Ibanapi(); api not work, problem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convert=findViewById(R.id.convertbtn);
        ibantxt=findViewById(R.id.ibantext);
        txt=findViewById(R.id.resulttxt);

       if(control())
       {
     /*

       try {
            api.sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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


}
