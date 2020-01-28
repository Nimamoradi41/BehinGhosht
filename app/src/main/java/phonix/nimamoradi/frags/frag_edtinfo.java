package phonix.nimamoradi.frags;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.util.HashMap;

import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.VollayRequest;

public class frag_edtinfo extends Fragment {
    EditText id_gmail,id_name,id_phonenumber;
    Button btn_edt_edit,btn_cancel_77_edt;
    Dialog dialog_progress;
    View dialog_progress_view;
    Bundle bundle;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frag_edtinfo,container,false);
        find(view);
        getdata_and_setdata();
        config_dialog(container,inflater);
        Back();
        edit_info();




        return view;
    }
    public void edit_info()
    {
        btn_edt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                String name=preferences.getString("name","");
                String pass=preferences.getString("pass","");
                if (id_name.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(), "نام را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (id_gmail.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(), "ایمیل را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (id_phonenumber.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(), "شماره موبایل را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog_progress.show();
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String,String> map=new HashMap<>();
                        map.put("userName",name);
                        map.put("userPassword",pass);
                        map.put("name",id_name.getText().toString().trim());
                        map.put("email",id_gmail.getText().toString().trim());
                        map.put("phoneNumber",id_phonenumber.getText().toString().trim());
                        VollayRequest request=new VollayRequest("editProfile.php", map, getContext(), new I_can() {
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
                                            Toast.makeText(getContext(), "ویرایش اطلاعات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                            SharedPreferences.Editor editor=getContext().getSharedPreferences("Login",Context.MODE_PRIVATE).edit();
                                            editor.putString("name",id_name.getText().toString().trim()).apply();
                                            getActivity().setResult(Activity.RESULT_OK);
                                            getActivity().finish();
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
                                dialog_progress.show();
                                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                            }
                            }
                        });
                    }
                });
                thread.start();
            }
        });

    }
       public void getdata_and_setdata()
       {
           id_name.setText(bundle.getString("name"));
           id_gmail.setText(bundle.getString("email"));
           id_phonenumber.setText(bundle.getString("phoneNumber"));
       }
    private void find(View view) {
        id_phonenumber=view.findViewById(R.id.id_phonenumber);
        id_name=view.findViewById(R.id.id_name);
        id_gmail=view.findViewById(R.id.id_gmail);
        btn_cancel_77_edt=view.findViewById(R.id.btn_cancel_77_edt);
        btn_edt_edit=view.findViewById(R.id.btn_edt_edit);
    }
    public void Back()
    {
        btn_cancel_77_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
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
