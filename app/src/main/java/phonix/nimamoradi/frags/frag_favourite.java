package phonix.nimamoradi.frags;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.adapter_item_spl8;
import phonix.nimamoradi.modeldate_mainpage_items;

public class frag_favourite extends Fragment {
    RecyclerView recy_foavurite;
    TextView textView33;
    Dialog dialog_progress;
    View dialog_progress_view;
    ArrayList<modeldate_mainpage_items> items;
    int height;
    int width;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_frag_favoruite,container,false);
        find(view);
        config_dialog(container,inflater);
        items=new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        dialog_progress.show();
        run();
        return  view;
    }
    public  void find(View view)
    {
        recy_foavurite=view.findViewById(R.id.recy_foavurite);
        textView33=view.findViewById(R.id.textView33);
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
    public void run()
    {
        SharedPreferences preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String name=preferences.getString("name","");
        String pass=preferences.getString("pass","");
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        VollayRequest request=new VollayRequest("listFav.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
              try {
                  dialog_progress.dismiss();
                  Boolean login=jsonObject.getBoolean("login");
                  Boolean error=jsonObject.getBoolean("error");
                  if (!error)
                  {
                      return;
                  }
                  if (login)
                  {
                      JSONArray array=jsonObject.getJSONArray("favList");
                      for (int i=0;i<array.length();i++)
                      {
                          items.add(new modeldate_mainpage_items(array.getJSONObject(i).getString("price")
                                  ,array.getJSONObject(i).getString("percentageOffer"),
                                  array.getJSONObject(i).getString("priceAfterOffer"),
                                  array.getJSONObject(i).getString("img"),
                                  array.getJSONObject(i).getString("star"),
                                  array.getJSONObject(i).getString("name"),
                                  array.getJSONObject(i).getString("id")));
                      }
                        if (items.size()!=0)
                        {
                            setRecy_foavurite(items);
                            dialog_progress.dismiss();
                        }else
                            {
                                dialog_progress.dismiss();
                                textView33.setAlpha(0);
                                textView33.setVisibility(View.VISIBLE);
                                textView33.animate().alpha(1).setDuration(700).start();
                            }


                  }else {
                      dialog_progress.dismiss();
                      Toast.makeText(getContext(), "شما وارد سیستم نشده اید", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setRecy_foavurite(ArrayList<modeldate_mainpage_items> foavurite)
    {
        recy_foavurite.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter_item_spl8 spl8=new adapter_item_spl8(getContext(), new adapter_item_spl8.send_info() {
            @Override
            public void info(String s, modeldate_mainpage_items items) {

            }
        },foavurite,width,height);
        recy_foavurite.setAdapter(spl8);
    }
}
