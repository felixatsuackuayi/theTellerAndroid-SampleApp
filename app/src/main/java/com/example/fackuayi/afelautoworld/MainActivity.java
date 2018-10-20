package com.example.fackuayi.afelautoworld;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gh.com.payswitch.thetellerandroid.Meta;
import gh.com.payswitch.thetellerandroid.card.CardFragment;
import gh.com.payswitch.thetellerandroid.card.CardPresenter;
import gh.com.payswitch.thetellerandroid.data.SavedCard;
import gh.com.payswitch.thetellerandroid.data.SavedPhone;
import gh.com.payswitch.thetellerandroid.ghmobilemoney.GhMobileMoneyContract;
import gh.com.payswitch.thetellerandroid.ghmobilemoney.GhMobileMoneyFragment;
import gh.com.payswitch.thetellerandroid.ghmobilemoney.GhMobileMoneyPresenter;
import gh.com.payswitch.thetellerandroid.thetellerConstants;
import gh.com.payswitch.thetellerandroid.thetellerActivity;
import gh.com.payswitch.thetellerandroid.thetellerManager;
import gh.com.payswitch.thetellerandroid.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //spinner -By 19-10-2018
    Spinner paymentspinner;
    Spinner spin;
    boolean mobileMoney = false;
    boolean cards = false;
    String fruit = "";
    //list to be set to spinner- 19-10-2018
    ArrayList<String> payments = new ArrayList<>();


    CardPresenter cardPresenter;
    GhMobileMoneyPresenter ghMobileMoneyPresenter;

    ProgressDialog progressDialog;

    EditText emailEt;
    EditText amountEt;
    EditText apiKeyEt;
    EditText txRefEt;
    EditText narrationEt;
    ImageView imageView;
    TextView   textView;
    TextView   paymentView;
    Spinner paymentSource;
    Spinner fruitSpinner;

    EditText currencyEt;
    EditText fNameEt;
    EditText lNameEt;
    Button startPayBtn;
    //    SwitchCompat cardSwitch;
//    SwitchCompat ghMobileMoneySwitch;
    SwitchCompat isLiveSwitch;


    List<Meta> meta = new ArrayList<>();

    //By Me Felix
    /*public String [] ItemsBought = {"Fridge", "Stove", "Air-conditioning", "Television", "Decoder", "Dispenser", "generator", "UPS"};
    public int Default = 0;*/

    /* String[] country = { "Fridge", "Television", "Air-conditioning", "Watches", "Other"};*/

    String[] fruits={"Apple","Grapes","Banana"};
    int images[] = {R.drawable.apple,R.drawable.banana, R.drawable.grape };








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Selecting the layout-widget by it id 19-10-2018*/
        paymentspinner=(Spinner)findViewById(R.id.selectpayment);

        //preparing dummy data for spinner
        prepareDummyData();

        //adapter for spinner
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (MainActivity.this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,payments);

        paymentspinner.setAdapter(adapter);


        //handle click of spinner item
        paymentspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // clicked item will be shown as spinner
                /*  Toast.makeText(getApplicationContext(),""+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();*/

                if(parent.getItemAtPosition(position).toString().equals("Mobile Money")){
                    cards = false;
                    mobileMoney = true;
                }

                if(parent.getItemAtPosition(position).toString().equals("Cards")){
                    cards = true;
                    mobileMoney = false;
                }

                if (parent.getItemAtPosition(position).toString().equals("Mobile & Cards")) {
                    cards = true;
                    mobileMoney = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        emailEt = findViewById(R.id.emailEt);
        amountEt = findViewById(R.id.amountEt);
        apiKeyEt = findViewById(R.id.apiKeyEt);
        txRefEt = findViewById(R.id.txRefEt);
        /* narrationEt = findViewById(R.id.narrationTV);*/
        imageView = findViewById(R.id.imageView);/*By Me*/
        textView = findViewById(R.id.textView); /*By Me*/
        textView = findViewById(R.id.textView); /*By Me*/
       /* paymentSource = findViewById(R.id.selectpayment);
        fruitSpinner = findViewById(R.id.spinner);*/



        currencyEt = findViewById(R.id.currencyEt);
        fNameEt = findViewById(R.id.fNameEt);
        lNameEt = findViewById(R.id.lnameEt);
        startPayBtn = findViewById(R.id.startPaymentBtn);
//        cardSwitch = findViewById(R.id.cardPaymentSwitch);
//        ghMobileMoneySwitch = findViewById(R.id.accountGHMobileMoneySwitch);
        isLiveSwitch = findViewById(R.id.isLiveSwitch);

        apiKeyEt.setText(thetellerConstants.API_KEY);

        //By me Felix
        /*Connecting up to the button*/
        /* Button setIfTestButton = (Button) findViewById(R.id.startPaymentBtn);*/

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
       /* Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
*/

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.spinner);
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, images, fruits);
        spin.setAdapter(customAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if(CustomAdapter.getFruit(i).equals("Apple")){
                    fruit = "Apple";
                }

                if(CustomAdapter.getFruit(i).equals("Grapes")){
                    fruit = "Grapes";
                }

                if (CustomAdapter.getFruit(i).equals("Banana")) {
                    fruit = "Banana";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        meta.add(new Meta("test key 1", "test value 1"));
        meta.add(new Meta("test key 2", "test value 2"));

        startPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressIndicator(true);

                showProgressIndicator(false);
                validateEntries();

                /*By Felix*/
               /* narrationEt.setText(ItemsBought[Default]);
                if(Default < ItemsBought.length -1){
                    Default++;
                }
                else {
                    Default = 0;
                }*/


            }

        });




    }





    public void prepareDummyData()
    {
        payments.add("Mobile Money");
        payments.add("Cards");
        payments.add("Mobile & Cards");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void showProgressIndicator(boolean active) {

        if (this.isFinishing())
        { return;
        }



        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
        }

        if (active && !progressDialog.isShowing()) {
            progressDialog.show();
        }
        else {
            progressDialog.dismiss();
        }
    }




    private void clearErrors() {
        emailEt.setError(null);
        amountEt.setError(null);
        apiKeyEt.setError(null);
        txRefEt.setError(null);
        /*  narrationEt.setError(null);*/
        currencyEt.setError(null);
        fNameEt.setError(null);
        lNameEt.setError(null);
    }



    private void validateEntries() {

        clearErrors();
        String email = emailEt.getText().toString();
        String amount = amountEt.getText().toString();
        String apiKey = apiKeyEt.getText().toString();
        String txRef = txRefEt.getText().toString();
        /* String narration = narrationEt.getText().toString();*/
        String currency = currencyEt.getText().toString();
        String fName = fNameEt.getText().toString();
        String lName = lNameEt.getText().toString();



        boolean valid = true;

        if (amount.length() == 0) {
            amount = "0";
        }

        //check for compulsory fields
        if (!Utils.isEmailValid(email)) {
            valid = false;
            emailEt.setError("A valid email is required");
        }

        if (apiKey.length() < 1){
            valid = false;
            apiKeyEt.setError("A valid public key is required");
        }

        if (txRef.length() < 1){
            valid = false;
            txRefEt.setError("A valid txRef key is required");
        }

        if (currency.length() < 1){
            valid = false;
            currencyEt.setError("A valid currency code is required");
        }

        if (valid) {
            ghMobileMoneyPresenter  = new GhMobileMoneyPresenter(this, new GhMobileMoneyFragment());
            cardPresenter = new CardPresenter(this, new CardFragment());
            List<SavedPhone> checkForSavedMobileMoney = ghMobileMoneyPresenter.checkForSavedGHMobileMoney(email);
            List<SavedCard> checkForSavedCards = cardPresenter.checkForSavedCards(email);

            if (checkForSavedCards.isEmpty() && checkForSavedMobileMoney.isEmpty()){
                new thetellerManager(this).setAmount(Double.parseDouble(amount))
                        .setCurrency(currency)
                        .setEmail(email)
                        .setfName(fName)
                        .setlName(lName)
                        .setNarration(fruit)
                        .setApiKey(apiKey)
                        .setTxRef(txRef)
                        .acceptCardPayments(cards)
                        .acceptGHMobileMoneyPayments(mobileMoney)


//                    .acceptCardPayments(cardSwitch.isChecked())
//                    .acceptGHMobileMoneyPayments(ghMobileMoneySwitch.isChecked())
                        .onStagingEnv(!isLiveSwitch.isChecked())
//                    .setMeta(meta)
//                    .withTheme(R.style.TestNewTheme)
                        .initialize();
            }else {
                new thetellerManager(this).setAmount(Double.parseDouble(amount))
                        .setCurrency(currency)
                        .setEmail(email)
                        .setfName(fName)
                        .setlName(lName)
                        .setNarration(fruit)
                        .setApiKey(apiKey)
                        .setTxRef(txRef)
//                        .selectCardSource(cards)
//                        .selectMobileSource(mobileMoney)
                        .acceptCardPayments(cards)
                        .acceptGHMobileMoneyPayments(mobileMoney)
                        .onStagingEnv(!isLiveSwitch.isChecked())
//                    .setMeta(meta)
//                    .withTheme(R.style.TestNewTheme)
                        .chooseCardOrNumber();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == thetellerConstants.theteller_REQUEST_CODE && data != null) {

            String message = data.getStringExtra("response");

            if (message != null) {
                Log.d("theteller response", message);
            }

            if (resultCode == thetellerActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == thetellerActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == thetellerActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int position = 0;
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}
