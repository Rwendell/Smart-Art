package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.austinsehnert.smartart.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class allows for new users to register, making a new username/password
 */
public class NewUserRegActivity extends AppCompatActivity {

    private static final String TAG = NewUserRegActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.example.austinsehnert.smartart.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_reg);
    }

    /**
     * Takes in user info and allows users to make a new password. If new password and its
     * confirmation do not add up, the user is prompted to enter information in again
     * @param view the view in which the actvity is displayed in
     */
    public void collectUserInfo(View view) {
        final Intent registrationSuccessful = new Intent(this, DisplayMessageActivity.class);
        final Intent registrationFailed = new Intent(this, RegistrationFailedActivity.class);

        EditText name = findViewById(R.id.username);
        String message = name.getText().toString();
        String fail = "";

        EditText password = findViewById(R.id.password);
        String passwordStr = password.getText().toString();

        EditText password2 = findViewById(R.id.password2);
        String password2str = password2.getText().toString();

        if (!passwordStr.equals(password2str)) {
            fail = "Passwords do not match";
            registrationFailed.putExtra(EXTRA_MESSAGE, fail);
            startActivity(registrationFailed);
            return;
        }
        else {
            String tag_json_obj = "json_obj_req";

            String p1 = "http://proj-309-sb-2.cs.iastate.edu:8080/user/add?username=";
            String p3 = "&password=";

            String all = p1 + message + p3 + passwordStr;

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
    }
}
