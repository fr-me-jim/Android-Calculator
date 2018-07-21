package com.jediupc.calculator;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calc_Fragment extends Fragment {

    // Atributos activity
    double result;
    double aux;
    boolean waitingOP = false, eq = false, square = false ;
    String num2 = "";
    String operation = "";
    TextView textViewResult;
    TextView textViewOp;
    Button ac, del, pow, sum, sub, set, vuit, nou, rel, div, tres, quatre, cinc, sis, mul, dot, cero, u, dos, res;
    View rootView;

    public Calc_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_calculadora, container, false);

        // Inflate the layout for this fragment



        ac = rootView.findViewById(R.id.AC);
        del = rootView.findViewById(R.id.DEL);
        pow = rootView.findViewById(R.id.POW);
        sum = rootView.findViewById(R.id.SUM);
        sub = rootView.findViewById(R.id.SUB);
        set = rootView.findViewById(R.id.set);
        vuit = rootView.findViewById(R.id.vuit);
        nou = rootView.findViewById(R.id.nou);
        rel = rootView.findViewById(R.id.REL);
        div = rootView.findViewById(R.id.DIV);
        tres = rootView.findViewById(R.id.tres);
        quatre = rootView.findViewById(R.id.quatre);
        cinc = rootView.findViewById(R.id.cinc);
        sis = rootView.findViewById(R.id.sis);
        mul = rootView.findViewById(R.id.MUL);
        dot = rootView.findViewById(R.id.DOT);
        cero = rootView.findViewById(R.id.cero);
        u = rootView.findViewById(R.id.u);
        dos = rootView.findViewById(R.id.dos);
        res = rootView.findViewById(R.id.RES);

        //View's
        textViewOp = rootView.findViewById(R.id.textView2);
        textViewResult =rootView.findViewById(R.id.textView);

        //Crides funcions
        ac.setOnClickListener(listenerOp);
        del.setOnClickListener(listenerOp);
        pow.setOnClickListener(listenerOp);
        sum.setOnClickListener(listenerOp);
        sub.setOnClickListener(listenerOp);
        set.setOnClickListener(appendNumber);
        vuit.setOnClickListener(appendNumber);
        nou.setOnClickListener(appendNumber);
        rel.setOnClickListener(listenerOp);
        div.setOnClickListener(listenerOp);
        tres.setOnClickListener(appendNumber);
        quatre.setOnClickListener(appendNumber);
        cinc.setOnClickListener(appendNumber);
        sis.setOnClickListener(appendNumber);
        mul.setOnClickListener(appendNumber);
        dot.setOnClickListener(appendNumber);
        cero.setOnClickListener(appendNumber);
        u.setOnClickListener(appendNumber);
        dos.setOnClickListener(appendNumber);
        res.setOnClickListener(listenerOp);


        return rootView;
    }


    // OnClick botón número
    View.OnClickListener appendNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button) view; // Castear la vista del onClick a botón
            if (eq) {
                textViewOp.setText("");
                textViewResult.setText("");
                eq = false;
            }
            textViewOp.append(b.getText());
            num2 += b.getText().toString(); // Leer el texto de un botón

        }
    };

    View.OnClickListener listenerOp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v; // Castear la vista del onClick a botón
            if (eq) {
                textViewOp.setText("");
                textViewResult.setText("");
                eq = false;
            }
            switch (b.getText().toString()){
                case "AC":
                    num2 = "";
                    textViewOp.setText("");
                    textViewResult.setText("");
                    break;
                case "DEL":
                    num2 = num2.substring(0,num2.length()-1);
                    String t = textViewOp.getText().toString();
                    textViewOp.setText(t.substring(0,t.length()-1));
                    break;
                case "=":
                    aux = performOperation();
                    operation = "";
                    num2= "";
                    eq = true;
                    textViewResult.setText(String.valueOf(aux));
                    break;
                default:
                    if(!waitingOP){
                        textViewOp.append(b.getText());
                        operation += b.getText().toString();
                        if (!num2.isEmpty()) result = Double.parseDouble(num2);
                        num2 = "";
                        waitingOP = true;
                    }
                    else {
                        if (b.getText().toString().equals("√") || square) {
                            textViewOp.append(b.getText());
                            if (!square )square = true;
                            else {
                                square = false;
                                if (!num2.isEmpty()) num2 = performSquare(num2);
                                result = performOperation();
                                operation = ""; operation += b.getText().toString();
                                num2 = "";
                                textViewResult.setText(String.valueOf(result));
                            }
                        }
                        else {
                            textViewOp.append(b.getText());
                            result = performOperation();
                            operation = ""; operation += b.getText().toString();
                            num2 = "";
                            textViewResult.setText(String.valueOf(result));
                        }
                    }
                    break;
            }

        }
    };

    double performOperation(){
        if (num2.equals("")) {
            if (operation.equals("*") || operation.equals("/")) num2 = "1";
            else num2 = "0";
        }
        double d = Double.parseDouble(this.num2);
        if (operation.equals("+")){
            d += result;
        }
        else if (operation.equals("-")){
            d = result - d;
        }
        else if (operation.equals("/")){
            d = result / d;
        }
        else if (operation.equals("*")){
            d *= result;
        }
        else if (operation.equals("√")){
            d = sqrt(d);
        }
        else if (operation.equals("^")){
            d = pow(result, d);
        }
        return d;
    }

    String performSquare (String num){
        Double d = sqrt(Double.parseDouble(num));
        return String.valueOf(d);
    }

}
