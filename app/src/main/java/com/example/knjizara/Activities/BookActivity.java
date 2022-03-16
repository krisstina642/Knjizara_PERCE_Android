package com.example.knjizara.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.knjizara.Adapters.CommentAdapter;
import com.example.knjizara.Database;
import com.example.knjizara.ExpandableHeightGridView;
import com.example.knjizara.R;
import com.example.knjizara.Types.Book;
import com.example.knjizara.Types.Comment;
import com.example.knjizara.Utility;

import java.util.ArrayList;

public class BookActivity extends LoggedActivityBase {
    static public Book book;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);

        Utility.initToolbar(this);

        Intent intent = getIntent();
        book= Database.getBook(Integer.parseInt(intent.getStringExtra("bookId")));

        ((TextView)findViewById(R.id.name)).setText(book.getName());
        ((TextView)findViewById(R.id.author)).setText(book.getAuthor());
        ((TextView)findViewById(R.id.num_of_pages)).setText("Broj strana : "+book.getNum_of_pages());
        ((TextView)findViewById(R.id.year)).setText("Godina izdavanja : "+book.getYear());
        ((TextView)findViewById(R.id.description)).setText(book.getDescription());
        ((TextView)findViewById(R.id.rating_text)).setText("Ocena: "+book.getRating());
        ((RatingBar)findViewById(R.id.ratingBar)).setRating(book.getRating());

        ((ImageView)findViewById(R.id.image)).setImageResource(getResources().getIdentifier(book.getImage(), "drawable", getPackageName()));
        if (book.isSale()) ((ImageView)findViewById(R.id.sale)).setImageResource(R.drawable.akcija);

        ExpandableHeightGridView mAppsGrid = (ExpandableHeightGridView) findViewById(R.id.comments);
        mAppsGrid.setExpanded(true);

        ArrayList<Comment> comments = Database.getBookComments(book.getId());
        CommentAdapter adapter= new CommentAdapter(this, comments);
        GridView gw=findViewById(R.id.comments);
        gw.setAdapter(adapter);
    }
}