package shecklock.example.com.cleanration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserListActivity extends AppCompatActivity {

    String url;
    JSONObject buyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        url="http://192.168.43.92:8080/ration-shop/buy/";
        buyList = new JSONObject();
        Intent intent = getIntent();
        String str = intent.getExtras().getString("Result");
//        str = str.replace("\"", "\\\"");
//        str = "\\\""+str+"\\\"";
        Toast.makeText(UserListActivity.this, "check\n"+str, Toast.LENGTH_LONG).show();
        Log.v("JSON to String", str);
        try
        {
            buyList.put("json", str);
            sendTransaction();
        } catch (JSONException e)
        {
            Toast.makeText(UserListActivity.this, "Errors", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }



    }

    public void sendTransaction()
    {
        JsonObjectRequest transactionRequest = new JsonObjectRequest(Request.Method.POST, url, buyList, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(UserListActivity.this, "Performing transaction", Toast.LENGTH_SHORT).show();
                TextView resultView = (TextView)findViewById(R.id.result);
                try {
                    if(response.getBoolean("success") == true)
                        resultView.setText("Transaction Complete\n"+response.getDouble("price"));
                    else
                        resultView.setText("Not Enough\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserListActivity.this, "Unable to make transcation, Try again later", Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(transactionRequest);
    }



}
