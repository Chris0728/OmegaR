package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.omegar.NonActivityClasses.CardAdapter;
import com.example.omegar.NonActivityClasses.Ed_card;
import android.view.MenuItem;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;

public class Education extends AppCompatActivity {
    private Button backBtn;
    private ArrayList<Ed_card> articleList;
    private String[] titleList;
    private String[] contentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

         titleList = this.getResources().getStringArray(R.array.title);
         contentList = this.getResources().getStringArray(R.array.content);
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Education.super.onBackPressed();
            }
        });

       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       articleList = new ArrayList<>();
       CardAdapter adapter = new CardAdapter(this, articleList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        for(int i = 0 ; i < titleList.length;i++)
            getBodyText(i);
        Toast.makeText(Education.this, "" + articleList.size(),Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getBodyText(int i) {
         final StringBuilder builder = new StringBuilder();
                try {
                    //Efficiency problem, so abort the method
                    /*tring url= http;//your website url
                    Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.4 Safari/537.36").get();

                    Element title = doc.select("title").first();
                    Element body = doc.select("#p-2").first();*/

                    Ed_card edCard = new Ed_card(titleList[i],contentList[i]);
                    articleList.add(edCard);
                } catch (Exception e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

    }
}
