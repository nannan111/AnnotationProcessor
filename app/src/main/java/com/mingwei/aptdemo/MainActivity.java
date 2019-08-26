package com.mingwei.aptdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingwe.myanno.BindView;
import com.mingwe.myanno.Fonts;
import com.mingwe.myanno.OnClick;
import com.mingwei.myapi.ButterKnife;

public class MainActivity extends Activity {

   public final String path="fonts/bold.otf";
    public final String path_Regular="fonts/Regular.otf";
    @BindView(value = R.id.btn1, fontsValue = path)
    public Button mBtn;
    @BindView(value = R.id.btn2, fontsValue = path)
    public Button mBtn2;
    @BindView(value = R.id.liner, fontsValue = "")
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

    @OnClick({R.id.btn1})
    public void click() {
        Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn2})
    public void click2() {
        Toast.makeText(this, "show2", Toast.LENGTH_SHORT).show();
    }

}
