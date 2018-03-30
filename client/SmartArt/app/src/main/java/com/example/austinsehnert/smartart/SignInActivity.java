package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.austinsehnert.smartart.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = SignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View view){
        Intent registrationSuccessful = new Intent(this, DisplayMessageActivity.class);

        String url1 = "http://proj-309-sb-2.cs.iastate.edu:8080/user/login?username=meme&password=lol";
        String url = "http://ip.jsontest.com";

        String tag_json_obj = "json_obj_req";

        final TextView mTxtDisplay;
        mTxtDisplay = findViewById(R.id.username);

        JSONObject obj = new JSONObject();

        //String username = NexUserRegActivity

        EditText name = findViewById(R.id.username);
        String nameStr = name.getText().toString();

        EditText password = findViewById(R.id.password);
        String passwordStr = password.getText().toString();

        try {
            obj.put("username", nameStr);
            obj.put("password", passwordStr);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url1, obj, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // mTxtDisplay.setText("Response: " + response.toString());
                        Log.d("RESPONSE", "Response: " + response.toString());

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), "Error while reading server", Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

        // Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

        AppController.getInstance().addToRequestQueue(jsObjRequest, tag_json_obj);

        startActivity(registrationSuccessful);
    }

//    public void goToNewuserReg(View view){
//        Intent newuser = new Intent(this, NewUserRegActivity.class);
//        startActivity(newuser);
//    }

}
