package com.jediupc.calculator;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.MyCustomViewHolder>{
    public ArrayList<User> dataSet;

    public class MyCustomViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{
        TextView textU,textP;
        CardView cardView;
        RecyclerViewDelegate delegate;

        public MyCustomViewHolder(View itemView) {
            super(itemView);
            textU = itemView.findViewById(R.id.textUser);
            textP = itemView.findViewById(R.id.textPunt);
            cardView = itemView.findViewById(R.id.card);

            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    delegate.didSelectedRow(getAdapterPosition());
                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
        void setItem(String string, int i) {
            textU.setText(string);
            textP.setText(i);
        }
    }

    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new MyCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        /*String item = dataSet.get(position);
        holder.setItem(item);*/
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
