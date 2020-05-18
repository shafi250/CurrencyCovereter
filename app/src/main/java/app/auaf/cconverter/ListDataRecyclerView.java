package app.auaf.cconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListDataRecyclerView extends AppCompatActivity {

    ArrayList<Actor> actorArrayList=new ArrayList<>();
    RecyclerView rvActors;

    ActorsDataAdapter actorsDataAdapter;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_recycler_view);


        actorsDataAdapter=new ActorsDataAdapter(this,actorArrayList);
        manager=new LinearLayoutManager(ListDataRecyclerView.this,LinearLayoutManager.VERTICAL,false);

        rvActors=findViewById(R.id.rvActors);
        rvActors.setAdapter(actorsDataAdapter);
        rvActors.setLayoutManager(manager);

        getAllActorsFromAPI();

    }

    private void getAllActorsFromAPI() {
        String url = "https://api.androidhive.info/json/contacts.json";


        JsonArrayRequest actorRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {


                Log.d("data1", "onResponse: "+response.toString());

                for (int i = 0; i <response.length() ; i++) {

                    try {
                        JSONObject object=response.getJSONObject(i);

                        Actor actor=new Actor();

                        actor.setName(object.getString("name"));
                        actor.setPhone(object.getString("phone"));
                        actor.setImage(object.getString("image"));

                        actorArrayList.add(actor);

                        actorsDataAdapter.notifyDataSetChanged();
                        rvActors.setAdapter(actorsDataAdapter);
                        rvActors.setLayoutManager(manager);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                Log.d("data2", "onResponse: "+actorArrayList.size());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("data", "Error: " + error.getMessage());

            }
        });
        RequestQueue queue = Volley.newRequestQueue(ListDataRecyclerView.this);
        queue.add(actorRequest);

    }


}
