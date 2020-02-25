package com.example.omegar.NonActivityClasses;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omegar.R;

import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ArticleHolder> {

    private Context context;
    private ArrayList<Ed_card> Ed_card_array;

    public CardAdapter(Context context, ArrayList<Ed_card> arrayList) {
        this.context = context;
        this.Ed_card_array = arrayList;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        Ed_card edCard = Ed_card_array.get(position);
        holder.setDetails(edCard);
    }

    @Override
    public int getItemCount() {
        return Ed_card_array.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        private TextView Title, AbstractText;

        ArticleHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title1);
            AbstractText = itemView.findViewById(R.id.AbstractText1);
        }

        void setDetails(Ed_card edCard) {
            edCard.setAbstract("");
            edCard.setAbstract("");
        }
    }
}
