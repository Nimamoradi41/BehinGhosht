package phonix.nimamoradi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.Transformations.ZoomOutTransformation;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import me.relex.circleindicator.CircleIndicator;
import phonix.nimamoradi.Retrofit.Retrofit_server;
import phonix.nimamoradi.Retrofit.list_api;
import phonix.nimamoradi.showProduct_api.attr;
import phonix.nimamoradi.showProduct_api.img;
import phonix.nimamoradi.showProduct_api.main_showProduct_api;
import phonix.nimamoradi.showProduct_api.productLike;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class activity_open_item extends AppCompatActivity {
        RecyclerView recy_open_item;
        TextView Name;
        TextView Brand;
        TextView Cate;
        TextView Disc;
        TextView Price;
        TextView Off;
        TextView More;
        Dialog dialog;
        TextView txt_looklike;
        ArrayList<modeldata_method> model_method2;
        ArrayList<String> img_link;
        ImageView btnadd;
        ImageView btmines;
        TextView Count;
        LinearLayout box_off;
        int count_request;
        ImageView btnadd_favourit;
    ViewPager viewPager;
    CircleIndicator indicator;
    Viewpager_adapter viewpager_adapter;
    Viewpager_adapter2 viewpager_adapter2;
    ArrayList<Integer> strings;
    RecyclerView recy_method;
    ArrayList<modeldata_method> methods;
    VollayRequest request;
    ArrayList<modeldate_mainpage_items> items_main;
    String id;
    ScrollView Scrol_main;
    AVLoadingIndicatorView loader2;
    Dialog dialog_progress;
    View dialog_progress_view;
    Button btn_add_open_item;
    String name2;
    String pass2;
    Dialog DIALOG_ERROR;
    View parentLayout;
    Retrofit_server server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setfont();
        setContentView(R.layout.activity_open_item);
        server=new Retrofit_server();
          parentLayout = findViewById(android.R.id.content);
          SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
          name2 = sharedPreferences.getString("name", "");
          pass2 = sharedPreferences.getString("pass", "");
         id=getIntent().getStringExtra("id");
        count_request=0;
        methods=new ArrayList<>();
        img_link=new ArrayList<>();
        items_main=new ArrayList<>();
        config_dialog();
        find();
        dialog_progress.show();
        btns_click();
        addfavourit();
        add_basket();
        Config_dialog_error_activity(R.layout.layout_erorr3);
        Run(name2,pass2,id);
//        set_slider();
//        setRecy_method();
//        View view= LayoutInflater.from(this).inflate(R.layout.layout_custome_dialog,null);
//        dialog=new Dialog(this);
//        dialog.setContentView(view);
//         dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//          dialog.show();
    }
    private void add_basket() {
        btn_add_open_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_request!=0)
                {
                    if (check_connection())
                    {
                        dialog_progress.show();
                        SharedPreferences sharedPreferences=getSharedPreferences("Login",Context.MODE_PRIVATE);
                        String name=sharedPreferences.getString("name","");
                        String pass=sharedPreferences.getString("pass","");
                        SharedPreferences sharedPreferences2=getSharedPreferences("guestId",Context.MODE_PRIVATE);
                        String Gest=sharedPreferences2.getString("gest2","");
                        if (name.equals("")||pass.equals(""))
                        {
                            Sell_gest(Gest,id,String.valueOf(count_request),v);
                        }else {
                            Sell(name,pass,id,String.valueOf(count_request),Gest,v);
                        }
                    }

                }
            }
        });

    }


    public void  find()
    {
        Count=findViewById(R.id.txt_1000);
        btn_add_open_item=findViewById(R.id.btn_add_open_item);
        btnadd_favourit=findViewById(R.id.btnadd_favourit);
        box_off=findViewById(R.id.box_off);
         btnadd=this.findViewById(R.id.btn_add);
         btmines=this.findViewById(R.id.btn_mines);
        txt_looklike=findViewById(R.id.txt_looklike);
        More=findViewById(R.id.txt_mor22);
        Name=findViewById(R.id.txt_title);
        Brand=findViewById(R.id.MDKMCKDMC);
        Cate=findViewById(R.id.textView17);
        Disc=findViewById(R.id.id_text);
        Price=findViewById(R.id.txt_price);
        Off=findViewById(R.id.txt_off_circle);
        indicator = findViewById(R.id.indicator);
        recy_open_item=findViewById(R.id.recy_open_item);
        viewPager=findViewById(R.id.slider_view_peager);
        recy_method=findViewById(R.id.recy_method);
        Scrol_main=findViewById(R.id.Scrol_main);
    }
    public void setRecy_method(ArrayList<attr> attrs)
    {
        adapter_openitem_method adapter_openitem_method=new adapter_openitem_method(attrs,this);
        recy_method.setLayoutManager(new LinearLayoutManager(this));
        recy_method.setAdapter(adapter_openitem_method);
    }
    public void set_recy_look_like(ArrayList<productLike> productLike)
    {
        if (items_main.size()==0)
        {
            recy_open_item.setVisibility(View.GONE);
            txt_looklike.setVisibility(View.GONE);
            More.setVisibility(View.GONE);
        }else {
            DisplayMetrics metrics=new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            adapter_item_spl4 spl4=new adapter_item_spl4(this, new adapter_item_spl4.send_info() {
                @Override
                public void info(String s, productLike items) {
                    Intent intent=new Intent(activity_open_item.this,activity_open_item.class);
                    intent.putExtra("id",items.getId());
                     startActivity(intent);
                }
            },productLike,metrics.widthPixels,metrics.heightPixels);
            recy_open_item.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,true));
            recy_open_item.setAdapter(spl4);
            spl4.click(new adapter_item_spl4.sell() {
                @Override
                public void info(Boolean aBoolean, productLike items) {
                    if (!aBoolean) {
                        if(check_connection())
                        {
                            SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                            String name = sharedPreferences.getString("name", "");
                            String pass = sharedPreferences.getString("pass", "");
                            SharedPreferences sharedPreferences2 = getSharedPreferences("guestId", Context.MODE_PRIVATE);
                            String Gest = sharedPreferences2.getString("gest2", "");
                            if (name.equals("") || pass.equals("")) {
                                dialog_progress.show();
                                Sell_gest2(Gest, items.getId(),parentLayout);
                            } else {
                                dialog_progress.show();
                                Sell2(name, pass, items.getId(),parentLayout);
                            }
                        }else {
                            Snackbar snackbar=Snackbar.make(parentLayout,"مشکلی در اتصال به وجود آمده",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }

                    }
                }
            });
        }
    }
    public void set_slider(ArrayList<img> slider)
    {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        strings=new ArrayList<>();
        viewpager_adapter2=new Viewpager_adapter2(this,slider,metrics.widthPixels,metrics.heightPixels);
        viewPager.setAdapter(viewpager_adapter2);
        viewPager.setPageTransformer(true,new ZoomOutTransformation());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(metrics.widthPixels,metrics.widthPixels/2));
        viewPager.requestLayout();
        indicator.setViewPager(viewPager);
    }
    public void run()
    {
        HashMap<String,String> map=new HashMap<>();
        if (name2.isEmpty()||pass2.isEmpty())
        {
            map.put("id",id);
        }
        else {
            map.put("userName",name2);
            map.put("userPassword",pass2);
            map.put("id",id);
        }

        request=new VollayRequest("showProduct.php", map, this, new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    String v = jsonObject.getString("offer");
                    if (v.equals("0") || v.equals("") || v.equals("null"))
                    {
                       box_off.setVisibility(View.GONE);
                    }else {
                        Off.setText(v + "%\n" + "تخفیف");
                    }

                    Boolean fav=jsonObject.getBoolean("fav");
                    if (fav)
                    {
                        btnadd_favourit.setImageResource(R.drawable.ic_like_fill);
                    }
                    else {
                        btnadd_favourit.setImageResource(R.drawable.add_favourit);
                    }

                    Name.setText(jsonObject.getString("name").trim());
                    Brand.setText(jsonObject.getString("brandName"));
                    Cate.setText(jsonObject.getString("catName"));
                    Disc.setText(jsonObject.getString("detail").trim());
                    Price.setText(jsonObject.getString("price")+"ریال");
                    JSONArray array_img=jsonObject.getJSONArray("img");
                    for (int i=0;i<array_img.length();i++)
                    {
                        img_link.add(array_img.getJSONObject(i).getString("img"));
                    }
                    JSONArray array_item_look_like=jsonObject.getJSONArray("productLike");

//                    if (array_item_look_like.length()==0)
//                    {
//
//                    }
                    for (int i=0;i<array_item_look_like.length();i++)
                    {
                        items_main.add(new modeldate_mainpage_items(array_item_look_like.getJSONObject(i).getString("price")
                                ,array_item_look_like.getJSONObject(i).getString("percentageOffer"),
                                array_item_look_like.getJSONObject(i).getString("priceAfterOffer"),
                                array_item_look_like.getJSONObject(i).getString("img"),
                                array_item_look_like.getJSONObject(i).getString("star"),
                                array_item_look_like.getJSONObject(i).getString("name"),
                                array_item_look_like.getJSONObject(i).getString("id")));
                    }
//                    set_recy_look_like();
//                    set_slider();
//                    setRecy_method();
                    Scrol_main.setAlpha(0);
                    Scrol_main.setVisibility(View.VISIBLE);
                    Scrol_main.animate().alpha(1).setDuration(400).start();
                    JSONArray array_method=jsonObject.getJSONArray("attr");
                     for (int i2=0;i2<array_method.length();i2++)
                    {
                        methods.add(new modeldata_method(array_method.getJSONObject(i2).getString("name"),
                                array_method.getJSONObject(i2).getString("value")));
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
                    DIALOG_ERROR.show();
                 }
            }
        });
    }
    public Boolean check_connection()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void init_count(Boolean flag)
    {
        if (flag)
        {
              count_request=count_request+1;
            Count.setText(String.valueOf(count_request));
        }else {
            if (count_request==0)
            {
            }else
            {
                count_request=count_request-1;
                Count.setText(String.valueOf(count_request));
            }
        }
    }
    public void addfavourit()
    {
        btnadd_favourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
                Boolean Login=   sharedPreferences.getBoolean("Login", false);
              String name=sharedPreferences.getString("name","");
                String pass=sharedPreferences.getString("pass","");
                if(name.equals("")||pass.equals(""))
                {
                     Snackbar snackbar=Snackbar.make(v,"ابتدا وارد سیستم شوید",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    if (check_connection())
                    {
                        add_favourit_server(id,name,pass,parentLayout);
                    }
                    else {
                        Snackbar snackbar=Snackbar.make(v,"مشکلی در اتصال به وچود آمده",Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                }
            }
        });
    }
    public void btns_click()
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_count(true);
            }
        });
        btmines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_count(false);
            }
        });
    }
    public void add_favourit_server(String  id,String name,String pass,View view)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("userName",name);
        map.put("userPassword",pass);
        VollayRequest request=new VollayRequest("fav.php", map, this, new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    Boolean erorr=jsonObject.getBoolean("error");
                    if (erorr)
                    {
                        Toast.makeText(activity_open_item.this, jsonObject.getString("MSG"), Toast.LENGTH_SHORT).show();
                    }else {
                        Boolean fav=jsonObject.getBoolean("fav");
                        if (fav)
                        {
                            btnadd_favourit.setImageResource(R.drawable.ic_like_fill);
                            Toast.makeText(activity_open_item.this, "به لیست علاقه مندی اضافه شد", Toast.LENGTH_SHORT).show();
                        }else {
                            btnadd_favourit.setImageResource(R.drawable.add_favourit);
                            Toast.makeText(activity_open_item.this, "از لیست علاقه مندی حذف شد", Toast.LENGTH_SHORT).show();
                        }
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
                   Snackbar.make(view,"مشکلی در اتصال به اینترنت به وجود آمده",Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void setfont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans_Small_Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    public void config_dialog()
    {
        dialog_progress=new Dialog(this);
        dialog_progress.setCancelable(false);
        dialog_progress_view= (View) LayoutInflater.from(this).inflate(R.layout.layout_progress,null,false);
        dialog_progress.setContentView(dialog_progress_view);
        dialog_progress.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog_progress.getWindow ().setBackgroundDrawable ( new ColorDrawable( Color.TRANSPARENT ) );
    }
    public void Sell(String name,String pass,String id,String count,String d,View view)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        map.put("count",count);
        map.put("GustId","");
        map.put("prId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php", map, activity_open_item.this, new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    dialog_progress.dismiss();
                    Boolean error=jsonObject.getBoolean("error");
                     if (!error)
                    {
                        dialog_progress.dismiss();
                        Toast.makeText(activity_open_item.this, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
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
                    Snackbar snackbar=Snackbar.make(view,"مشکلی در اتصال به وجود آمده",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                 }
            }
        });
    }
    public void
    Sell_gest(String gest,String id,String conut,View view)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword","");
        map.put("count",conut);
        map.put("GustId",gest);
        map.put("userName","");
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php",map, activity_open_item.this, new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        if (name2.equals(""))
                        {
                            String s=jsonObject.getString("gustId");
                            SharedPreferences.Editor sharedPreferences=getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                            sharedPreferences.putString("gest2",s).apply();
                        }
                        dialog_progress.dismiss();
                        Toast.makeText(activity_open_item.this, "محصول به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
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
                    Snackbar snackbar=Snackbar.make(view,"مشکلی در اتصال به وجود آمده",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }
    public void Sell2(String name,String pass,String id,View view)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        map.put("count","ADDBTN");
        map.put("GustId","");
        map.put("prId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php", map, this, new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    dialog_progress.dismiss();
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
//                        if (name2.equals(""))
//                        {
//                            String s=jsonObject.getString("gustId");
//                            SharedPreferences.Editor sharedPreferences=getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
//                            sharedPreferences.putString("gest2",s).apply();
//                        }
                        dialog_progress.dismiss();
                        Toast.makeText(activity_open_item.this, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
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
                    Snackbar snackbar=Snackbar.make(view,"مشکلی در اتصال به وجود آمده",Snackbar.LENGTH_SHORT);
                    snackbar.show();

                 }
            }
        });
    }
    public void Sell_gest2(String gest,String id,View view)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword","");
        map.put("count","addBTN");
        map.put("GustId",gest);
        map.put("userName","");
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php",map, this, new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    dialog_progress.dismiss();
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        String s=jsonObject.getString("gustId");
                        SharedPreferences.Editor sharedPreferences=getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                        sharedPreferences.putString("gest2",s).apply();
                        dialog_progress.dismiss();
                        Toast.makeText(activity_open_item.this, "محصول به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
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
                    Snackbar  snackbar=Snackbar.make(view,"مشکلی در اتصال به وجود آمده",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }
    public void Config_dialog_error_activity(int layout)
    {
        Button Ok,Reload;
        DIALOG_ERROR=new Dialog(this,R.style.PauseDialog);
        View view=LayoutInflater.from(this).inflate(layout,null,false);
        Ok=view.findViewById(R.id.button5);
        Reload=view.findViewById(R.id.button4);
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DIALOG_ERROR.dismiss();
                finish();
            }
        });
        Reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DIALOG_ERROR.dismiss();
                dialog_progress.show();
               Run(name2,pass2,id);
            }
        });
        DIALOG_ERROR.setContentView(view);
        DIALOG_ERROR.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        DIALOG_ERROR.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
    public  void Run(String name,String pass,String id)
    {
        list_api api= server.getRetrofit().create(list_api.class);
        Call<main_showProduct_api> call=api.getshowProduct(id,name,pass);
        call.enqueue(new Callback<main_showProduct_api>() {
            @Override
            public void onResponse(Call<main_showProduct_api> call, Response<main_showProduct_api> response) {
                if (response.isSuccessful())
                {
                    dialog_progress.dismiss();
                    main_showProduct_api respo=response.body();
                    String v=respo.getOffer();
                    if (v.equals("0") || v.equals("") || v.equals("null"))
                    {
                        box_off.setVisibility(View.GONE);
                    }else {
                        Off.setText(v + "%\n" + "تخفیف");
                    }
                    Boolean fav=respo.getFav();
                    if (fav)
                    {
                        btnadd_favourit.setImageResource(R.drawable.ic_like_fill);
                    }
                    else {
                        btnadd_favourit.setImageResource(R.drawable.add_favourit);
                    }
                    Name.setText(respo.getName().trim());
                    if (respo.getBrandName()==null)
                    {
                        Brand.setText("نامشخص");
                    }else {
                        Brand.setText(respo.getBrandName().trim());
                    }
//                    Brand.setText(respo.getBrandName().trim());
                    Cate.setText(respo.getCatName().trim());
                    Disc.setText(respo.getDetail().trim());
                    Price.setText(respo.getPrice().trim()+" ریال");
                    set_slider(respo.getImg());
                    set_recy_look_like(respo.getProductLike());
                    setRecy_method(respo.getAttr());
                    Scrol_main.setAlpha(0);
                    Scrol_main.setVisibility(View.VISIBLE);
                    Scrol_main.animate().alpha(1).setDuration(400).start();

                }
            }

            @Override
            public void onFailure(Call<main_showProduct_api> call, Throwable t) {
                dialog_progress.dismiss();
                DIALOG_ERROR.show();

            }
        });




    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}

