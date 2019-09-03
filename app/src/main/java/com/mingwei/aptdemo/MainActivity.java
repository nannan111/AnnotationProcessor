package com.mingwei.aptdemo;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    @BindViewFonts(value = R.id.btn2, fontsValue = path)
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
    public void onClicks1(View view) {
//        Toast.makeText(this, "showbtn42=id"+view.getId(), Toast.LENGTH_SHORT).show();
//        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_card, null);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_card,null,false);
        ViewHolder viewHolder=new ViewHolder(MainActivity.this,contentView);
        viewHolder.text.setText("测试测试看看怎么样");
        viewHolder.text2.setText("测试测试看看怎么样");
        viewHolder.cancel_button.setText("测试测试看看怎么样");
    }

    public void onClicks1(int v) {
        Toast.makeText(this, "showbtn43", Toast.LENGTH_SHORT).show();
    }
    public class ViewHolder {
        @BindView(R.id.bg_img)
        public ImageView bg_img;
        @BindView(R.id.bg_finger)
        public ImageView bg_finger;
        @BindView(R.id.star1)
        public ImageView star1;
        @BindView(R.id.star2)
        public ImageView star2;
        @BindView(R.id.star3)
        public ImageView star3;
        @BindView(R.id.star4)
        public ImageView star4;
        @BindView(R.id.star5)
        public ImageView star5;
        @BindView(R.id.start_layout)
        public LinearLayout start_layout;
        @BindViewFonts(value = R.id.text, fontsValue = path)
        public TextView text;
        @BindViewFonts(value = R.id.text2, fontsValue = path)
        public TextView text2;
        @BindView(R.id.cancel_button)
        public Button cancel_button;

        public ViewHolder(Activity activity,View rt) {
            ButterKnife.bind(this,rt);
        }

    }
    @OnClick( R.id.btn4)
    public void onClicks4() {
        Toast.makeText(this, "showbtn43", Toast.LENGTH_SHORT).show();
    }
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
