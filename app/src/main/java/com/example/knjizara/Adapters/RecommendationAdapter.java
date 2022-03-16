package com.example.knjizara.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.knjizara.Database;
import com.example.knjizara.R;

import java.util.ArrayList;
import com.example.knjizara.Types.Recommendation;
import com.example.knjizara.Types.User;


public class RecommendationAdapter extends ArrayAdapter<Recommendation> {
    private Context mContext;
    public RecommendationAdapter(Context context, ArrayList<Recommendation> rec) {
        super(context, 0, rec);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Recommendation recommendation = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recommendation, parent, false);
        }

        TextView name_and_username = (TextView) convertView.findViewById(R.id.name_and_ussername);
        TextView book_name = (TextView) convertView.findViewById(R.id.book_name);
        TextView book_author = (TextView) convertView.findViewById(R.id.book_author);
        User user=Database.getUser(recommendation.getSender_Id());

        name_and_username.setText(user.getName()+" ( "+user.getUssername()+" ) ");
        book_name.setText(recommendation.getBook().getName());
        book_author.setText(recommendation.getBook().getAuthor());


        ImageView tvImage = (ImageView) convertView.findViewById(R.id.image);
        ImageView tvAkcija = (ImageView) convertView.findViewById(R.id.sale);
        tvImage.setImageResource( getDrawable(mContext, recommendation.getBook().getImage()));
        if (recommendation.getBook().isSale()) tvAkcija.setImageResource(R.drawable.akcija);

        return convertView;
    }

    public static int getDrawable(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
