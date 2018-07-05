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

public class Register extends AppCompatActivity {

    EditText u,p,rp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User SignIn");
        setSupportActionBar(toolbar);

        //Space input filter
        u = findViewById(R.id.nUser);
        u.setFilters(new InputFilter[] { filter });

        p = findViewById(R.id.nPass);
        p.setFilters(new InputFilter[] { filter });

        rp = findViewById(R.id.rPass);
        rp.setFilters(new InputFilter[] { filter });

        //Button event
        Button register = findViewById(R.id.register);
        register.setOnClickListener(signIn);

    }

    View.OnClickListener signIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = u.getText().toString(), pass = p.getText().toString(), rpass = rp.getText().toString();
            if (!user.isEmpty() && !pass.isEmpty()){
                if (!rpass.isEmpty() && rpass.equals(pass)){
                    /* CONSULTAR SHARED PREFERENCES*/
                    //Instanciamos el SharedPreferences
                    SharedPreferences settings = getSharedPreferences("User Account", Context.MODE_PRIVATE);
                    //Consultamos
                    boolean silent = settings.getBoolean("myBoolean", false);


                    /* ESCRIBIR EN SHARED PREFERENCES*/
                    //Instanciamos el SharedPreferences
                    SharedPreferences settings = getSharedPreferences("User Account", 0);
                    //Obtenemos el editor
                    SharedPreferences.Editor editor = settings.edit();
                    //Editamos
                    editor.putBoolean("silentMode", mSilentMode);
                    //Guardamos los cambios
                    editor.apply();

                    //Back when pressing Register
                    finishActivity(1);
                }
            }
            else {
                Context context = getApplicationContext();
               if() CharSequence text = "Fill all the gaps pls.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }
    };

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
