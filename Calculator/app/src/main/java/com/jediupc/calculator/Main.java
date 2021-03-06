package com.jediupc.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Main extends AppCompatActivity {
    // Atributos activity
    double result;
    double aux;
    String num2 = "";
    String operation = "";
    TextView textViewResult;
    TextView textViewOp;
    Button ac, del, pow, sum, sub, set, vuit, nou, rel, div, tres, quatre, cinc, sis, mul, dot, cero, u, dos, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ac = findViewById(R.id.AC);
        del = findViewById(R.id.DEL);
        pow = findViewById(R.id.POW);
        sum = findViewById(R.id.SUM);
        sub = findViewById(R.id.SUB);
        set = findViewById(R.id.set);
        vuit = findViewById(R.id.vuit);
        nou = findViewById(R.id.nou);
        rel = findViewById(R.id.REL);
        div = findViewById(R.id.DIV);
        tres = findViewById(R.id.tres);
        quatre = findViewById(R.id.quatre);
        cinc = findViewById(R.id.cinc);
        sis = findViewById(R.id.sis);
        mul = findViewById(R.id.MUL);
        dot = findViewById(R.id.DOT);
        cero = findViewById(R.id.cero);
        u = findViewById(R.id.u);
        dos = findViewById(R.id.dos);
        res = findViewById(R.id.RES);
        textViewOp = findViewById(R.id.textView2);
        textViewResult = findViewById(R.id.textView);
        //Crides funcions
        ac.setOnClickListener(appendNumber);
        del.setOnClickListener(appendNumber);
        pow.setOnClickListener(appendNumber);
        sum.setOnClickListener(appendNumber);
        sub.setOnClickListener(appendNumber);
        set.setOnClickListener(appendNumber);
        vuit.setOnClickListener(appendNumber);
        nou.setOnClickListener(appendNumber);
        rel.setOnClickListener(appendNumber);
        div.setOnClickListener(appendNumber);
        tres.setOnClickListener(appendNumber);
        quatre.setOnClickListener(appendNumber);
        cinc.setOnClickListener(appendNumber);
        sis.setOnClickListener(appendNumber);
        mul.setOnClickListener(appendNumber);
        dot.setOnClickListener(appendNumber);
        cero.setOnClickListener(appendNumber);
        u.setOnClickListener(appendNumber);
        dos.setOnClickListener(appendNumber);
        res.setOnClickListener(appendNumber);
    }
    // OnClick botón número
    View.OnClickListener appendNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button) view; // Castear la vista del onClick a botón
            textViewOp.append(b.getText());
            num2 += b.getText().toString(); // Leer el texto de un botón
            if (num2.equals("AC")){
                textViewResult.setText(" ");
            }
            else {
                double d = performOperation();

                textViewResult.setText(String.valueOf(d));
            }
        }
    };


    double performOperation(){
        double d = Double.parseDouble(this.num2);
        if (operation.equals("+")){
            d += result;
        }
        else if (operation.equals("-")){
            d = result - d;
        }
        else if (Objects.equals("/", operation)){
            d = result / d;
        }
        else if (Objects.equals(operation, "x")){
            d *= result;
        }
        else if (operation.equals("REL")){
            d = sqrt(result);
        }
        else if (operation.equals("POW")){
            d = pow(result, d);
        }
        return d;
    }
}
