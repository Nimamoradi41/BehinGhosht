package phonix.nimamoradi.frags;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import phonix.nimamoradi.Activity_moreandfestival;
import phonix.nimamoradi.I_can;
import phonix.nimamoradi.I_cant;
import phonix.nimamoradi.R;
import phonix.nimamoradi.Retrofit.Retrofit_server;
import phonix.nimamoradi.Retrofit.list_api;
import phonix.nimamoradi.VollayRequest;
import phonix.nimamoradi.adapter_event2;
import phonix.nimamoradi.adapter_item_spl;
import phonix.nimamoradi.adapter_item_spl2;
import phonix.nimamoradi.festivalList_api.main_festivallist_api;
import phonix.nimamoradi.mainPage_api.festival;
import phonix.nimamoradi.mainPage_api.main_mainPage_api;
import phonix.nimamoradi.model_Data_festivals;
import phonix.nimamoradi.modeldate_mainpage_items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_harag extends Fragment {
    RecyclerView recyHarag;
    RecyclerView Event;
    ImageView img_harag;
    adapter_event2 event2;
    LinearLayout img_harag2;
    SwipeRefreshLayout Ref;
    ArrayList<model_Data_festivals> festivals;
    Bundle bundle;
    Dialog dialog_progress;
    View dialog_progress_view;
    Retrofit_server server;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phonix_harrag, container, false);
        find(view);
        server=new Retrofit_server();
        config_dialog(container, inflater);
        main_mainPage_api items2 = (main_mainPage_api) bundle.getSerializable("Data");
        setrecy_event(items2.getFestival());
        Ref.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog_progress.show();
//                Opration_Ref();
                Opration_REF();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setrecy_event(ArrayList<festival> festivals2) {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Event.setLayoutManager(new LinearLayoutManager(getContext()));
        event2 = new adapter_event2(getContext(), metrics.heightPixels, metrics.widthPixels, festivals2, new adapter_event2.info() {
            @Override
            public void getinfo(festival festivals) {
                Intent intent = new Intent(getContext(), Activity_moreandfestival.class);
                intent.putExtra("Type", "Festival");
                intent.putExtra("id", festivals.getId());
                startActivity(intent);
            }
        });
        Event.setAdapter(event2);
    }

    public void find(View view) {
        recyHarag = view.findViewById(R.id.recy_harag);
        Ref = view.findViewById(R.id.Ref);
        img_harag2 = view.findViewById(R.id.img_harag2);
        Event = view.findViewById(R.id.recy_event2);
        img_harag = view.findViewById(R.id.img_harag);
        Ref = view.findViewById(R.id.Ref);
    }





    public void config_dialog(ViewGroup container, LayoutInflater inflater) {
        dialog_progress = new Dialog(getContext());
        dialog_progress.setCancelable(false);
        dialog_progress_view = inflater.inflate(R.layout.layout_progress, container, false);
        dialog_progress.setContentView(dialog_progress_view);
        dialog_progress.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog_progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void Opration_REF()
    {
        list_api api= server.getRetrofit().create(list_api.class);
        Call<main_festivallist_api> call=api.getfestivallist();
        call.enqueue(new Callback<main_festivallist_api>() {
            @Override
            public void onResponse(Call<main_festivallist_api> call, Response<main_festivallist_api> response) {
                if (response.isSuccessful())
                {
                    dialog_progress.dismiss();
                    main_festivallist_api api1=response.body();
                    setrecy_event(api1.getFestivals());
                    Ref.setRefreshing(false);


                }
            }

            @Override
            public void onFailure(Call<main_festivallist_api> call, Throwable t) {
                dialog_progress.dismiss();
                Ref.setRefreshing(false);
                Snackbar snackbar=Snackbar.make(getView(),"مشکلی در اتصال به اینترنت به وجود آمده",Snackbar.LENGTH_SHORT);
                ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
                snackbar.show();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog_progress=null;
        event2=null;
        bundle=null;
        dialog_progress_view=null;
;       System.gc();
    }
}
