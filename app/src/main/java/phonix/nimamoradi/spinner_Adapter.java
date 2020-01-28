package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class spinner_Adapter extends BaseAdapter{
        ArrayList<modeldata_citys> citys;
        Context context;

    public spinner_Adapter(ArrayList<modeldata_citys> citys, Context context) {
        this.citys = citys;
        this.context = context;
    }

    @Override
    public int getCount() {
        return citys.size();
    }

    @Override
    public Object getItem(int position) {
        return citys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        modeldata_citys modeldata_citys=citys.get(position);
       View view= LayoutInflater.from(context).inflate(R.layout.layoout_custome_spinner,null);
        TextView txtTitle = (TextView) view.findViewById(R.id.id_txt_spinner);
        txtTitle.setText(modeldata_citys.getName());
        return view;
    }
//    ArrayList<modeldata_citys> citys;
//    Context context;
//    public spinner_Adapter(@NonNull Context context, int resource, ArrayList<modeldata_citys> citys) {
//        super(context, resource,citys);
//        this.context=context;
//        this.citys=citys;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//       modeldata_citys modeldata_citys=citys.get(position);
//       View view= LayoutInflater.from(context).inflate(R.layout.layoout_custome_spinner,null,false);
//        TextView txtTitle = (TextView) view.findViewById(R.id.id_txt_spinner);
//        txtTitle.setText(modeldata_citys.getName());
//        return view;
//
//    }
//
//    @Override
//    public int getCount() {
//      return   citys.size();
//    }
}
