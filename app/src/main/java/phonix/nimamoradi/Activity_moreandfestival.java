package phonix.nimamoradi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import phonix.nimamoradi.frags.frag_festival;
import phonix.nimamoradi.frags.frag_more;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Activity_moreandfestival extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setfont();
        setContentView(R.layout.activity_moreandfestival);
        String s=getIntent().getStringExtra("Type");
        if (s.equals("More"))
        {
            ArrayList<modeldate_mainpage_items> items= (ArrayList<modeldate_mainpage_items>) getIntent().
                    getBundleExtra("Bu").getSerializable("items2");
           frag_more frag_more=new frag_more();
           Bundle bundle=new Bundle();
           bundle.putSerializable("items",items);
           frag_more.setArguments(bundle);
           getSupportFragmentManager().beginTransaction()
                   .add(R.id.frame_more_festival,frag_more)
                   .commit();
        }
        if (s.equals("Festival"))
        {
            String ss=getIntent().getStringExtra("id");
            frag_festival festival=new frag_festival();
            Bundle bundle=new Bundle();
            bundle.putString("id",ss);
            festival.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_more_festival,festival)
                    .commit();
        }

    }
    public void setfont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSansMobile_Bold")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
