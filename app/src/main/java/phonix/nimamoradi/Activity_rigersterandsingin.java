package phonix.nimamoradi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import phonix.nimamoradi.frags.frag_sabtenam;
import phonix.nimamoradi.frags.frag_subcate;
import phonix.nimamoradi.frags.frag_voroud;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
public class Activity_rigersterandsingin extends AppCompatActivity implements View.OnClickListener {
    Button btn_sabt_2;
    Button btn_cancel;
    LinearLayout edt_pass_2;
    TextView txt_new_acount;
    TextView txt_sabt;
    View line_4141;
    ScrollView scrol;
   boolean type=true;
   LinearLayout lin741;
   String Type;
   frag_subcate subcate;
   LinearLayout edt_mobile;
   LinearLayout edt_email;
   FrameLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setfont();
        setContentView(R.layout.activity_rigersterandsingin);
        if (getIntent().getStringExtra("Type77").equals("A"))
        {
            frag_voroud sabtenam=new frag_voroud();
            getSupportFragmentManager().beginTransaction().replace(R.id.chatfragment,sabtenam).commit();
        }else if (getIntent().getStringExtra("Type77").equals("B")){
            frag_sabtenam sabtenam=new frag_sabtenam();
            getSupportFragmentManager().beginTransaction().replace(R.id.chatfragment,sabtenam).commit();
        }


//        btn_cancel.setOnClickListener(this);
//        txt_new_acount.setOnClickListener(this);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void setfont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    public void find( )
    {
        btn_sabt_2=findViewById(R.id.btn_sabt_2);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_email=findViewById(R.id.edt_email);
        btn_cancel=findViewById(R.id.btn_cancel);
        edt_pass_2=findViewById(R.id.edt_pass);
        txt_new_acount=findViewById(R.id.txt_new_acount);
        txt_sabt=findViewById(R.id.txt_sabt);
//        scrol=findViewById(R.id.scrol);
        lin741=findViewById(R.id.lin741);
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.txt_new_acount)
        {
            txt_sabt.animate().setDuration(250).alpha(0).scaleX(0).scaleY(0).withEndAction(new Runnable() {
                @Override
                public void run() {
                    txt_sabt.setText("ساخت حساب کاربری");
                    txt_sabt.animate().alpha(1).scaleX(1).scaleY(1).setDuration(250).setStartDelay(250).start();
                }
            }).start();
//            edt_pass_2.setVisibility(View.VISIBLE);
//            edt_pass_2.animate().translationY(edt_pass_2.getHeight());
            if (type)
            {
                expand(edt_pass_2);
                collapse(txt_new_acount);
                type=false;
            }else {
            }
            if (edt_pass_2.getVisibility()==View.GONE)
            {


            }else {

            }

//            TranslateAnimation animate = new TranslateAnimation(
//                    0,
//                    0,
//                    edt_pass_2.getHeight(),
//                    0);
//            animate.setDuration(500);
//            animate.setFillAfter(true);
//            edt_pass_2.startAnimation(animate);
//                expand(edt_pass_2);
//            Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.anim2);
//            if (edt_pass_2.getVisibility() == View.GONE) {
//                edt_pass_2.setVisibility(View.VISIBLE);
//                edt_pass_2.startAnimation(slideUp);
//            }
//            edt_pass_2.setVisibility(View.VISIBLE);
//            edt_pass_2.setAlpha(0f);
//            edt_pass_2.animate().alpha(1.0f).setDuration(800).start();
//            expand(edt_pass_2);
//                edt_pass_2.getLayoutParams().height=0;
//                edt_pass_2.setVisibility(View.VISIBLE);
//                edt_pass_2.getLayoutParams().height=LinearLayout.LayoutParams.WRAP_CONTENT;
//                edt_pass_2.getLayoutParams().width=LinearLayout.LayoutParams.WRAP_CONTENT;
//                edt_pass_2.setAlpha(0f);
//            edt_pass_2.animate().alpha(1.0f).setDuration(800).start();
//            edt_pass_2.animate().alpha(1.0f).setDuration(800).start();
//            expand(edt_pass_2);
 //            txt_sabt.animate().setDuration(250).alpha(0).scaleX(0).scaleY(0).withEndAction(new Runnable() {
//                @Override
//                public void run() {
//                    txt_sabt.setText("ساخت حساب کاربری");
//                    txt_sabt.animate().alpha(1).scaleX(1).scaleY(1).setDuration(250).withEndAction(new Runnable() {
//                        @Override
//                        public void run() {
//                            txt_new_acount.animate().alpha(0).scaleY(0).scaleX(0).setDuration(200).setStartDelay(300).withEndAction(new Runnable() {
//                                @Override
//                                public void run() {
//                                    line_4141.animate().translationY(-(line_4141.getHeight()+txt_new_acount.getHeight()));
//                                }
//                            }).start();
//                        }
//                    }).start();
//                }
//            }).start();
        }
    }
    public static void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    @Override
    public void onBackPressed() {
        if (!type)
        {
            collapse(edt_pass_2);
            expand(txt_new_acount);
            type=true;
        }else {
            super.onBackPressed();
        }

    }
}
