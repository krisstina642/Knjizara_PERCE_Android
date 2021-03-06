package com.example.knjizara;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knjizara.Types.Book;
import com.example.knjizara.Types.Comment;
import com.example.knjizara.Types.Recommendation;
import com.example.knjizara.Types.User;
import com.example.knjizara.Types.Friendship;
import com.example.knjizara.Types.UserListType;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    Database(){
        createDatabase();
    }

    static public Comment getComment(int book_id, int user_id){
        for (Comment c:arrayOfComments){
            if (c.getBook_id()==book_id && user_id==c.getUser_id()) return c;
        }
        return null;
    }

    static public ArrayList<Recommendation> getRecommendations(int user_id){
        ArrayList<Recommendation> list=new ArrayList<>();
        for (Recommendation r: arrayOfRecommendations){
            if( r.getReceiver_id()==user_id) list.add(r);
        }
        return list;
    }

    static public void addFriendship(Friendship p){
        arrayOfFriendships.add(p);
    }

    static public void addComment(Comment p){
        for (Comment c: arrayOfComments){
            if (c.getBook_id()==p.getBook_id() && c.getUser_id()==p.getUser_id() && c.getComment()==null){
                c.setComment(p.getComment());
                return;
            }
            else if (c.getBook_id()==p.getBook_id() && c.getUser_id()==p.getUser_id() && c.getRating()<0){
                c.setRating(p.getRating());
                return;
            }
        }
        arrayOfComments.add(p);
        Log.v("aaa","pozzz funx");
    }

    static public void acceptRequest(int sender, int receiver){
        for (Friendship f: arrayOfFriendships){
            if (f.getSender_Id()==sender && f.getReceiver_id()==receiver){
                f.setStatus(1);
                Utility.update(UserListType.REQUESTS);
                break;
            }
        }
    }

    static public ArrayList<User> getFriends(User user){
        ArrayList<Integer> friendId= new ArrayList<>();
        for (Friendship f: arrayOfFriendships){
            if (f.getStatus()==1 && f.getReceiver_id()==user.getId()){
                friendId.add(f.getSender_Id());
            }
            else if (f.getStatus()==1 && f.getSender_Id()==user.getId()){
                friendId.add(f.getReceiver_id());
            }
        }
        ArrayList<User> friends=new ArrayList<>();
        for (Integer i :friendId){
            friends.add(arrayOfUsers.get(i));
        }
        return friends;
    }

    static public ArrayList<User> getRequests(User user){
        ArrayList<Integer> friendId= new ArrayList<>();
        for (Friendship f: arrayOfFriendships){
            if (f.getStatus()==0 && f.getReceiver_id()==user.getId()){
                friendId.add(f.getSender_Id());
            }
        }
        ArrayList<User> friends=new ArrayList<>();
        for (Integer i :friendId){
            friends.add(arrayOfUsers.get(i));
        }
        return friends;
    }

    static private ArrayList<Book> arrayOfBooks;
    static private ArrayList<Comment> arrayOfComments;
    static private ArrayList<User> arrayOfUsers;
    static private ArrayList<Friendship> arrayOfFriendships;
    static private ArrayList<Recommendation> arrayOfRecommendations;

    public static ArrayList<Book> getArrayOfBooks() {
        if (arrayOfUsers==null) createDatabase();
        return arrayOfBooks;
    }

    public static ArrayList<Friendship> getArrayOfFriendships() {
        if (arrayOfUsers==null) createDatabase();
        return arrayOfFriendships;
    }

    public static ArrayList<Comment> getArrayOfComments() {
        if (arrayOfUsers==null) createDatabase();
        return arrayOfComments;
    }
    public static ArrayList<User> getArrayOfUsers() {
        if (arrayOfUsers==null) createDatabase();
        return arrayOfUsers;
    }

    static public int[] mImages = new int[]{
            R.drawable.image1, R.drawable.image2, R.drawable.image3 };
    static public String[] mDescriptions = new String[]{
            "18.11. 2021. knji??ara Perce slavi 5 godina postojanja. Kupovinom u na??im radnjama na ovaj datum dobijate vau??er od 1000 dinara koji mo??ete iskoristiti do kraja godine.",
            "S obzirom na predstoje??e praznike pripremili smo vam dodatni popust od 30% na sav asortiman",
            "Povodom predstoje??eg sajma knjiga u porostoru knji??are 15.12. bi??e organizovano potpisivanje najnovijeg romana Mare Mari??. Posetioci se mole da nose maske i postuju predvidjenu distancu. "
    };

    public static void createDatabase(){

        arrayOfBooks = new ArrayList<>();
        arrayOfUsers = new ArrayList<>();
        arrayOfFriendships = new ArrayList<>();
        arrayOfComments = new ArrayList<>();
        arrayOfRecommendations = new ArrayList<>();

       arrayOfUsers.add( new User(0,"pera","peric", "pera", "pera123", "Loznica", "0612345678","Karadjordjeva", "bb"));
       arrayOfUsers.add( new User(1,"zika","zikic", "zika", "zika123", "Loznica", "0612345678","Karadjordjeva", "bb"));
       arrayOfUsers.add( new User(2,"mara","maric", "mara", "mara123", "Loznica", "0612345678","Karadjordjeva", "bb"));
       arrayOfUsers.add( new User(3,"p","maric", "", "", "Loznica", "0612345678","Karadjordjeva", "bb"));

       arrayOfFriendships.add(new Friendship(1,2, 1));
       arrayOfFriendships.add(new Friendship(3,2, 1));
       arrayOfFriendships.add(new Friendship(3,1, 1));
       arrayOfFriendships.add(new Friendship(0,3, 0));

       new Comment(1,1,"Divno");
       new Comment(1,2,"Predobra knjiga");
       new Comment(0,2,"Lepo");

       arrayOfBooks.add(new Book(0,"Igre gladi - Sjaj slobode", "Suzanne Collins", "DA LI BI MOGAO DA PRE??IVI?? SAM U DIVLJINI, DOK SE SVI TRUDE DA TI ONEMOGU??E DA ??IV DO??EKA?? JUTRO?\n\n U ru??evinama podru??ja nekada poznatog pod imenom Severna Amerika, nalazi se zemlja Panem, sa blistavim glavnim gradom Kapitolom i dvanaest distrikta. Kapitol je surov i okrutan, dr??i distrikte u pokornosti tako ??to ih primorava da svake godine ??alju po jednog de??aka i jednu devoj??icu, od dvanaest do osamnaest godina, koji ??e u??estvovati u Igrama gladi ??? borbi do smrti koju u??ivo prenosi televizija.\n\n??esnaestogodi??nja Ketnis Everdin, koja ??ivi sa majkom i mla??om sestrom, smatra da je potpisala sebi smrtnu presudu kada se dobrovoljno prijavila da u??estvuje u Igrama umesto sestre. Me??utim, bila je na vratima smrti i ranije ??? pre??ivljavanje je njena druga priroda. Mada joj to nije bila namera, postala je pravi borac. Me??utim, ako ??eli da pobedi, mora??e da bira izme??u pre??ivljavanja i humanosti, izme??u ??ivota i ljubavi.\nAvantura i romansa stapaju se u ovom uzbudljivom romanu ??ija se radnja odvija u budu??nosti koja na veoma zabrinjavaju??i na??in podse??a na na??u sada??njost.",
                "knjiga1", 2009,287,true, (float)555.0,(float)4.5));
       arrayOfBooks.add(new Book(1, "Harry Potter - Zatvorenik iz Askabana", "J. K. Rowling", "Tre??a hogvortska godina je na pragu, a leto koje joj prethodi kao da nikada nije bilo du??e... Darslijevi, suroviji nego ikada pre, mu??traju i maltretiraju Harija za svaku sitnicu. Za to vreme, zemlja je u strahu i panici. I to oba njena sveta, i normalski I ??arobnja??ki. Razlog je Sirijus Blek, jezivi i monstruozni ubica koji je pobegao iz najbolje ??uvanog zatvora na svetu ??? Askabana, kako bi podmirio jo?? nekoliko starih ra??una... Romori se da mu je cilj samo jedan ??? sam Hari Poter koji mu pripada na neki sasvim poseban na??in... ",
                        "knjiga2", 2015,336,true, 1000,(float)4.1));
       arrayOfBooks.add(new Book(2, "Starac i more", "Ernest Hengvej", "Starac i more je verovatno najpoznatiji roman najpoznatijeg pisca na svetu. Pri??a o ribaru sa Kube koji se tokom tri dana bori sa silama prirode da pre??ivi na otvorenom moru. Ali i sa svojom ljudskom naravi koja, sa??injena od slabosti, ponosa, strahova i svesti o svojoj krhkoj prirodi, mo??e veoma lako postati i glavni protivnik u borbi za ??ivot. \n Pred ??itaocima je ponovo remek-delo neprolazne vrednosti koje jednostavnim, a ??ivim, ubedljivim i majstorski izvedenim jezikom obra??uje temu hrabrosti da se izna??e li??na pobeda u susretu sa o??iglednim porazom. Potvrda za ovo je ??to je Starac i more jedan od samo devet romana koja je Nobelov komitet ikada eksplicitno naveo u svom obrazlo??enju za odluku o dodeljivanju najve??e knji??evne nagrade na svetu. Hemingvej nam je ovim delom ostavio kolosalni dokaz svog knji??evnog genija i veli??anstveni doprinos knji??evnosti XX veka.",
                        "knjiga4", 2009,103,false, (float)700.0,(float)4.7));
       arrayOfBooks.add(new Book(3, "??ivotinjska farma", "D??ord?? Orvel", "Kultna knjiga XX veka.\n ???Slabi ili jaki, pametni ili priprosti, svi smo mi bra??a. Nijedna ??ivotinja nikad ne sme ubiti neku drugu. Sve ??ivotinje su jednake.\n A sada, drugovi, ispri??a??u vam ??ta sam pro??le no??i sanjao. Ne mogu vam opisati taj san. Bio je to san o tome kako ??e zemlja izgledati po??to ??ovek sa nje i????ezne. Ali podsetio me je na ne??to ??to sam odavno ve?? zaboravio.???\nPovod za nastanak ??ivotinjske farme nalazio se u Orvelovoj analizi posledica Ruske revolucije koja je prerasla u totalitarni re??im i diktaturu stvorenu oko kulta li??nosti, kao i u njegovom iskustvu ste??enom tokom ??panskog gra??anskog rata. Iako je javnost ??esto insistirala na momentu kritike sovjetske vlasti, Orvel je uvek nagla??avao da se ??ivotinjska farma iako prvenstveno satira o Ruskoj revoluciji, odnosi na svaku nasilnu revoluciju koju predvode nemarni ljudi gladni vlasti. ???Hteo sam da naravou??enije bude da revolucije donose radikalno pobolj??anje samo kada su mase budne i znaju kako da zbace svoje vo??e ??im ovi obave svoj posao. Trebalo je da prekretnica u pri??i bude trenutak kada svinje zadr??e mleko i jabuke za sebe???, isticao je Orvel razo??aran ??to je ??itaocima mo??da promakao ovaj klju??ni momenat teksta. \n I pored pi????eve bojazni, ova alegorijska antiutopija odmah je privukla veliku pa??nju. Danas se smatra jednim od najboljih romana XX veka, postala je neizostavna lektira svakog savremenog ??itaoca, a 1996. godine dobila je nagradu Hugo. Danas je mo??da vi??e nego ikad pre potrebno da ovo delo ??itamo i razumemo.\n ???Sve ??ivotinje su jednake,\n ali neke ??ivotinje su jednakije \n od drugih.??? \n Post scriptum \n Orvel je za prvo izdanje napisao predgovor pod naslovom Sloboda ??tampe. Izdava?? je odbio da ga ??tampa. Tekst je bio gotovo zaboravljen do 1972. godine, kada je objavljen u Knji??evnom dodatku Timesa. Sada se prvi put pojavljuje na srpskom jeziku.",
                        "knjiga5", 1999,160,false, 1300,5));
       arrayOfBooks.add(new Book(4, "Orkanski visovi", "Emili Bronte", "Remek-delo engleske i svetske knji??evnosti, jedna od najlep??ih ljubavnih pri??a svih vremena. Pri??a o fatalnoj ljubavi i kobnoj osveti, o tajnama i protivre??nostima skrivenim u dubinama ljudskog srca. Ho??e li se duhovi Orkanskih visova ikada umiriti? \n Usamljena vresi??ta severne Engleske. Dve porodice, dva suprotstavljena sveta destruktivnih strasti i stidljivog konformizma. Neprevazi??enom dramskom snagom bezvremenog klasika, surova stvarnost svakodnevnog ??ivota stapa se sa neutoljivim strastima, dubokim emocijama i gnevom osvete, pripovedaju??i o nesre??i ljudskog roda sputanog i pora??enog sopstvenim ograni??enjima. \n Ova tragi??na ljubavna pri??a, burna poput severnog vetra koji bri??e pustarama Jork??ira, obuhvata dve generacije ??? od vremena kada je Hitklif, ostav??i sam na svetu, do??ao da ??ivi na imanju porodice Ern??o, pa sve do njegove smrti mnogo godina kasnije.\n Jedini roman Emili Bronte uzbudljiv je viktorijanski klasik o snazi ljubavi koja, uprkos svemu, pa i smrti, ??ivi ve??no. Ketrin i Hitklif nezaboravni su likovi koji i dan-danas o??aravaju ??itaoce neobuzdanom snagom svojih karaktera.",
                        "knjiga3", 2020,334,false, 688,5));

       new Recommendation(1,3, arrayOfBooks.get(2));
       new Recommendation(2,3, arrayOfBooks.get(2));
        new Recommendation(1,3, arrayOfBooks.get(3));
        new Recommendation(2,3, arrayOfBooks.get(3));
        new Recommendation(1,3, arrayOfBooks.get(1));
        new Recommendation(2,3, arrayOfBooks.get(1));
        new Recommendation(3,1, arrayOfBooks.get(0));

    }

    static public String getUsersName(int user_id){
        return getUser(user_id).getName();
    }
    static public User getUser(int user_id){
        return arrayOfUsers.get(user_id);
    }

    static public void deleteFriendship(int id1, int id2){
        Friendship del = null;
        for (Friendship f: arrayOfFriendships){
            if ((f.getSender_Id()==id1 && f.getReceiver_id()==id2) || (f.getSender_Id()==id2 && f.getReceiver_id()==id1)){
                del=f;
                break;
            }
        }
        if (del!=null) {
            arrayOfFriendships.remove(del);
            Utility.update((del.getStatus()==1)?UserListType.FRIENDSHIPS:UserListType.REQUESTS);
        }
    }

    public static void addRecommendation(Recommendation r){
        if (arrayOfRecommendations==null) createDatabase();
        Database.arrayOfRecommendations.add(r);
    }

    public static ArrayList<User> getNotRecomendedBook(User user, Book book){
        ArrayList<User> friends=getFriends(user);

        ArrayList<User> arrayList=getFriends(user);
        for (User u: friends){
            for (Recommendation r :arrayOfRecommendations){
                if (r.getSender_Id()== user.getId() && r.getReceiver_id()==u.getId() && book.getId()==r.getBook().getId()) {
                    arrayList.remove(u);
                    break;
                }
            }
        }
        return arrayList;

    }

    public static void addRecommendation(int sender, int receiver, Book book){
        new Recommendation(sender,receiver,book);
        Utility.update(UserListType.RECOMMEND);
    }

    public static Book getBook(int id){
        return arrayOfBooks.get(id);
    }

    public static ArrayList<Comment> getBookComments(int book_id){
        ArrayList<Comment> comm= new ArrayList<>();
        for (Comment c: arrayOfComments){
            if (c.getBook_id()==book_id && c.getComment()!=null) comm.add(c);
        }
        return comm;
    }

}
