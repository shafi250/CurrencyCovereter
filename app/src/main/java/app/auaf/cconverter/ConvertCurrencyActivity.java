package app.auaf.cconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConvertCurrencyActivity extends AppCompatActivity {

    EditText etAmount;
    Button btnConvert;
    String date;

    TextView tvAfghani,tvInr,tvDerham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);


        etAmount=findViewById(R.id.etAmount);
        btnConvert=findViewById(R.id.btnConvert);
        tvAfghani=findViewById(R.id.tvAfghaniValue);
        tvInr=findViewById(R.id.tvINRValue);
        tvDerham=findViewById(R.id.tvEmirateDerhamVlaue);







        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getValueOnlineFromAPI();
            }
        });




    }

    private void getValueOnlineFromAPI() {


            String url = "http://data.fixer.io/api/latest\n" +
                    "?access_key=501735be2fd52744492f54d468993c4a";

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Tag", response.toString());
                    try {
                        // Parsing json object response
                        // response will be a json object

                        date = response.getString("date");
                        JSONObject data = response.getJSONObject("rates");

                        String AFN = data.getString("AFN");
                        String INR = data.getString("INR");
                        String AED = data.getString("AED");

                        double afnRate = Double.parseDouble(AFN);
                        double inrRate = Double.parseDouble(INR);
                        double aedRate= Double.parseDouble(AED);


                        double amountConvert = Double.parseDouble(etAmount.getText().toString());


                        tvAfghani.setText(amountConvert* afnRate+" Afghani");
                        tvDerham.setText(amountConvert* aedRate+" Derham");
                        tvInr.setText(amountConvert*inrRate+" Rupees");


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            RequestQueue queue = Volley.newRequestQueue(this);
            // Adding request to request queue
            queue.add(jsonObjReq);

        }





}
