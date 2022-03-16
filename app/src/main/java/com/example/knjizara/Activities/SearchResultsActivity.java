package com.example.knjizara.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.example.knjizara.Adapters.BookAdapter;
import com.example.knjizara.Database;
import com.example.knjizara.ExpandableHeightGridView;
import com.example.knjizara.R;
import com.example.knjizara.Types.Book;
import com.example.knjizara.Utility;

import java.util.ArrayList;

public class SearchResultsActivity extends LoggedActivityBase {


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        Utility.initToolbar(this);

        Intent intent= getIntent();
        String search= intent.getStringExtra("value");

        ArrayList<Book> arrayOfBooks= Database.getArrayOfBooks();
        ArrayList<Book> arrayOfBooksSearch= new ArrayList<>();

        for(Book k: arrayOfBooks){
            if (k.getAuthor().toLowerCase().contains(search.toLowerCase()) || k.getName().toLowerCase().contains(search.toLowerCase()))
                arrayOfBooksSearch.add(k);
        }

        ExpandableHeightGridView mAppsGrid = (ExpandableHeightGridView) findViewById(R.id.gridview);
        mAppsGrid.setExpanded(true);
        Log.v("aaa", ""+arrayOfBooksSearch.size());
        BookAdapter adapter = new BookAdapter(this, arrayOfBooksSearch);
        GridView gv = (GridView) this.findViewById(R.id.gridview);
        gv.setAdapter(adapter);

        TextView tw = findViewById(R.id.rez_pretrage);
        tw.setText("Rezultati pretrage \""+search+"\"");
    }

}