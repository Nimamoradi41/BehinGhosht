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

public class frag_change_pass extends Fragment {
    Button btn_edt,btn_cancel_77;
    EditText old_pass,new_pass,repeat_pass;
    Dialog dialog_progress;
    View dialog_progress_view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frag_change_pass,container,false);
        find(view);
        Back();
        config_dialog(container,inflater);
        btn_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edt_Info();
            }
        });


        return view;
    }

    private void find(View view) {
        btn_edt=view.findViewById(R.id.btn_edt);
        btn_cancel_77=view.findViewById(R.id.btn_cancel_77);
        old_pass=view.findViewById(R.id.old_pass);
        new_pass=view.findViewById(R.id.new_pass);
        repeat_pass=view.findViewById(R.id.repeat_pass);

    }
    public void Back()
    {
        btn_cancel_77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    public void Edt_Info()
    {
        SharedPreferences preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String name=   preferences.getString("name","");
        String pass= preferences.getString("pass","");
        if (new_pass.getText().toString().trim().equals(repeat_pass.getText().toString().trim()))
        {

        }else {
            Toast.makeText(getContext(), "رمز ها مساوی نمی باشند", Toast.LENGTH_SHORT).show();
            return;
        }

        if (new_pass.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getContext(), "رمز جدید را وارد کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        if (old_pass.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getContext(), "رمز قبلی را وارد کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog_progress.show();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> map=new HashMap<>();
                map.put("userName",name);
                map.put("userPassword",pass);
                map.put("password",new_pass.getText().toString().trim());
                map.put("checkPassword",repeat_pass.getText().toString().trim());
                map.put("oldPassword",old_pass.getText().toString().trim());
                VollayRequest request=new VollayRequest("changePassword.php", map, getContext(), new I_can() {
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
                                      Toast.makeText(getContext(), "تغییر رمز با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor preferences=getContext().getSharedPreferences("Login", Context.MODE_PRIVATE).edit();
                                      preferences.putString("pass",new_pass.getText().toString().trim()).apply();
                                    getActivity().finish();
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
                            dialog_progress.show();
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
}
