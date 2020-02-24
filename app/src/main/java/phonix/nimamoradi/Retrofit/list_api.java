package phonix.nimamoradi.Retrofit;

import phonix.nimamoradi.festivalList_api.main_festivallist_api;
import phonix.nimamoradi.login_api.main_login_api;
import phonix.nimamoradi.mainPage_api.main_mainPage_api;
import phonix.nimamoradi.modeldata_showcate_and_showsubcate_and_search;
import phonix.nimamoradi.openFestival_api.main_openfestival_api;
import phonix.nimamoradi.search_Api.main_search_api;
import phonix.nimamoradi.shoppingCard_api.main_shoppingCard_api;
import phonix.nimamoradi.showCat_api.model_data_showCat;
import phonix.nimamoradi.showProduct_api.main_showProduct_api;
import phonix.nimamoradi.showSubCat_api.main_showSubCat_api;
import phonix.nimamoradi.submit_api.main_submit_api;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
public interface list_api {
    @POST("showCat.php")
    @FormUrlEncoded
    Call<model_data_showCat> getshowCat(@Field("id") String id);
    @POST("showCat.php")
    @FormUrlEncoded
    Call<modeldata_showcate_and_showsubcate_and_search> getshowCat_main(@Field("id") String id);




    @POST("shoppingCard.php")
    @FormUrlEncoded
    Call<main_shoppingCard_api> getshoppinhCard(@Field("id") String id,@Field("userName") String userName,
                                                @Field("userPassword") String userPassword,
                                                @Field("GustId") String GustId,
                                                @Field("count") String count);
    @POST("showSubCat.php")
    @FormUrlEncoded
    Call<modeldata_showcate_and_showsubcate_and_search> getshowSubCat_main(@Field("id") String id);


    @POST("mainPage.php")
    Call<main_mainPage_api>  getmainPage();
    @POST("search.php")
    @FormUrlEncoded
   Call<modeldata_showcate_and_showsubcate_and_search> getsearch_main(@Field("name") String name);
    @POST("login.php")
    @FormUrlEncoded
    Call<main_login_api> getlogin(@Field("userName") String userName,@Field("password") String password,@Field("GuestId") String GuestId);
    @POST("submit.php")
    @FormUrlEncoded
    Call<main_submit_api> getsubmit(@Field("userName")
                                            String userName, @Field("phoneNumber")
            String phoneNumber, @Field("userEmail") String
            userEmail, @Field("userPassword")String userPassword);
    @POST("festivalList.php")
    Call<main_festivallist_api> getfestivallist();
    @POST("openFestival.php")
    @FormUrlEncoded
    Call<main_openfestival_api> getopenfestival(@Field("id") String id);
    @POST("showProduct.php")
    @FormUrlEncoded
    Call<main_showProduct_api> getshowProduct(@Field("id") String id,@Field("userName") String userName,@Field("userPassword") String userPassword);

}
