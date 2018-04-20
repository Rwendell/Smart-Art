package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


/**
 * Activity for the user to sign in
 */
public class SignInActivity extends AppCompatActivity {
    private static final String TAG = SignInActivity.class.getSimpleName();


    String response_string = "";
    JSONObject rsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }



    /**
     * Sign in method for user to enter username and password to sign in to account
     *
     * @param view
     */
    public void signIn(View view) throws JSONException {
        Intent registrationSuccessful = new Intent(this, DisplayMessageActivity.class);
        Intent registrationFailed = new Intent(this, RegistrationFailedActivity.class);

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

        String p1 = "http://proj-309-sb-2.cs.iastate.edu:8080/user/login?username=";
        String p3 = "&password=";

        String all = p1 + nameStr + p3 + passwordStr;






        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, all, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // mTxtDisplay.setText("Response: " + response.toString());

                        Log.d("RESPONSE", "Response: " + response.toString());
                        rsp = response;
                        System.out.println(rsp.toString());
                        response_string = rsp.toString();
                        //System.out.println(response.toString());

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

       // AppController.getInstance().addToRequestQueue(jsObjRequest, tag_json_obj);

        //startActivity(registrationSuccessful);


        String main_response = response_string;
        System.out.println(response_string + "TEST");
        //System.out.println(main_response);
        //System.out.println(u_response.get("response"));

        if (main_response.contains("Successful")) {
            System.out.println("hi");
            startActivity(registrationSuccessful);

        } else if (main_response.contains("Incorrect Login")) {
            startActivity(registrationFailed);
        }

    }


    /**
     * Brings user to new user registration if user clicks on new user
     *
     * @param view
     */
    public void goToNewuserReg(View view) {
        Intent newuser = new Intent(this, NewUserRegActivity.class);
        startActivity(newuser);
    }

}