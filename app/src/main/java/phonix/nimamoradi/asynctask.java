//package phonix.nimamoradi;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//import phonix.nimamoradi.Retrofit.Retrofit_server;
//import phonix.nimamoradi.Retrofit.list_api;
//import phonix.nimamoradi.showCat_api.model_data_showCat;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class asynctask extends AsyncTask<Void,Void, model_data_showCat> {
//    Context context;
//   static   Interface_asynctask asynctask;
//    public void Get_data(Interface_asynctask asynctask)
//    {
//        this.asynctask=asynctask;
//    }
//
//    public asynctask(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    protected void onPostExecute(model_data_showCat s) {
//        if (s!=null)
//        {
//            Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Opps!", Toast.LENGTH_SHORT).show();
//        }
//        super.onPostExecute(s);
//    }
//
//    @Override
//    protected model_data_showCat doInBackground(Void... voids) {
//        final model_data_showCat[] showCat = {new model_data_showCat()};
//        list_api api= Retrofit_server.server().create(list_api.class);
//        Call<model_data_showCat> call=api.getdata("20190827123038058529");
//        call.enqueue(new Callback<model_data_showCat>() {
//            @Override
//            public void onResponse(Call<model_data_showCat> call, Response<model_data_showCat> response) {
//                if (response.isSuccessful())
//                {
//                  showCat[0] =response.body();
//
//                }
//            }
//            @Override
//            public void onFailure(Call<model_data_showCat> call, Throwable t) {
//                onPostExecute(null);
//            }
//        });
//
//
//        return showCat[0];
//    }
//    public interface Interface_asynctask{
//        public void data(model_data_showCat s,Boolean Error);
//    }
//}
