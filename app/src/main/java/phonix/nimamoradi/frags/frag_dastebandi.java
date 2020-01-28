package phonix.nimamoradi.frags;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import phonix.nimamoradi.Activity_evrything;
import phonix.nimamoradi.R;
import phonix.nimamoradi.modeldata_mainpage_category;
import phonix.nimamoradi.recy_cate_dastebandi;

public class frag_dastebandi extends Fragment {
    RecyclerView recyclerView;
    ArrayList<modeldata_mainpage_category> category;
    Bundle bundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.layout_frag_dastebandi,container,false);
        category=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recy_dastebandi);


       return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        category= (ArrayList<modeldata_mainpage_category>) bundle.getSerializable("cate2");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recy_cate_dastebandi recy_cate_dastebandi=new recy_cate_dastebandi(getContext(), category, new recy_cate_dastebandi.info() {
            @Override
            public void data(modeldata_mainpage_category subcate) {
                Intent intent=new Intent(getContext(), Activity_evrything.class);
                intent.putExtra("Type","frag_subcate");
                intent.putExtra("Type2","frag_subcate_cate");
                intent.putExtra("id",subcate.getId());
                intent.putExtra("name",subcate.getName());
                startActivity(intent);
            }
        },metrics.widthPixels);
        recyclerView.setAdapter(recy_cate_dastebandi);
    }
}
