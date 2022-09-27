package com.example.reviewerjava.data.mock;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.model.Shop;
import com.example.reviewerjava.data.repository.RegisterRepository;
import com.example.reviewerjava.data.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class MockBase implements Repository, RegisterRepository {

    private MutableLiveData<Boolean> loggedIn;
    private MutableLiveData<List<Review>> data;
    private List<Review> list;

    public MockBase(){
        list = new ArrayList<>();

        List <Shop> shopList = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        cities.add("Moscow");
        cities.add("Belgorod");
        cities.add("Vladimir");
        cities.add("Khimki");
        Shop shop = new Shop("asdasd", cities);
        shopList.add(shop);

        list.add(new Review(
                "ARTICLE 1", "OIUASFHDIAUHFOAIUHFOAIUGHFOAIUHOFIUHAOSUFH", "21.08.2012",
                new Author("Aboba", "New-Mirea"),
                "asdasd",
                new Item("mobilka", shopList)
        ));

        list.add(new Review(
                "ARTICLE 2", "ASJFHNPOAJSFHOHASPFOHIJAOSFIHJAPFHPAISHFPAISHFAF", "31.08.3012",
                new Author("asdasdasda", "Ndasdasdasdasda"),
                "xvcbxcvbxcvbxvcb",
                new Item("mob?L:JKa", shopList)
        ));

        list.add(new Review(
                "ARTICLE 3", "L;VKJKB[XCVB[XPCVJOBCVB", "31.08.3012",
                new Author("asdasdasda", "Ndasdasdasdasda"),
                "xvcbxcvbxcvbxvcb",
                new Item("mob?L:JKa", shopList)
        ));
        list.add(new Review(
                "ARTICLE 4", "ASJFH6984556435135132132121ISHFAF", "31.08.3012",
                new Author("asasdfasdgasda", "479okur,jyfsfgnsf"),
                "xvcbxcvbxcvbasgxvcb",
                new Item("pgaeruiopghr", shopList)
        ));
        list.add(new Review(
                "ARTICLE 5", "1236548941232316518919849878491351351321358498794651231", "31.08.3012",
                new Author("asdasdasda", "Ndasdasdasdasda"),
                "xvcbxcvbxcvbxvcb",
                new Item("mob?L:JKa", shopList)
        ));
        data = new MutableLiveData<>(list);
        loggedIn = new MutableLiveData<>(false);
    }

    @Override
    public MutableLiveData<List<Review>> getReviewList() {
        return data;
    }

    @Override
    public void createNewReview(Review review) {
        list.add(review);
        data.setValue(list);
    }

    @Override
    public MutableLiveData<Boolean> getLoggedIn() {
        return loggedIn;
    }

    @Override
    public boolean login(String login, String password) {
        if(login.equals("admin") && password.equals("admin")){
            loggedIn.setValue(true);
            return true;
        } else return false;
    }

    @Override
    public void logOut() {
        loggedIn.setValue(false);
    }
}
