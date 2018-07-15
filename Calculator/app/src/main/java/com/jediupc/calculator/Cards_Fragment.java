package com.jediupc.calculator;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cards_Fragment extends Fragment {
    View rootView;
    Drawable neutral, geralt,yen,triss,ciri,ilmerith,avallach, draug, crone, arachas, letho;
    ImageButton img1,img1c,img2,img2c,img3,img3c,img4,img4c,img5,img5c,img6,img6c,img7,img7c,img8,img8c,img9,img9c,img10,img10c;
    ArrayList<ImageButton> cards;
    ArrayList<Drawable> cardImg;
    CoolImageFlipper flipper;
    ImageButton first, second;
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

        //Initialize arrays
        arrayImgFill(cardImg);
        arrayFill(cards);

        //Shuffle
        Collections.shuffle(cardImg);

        //ImageButtons attached to onClickListener
        for (ImageButton card : cards) {
            card.setOnClickListener(flip);
        }



        return rootView;
    }

    View.OnClickListener flip = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!firstCard) {
                firstCard = true;
                first = (ImageButton) v;
                flipper.flipImage(cardImg.get(cards.indexOf(first)), cards.get(cards.indexOf(first)));
            }
            else {
                if (!secondCard) {
                    secondCard = true;
                    second = (ImageButton) v;
                    flipper.flipImage(cardImg.get(cards.indexOf(second)), cards.get(cards.indexOf(second)));
                }
                else {
                    if (check()) {
                        firstCard = false;
                        secondCard = false;
                    }
                    else {
                        firstCard = false;
                        flipper.flipImage(neutral, cards.get(cards.indexOf(first)));
                        secondCard = false;
                        flipper.flipImage(neutral, cards.get(cards.indexOf(second)));
                    }
                }
            }
        }
    };

    public void arrayFill(ArrayList<ImageButton> a){

        LinearLayout ly = rootView.findViewById(R.id.board);

        for (int i = 0; i < 5; i++) {
            LinearLayout row = (LinearLayout) ly.getChildAt(i);
            for (int j = 0; j < 4; j++) {
                ImageButton ib = (ImageButton) row.getChildAt(j);
                a.add(ib);
            }
        }
    }

    public void arrayImgFill(ArrayList<Drawable> a) {
        a.add(arachas);
        a.add(arachas);
        a.add(avallach);
        a.add(avallach);
        a.add(ciri);
        a.add(ciri);
        a.add(crone);
        a.add(crone);
        a.add(draug);
        a.add(draug);
        a.add(geralt);
        a.add(geralt);
        a.add(ilmerith);
        a.add(ilmerith);
        a.add(letho);
        a.add(letho);
        a.add(triss);
        a.add(triss);
        a.add(yen);
        a.add(yen);
    }

    public boolean check() {
        if (first.getDrawable().equals(second.getDrawable())) return true;
        else return false;
    }
}
