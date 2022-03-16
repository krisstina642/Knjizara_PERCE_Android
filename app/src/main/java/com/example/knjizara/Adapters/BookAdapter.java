package com.example.knjizara.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.knjizara.Activities.BookActivity;
import com.example.knjizara.R;
import com.example.knjizara.Types.Book;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context mContext;
    public BookAdapter(Context context, ArrayList<Book> knjige) {
        super(context, 0, knjige);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book, parent, false);
        }

        TextView tvAutor = (TextView) convertView.findViewById(R.id.author);
        TextView tvNaziv = (TextView) convertView.findViewById(R.id.name);
        ImageView tvImage = (ImageView) convertView.findViewById(R.id.image);
        ImageView tvAkcija = (ImageView) convertView.findViewById(R.id.sale);
        convertView.findViewById(R.id.book).setOnClickListener(view -> {
            Intent intent = new Intent(mContext, BookActivity.class);
            intent.putExtra("bookId", String.valueOf(book.getId()));
            mContext.startActivity(intent);
        });

        tvAutor.setText(book.getAuthor());
        tvNaziv.setText(book.getName());
        tvImage.setImageResource(getDrawable(mContext, book.getImage()));
        if (book.isSale()) tvAkcija.setImageResource(R.drawable.akcija);

        return convertView;
    }

    public static int getDrawable(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
