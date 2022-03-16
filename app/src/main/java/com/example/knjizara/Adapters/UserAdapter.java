package com.example.knjizara.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.knjizara.Activities.BookActivity;
import com.example.knjizara.Database;
import com.example.knjizara.R;
import com.example.knjizara.Types.User;
import com.example.knjizara.Types.UserListType;
import com.example.knjizara.Utility;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private UserListType type;

    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
        mContext = context;
    }
    public UserAdapter(Context context, ArrayList<User> users, UserListType type) {
        super(context, 0, users);
        mContext = context;
        this.type=type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);

        if (convertView == null) {
          if (type==UserListType.FRIENDSHIPS)  convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_show, parent, false);
          else if (type==UserListType.REQUESTS) convertView = LayoutInflater.from(getContext()).inflate(R.layout.request_show, parent, false);
          else convertView = LayoutInflater.from(getContext()).inflate(R.layout.recommend_show, parent, false);
        }
        TextView tvName= (TextView) convertView.findViewById(R.id.name);
        TextView tvUssername = (TextView) convertView.findViewById(R.id.ussername);
        ImageView tDelete = (ImageView) convertView.findViewById(R.id.delete);
        ImageView tAccept = (ImageView) convertView.findViewById(R.id.accept);

        if (tDelete!=null) tDelete.setOnClickListener(view -> {
            Database.deleteFriendship(Utility.getUser().getId(), user.getId());
        });
        if (tAccept!=null && type==UserListType.REQUESTS) tAccept.setOnClickListener(view -> {
            Database.acceptRequest(user.getId(), Utility.getUser().getId());
        });
        else if (tAccept!=null) tAccept.setOnClickListener(view -> {
            Database.addRecommendation(Utility.getUser().getId(), user.getId(), BookActivity.book);
        });

        tvName.setText(user.getName());
        tvUssername.setText(user.getUssername());

        return convertView;
    }
}
