package coalfor.hy.com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout content;
    private ImageView iv;
    DialogView dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv_main);
        dv = (DialogView) findViewById(R.id.dv);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Dialog dialog = new TDialog(this, R.style.dialog);
//        dialog.show();
        if(dv.isShow()){
            dv.setVisibility(View.GONE);
            dv.setIsShow(false);
        }else {
            dv.setVisibility(View.VISIBLE);
            dv.show();
        }

    }
}
