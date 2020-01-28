package phonix.nimamoradi.frags;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.Retrofit.Retrofit_server;
import phonix.nimamoradi.Retrofit.list_api;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.activity_open_item;
import phonix.nimamoradi.adapter_item_spl7;
import phonix.nimamoradi.modeldate_mainpage_items;
import phonix.nimamoradi.openFestival_api.main_openfestival_api;
import phonix.nimamoradi.openFestival_api.productList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_festival extends Fragment {
    RecyclerView recy_festival;
    Bundle bundle;
    ArrayList<modeldate_mainpage_items> items;
    Dialog dialog_progress;
    View dialog_progress_view;
    String id;
    TextView txt_text;
    Dialog DIALOG_ERROR;
    Retrofit_server server;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frag_festival,container,false);
        find(view);
        server=new Retrofit_server();
        items=new ArrayList<>();
        id=bundle.getString("id");
        config_dialog(container,inflater);
        dialog_progress.show();
        Config_dialog_error_fragment(inflater,container,R.layout.layout_erorr3);
//        run(id,view);
        Run(id);
        return view;
    }
    public  void find(View view)
    {
        recy_festival=view.findViewById(R.id.recy_festival);
        txt_text=view.findViewById(R.id.textView33);

    }
    public void setRecy_festival(ArrayList<productList> list)
    {
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        recy_festival.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter_item_spl7 spl7=new adapter_item_spl7(getContext(), new adapter_item_spl7.send_info() {
            @Override
            public void info(String s, productList items) {
                if (s.equals("true"))
                {
                    Intent intent=new Intent(getContext(), activity_open_item.class);
                    intent.putExtra("id",items.getId());
                    startActivity(intent);
                }
            }
        },list,metrics.widthPixels,metrics.heightPixels);
        recy_festival.setAdapter(spl7);
        dialog_progress.dismiss();
        spl7.click(new adapter_item_spl7.sell() {
            @Override
            public void data(Boolean aBoolean, productList items) {
                if (!aBoolean)
                {
                    SharedPreferences sharedPreferences=getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
                    String name=sharedPreferences.getString("name","");
                    String pass=sharedPreferences.getString("pass","");
                    SharedPreferences sharedPreferences2=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE);
                    String Gest=sharedPreferences2.getString("gest2","");
                    if (name.equals("")||pass.equals(""))
                    {
                        dialog_progress.show();
                        Sell_gest(Gest,items.getId());
                    }else {
                        dialog_progress.show();
                        Sell(name,pass,items.getId());
                    }
                }
            }
        });
    }
    public  void run(String id,View view)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("id",id);
        VollayRequest request=new VollayRequest("openFestival.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
              try {
                  Boolean error=jsonObject.getBoolean("error");
                  if (error)
                  {
                      dialog_progress.dismiss();
                      String s=jsonObject.getString("MSG");
                      Toast.makeText(getContext(),s, Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                  }
                  JSONArray array=jsonObject.getJSONArray("product");
                  for (int i=0;i<array.length();i++)
                  {
                      items.add(new modeldate_mainpage_items(array.getJSONObject(i)
                              .getString("price"),array.getJSONObject(i)
                              .getString("percentageOffer"),
                              array.getJSONObject(i)
                                      .getString("priceAfterOffer"),
                              array.getJSONObject(i)
                                      .getString("img"),
                              array.getJSONObject(i)
                                      .getString("star"),
                              array.getJSONObject(i)
                                      .getString("name"),
                              array.getJSONObject(i)
                                      .getString("id")
                              ));
                  }
                    if (items.size()!=0)
                    {
//                        setRecy_festival();
                    }else {
                        dialog_progress.dismiss();
                        txt_text.setAlpha(0);
                        txt_text.setVisibility(View.VISIBLE);
                        txt_text.animate().alpha(1).setDuration(700).start();
                    }
              }catch (Exception e)
              {

              }
            }
        }, new I_cant() {
            @Override
            public void error(String s) {
                if (s.equals("true"))
                {
                    dialog_progress.dismiss();
                    Snackbar snackbar=Snackbar.make(view,"مشکلی در اتصال به اینترنت به وجود آمده",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
    public void Sell(String name,String pass,String id)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        map.put("count","ADDBTN");
        map.put("GustId","");
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        dialog_progress.dismiss();
                        Toast.makeText(getContext(), "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
                    }
                    dialog_progress.dismiss();

                }catch (Exception e)
                {

                }
            }
        }, new I_cant() {
            @Override
            public void error(String s) {
                if (s.equals("true"))
                {
                    dialog_progress.dismiss();
                    Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Sell_gest(String gest,String id)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword","");
        map.put("count","addBTN");
        map.put("GustId",gest);
        map.put("userName","");
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php",map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        String s=jsonObject.getString("gustId");
                        SharedPreferences.Editor sharedPreferences=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                        sharedPreferences.putString("gest2",s).apply();
                        dialog_progress.dismiss();
                        Toast.makeText(getContext(), "محصول به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                }
            }
        }, new I_cant() {
            @Override
            public void error(String s) {
                if (s.equals("true"))
                {
                    dialog_progress.dismiss();
                    Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void config_dialog(ViewGroup container, LayoutInflater inflater)
    {
        dialog_progress=new Dialog(getContext());
        dialog_progress.setCancelable(false);
        dialog_progress_view=inflater.inflate(R.layout.layout_progress,container,false);
        dialog_progress.setContentView(dialog_progress_view);
        dialog_progress.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog_progress.getWindow ().setBackgroundDrawable ( new ColorDrawable( Color.TRANSPARENT ) );
    }
    public void Run(String id)
    {
        list_api api= server.getRetrofit().create(list_api.class);
        Call<main_openfestival_api> call=api.getopenfestival(id);
        call.enqueue(new Callback<main_openfestival_api>() {
            @Override
            public void onResponse(Call<main_openfestival_api> call, Response<main_openfestival_api> response) {
                if (response.isSuccessful())
                {
                    dialog_progress.dismiss();;
                    main_openfestival_api api=response.body();
                    if (!api.getError())
                    {
                        if (api.getProductList().size()!=0)
                        {
                            setRecy_festival(api.getProductList());
                        }else {
                            txt_text.setAlpha(0);
                            txt_text.setVisibility(View.VISIBLE);
                            txt_text.animate().alpha(1).setDuration(700).start();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<main_openfestival_api> call, Throwable t) {
                dialog_progress.dismiss();
                DIALOG_ERROR.show();

            }
        });
    }
    public void Config_dialog_error_fragment(LayoutInflater inflater,ViewGroup group,int layout)
    {
        Button Ok,Reload;
        DIALOG_ERROR=new Dialog(getContext(),R.style.PauseDialog);
        View view=inflater.inflate(layout,group,false);
        Ok=view.findViewById(R.id.button5);
        Reload=view.findViewById(R.id.button4);
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DIALOG_ERROR.dismiss();
                getActivity().finish();
            }
        });
        Reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DIALOG_ERROR.dismiss();
                dialog_progress.show();
                Run(id);
            }
        });
        DIALOG_ERROR.setContentView(view);
        DIALOG_ERROR.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        DIALOG_ERROR.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        DIALOG_ERROR.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white_transperent)));

    }
}
