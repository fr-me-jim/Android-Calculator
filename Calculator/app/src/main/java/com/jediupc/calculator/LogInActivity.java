package com.jediupc.calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class LogInActivity extends AppCompatActivity  {
    EditText u,p;
    Button l, r;
    TextView tr;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Init Realm
        Realm.init(getApplicationContext());
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User LogIn");
        setSupportActionBar(toolbar);

        //logIn phase
        tr = findViewById(R.id.notReg);
        l = findViewById(R.id.logIn);
        r = findViewById(R.id.register);
        u = findViewById(R.id.LogU);
        p = findViewById(R.id.LogP);
        l.setOnClickListener(log);
        r.setOnClickListener(reg);

    }
    View.OnClickListener log = new View.OnClickListener() {
        public void onClick(View v) {

            if(logIn(u.getText().toString(), p.getText().toString())) {
                Intent i = new Intent(getApplicationContext(), NavigatorActivity.class);
                startActivity(i);
                setContentView(R.layout.activity_navi);
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Wrong Username or Password.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }
    };
    View.OnClickListener reg = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
            setContentView(R.layout.activity_register);
        }
    };
    boolean logIn(String User, String Pass) {
        return true;
        //Select en BD  Realm
        /*RealmResults<User> realmList = realm.where(User.class).findAll();
        for (User us : realmList) {
            if (User.equals(us.getUsername()) && Pass.equals(us.getPassword())) return true;
        }
        return false;*/
        /*final User userName = realm.where(User.class).equalTo("username", User).findFirst();
        final User passWord = realm.where(User.class).equalTo("password", Pass).findFirst();
        if (User.equals(userName) && Pass.equals(passWord)) return true;
        else return false;*/
    }


    //Screen Toggle
    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString("username", u.getText().toString());
        Log.v("username", u.getText().toString());
        outstate.putString("password",p.getText().toString());
        Log.v("password", p.getText().toString());
        outstate.putString("notReg", tr.getText().toString());
        Log.v("notReg", tr.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        u.setText(savedInstanceState.getString("username"));
        Log.v("retrieving",savedInstanceState.getString("username"));
        p.setText(savedInstanceState.getString("password"));
        Log.v("retrieving",savedInstanceState.getString("password"));
        tr.setText(savedInstanceState.getString("notReg"));
        Log.v("retrieving",savedInstanceState.getString("notReg"));


    }
}
