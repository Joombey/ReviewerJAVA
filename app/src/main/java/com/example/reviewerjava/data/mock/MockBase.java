package com.example.reviewerjava.data.mock;



//public class MockBase implements ReviewListRepository, AuthRepository, AddReviewRepository {
//
//    private MutableLiveData<Boolean> loggedIn;
//    private MutableLiveData<List<Review>> data;
//    private List<Review> list;
//
//    public MockBase(){
//        list = new ArrayList<>();
//        /*List <Shop> shopList = new ArrayList<>();
//        List<String> cities = new ArrayList<>();
//        cities.add("Moscow");
//        cities.add("Belgorod");
//        cities.add("Vladimir");
//        cities.add("Khimki");
//        Shop shop = new Shop("asdasd", cities);
//        shopList.add(shop);
//
//        list.add(new Review(
//                "ARTICLE 1", "OIUASFHDIAUHFOAIUHFOAIUGHFOAIUHOFIUHAOSUFH", "21.08.2012",
//                new User("Aboba", "New-Mirea"),
//                "asdasd",
//                new Item("mobilka", shopList)
//        ));
//
//        list.add(new Review(
//                "ARTICLE 2", "ASJFHNPOAJSFHOHASPFOHIJAOSFIHJAPFHPAISHFPAISHFAF", "31.08.3012",
//                new User("asdasdasda", "Ndasdasdasdasda"),
//                "xvcbxcvbxcvbxvcb",
//                new Item("mob?L:JKa", shopList)
//        ));
//
//        list.add(new Review(
//                "ARTICLE 3", "L;VKJKB[XCVB[XPCVJOBCVB", "31.08.3012",
//                new User("asdasdasda", "Ndasdasdasdasda"),
//                "xvcbxcvbxcvbxvcb",
//                new Item("mob?L:JKa", shopList)
//        ));
//        list.add(new Review(
//                "ARTICLE 4", "ASJFH6984556435135132132121ISHFAF", "31.08.3012",
//                new User("asasdfasdgasda", "479okur,jyfsfgnsf"),
//                "xvcbxcvbxcvbasgxvcb",
//                new Item("pgaeruiopghr", shopList)
//        ));
//        list.add(new Review(
//                "ARTICLE 5", "1236548941232316518919849878491351351321358498794651231", "31.08.3012",
//                new User("asdasdasda", "Ndasdasdasdasda"),
//                "xvcbxcvbxcvbxvcb",
//                new Item("mob?L:JKa", shopList)
//        ));*/
//        data = new MutableLiveData<>(list);
//        loggedIn = new MutableLiveData<>(false);
//    }
//
//    @Override
//    public LiveData<List<ReviewEntity>> getReviewList() {
//        return (MutableLiveData) data;
//    }
//
//    @Override
//    public void addReview(ReviewEntity report) {
//        list.add(report);
//        data.setValue(list);
//    }
//
//
//    public MutableLiveData<Boolean> isLoggedIn() {
//        return loggedIn;
//    }
//
//    @Override
//    public boolean signIn(String login, String password) {
//        if(login.equals("admin") && password.equals("admin")){
//            loggedIn.setValue(true);
//            return true;
//        } else return false;
//    }
//
//    public void logOut() {
//        loggedIn.setValue(false);
//    }
//
//    @Override
//    public PermissionEntity getPermission(String role) {
//        return null;
//    }
//
//    @Override
//    public ReviewEntity getReviewById(int id) {
//        return null;
//    }
//
//    @Override
//    public LiveData<List<ReviewEntity>> getReviewsByUserId(int userId) {
//        return null;
//    }
//}
