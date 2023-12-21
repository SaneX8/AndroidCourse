package com.example.androidkurssi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YtjActivity extends AppCompatActivity {
    public static final String TAG ="urli:";
    RequestQueue requestQueue;
    MyAdapter mAdapter;
    ArrayList<Company> myDataset;


    String url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name=lappeenranta&companyRegistrationFrom=2014-02-28";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytj);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        // get data via the key

        String value1 = extras.getString("Value1");
        if (value1 != null) {
            url = url.replace("lappeenranta",value1);
            Log.e(TAG,url);
        }

        final RecyclerView Sometextt = findViewById(R.id.reSycle);
        Sometextt.setLayoutManager(new LinearLayoutManager(this));
        Sometextt.setHasFixedSize(true);


        requestQueue = Volley.newRequestQueue(this);
        myDataset = new ArrayList<Company>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {






                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());



                        try {
                            JSONArray responseItems = (JSONArray) response.getJSONArray("results");

                            for(int i = 0; i < responseItems.length(); i++)
                            {
                                JSONObject object = responseItems.getJSONObject(i);
                                Company currentCompany  = new Company();
                                currentCompany.setName(object.getString("name"));
                                Log.e(TAG, object.getString("name"));
                                myDataset.add(currentCompany);
                            }
                            mAdapter = new MyAdapter(myDataset);
                            mAdapter.notifyDataSetChanged();
                            Sometextt.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        requestQueue.add(jsonObjectRequest);

    }
}