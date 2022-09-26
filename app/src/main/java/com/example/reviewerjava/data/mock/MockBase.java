package com.example.reviewerjava.data.mock;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.model.Shop;
import com.example.reviewerjava.data.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockBase implements Repository {

    private MutableLiveData<List<Review>> data;
    private List<Review> list;

    @Override
    public MutableLiveData<List<Review>> getReviewList() {

        List <Shop> shopList = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        cities.add("Moscow");
        cities.add("Belgorod");
        cities.add("Vladimir");
        cities.add("Khimki");


        Shop shop = new Shop("asdasd", cities);
        Map<String, String> map = new HashMap<>();
        list.add(new Review(
                map,
                "21.08.2012",
                new Author("Aboba", "New-Mirea"),
                "asdasd",
                new Item("mobilka", shopList)
        ));

        list.add(new Review(
                map,
                "31.08.3012",
                new Author("asdasdasda", "Ndasdasdasdasda"),
                "xvcbxcvbxcvbxvcb",
                new Item("mob?L:JKa", shopList)
        ));

        list.add(new Review(
                map,
                "31.08.3012",
                new Author("asdasdasda", "Ndasdasdasdasda"),
                "xvcbxcvbxcvbxvcb",
                new Item("mob?L:JKa", shopList)
        ));
        list.add(new Review(
                map,
                "31.08.3012",
                new Author("asasdfasdgasda", "479okur,jyfsfgnsf"),
                "xvcbxcvbxcvbasgxvcb",
                new Item("pgaeruiopghr", shopList)
        ));
        list.add(new Review(
                map,
                "31.08.3012",
                new Author("asdasdasda", "Ndasdasdasdasda"),
                "xvcbxcvbxcvbxvcb",
                new Item("mob?L:JKa", shopList)
        ));
        data.setValue(list);
        return data;
    }

    @Override
    public void createNewReview(Review review) {

    }
}
