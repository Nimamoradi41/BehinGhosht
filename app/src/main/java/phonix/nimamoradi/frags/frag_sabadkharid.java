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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.Activity_evrything;
import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.adapter_footer;
import phonix.nimamoradi.adapter_sabadkharid;
import phonix.nimamoradi.modeldata_sabadkharid;

public class frag_sabadkharid extends Fragment {
    RecyclerView recy_sabadkharid;
    Dialog dialog_progress;
    View dialog_progress_view;
    ImageView imag_shop;
    ArrayList<modeldata_sabadkharid> list;
    DisplayMetrics metrics;
    Boolean Flag;
    String name;
    String Gest;
    String pass;
    adapter_sabadkharid sabadkharid;
    TextView textView13_Sum;
    float a;
    NestedScrollView dvvdvvdvdv;
    TextView text_off_textView4;
    TextView number_textView9;
    String cd;
    LinearLayout cdcdcdcdc;
    Button btn_sabt;
    EditText btn_code_off;
    int final_total;
    LinearLayout button2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.layout_frag_sabadkharid,container,false);
        Flag=true;
        a=0;
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("name","");
        pass=sharedPreferences.getString("pass","");
        SharedPreferences sharedPreferences2=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE);
        Gest=sharedPreferences2.getString("gest2","");
        list=new ArrayList<>();
          metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        recy_sabadkharid=view.findViewById(R.id.recy_sabadkharid);
        button2=view.findViewById(R.id.button2);
        btn_code_off=view.findViewById(R.id.btn_code_off);
        btn_sabt=view.findViewById(R.id.btn_sabt);
        text_off_textView4=view.findViewById(R.id.textView4);
        number_textView9=view.findViewById(R.id.textView9);
        cdcdcdcdc=view.findViewById(R.id.cdcdcdcdc);
        dvvdvvdvdv=view.findViewById(R.id.dvvdvvdvdv);
        textView13_Sum=view.findViewById(R.id.textView13);
        imag_shop=view.findViewById(R.id.imag_shop);
        imag_shop.setLayoutParams(new LinearLayout.LayoutParams(metrics.widthPixels/3,metrics.widthPixels/3));
        imag_shop.requestLayout();
        config_dialog(container,inflater);
        setRecy_sabadkharid();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("")||pass.equals(""))
                {
                 Snackbar snackbar=Snackbar.make(v,"ابتدا وارد شوید",Snackbar.LENGTH_LONG);
                 snackbar.show();
                }else {
                    if (a!=0)
                    {
                        Intent intent=new Intent(getContext(), Activity_evrything.class);
                        intent.putExtra("Type","Shopping");
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "محصولی در سبد خرید وجود ندارد", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=btn_code_off.getText().toString().trim();
                if (s.isEmpty())
                {
                }else {
                    if (name.equals("")||pass.equals(""))
                    {
                        Snackbar snackbar=Snackbar.make(v,"ابتدا وارد شوید",Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }else {
                         if (a!=0)
                         {
                             dialog_progress.show();
                             code_off(btn_code_off.getText().toString().trim());
                         }else {
                             Toast.makeText(getContext(), "محصولی در سبد خرید وجود ندارد", Toast.LENGTH_SHORT).show();
                         }

                    }

                }
            }
        });
        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    public void setRecy_sabadkharid()
    {
        recy_sabadkharid.setLayoutManager(new LinearLayoutManager(getContext()));
                dialog_progress.show();
                run2(Gest,name,pass);
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
    public void run2(String gest,String name,String pass)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword",pass);
        map.put("GustId",gest);
        map.put("userName",name);
         VollayRequest request=new VollayRequest("showShoppingCard.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                     dialog_progress.dismiss();
                         float de = 0;
                        Boolean error=jsonObject.getBoolean("error");
                         if (error)
                        {
                             Toast.makeText(getContext(), jsonObject.getString("MSG"), Toast.LENGTH_SHORT).show();
                        }else {
                              if (name.equals(""))
                             {
                                  SharedPreferences.Editor sharedPreferences2=  getContext().getSharedPreferences("guestId", Context.MODE_PRIVATE).edit();
                                 sharedPreferences2.putString("gest2",jsonObject.getString("GustId")).apply();

                             }
                             JSONArray array=jsonObject.getJSONArray("product");
                             for (int i=0;i<array.length();i++)
                            {
                                list.add(new modeldata_sabadkharid(array.getJSONObject(i).getString("id"),
                                        array.getJSONObject(i).getString("img"),
                                        array.getJSONObject(i).getString("price").trim(),
                                        array.getJSONObject(i).getString("count").trim(),
                                        array.getJSONObject(i).getString("offer"),
                                        array.getJSONObject(i).getString("name"),
                                        array.getJSONObject(i).getString("total")));
                                ;                    }
                            sabadkharid=new adapter_sabadkharid(list,getContext(), metrics.widthPixels,metrics.heightPixels);
                                recy_sabadkharid.setAdapter(sabadkharid);
                            if (list.size()>0)
                            {
                                sabadkharid=new adapter_sabadkharid(list,getContext(), metrics.widthPixels,metrics.heightPixels);
                                recy_sabadkharid.setAdapter(sabadkharid);
                            }else {
                                cdcdcdcdc.setAlpha(0);
                                cdcdcdcdc.setVisibility(View.VISIBLE);
                                cdcdcdcdc.animate().alpha(1).setDuration(600).start();
                            }
                             for (int i=0;i<list.size();i++)
                            {
                               cd=list.get(i).getTotal();
                               String v=cd.replace(",","");
                                 de= Float.parseFloat(v);
                                 a=a+de;
//                               int fff= Integer.parseInt(v);
                            }
                             final_total= (int) a;
                              DecimalFormat formatter =new DecimalFormat("#,###,###,###");
//                            int w= Integer.parseInt(formatter.format(a));
                            textView13_Sum.setText(String.valueOf(formatter.format((int) a)+" ریال "));
                            dialog_progress.dismiss();
                             sabadkharid.data(new adapter_sabadkharid.click() {
                                @Override
                                public void data(String s,int d,modeldata_sabadkharid sabadkhari2) {
                                    if (s.equals("A"))
                                    {
                                        dialog_progress.show();
                                        int c=Integer.parseInt(sabadkhari2.getCount());
                                        Sell_gest(Gest,sabadkhari2.getId(),c+1,d,sabadkhari2,name,pass);
//                                        String S=sabadkhari2.getTotal().trim().replace(",","");
//                                        Toast.makeText(getContext(), S, Toast.LENGTH_SHORT).show();
//                                        int ds= Integer.parseInt(S);
//                                        Toast.makeText(getContext(), String.valueOf(ds), Toast.LENGTH_SHORT).show();
//                                        sabadkhari2.setName("Nima");
//                                        list.set(d,sabadkhari2);
//                                        sabadkharid.notifyItemChanged(d);
//                                        sabadkharid.notifyItemRangeChanged(d,list.size());
//                                        if (Flag)
//                                        {
//                                            Toast.makeText(getContext(), String.valueOf(d), Toast.LENGTH_SHORT).show();
//                                            sabadkharid.notifyItemRemoved(d);
//                                            Flag=false;
//                                        }else {
//                                            Toast.makeText(getContext(), String.valueOf(d), Toast.LENGTH_SHORT).show();
//                                            sabadkharid.notifyItemRemoved(d-1);
//                                        }

                                    }
                                    if (s.equals("B"))
                                    {
                                        if (sabadkhari2.getCount().trim().equals("1"))
                                        {
                                            dialog_progress.show();
                                            Sell_gest_delete(Gest,sabadkhari2.getId(),2,d,sabadkhari2,name,pass);
                                        }else {
                                            dialog_progress.show();

                                            int cs= Integer.parseInt(sabadkhari2.getCount().trim());
                                            Sell_gest_mines(Gest,sabadkhari2.getId(),cs-1,d,sabadkhari2,name,pass);
                                        }

                                    }
                                    if (s.equals("C"))
                                    {
                                        dialog_progress.show();
                                        Sell_gest_delete(Gest,sabadkhari2.getId(),2,d,sabadkhari2,name,pass);
                                    }
                                }
                            });
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
                 }
            }
        });
    }
    public void Sell(String name,String pass,String id,int c,String gest)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        map.put("count", String.valueOf(c));
        map.put("GustId",gest);
        map.put("prId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        if (name.equals("")||pass.equals(""))
                        {
                            String s=jsonObject.getString("gustId");
                            SharedPreferences.Editor sharedPreferences=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                            sharedPreferences.putString("gest2",s).apply();
                            dialog_progress.dismiss();
                        }

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
    public void Sell_gest(String gest,String id,int c,int pos,modeldata_sabadkharid sabadkharid3,String name,String pass)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword",pass);
        map.put("count", String.valueOf(c));
        map.put("GustId",gest);
        map.put("userName",name);
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php",map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    float de = 0;
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        if (name.equals("")||pass.equals(""))
                        {
                            String s=jsonObject.getString("gustId");
                            SharedPreferences.Editor sharedPreferences=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                            sharedPreferences.putString("gest2",s).apply();
                            dialog_progress.dismiss();
                        }
                        sabadkharid3.setCount(String.valueOf(c));
                        int price= Integer.parseInt(sabadkharid3.getPrice().replace(",","").trim());
                        if (sabadkharid3.getOff().equals("0"))
                        {
                            DecimalFormat formatter =new DecimalFormat("#,###,###,###");
                            sabadkharid3.setTotal("" + formatter.format(price*c) );
                        }else {
                            int off= Integer.parseInt(sabadkharid3.getOff().replace(",","").trim());
                            DecimalFormat formatter =new DecimalFormat("#,###,###,###");
                            sabadkharid3.setTotal("" +formatter.format((price*c)-off));
                        }

                        list.set(pos,sabadkharid3);
                        sabadkharid.notifyItemChanged(pos);
                        sabadkharid.notifyItemRangeChanged(pos,list.size());
                        if (list.size()>0)
                        {

                        }else {
                            cdcdcdcdc.setAlpha(0);
                            cdcdcdcdc.setVisibility(View.VISIBLE);
                            cdcdcdcdc.animate().alpha(1).setDuration(600).start();
                        }
                        a=0;
                        for (int i=0;i<list.size();i++)
                        {
                            cd=list.get(i).getTotal();
                            String v=cd.replace(",","");
                            de= Float.parseFloat(v);
                            a=a+de;
//                               int fff= Integer.parseInt(v);
                        }
                        DecimalFormat formatter =new DecimalFormat("#,###,###,###");
                        textView13_Sum.setText(String.valueOf(formatter.format((int) a)+" ریال "));

//                         for (int i=0;i<list.size();i++)
//                        {
//                            String b= list.get(i).getTotal().replace(",","");
//                            a= Integer.parseInt(b);
////                           sum= Integer.parseInt(b.replace(",",""));
////                            sum2=sum+sum2;
//                        }
//                        Toast.makeText(getContext(), String.valueOf(String.valueOf(sum2)), Toast.LENGTH_SHORT).show();
////                        NumberFormat numberFormat=new DecimalFormat("#,###,###,###");
////                        Toast.makeText(getContext(), "dcdcc", Toast.LENGTH_SHORT).show();
////                        textView13_Sum.setText(a);
////                        textView13_Sum.setText(sum2);
                        dialog_progress.dismiss();
                    }
                    else {
                        dialog_progress.dismiss();
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
    public void Sell_gest_delete(String gest,String id,int c,int pos,modeldata_sabadkharid sabadkharid3,String name,String pass)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword",pass);
        map.put("count","delete");
        map.put("GustId",gest);
        map.put("userName",name);
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php",map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    float de = 0;
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        if (name.equals("")||pass.equals(""))
                        {
                            String s=jsonObject.getString("gustId");
                            SharedPreferences.Editor sharedPreferences=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                            sharedPreferences.putString("gest2",s).apply();
                            dialog_progress.dismiss();
                        }
                        list.remove(pos);
                        sabadkharid.notifyItemRemoved(pos);
                        sabadkharid.notifyItemRangeChanged(pos,list.size());
                        if (list.size()>0)
                        {

                        }else {
                            cdcdcdcdc.setAlpha(0);
                            cdcdcdcdc.setVisibility(View.VISIBLE);
                            cdcdcdcdc.animate().alpha(1).setDuration(600).start();
                        }
                        a=0;
                        for (int i=0;i<list.size();i++)
                        {
                            cd=list.get(i).getTotal();
                            String v=cd.replace(",","");
                            de= Float.parseFloat(v);
                            a=a+de;
//                               int fff= Integer.parseInt(v);
                        }
                        DecimalFormat formatter =new DecimalFormat("#,###,###,###");
                        textView13_Sum.setText(String.valueOf(formatter.format((int) a)+" ریال "));
//                        DecimalFormat formatter =new DecimalFormat("#,###,###,###");
//                        textView13_Sum.setText(String.valueOf(formatter.format((int) a)+" ریال "));
//                        for (int i=0;i<list.size();i++)
//                        {
//                            a= Integer.parseInt(list.get(i).getTotal().replace(",",""));
//                            sum=a+sum;
//                        }
//                        NumberFormat numberFormat=new DecimalFormat("#,###,###,###");
//                        textView13_Sum.setText(numberFormat.format(sum));
                        dialog_progress.dismiss();
                    }else {
                        dialog_progress.dismiss();
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
    public void Sell_gest_mines(String gest,String id,int c,int pos,modeldata_sabadkharid sabadkharid3,String name,String pass)
    {
        HashMap<String,String> map=new HashMap<>();
        map.put("userPassword",pass);
        map.put("count", String.valueOf(c));
        map.put("GustId",gest);
        map.put("userName",name);
        map.put("PrId",id);
        VollayRequest request=new VollayRequest("shoppingCard.php",map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    float de = 0;
                    Boolean error=jsonObject.getBoolean("error");
                    if (!error)
                    {
                        if (name.equals("")||pass.equals(""))
                        {
                            String s=jsonObject.getString("gustId");
                            SharedPreferences.Editor sharedPreferences=getContext().getSharedPreferences("guestId",Context.MODE_PRIVATE).edit();
                            sharedPreferences.putString("gest2",s).apply();
                            dialog_progress.dismiss();
                        }
                        sabadkharid3.setCount(String.valueOf(c));
                        int price= Integer.parseInt(sabadkharid3.getPrice().replace(",","").trim());
                        if (sabadkharid3.getOff().equals("0"))
                        {
                            NumberFormat formatter =new DecimalFormat("#,###,###,###");
                            sabadkharid3.setTotal("" + formatter.format(price*c) );
                        }else {
                            int off= Integer.parseInt(sabadkharid3.getOff().replace(",","").trim());
                            NumberFormat formatter =new DecimalFormat("#,###,###,###");
                            sabadkharid3.setTotal("" +formatter.format((price*c)-off));
                        }
                        list.set(pos,sabadkharid3);
                        sabadkharid.notifyItemChanged(pos);
                        sabadkharid.notifyItemRangeChanged(pos,list.size());
                        if (list.size()>0)
                        {

                        }else {
                            cdcdcdcdc.setAlpha(0);
                            cdcdcdcdc.setVisibility(View.VISIBLE);
                            cdcdcdcdc.animate().alpha(1).setDuration(600).start();
                        }
                        a=0;
                        for (int i=0;i<list.size();i++)
                        {
                            cd=list.get(i).getTotal();
                            String v=cd.replace(",","");
                            de= Float.parseFloat(v);
                            a=a+de;
//                               int fff= Integer.parseInt(v);
                        }
                        DecimalFormat formatter =new DecimalFormat("#,###,###,###");
                        textView13_Sum.setText(String.valueOf(formatter.format((int) a)+" ریال "));
//                        for (int i=0;i<list.size();i++)
//                        {
//                            a= Integer.parseInt(list.get(i).getTotal().replace(",",""));
//                            sum=a+sum;
//                        }
//                        NumberFormat numberFormat=new DecimalFormat("#,###,###,###");
//                        textView13_Sum.setText(numberFormat.format(sum));
                        dialog_progress.dismiss();
                    }
                    else {
                        dialog_progress.dismiss();
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
    public void code_off(String r)
    {
        SharedPreferences preferences=getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
        String name=preferences.getString("name","");
        String pass=preferences.getString("pass","");
        HashMap<String,String>map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        map.put("code",r);
        VollayRequest request=new VollayRequest("offerCode.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    dialog_progress.dismiss();
                    Boolean error=jsonObject.getBoolean("error");
                    Boolean login=jsonObject.getBoolean("login");
                    if (!error)
                    {
                        if (login)
                        {
                            String sa=jsonObject.getString("percentage");
                            int percentage= Integer.parseInt(sa);
                            int price_percentage=final_total*percentage/100;
                            final_total=final_total-price_percentage;
                            NumberFormat formatter =new DecimalFormat("#,###,###,###");
                            number_textView9.setText(formatter.format(price_percentage)+" ریال");
                            textView13_Sum.setText(formatter.format(final_total)+" ریال");
                            number_textView9.setVisibility(View.VISIBLE);
                            text_off_textView4.setVisibility(View.VISIBLE);
//                            int total=
                        }
                    }else {
                        Toast.makeText(getContext(), "کد تخفیف در سیستم وجود ندارد", Toast.LENGTH_SHORT).show();
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
                  Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
    public void Run(String name,String pass,String gestid)
    {



    }
}

