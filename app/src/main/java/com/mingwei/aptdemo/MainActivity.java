package com.mingwei.aptdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingwe.myanno.BindView;
import com.mingwe.myanno.BindViewFonts;
import com.mingwe.myanno.OnClick;
import com.mingwei.myapi.ButterKnife;

import static com.mingwei.aptdemo.R.id.btn2;

public class MainActivity extends Activity {

    public final String path = "fonts/bold.otf";
    public final String path_Regular = "fonts/Regular.otf";
    @BindViewFonts(value = R.id.btn1, fontsValue = path)
    public Button mBtn;
    @BindViewFonts(value = btn2, fontsValue = path)
    public Button mBtn2;
    @BindView(value = R.id.liner)
    public LinearLayout liner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBtn.setText("按钮");
        mBtn2.setText("按钮2");
//        liner.setVisibility(View.GONE);
//        Utls.setFonts(this,mBtn,path);
    }

    //    public void onClicks() {
//        Toast.makeText(this, "showbtn1", Toast.LENGTH_SHORT).show();
//    }
//    @OnClick(R.id.btn4)
//    public void onClicks4() {
//        Toast.makeText(this, "showbtn4", Toast.LENGTH_SHORT).show();
//    }
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClicks1() {

    }
    public void onClicks3(View v) {

    }

//    @OnClick( R.id.btn4)
//    public void onClicks4( ) {
//        Toast.makeText(this, "showbtn42", Toast.LENGTH_SHORT).show();
//    }
    public void onClicks(View v) {
        Toast.makeText(this, "show" + v.getId(), Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.btn1:
                Toast.makeText(this, "showbtn1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this, "showbtn2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(this, "showbtn3", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    @OnClick({R.id.btn2})
//    public void click2(View v) {
//        Toast.makeText(this, "show2view=", Toast.LENGTH_SHORT).show();
//    } ;

}
