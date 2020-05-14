package app.auaf.cconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
    Spinner spinner_base_currency;
    ImageView ivlogoCurrency;
    String baseCurrenncy;

    TextView tvAfghani,tvInr,tvDerham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);


        spinner_base_currency = (Spinner) findViewById(R.id.spinnerbase);
        etAmount=findViewById(R.id.etAmount);
        btnConvert=findViewById(R.id.btnConvert);
        tvAfghani=findViewById(R.id.tvAfghaniValue);

        ivlogoCurrency=findViewById(R.id.ivlogoCurrency);







        spinner_base_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Convert",""+position);


                switch (position){

                    case 0:
                        ivlogoCurrency.setImageResource(R.drawable.ic_afg);
                        baseCurrenncy="AFN";
                        break;
                    case 1:
                        ivlogoCurrency.setImageResource(R.drawable.ic_usa);
                        baseCurrenncy="USD";
                        break;

                    case 2:
                        ivlogoCurrency.setImageResource(R.drawable.ic_india);
                        baseCurrenncy="INR";
                        break;

                    case 3:
                        ivlogoCurrency.setImageResource(R.drawable.ic_euro);
                        baseCurrenncy="EUR";
                        break;
                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

getValueOnlineFromAPI();
            }
        });


//        etAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                getValueOnlineFromAPI();
//            }
//        });




    }

    private void getValueOnlineFromAPI() {


            String url = "https://free.currconv.com/api/v7/convert?q="+baseCurrenncy+"_AFN&compact=ultra&apiKey=cf56bbee1671257934e4";


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Tag", response.toString());


                                           try {
                                               String valueConverted = response.getString(""+baseCurrenncy+"_AFN");

                                               double valueAmount=Double.parseDouble(etAmount.getText().toString());
                                               double valueConverteddouble=Double.parseDouble(valueConverted);




                                               Log.d("Tag", "value: "+valueConverted);
                                               tvAfghani.setText(valueAmount*valueConverteddouble+" Afghani");
                                           }catch (JSONException e){

                                               Log.d("Tag", "onResponse: "+e);

                                           }
                 //       JSONObject data = response.getJSONObject("rates");
//                    try {
//                        // Parsing json object response
//                        // response will be a json object
//
//                        date = response.getString("date");
//                        JSONObject data = response.getJSONObject("rates");
//
//                        String AFN = data.getString("AFN");
//                        String INR = data.getString("INR");
//                        String AED = data.getString("AED");
//
//                        double afnRate = Double.parseDouble(AFN);
//                        double inrRate = Double.parseDouble(INR);
//                        double aedRate= Double.parseDouble(AED);
//
//
//                        double amountConvert = Double.parseDouble(etAmount.getText().toString());
//
//
//                        tvAfghani.setText(amountConvert* afnRate+" Afghani");
//                        tvDerham.setText(amountConvert* aedRate+" Derham");
//                        tvInr.setText(amountConvert*inrRate+" Rupees");
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(),
//                                "Error: " + e.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }


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
