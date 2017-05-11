package coalfor.hy.com.demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * @author ${mayong}
 *         创建时间 2017/5/10.
 */
public class TDialog extends Dialog {
    private ImageView ivLeft;
    private ImageView ivMiddle;
    private ImageView ivRight;
    private ImageView switch1;
    private AnimatorSet a;

    public TDialog(Context context) {
        super(context);
    }

    public TDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_layout);
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        getWindow().setGravity(Gravity.CENTER);
        initView();
    }

    protected TDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void initView() {
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivMiddle = (ImageView) findViewById(R.id.iv_middle);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        switch1 = (ImageView) findViewById(R.id.switch1);
    }

    @Override
    public void show() {
        super.show();
        a = new AnimatorSet();
        showAnim(ivLeft, 0);
        showAnim(ivRight, 0);
        showAnim(ivMiddle, 0);
        a.setDuration(1000);
        a.start();
    }

    private void showAnim(ImageView ivLeft, int index) {
        float x = ivLeft.getX();
        float y = ivLeft.getY();

        float switchX = switch1.getX();
        float switchY = switch1.getY();
        float bX = x - switchX;
        float bY = y - switchY;

        ObjectAnimator anY = ObjectAnimator.ofFloat(ivLeft, View.TRANSLATION_Y, switchY, bY);
        ObjectAnimator anX = ObjectAnimator.ofFloat(ivLeft, View.TRANSLATION_X, switchX, bX);
        a.play(anX).with(anY);
    }
}
