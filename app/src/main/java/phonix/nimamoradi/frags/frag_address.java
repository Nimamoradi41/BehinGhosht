package phonix.nimamoradi.frags;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.adpter_address;
import phonix.nimamoradi.modeldata_citys;
import phonix.nimamoradi.modeldata_item_address;
import phonix.nimamoradi.modeldataosta;
import phonix.nimamoradi.spinner_Adapter;

public class frag_address extends Fragment {
    TextView textView39_noaddress;
    ImageView imageView20;
    Dialog dialog_progress;
    View dialog_progress_view;
    RecyclerView recy_address;
    Button add_address;
    Dialog addaddress;
    View view_address;
   Spinner spl_osta;
   Spinner id_shahr;
   ArrayList<modeldataosta> Ostan;
   ArrayList<String> Ostan2;
   ArrayList<modeldata_citys> City;
   ArrayList<String> City2;
   AlertDialog.Builder builder;
   String provinceId;
   String cityId;
   Button Cansel34;
   Button btn_sabt_wq;
   EditText edt_postal_card;
   EditText edt_Address;
   ArrayList<modeldata_citys> Zapas;
    ArrayList<modeldata_item_address> addresses;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_address,container,false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        find(view);
        cityId="";
        provinceId="";
        addresses=new ArrayList<>();
        config_dialog(container,inflater);
        config_dialog_address(container,inflater);
        dialog_progress.show();
        btn_sabt_wq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_progress.show();
                save_address();
            }
        });
        id_shahr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//             String name=

                if (Zapas.get(position).getName().equals("ندارد"))
                {
                    cityId="";

                    return;

                }
                else {
                    cityId = City.get(position).getId();
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Cansel34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addaddress.dismiss();
            }
        });
        run();
        spl_osta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                  cityId=Zapas.get(position).getId();
                provinceId = Ostan.get(position).getId();
//                Toast.makeText(getContext(), cityId, Toast.LENGTH_SHORT).show();
//                String ostan=Ostan.get(position).getId();
//                provinceId=ostan;
                Zapas = new ArrayList<>();
                 for (int i = 0; i < City.size(); i++) {
                    String J = City.get(i).getId_ostan();
                    if (provinceId.equals(J)) {
                        Zapas.add(new modeldata_citys(City.get(i).getId(), City.get(i).getName(), City.get(i).getId_ostan()));
                    }
                }
                if (Zapas.size() != 0) {
                    cityId=Zapas.get(0).getId();
                 }else {
                    Zapas.add(new modeldata_citys("1","ندارد","A"));
                    cityId="";
                 }
                spinner_Adapter adapter=new spinner_Adapter(Zapas,getContext());
                id_shahr.setAdapter(adapter);
             }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

//                provinceId= City.get(0).getId_ostan();
//                cityId=City.get(0).getId();
//                Toast.makeText(getContext(), provinceId, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), cityId, Toast.LENGTH_SHORT).show();

            }
        });
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_progress.show();
                   getcitys();
//                ArrayList<String>  strings=new ArrayList<>();
//                strings.add("cdsvsvsv");
//                strings.add("cdsvsvsv");
//                strings.add("cdsvsvsv");
//                strings.add("cdsvsvsv");
//                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strings);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spl_osta.setAdapter(adapter);
//                addaddress.show();
//                Ostan.add(new modeldataosta("1","تهران"));
//                Ostan.add(new modeldataosta("2","اهواز"));
//                Ostan.add(new modeldataosta("3","اصفهان"));
//                Ostan2.add("تهران");
//                Ostan2.add("اهواز");
//                Ostan2.add("اصفهان");
//                City.add(new modeldata_citys("45","شهر ری","1"));
//                City.add(new modeldata_citys("55","ورامین","1"));
//                City.add(new modeldata_citys("87","ملک شهر","2"));
//                City.add(new modeldata_citys("71","شاهین شهر","2"));
//                City.add(new modeldata_citys("69","ابادان","3"));
//                City.add(new modeldata_citys("12","دزفول","3"));
//                City2.add("شهر ری");
//                City2.add("ورامین");
//                City2.add("ملک شهر");
//                City2.add("شاهین شهر");
//                City2.add("ابادان");
//                City2.add("دزفول");
//                ArrayAdapter stringArrayAdapter2=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,Ostan2);
//                stringArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spl_osta.setAdapter(stringArrayAdapter2);
//                ArrayAdapter stringArrayAdapter3=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,City2);
//                stringArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                id_shahr.setAdapter(stringArrayAdapter3);
//                addaddress.show();
            }
        });

        return  view;
    }
    public void getcitys()
    {
        Zapas=new ArrayList<>();
        Ostan=new ArrayList<>();
        City=new ArrayList<>();
        City2=new ArrayList<>();
        Ostan2=new ArrayList<>();
        SharedPreferences preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String name=preferences.getString("name","");
        String pass=preferences.getString("pass","");
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        VollayRequest request=new VollayRequest("getCitesProvince.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
                try {
                    Boolean error=jsonObject.getBoolean("error");
                    Boolean login=jsonObject.getBoolean("login");
                    if (!error)
                    {
                        if (login)
                        {
                            JSONArray array=jsonObject.getJSONArray("provinces");
                            JSONArray array1=jsonObject.getJSONArray("cites");
                            for (int i=0;i<array.length();i++)
                            {
                                String name=array.getJSONObject(i).getString("name");
                                String id=array.getJSONObject(i).getString("id");
                                Ostan.add(new modeldataosta(id,
                                      name));
//                                Ostan2.add(name);
                              Ostan2.add(name);
                            }
                            for (int i=0;i<array1.length();i++)
                            {
                              City.add(new modeldata_citys(
                                      array1.getJSONObject(i).getString("id"),
                                      array1.getJSONObject(i).getString("name"),
                                      array1.getJSONObject(i).getString("provinceId")));
                                     City2.add(array1.getJSONObject(i).getString("name"));
                            }
                            ArrayAdapter stringArrayAdapter2=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,Ostan2);
                            stringArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spl_osta.setAdapter(stringArrayAdapter2);
//                            ArrayAdapter stringArrayAdapter3=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,City2);
//                            stringArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            id_shahr.setAdapter(stringArrayAdapter3);
                            String id_fisrt_ostan=Ostan.get(0).getId();
                            provinceId=Ostan.get(0).getId();
                            Zapas=new ArrayList<>();
                            for (int i=0;i<City.size();i++)
                            {
                                String id=City.get(i).getId_ostan();
                                if (id.equals(id_fisrt_ostan))
                                {
                                    Zapas.add(new modeldata_citys(City.get(i).getId(),City.get(i).getName(),City.get(i).getId_ostan()));
                                }
                            }
                             if (Zapas.size()!=0)
                             {
                                 cityId=Zapas.get(0).getId();
                              }
                             else {
                                 Zapas.add(new modeldata_citys("1","ندارد","A"));
                                 cityId="";
                              }


                             spinner_Adapter adapter=new spinner_Adapter(Zapas,getContext());
                             id_shahr.setAdapter(adapter);
                            dialog_progress.dismiss();
                             addaddress.show();
                        }
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
               Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
           }
            }
        });
    }
    private void config_dialog_address(ViewGroup container, LayoutInflater inflater) {
        addaddress=new Dialog(getContext());
        addaddress.setCancelable(false);
        dialog_progress_view=inflater.inflate(R.layout.layout_custome_dialog,container,false);
        spl_osta=dialog_progress_view.findViewById(R.id.spl_osta);
        id_shahr=dialog_progress_view.findViewById(R.id.id_shahr);
        Cansel34=dialog_progress_view.findViewById(R.id.Cansel34);
        btn_sabt_wq=dialog_progress_view.findViewById(R.id.btn_sabt_wq);
        edt_Address=dialog_progress_view.findViewById(R.id.edt_Address);
        edt_postal_card=dialog_progress_view.findViewById(R.id.edt_postal_card);
        addaddress.setContentView(dialog_progress_view);
        addaddress.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        addaddress.getWindow ().setBackgroundDrawable ( new ColorDrawable( Color.TRANSPARENT ) );
    }

    private void run() {
        SharedPreferences preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String name=preferences.getString("name","");
        String pass=preferences.getString("pass","");
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",name);
        map.put("userPassword",pass);
        addresses=new ArrayList<>();
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
                            textView39_noaddress.setVisibility(View.GONE);
                            imageView20.setVisibility(View.GONE);
                            recy_address.setVisibility(View.VISIBLE);
                          adpter_address adapter_item_address=new adpter_address(addresses,getContext());
                          recy_address.setAdapter(adapter_item_address);
                          dialog_progress.dismiss();
                          adapter_item_address.setonclick(new adpter_address.click() {
                              @Override
                              public void data(int pos) {
                                  for (int i=0;i<addresses.size();i++)
                                  {
                                      if (i==pos)
                                      {
                                          addresses.get(i).setFlg(true);
                                      }else {
                                          addresses.get(i).setFlg(false);
                                      }
                                  }
                                  adapter_item_address.notifyDataSetChanged();

                              }
                          });
                        }else {
                            dialog_progress.dismiss();
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
                    Toast.makeText(getContext(), "Erorr", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void find(View view)
    {
        textView39_noaddress=view.findViewById(R.id.textView34556);
        add_address=view.findViewById(R.id.comeon);
        imageView20=view.findViewById(R.id.imageView2045554);
        recy_address=view.findViewById(R.id.recy_address);
        recy_address.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void save_address()
    {
        if (cityId.equals(""))
        {
            dialog_progress.dismiss();
            Toast.makeText(getContext(), "در سیستم موجود نمی باشد", Toast.LENGTH_SHORT).show();

            return;
        }
        if (edt_postal_card.getText().toString().trim().isEmpty())
        {
            dialog_progress.dismiss();
            Toast.makeText(getContext(), "ورودی هارا پر کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edt_Address.getText().toString().trim().isEmpty())
        {
            dialog_progress.dismiss();
            Toast.makeText(getContext(), "ورودی هارا پر کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences preferences=getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
        String Name=preferences.getString("name","");
        String Pass=preferences.getString("pass","");
        HashMap<String,String> map=new HashMap<>();
        map.put("userName",Name);
        map.put("userPassword",Pass);
        map.put("postalCode",edt_postal_card.getText().toString().trim());
        map.put("address",edt_Address.getText().toString().trim());
        map.put("cityId",cityId);
        map.put("provinceId",provinceId);
        Log.e("NIMAMMORADIANOf",provinceId);
        Log.e("NIMAMMORADIANOf",cityId);
        VollayRequest request=new VollayRequest("addAddress.php", map, getContext(), new I_can() {
            @Override
            public void info(JSONObject jsonObject) {
            try {

                Boolean error=jsonObject.getBoolean("error");
                String addressId=jsonObject.getString("addressId");

//                 String s=
                if (!error)
                {

                    if (!addressId.isEmpty())
                    {
                        Toast.makeText(getContext(), "آدرس با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                        addaddress.dismiss();
                        dialog_progress.show();
                        run();
                    }else {
                        Toast.makeText(getContext(), "آدرس با موفقیت ثبت نشد", Toast.LENGTH_SHORT).show();
                        dialog_progress.dismiss();
                    }
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
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
