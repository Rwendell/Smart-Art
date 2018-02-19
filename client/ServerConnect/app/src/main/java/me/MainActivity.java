package com.example.austinsehnert.smartart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.austinsehnert.smartart.app.AppController;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.example.austinsehnert.smartart.MESSAGE";
    public ArrayList<String> usernames = new ArrayList<String>();
    public ArrayList<String> passwords = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void collectUserInfo(View view){
        Intent registrationSuccessful = new Intent(this, DisplayMessageActivity.class);
        Intent registrationFailed = new Intent(this, RegistrationFailedActivity.class);

        EditText name = (EditText) findViewById(R.id.username);
        String message = name.getText().toString();
        String fail = "";

        for(String checkName : usernames){
            if(checkName.equals(message)) {
                fail = "Not unique username";
                registrationFailed.putExtra(EXTRA_MESSAGE, fail);
                startActivity(registrationFailed);
                return;
            }
        }

        usernames.add(message);

        EditText password = (EditText) findViewById(R.id.password);
        String passwordstr = password.getText().toString();


        EditText password2 = (EditText) findViewById(R.id.password2);
        String password2str = password2.getText().toString();

        if(! passwordstr.equals(password2str)){
            fail = "Passwords do not match";
            registrationFailed.putExtra(EXTRA_MESSAGE, fail);
            startActivity(registrationFailed);
            return;
        }
        else {
            message = "Welcome " + message + "!";
            passwords.add(passwordstr);

        }

        registrationSuccessful.putExtra(EXTRA_MESSAGE, message);


        startActivity(registrationSuccessful);

    }

    public void globalArtboard(View view){

    }

//        RequestQueue queue = Volley.newRequestQueue(this);
//        // Tag used to cancel the request
//        String tag_json_arry = "json_array_req";
//        String url ="http://proj-309-sb-2.cs.iastate.edu:8080/user/changepass?userId=24&password=dogs";
//        ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show(context);
//        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//            @Override
//                    public void onResponse(JSONArray response) {
//                Log.d(TAG, response.toString());
//                pDialog.hide();
//            }
//        },new Response.ErrorListener() {
//            @Override
//                    public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG,"Error: "+ error.getMessage());
//                pDialog.hide();
//            }
//        });
//
//        //AppController.getInstance().addToRequestQueue(req, tag_json_arry);
//        queue.add(req);










}
