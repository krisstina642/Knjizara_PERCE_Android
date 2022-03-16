package com.example.knjizara;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.knjizara.Activities.BookActivity;
import com.example.knjizara.Activities.LoginActivity;
import com.example.knjizara.Activities.MainActivity;
import com.example.knjizara.Activities.ProfilActivity;
import com.example.knjizara.Activities.RecommendationsPageActivity;
import com.example.knjizara.Activities.SearchResultsActivity;
import com.example.knjizara.Adapters.BookAdapter;
import com.example.knjizara.Adapters.UserAdapter;
import com.example.knjizara.Types.Book;
import com.example.knjizara.Types.Comment;
import com.example.knjizara.Types.User;
import com.example.knjizara.Types.Friendship;
import com.example.knjizara.Types.UserListType;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class Utility extends AppCompatActivity {
    static User user;
    static AlertDialog alert;

    public static void setUser(User user) {
        Utility.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static boolean logIn(String username, String password) {
        ArrayList<User> ak = Database.getArrayOfUsers();
        for (User k: ak){
            if (k.getUssername().equals(username) && k.getPassword().equals(password)){
                user =k;
                return true;
            }
        }
        return false;
    }

    public static boolean isLogged() {
        return user !=null;
    }

    public static boolean initMenuItemFunctions(AppCompatActivity app, MenuItem item){
        if (!isLogged()) return false;
        Intent intent;
        switch (item.getItemId()){
            case R.id.profile:
                intent=new Intent(app, ProfilActivity.class);
                app.startActivity(intent);
                return true;
            case R.id.recomendations:
                intent=new Intent(app, RecommendationsPageActivity.class);
                app.startActivity(intent);
                return true;
            case R.id.friend_requests:
                initAllFriendsAlert(app, UserListType.REQUESTS);
                return true;
            case R.id.find_friend:
                AlertDialog alert = showAlert(app, R.layout.search);

                EditText et = alert.findViewById(R.id.search_value);
                et.setHint("Korisnicko ime");

                alert.findViewById(R.id.button_search).setOnClickListener(view -> {
                    int id1= user.getId();
                    int id2=-1;
                    String korisnicko_ime2= ((EditText)alert.findViewById(R.id.search_value)).getText().toString();
                    ArrayList<User> arrayListUser = Database.getArrayOfUsers();
                    for (User k: arrayListUser){
                        if (k.getUssername().equals(korisnicko_ime2)){
                            id2=k.getId();
                            break;
                        }
                    }
                    if (id2==-1){
                        Toast.makeText(app, "Korisnik ne postoji", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ArrayList<Friendship> arrayList = Database.getArrayOfFriendships();
                        for (Friendship p : arrayList) {
                            if (p.getStatus() == 1 && ((p.getSender_Id() == id1 && p.getReceiver_id() == id2) || (p.getSender_Id() == id2 && p.getReceiver_id() == id1))) {
                                Toast.makeText(app, "Vec ste prijatelji", Toast.LENGTH_SHORT).show();
                                break;
                            } else if (p.getStatus() == 0 && (p.getSender_Id() == id1 && p.getReceiver_id() == id2)) {
                                Toast.makeText(app, "Zahtev je vec poslat", Toast.LENGTH_SHORT).show();
                                break;
                            } else if (p.getStatus() == 0 && (p.getSender_Id() == id2 && p.getReceiver_id() == id1)) {
                                Toast.makeText(app, "Korisnik je dodat u listu prijatelja", Toast.LENGTH_SHORT).show();
                                p.setStatus(1);
                                break;
                            } else {
                                Toast.makeText(app, "Zahtev je poslat", Toast.LENGTH_SHORT).show();
                                Friendship friendship = new Friendship(id1, id2);
                                break;
                            }
                        }
                    }
                });

                return true;
            case R.id.all_friends:

                initAllFriendsAlert(app, UserListType.FRIENDSHIPS);

                return true;
            case R.id.logout:
                intent=new Intent(app, MainActivity.class);
                Utility.logOut();
                app.startActivity(intent);
                return true;
        }
        return false;

    }

    public static void logOut(){
        user =null;
    }

    public static void initloginToolbarFunction(AppCompatActivity app){
        if (app.getClass()!= LoginActivity.class) {
            app.findViewById(R.id.login).setOnClickListener(view -> {
                Intent intent = new Intent(app, LoginActivity.class);
                app.startActivity(intent);
            });
        }
    }

    public static void initToolbar(AppCompatActivity app){
        if (Utility.isLogged() && app.getClass()!= BookActivity.class){
            Toolbar myToolbar = (Toolbar) app.findViewById(R.id.toolbar_logged);
            app.setSupportActionBar(myToolbar);
            ((ViewManager)app.findViewById(R.id.toolbar_unlogged_layout).getParent()).removeView(app.findViewById(R.id.toolbar_unlogged_layout));
            ((ViewManager)app.findViewById(R.id.toolbar_logged_book_layout).getParent()).removeView(app.findViewById(R.id.toolbar_logged_book_layout));
        }
        else if (Utility.isLogged()){
            Toolbar myToolbar = (Toolbar) app.findViewById(R.id.toolbar_logged_book);
            app.setSupportActionBar(myToolbar);
            ((ViewManager)app.findViewById(R.id.toolbar_unlogged_layout).getParent()).removeView(app.findViewById(R.id.toolbar_unlogged_layout));
            ((ViewManager)app.findViewById(R.id.toolbar_logged_layout).getParent()).removeView(app.findViewById(R.id.toolbar_logged_layout));
            initBookOptions(app);
        }
        else{
            Toolbar myToolbar = (Toolbar) app.findViewById(R.id.toolbar_unlogged);
            app.setSupportActionBar(myToolbar);
            ((ViewManager)app.findViewById(R.id.toolbar_logged_layout).getParent()).removeView(app.findViewById(R.id.toolbar_logged_layout));
            ((ViewManager)app.findViewById(R.id.toolbar_logged_book_layout).getParent()).removeView(app.findViewById(R.id.toolbar_logged_book_layout));
            Utility.initloginToolbarFunction(app);
        }
        initToolbarSearchFunction(app);
        initToolbarLogo(app);
    }

    public static void initToolbarLogo(AppCompatActivity app){
        if (app.getClass()!=MainActivity.class) {
            app.findViewById(R.id.logo).setOnClickListener(view -> {
                Intent intent = new Intent(app, MainActivity.class);
                app.startActivity(intent);
            });
        }
    }

    public static void initBookOptions(AppCompatActivity app){
            app.findViewById(R.id.book).setOnClickListener(view -> {

                AlertDialog alert=showAlert(app, R.layout.book_options);

                alert.findViewById(R.id.recomend).setOnClickListener(view1 -> {
                    alert.dismiss();
                    initAllFriendsAlert(app, UserListType.RECOMMEND);
                });

                alert.findViewById(R.id.rate).setOnClickListener(view1 -> {
                    alert.dismiss();
                    AlertDialog alertDialog= showAlert(app, R.layout.give_rating);
                    Comment c= Database.getComment(BookActivity.book.getId(), user.getId());
                    if (c!=null && c.getRating()>=0) {
                        ((RatingBar)alertDialog.findViewById(R.id.ratingBar)).setRating(c.getRating());
                    }
                    else ((RatingBar)alertDialog.findViewById(R.id.ratingBar)).setRating(0);

                    alertDialog.findViewById(R.id.button_close).setOnClickListener(view2 -> {
                       if (c!=null) c.setRating(((RatingBar)alertDialog.findViewById(R.id.ratingBar)).getRating());
                       else new Comment(BookActivity.book.getId(), user.getId(), null, ((RatingBar)alertDialog.findViewById(R.id.ratingBar)).getRating());

                        alertDialog.dismiss();
                    });
                });
                alert.findViewById(R.id.comment).setOnClickListener(view1 -> {
                    alert.dismiss();
                    AlertDialog alertDialog= showAlert(app, R.layout.search);
                    Comment c= Database.getComment(BookActivity.book.getId(), user.getId());

                    if (c!=null && c.getComment()!=null) ((EditText)alertDialog.findViewById(R.id.search_value)).setText(c.getComment());
                    else ((EditText)alertDialog.findViewById(R.id.search_value)).setHint("Unesite komentar");

                    ((Button)alertDialog.findViewById(R.id.button_search)).setText("Potvrdi");
                    ((Button)alertDialog.findViewById(R.id.button_search)).setOnClickListener(view2 -> {

                        if (c!=null && c.getComment()!=null) c.setComment(((EditText)alertDialog.findViewById(R.id.search_value)).getText().toString());
                        else new Comment(BookActivity.book.getId(), user.getId(), ((EditText)alertDialog.findViewById(R.id.search_value)).getText().toString());
                        alertDialog.dismiss();
                        app.finish();
                        app.startActivity(app.getIntent());

                    });
                });
            });

    }

    public static void initMainPart(AppCompatActivity app){

        int[] mImages = Database.mImages;
        String[] mDescriptions = Database.mDescriptions;

        CarouselView carouselView = app.findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });

        ArrayList<Book> arrayOfBooks = Database.getArrayOfBooks();

        ArrayList<Book> arrayOfBooksNoSale = new ArrayList<>();
        ArrayList<Book> arrayOfBooksSale = new ArrayList<>();
        for (Book k : arrayOfBooks) {
            if (!k.isSale()) arrayOfBooksNoSale.add(k);
            else arrayOfBooksSale.add(k);
        }

        ExpandableHeightGridView mAppsGrid = (ExpandableHeightGridView) app.findViewById(R.id.gridview);
        mAppsGrid.setExpanded(true);

        BookAdapter adapter = new BookAdapter(app, arrayOfBooksNoSale);
        GridView gv = (GridView) app.findViewById(R.id.gridview);
        gv.setAdapter(adapter);

        CarouselView carouselView2 = app.findViewById(R.id.carousel2);
        carouselView2.setPageCount(arrayOfBooksSale.size());
        carouselView2.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Drawable bookImage = ContextCompat.getDrawable(app, app.getResources().getIdentifier(arrayOfBooksSale.get(position).getImage(), "drawable", app.getPackageName()));
                LayerDrawable finalDrawable = new LayerDrawable(new Drawable[]{bookImage, ContextCompat.getDrawable(app, R.drawable.akcija)});
                ;
                imageView.setImageDrawable(finalDrawable);
            }
        });

        carouselView2.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(app, BookActivity.class);
                intent.putExtra("bookId", String.valueOf((arrayOfBooksSale.get(position)).getId()));
                app.startActivity(intent);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                AlertDialog alert=showAlert(app,R.layout.event);

                ImageView iw = alert.findViewById(R.id.dogadjaj_slika);
                iw.setImageResource(mImages[position]);

                TextView tw = alert.findViewById(R.id.dogadjaj_tekst);
                tw.setText(mDescriptions[position]);
            }
        });
    }

    public static void initToolbarSearchFunction(AppCompatActivity app){

            app.findViewById(R.id.search).setOnClickListener(view -> {

            AlertDialog alert = showAlert(app,R.layout.search);

            alert.findViewById(R.id.button_search).setOnClickListener(view1 -> {
                Intent intent = new Intent(app, SearchResultsActivity.class);
                final EditText et= (EditText) alert.findViewById(R.id.search_value);
                // Log.v("EditText", et.getText().toString());
                alert.dismiss();
                intent.putExtra("value", et.getText().toString());
                app.startActivity(intent);
            });

        });
    }

    static AlertDialog showAlert(AppCompatActivity app, int layout_resource){
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(app);
        LayoutInflater inflater2 = app.getLayoutInflater();
        View dialogView = inflater2.inflate(layout_resource, null);
        builder.setView(dialogView);
        alert = builder.create();
        alert.show();
        return alert;
    }

     static void initAllFriendsAlert(AppCompatActivity app, UserListType type){
        alert=showAlert(app,R.layout.all_friends);
        update(type);
        ((Button)alert.findViewById(R.id.button_close)).setOnClickListener(view -> {
            alert.dismiss();
        });
    }

    public static void update(UserListType type){
        ArrayList<User> arrayUsers;
        if (type==UserListType.REQUESTS) arrayUsers= Database.getRequests(user);
        else if (type==UserListType.FRIENDSHIPS) arrayUsers=Database.getFriends(user);
        else arrayUsers=Database.getNotRecomendedBook(user,BookActivity.book);

        UserAdapter adapter = new UserAdapter(alert.getContext(), arrayUsers, type);
        GridView gv = (GridView) alert.findViewById(R.id.gridview);
        gv.setAdapter(adapter);
    }
}
