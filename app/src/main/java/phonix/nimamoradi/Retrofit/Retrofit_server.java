package phonix.nimamoradi.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_server  {
          Retrofit retrofit=null;
          String base_url="http://fonixmall.com/requestMobile/";

    public Retrofit_server() {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(base_url)
                    .build();
        }


    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
