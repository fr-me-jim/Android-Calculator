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

public class User extends AppCompatActivity {
    EditText n,p;
    Button l, r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User LogIn");
        setSupportActionBar(toolbar);

        //logIn phase
        l = findViewById(R.id.logIn);
        r = findViewById(R.id.register);
        n = findViewById(R.id.editText);
        p = findViewById(R.id.editText2);
        l.setOnClickListener(log);
        r.setOnClickListener(reg);

    }
    View.OnClickListener log = new View.OnClickListener() {
        public void onClick(View v) {

            if(logIn(n.getText().toString(), p.getText().toString())) {
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
                setContentView(R.layout.activity_main);
            }
            else {
                Context context = getApplicationContext();
                CharSequence text = "Wrong Username or Password.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }
    };
    View.OnClickListener reg = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), Register.class);
            startActivity(i);
            setContentView(R.layout.activity_register);
        }
    };
    static boolean logIn (String nom, String pass) {
            if (nom.equals("Fran") && pass.equals("fran")) return true;
            else return false;
    }


    //Screen Toggle
    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        TextView t = findViewById(R.id.textView);
        outstate.putString("result", t.getText().toString());
        Log.v("result", t.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        TextView t = findViewById(R.id.textView);
        t.setText(savedInstanceState.getString("result"));
        Log.v("retrieving",savedInstanceState.getString("result"));
    }
}
