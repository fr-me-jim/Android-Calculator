package com.jediupc.calculator;

import android.content.Context;
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

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {

    EditText u,p,rp,ph,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sign In");
        setSupportActionBar(toolbar);

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
            String userStr = u.getText().toString(), pass = p.getText().toString(), rpass = rp.getText().toString();
            String phone = ph.getText().toString(), coll = c.getText().toString();
            if (!userStr.isEmpty() && !pass.isEmpty() && !phone.isEmpty() && !coll.isEmpty()){
                if (!rpass.isEmpty() && rpass.equals(pass)){
                    Realm realm = Realm.getDefaultInstance();
                    final User userName = realm.where(User.class).equalTo("username", userStr).findFirst();
                    final User passWord = realm.where(User.class).equalTo("password", pass).findFirst();
                    if (!userStr.equals(userName) && !pass.equals(passWord)) {
                        User user = new User();
                        user.setUsername(userStr);
                        user.setPassword(pass);
                        user.setTelefon(phone);
                        user.setUniversitat(coll);
                        realm.beginTransaction();
                        Puntuacion punt = realm.createObject(Puntuacion.class);
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                        String date = "" + mdformat.format(calendar.getTime());
                        punt.setPuntuacion(0); punt.setFecha(date);
                        user.getPunt().add(punt);
                        realm.copyToRealm(user);
                        realm.commitTransaction();
                    }
                    else if (!userStr.equals(userName)) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "This Username already exists.",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                    //Back when pressing Register Button
                    finishActivity(0);

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
                else if (!rpass.equals(pass)){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Passwords do not match.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }

            }
            //Unfilled gaps
            else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "All gaps must  be filled.",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
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
}
