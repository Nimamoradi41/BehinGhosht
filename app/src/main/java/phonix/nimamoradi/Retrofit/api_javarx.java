package phonix.nimamoradi.Retrofit;

import io.reactivex.Observable;
import phonix.nimamoradi.showCat_api.model_data_showCat;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface api_javarx {
    @POST("showCat.php")
    Observable<model_data_showCat> getPosts(@Path("id") String subject);
}
