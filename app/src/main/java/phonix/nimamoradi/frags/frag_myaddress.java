package phonix.nimamoradi.frags;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.adpter_address;
import phonix.nimamoradi.adpter_myaddress;
import phonix.nimamoradi.modeldata_item_address;

public class frag_myaddress extends Fragment {
    RecyclerView  recy_myaddress;
    ImageView imageView2045554_myaddress;
    TextView textView34556_myaddress;
    ArrayList<modeldata_item_address> addresses;
    Dialog dialog_progress;
    View dialog_progress_view;
    adpter_myaddress adapter_item_address;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frag_myaddress,container,false);
        find(view);
        config_dialog(container,inflater);
        addresses=new ArrayList<>();
        run();




        return view;
    }
    private void find(View view) {
        recy_myaddress=view.findViewById(R.id.recy_myaddress);
        recy_myaddress.setLayoutManager(new LinearLayoutManager(getContext()));
        imageView2045554_myaddress=view.findViewById(R.id.imageView2045554_myaddress);
        textView34556_myaddress=view.findViewById(R.id.textView34556_myaddress);
    }
    public void run()
    {
        dialog_progress.show();
        SharedPreferences preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String name=preferences.getString("name","");
        String pass=preferences.getString("pass","");
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> map=new HashMap<>();
                map.put("userName",name);
                map.put("userPassword",pass);
                VollayRequest request=new VollayRequest("addressList.php", map, getContext(), new I_can() {
                    @Override
                    public void info(JSONObject jsonObject) {
                        try {
                            Boolean error=jsonObject.getBoolean("error");
                            Boolean login=jsonObject.getBoolean("login");
                            dialog_progress.dismiss();
                            if (!error)
                            {
                                if (login)
                                {
                                    JSONArray array=jsonObject.getJSONArray("addressList");
                                     for (int i=0;i<array.length();i++)
                                    {
                                        addresses.add(new modeldata_item_address(array.getJSONObject(i)
                                                .getString("id"),array.getJSONObject(i)
                                                .getString("address"),array.getJSONObject(i)
                                                .getString("city"),array.getJSONObject(i)
                                                .getString("province"),array.getJSONObject(i)
                                                .getString("postCode"),false));
                                    }
                                    if (addresses.size()!=0)
                                    {
                                         textView34556_myaddress.setVisibility(View.GONE);
                                        imageView2045554_myaddress.setVisibility(View.GONE);
                                        recy_myaddress.setVisibility(View.VISIBLE);
                                          adapter_item_address= new adpter_myaddress(addresses,getContext());
                                        recy_myaddress.setAdapter(adapter_item_address);
                                        adapter_item_address.setonclick(new adpter_myaddress.click() {
                                            @Override
                                            public void data(int pos, modeldata_item_address address23) {
                                                del_Address(address23.getId(),pos);
                                             }
                                        });

                                    }else {

                                    }

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
                        dialog_progress.dismiss();
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });
        thread.start();
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
    public void del_Address(String id,int pos)
    {
        dialog_progress.show();
        SharedPreferences preferences=getContext().getSharedPreferences("Login",    Context.MODE_PRIVATE);
        String name=preferences.getString("name","");
        String pass=preferences.getString("pass","");
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> map=new HashMap<>();
                map.put("userName",name);
                map.put("userPassword",pass);
                map.put("id",id);
                VollayRequest request=new VollayRequest("deleteAddress.php", map, getContext(), new I_can() {
                    @Override
                    public void info(JSONObject jsonObject) {
                        try {
                            Boolean error=jsonObject.getBoolean("error");
                            Boolean login=jsonObject.getBoolean("login");
                            dialog_progress.dismiss();
                            if (!error)
                            {
                                if (login)
                                {
                                    addresses.remove(pos);
                                    adapter_item_address.notifyItemChanged(pos);
                                    adapter_item_address.notifyItemRangeChanged(pos,addresses.size());
                                    if (addresses.size()==0)
                                    {
                                        imageView2045554_myaddress.setVisibility(View.VISIBLE);
                                        textView34556_myaddress.setVisibility(View.VISIBLE);
                                    }

                                }
                            }else {
                                Toast.makeText(getContext(), jsonObject.getString("MSG"), Toast.LENGTH_SHORT).show();
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
                          Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                      }
                    }
                });
            }
        });
        thread.start();
    }
}
