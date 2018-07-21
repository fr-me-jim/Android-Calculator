package com.jediupc.calculator;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cards_Fragment extends Fragment {
    View rootView;
    Drawable neutral;
    ArrayList<ImageButton> cards;
    ArrayList<Drawable> cardImg;
    ArrayList<Boolean> revealed;
    CoolImageFlipper flipper;
    ImageButton first, second;
    Button stop,start;
    boolean firstCard = false, secondCard = false;
    public Cards_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cards, container, false);
        flipper = new CoolImageFlipper(rootView.getContext());
        neutral = rootView.getResources().getDrawable(R.drawable.neutral);

        //Initialize arrays
        cards = new ArrayList<>();
        cardImg = new ArrayList<>();
        revealed = new ArrayList<>();
        for (int i = 0; i < 16;++i) {
            revealed.add(false);
        }
        arrayImgFill();
        arrayFill();

        //Shuffle
        Collections.shuffle(cardImg);

        //ImageButtons attached to onClickListener
        for (ImageButton card : cards) {
            card.setOnClickListener(flip);
        }

        //Pause Dialog
        stop = rootView.findViewById(R.id.stop);
        stop.setOnClickListener(showDialog);



        return rootView;
    }

    View.OnClickListener showDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
            View view = inflater.inflate(R.layout.dialog_pause, null);
            builder.setView(view);
            Button resume = view.findViewById(R.id.btnResume);
            resume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ;
                }
            });
        }
    };

    View.OnClickListener flip = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!firstCard) {
                first = (ImageButton) v;
                if(!revealed.get(cards.indexOf(first))) {
                    firstCard = true;
                    flipper.flipImage(cardImg.get(cards.indexOf(first)), cards.get(cards.indexOf(first)));
                }
            }
            else {
                if (!secondCard) {
                    second = (ImageButton) v;
                    if (!revealed.get(cards.indexOf(second))) {
                        secondCard = true;
                        flipper.flipImage(cardImg.get(cards.indexOf(second)), cards.get(cards.indexOf(second)));
                    }

                }
            }
            if (firstCard && secondCard) {
                if (check()) {
                    firstCard = false;
                    secondCard = false;
                    revealed.set(cards.indexOf(first), true);
                    revealed.set(cards.indexOf(second), true);
                    /*cards.get(cards.indexOf(first)).setImageResource(cardImg.get(cards.indexOf(first)));
                    cards.get(cards.indexOf(second)).setImageDrawable(cardImg.get(cards.indexOf(second)));*/
                }
                else {
                    firstCard = false;
                    flipper.flipImage(neutral, cards.get(cards.indexOf(first)));
                    secondCard = false;
                    flipper.flipImage(neutral, cards.get(cards.indexOf(second)));
                }
            }
        }
    };

    public void arrayFill(){
        cards.add((ImageButton) rootView.findViewById(R.id.img1));
        cards.add((ImageButton) rootView.findViewById(R.id.img1c));
        cards.add((ImageButton) rootView.findViewById(R.id.img2));
        cards.add((ImageButton) rootView.findViewById(R.id.img2c));
        cards.add((ImageButton) rootView.findViewById(R.id.img3));
        cards.add((ImageButton) rootView.findViewById(R.id.img3c));
        cards.add((ImageButton) rootView.findViewById(R.id.img4));
        cards.add((ImageButton) rootView.findViewById(R.id.img4c));
        cards.add((ImageButton) rootView.findViewById(R.id.img5));
        cards.add((ImageButton) rootView.findViewById(R.id.img5c));
        cards.add((ImageButton) rootView.findViewById(R.id.img6));
        cards.add((ImageButton) rootView.findViewById(R.id.img6c));
        cards.add((ImageButton) rootView.findViewById(R.id.img7));
        cards.add((ImageButton) rootView.findViewById(R.id.img7c));
        cards.add((ImageButton) rootView.findViewById(R.id.img8));
        cards.add((ImageButton) rootView.findViewById(R.id.img8c));
    }

    public void arrayImgFill() {
        cardImg.add(rootView.getResources().getDrawable(R.drawable.arachas));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.arachas));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.avallach));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.avallach));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.ciri));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.ciri));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.draug));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.draug));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.geralt));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.geralt));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.ilmerith));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.ilmerith));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.letho));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.letho));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.triss));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.triss));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.yen));
        cardImg.add(rootView.getResources().getDrawable(R.drawable.yen));
    }

    public boolean check() {
        Drawable f = cardImg.get(cards.indexOf(first));
        Drawable s = cardImg.get(cards.indexOf(second));
        if (f.equals(s)) return true;
        else return false;
    }
}
