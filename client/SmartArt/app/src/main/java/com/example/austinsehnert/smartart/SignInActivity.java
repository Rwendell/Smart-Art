
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
        public void signIn(View view) {
            final Intent registrationSuccessful = new Intent(this, GlobalActivity.class);
            final Intent registrationFailed = new Intent(this, RegistrationFailedActivity.class);

            String tag_json_obj = "json_obj_req";

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
                            Log.d("RESPONSE", "Response: " + response.toString());
                            if (response.toString().contains("success")) {
                                startActivity(registrationSuccessful);
                            } else {
                                startActivity(registrationFailed);
                            }
                        }

                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                        }
                    });

            AppController.getInstance().addToRequestQueue(jsObjRequest, tag_json_obj);
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

