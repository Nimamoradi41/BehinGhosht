package phonix.nimamoradi.frags;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.activity_open_item;
import phonix.nimamoradi.adapter_item_spl6;
import phonix.nimamoradi.modeldate_mainpage_items;
import phonix.nimamoradi.modeldate_mainpage_items_2;

public class frag_more extends Fragment {
    RecyclerView recy_more;
    adapter_item_spl6 spl6;
    ArrayList<modeldate_mainpage_items> items;
    Bundle bundle;
    Dialog dialog_progress;
    View dialog_progress_view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          bundle=getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.layout_frag_more,container,false);
       find(view);
       items= (ArrayList<modeldate_mainpage_items>) bundle.getSerializable("items");
       config_dialog(container,inflater);
       setRecy_more();
       return view;
    }

    private void find(View view) {
        recy_more=view.findViewById(R.id.recy_more);
    }
    public void setRecy_more()
    {
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        recy_more.setLayoutManager(new GridLayoutManager(getContext(),2));
        spl6=new adapter_item_spl6(getContext(), new adapter_item_spl6.send_info() {
            @Override
            public void info(String s, modeldate_mainpage_items items) {
                Intent intent=new Intent(getContext(), activity_open_item.class);
                intent.putExtra("id",items.getId());
                startActivity(intent);
            }
        },items, metrics.widthPixels,metrics.heightPixels);
        recy_more.setAdapter(spl6);
        spl6.click(new adapter_item_spl6.sell() {
            @Override
            public void data(Boolean aBoolean, modeldate_mainpage_items items) {
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

}
