package shecklock.example.com.cleanration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {

    SharedPreferences loginPref;
    SharedPreferences.Editor loginPrefEditor;

    String url;

    EditText userName,passWord;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.done);

        url = "http://192.168.43.92:8080/ration-shop/login-shopkeeper";

        loginPref = getSharedPreferences("login", Context.MODE_PRIVATE);

        if(loginPref.getInt("success",0)==1)
        {
            Log.v("Entering man act", "");
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Log.v("Entering else cond", "");
                    JSONObject userDetails = new JSONObject();
                    try
                    {
                        userDetails.put("username", userName.getText().toString());
                        userDetails.put("password", passWord.getText().toString());
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    validateUser(userDetails);
                }
        });
    }

    private void validateUser(final JSONObject loginDetails)
    {
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, url, loginDetails, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loginPrefEditor = loginPref.edit();
                Log.v("Entering onResponse", "");
                try
                {
                    Log.v("Entering Success", response.toString());
                    if(response.getBoolean("success"))
                    {
                        Log.v("Entering Success 1", response.toString());
                        loginDetails.put("username", userName.getText().toString());
                        loginDetails.put("password", passWord.getText());
                        loginPrefEditor.putString("username", userName.getText().toString());
                        loginPrefEditor.putString("password", passWord.getText().toString());
                        loginPrefEditor.putInt("success", 1);
                        loginPrefEditor.commit();
                        Intent intent=new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Log.v("Entering Failure", response.toString());
                        Toast.makeText(login.this,"Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (JSONException e)
                {
                    Log.v("Entering Success 2", response.toString());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(loginRequest);

    }
}
