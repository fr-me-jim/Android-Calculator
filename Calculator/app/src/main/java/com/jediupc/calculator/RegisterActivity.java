package com.jediupc.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class RegisterActivity extends AppCompatActivity {

    EditText u,p,rp,ph,c;
    String userStr, passStr, rpass, phone, college;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sign In");
        setSupportActionBar(toolbar);

        //Realm
        realm = Realm.getDefaultInstance();

        //Space input filter
        u = findViewById(R.id.nUser);
        u.setFilters(new InputFilter[] { filter });

        p = findViewById(R.id.nPass);
        p.setFilters(new InputFilter[] { filter });

        rp = findViewById(R.id.rPass);
        rp.setFilters(new InputFilter[] { filter });

        ph = findViewById(R.id.phone);
        ph.setFilters(new InputFilter[] { filter });

        c = findViewById(R.id.uni);


        //Button event
        Button register = findViewById(R.id.register);
        register.setOnClickListener(signIn);

    }

    View.OnClickListener signIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Get gap's content
            userStr = u.getText().toString();
            passStr = p.getText().toString();
            rpass = rp.getText().toString();
            phone = ph.getText().toString();
            college = c.getText().toString();

            if (!userStr.isEmpty() && !passStr.isEmpty()){
                if (!rpass.isEmpty() && rpass.equals(passStr)){
                    User check = realm.where(User.class).equalTo("username",userStr).findFirst();
                    if (check == null) {
                       //Add to DB
                       saveToDatabase(userStr,passStr,phone,college);
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "This Username already exists.",
                                 Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                }
               //Unfilled Repeat pass
                else if (rpass.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Remaining Repeated Password.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
                //Passwords don't match
                else if (!rpass.equals(passStr)){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Passwords do not match.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }

            }
        }
    };

    //Whitespace filter
    InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (Character.isWhitespace(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }

    };

    //Screen Toggle
    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString("username", u.getText().toString());
        Log.v("username", u.getText().toString());
        outstate.putString("password", p.getText().toString());
        Log.v("password", p.getText().toString());
        outstate.putString("repeatedP", rp.getText().toString());
        Log.v("repeatedP", rp.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        u.setText(savedInstanceState.getString("username"));
        Log.v("retrieving",savedInstanceState.getString("username"));
        p.setText(savedInstanceState.getString("password"));
        Log.v("retrieving",savedInstanceState.getString("password"));
        rp.setText(savedInstanceState.getString("repeatedP"));
        Log.v("retrieving",savedInstanceState.getString("repeatedP"));
    }

    public void saveToDatabase(final String username, final String password, final String telefon, final String universitat){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                User user = bgRealm.createObject(User.class, username);
                user.setPassword(password);
                user.setTelefon(telefon);
                user.setUniversitat(universitat);

                //Getting last parameter
                PuntuacionRealm puntuacionRealm = bgRealm.createObject(PuntuacionRealm.class);
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("yyyy / MM / dd ");
                String date = "" + mdFormat.format(calendar.getTime());
                puntuacionRealm.setPuntuacion((double) 0); puntuacionRealm.setFecha(date);
                user.getPunt().add(puntuacionRealm);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.

                Toast toast = Toast.makeText(getApplicationContext(),
                        "You have Signed In successfully!",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();

                //Back when pressing Register Button
                Log.v("Success", "User added.");
                Intent i = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(i);
                setContentView(R.layout.activity_login);


            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("Error", error.getMessage());
            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
