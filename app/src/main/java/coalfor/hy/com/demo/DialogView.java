package coalfor.hy.com.demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author ${mayong}
 *         创建时间 2017/5/10.
 */
public class DialogView extends RelativeLayout {
    private View content;
    private ImageView ivLeft;
    private ImageView ivMiddle;
    private ImageView ivRight;
    private ImageView switch1;
    private AnimatorSet a;
    private int width;
    private int height;

    public DialogView(Context context) {
        super(context);
        init(context);
    }

    public DialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        content = View.inflate(context, R.layout.dialog_layout, this);
        ivLeft = (ImageView) content.findViewById(R.id.iv_left);
        ivMiddle = (ImageView) content.findViewById(R.id.iv_middle);
        ivRight = (ImageView) content.findViewById(R.id.iv_right);
        switch1 = (ImageView) content.findViewById(R.id.switch1);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    private ObjectAnimator[] showAnim(ImageView ivLeft, int index) {
        float x = ivLeft.getX();
        float y = ivLeft.getY();

        float switchX = switch1.getX();
        float switchY = switch1.getY();
        float bX = x - switchX;
        float bY = y - switchY;

        ObjectAnimator anY = ObjectAnimator.ofFloat(ivLeft, View.TRANSLATION_Y, 0, -(height / 5));
        if (index == 0) {
            bX = -(width / 2 - 100);
        } else if (index == 1) {
            bX = 0;
        } else if (index == 2) {
            bX = (width / 2 - 100);
        }
        ObjectAnimator anX = ObjectAnimator.ofFloat(ivLeft, View.TRANSLATION_X, 0, bX);
        return new ObjectAnimator[]{anX, anY};
    }

    private boolean isShow = false;

    public boolean isShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public void show() {
        isShow = !isShow;
        a = new AnimatorSet();
        ObjectAnimator[] anLeft = showAnim(ivLeft, 0);
        ObjectAnimator[] anMiddle = showAnim(ivMiddle, 1);
        ObjectAnimator[] anRight = showAnim(ivRight, 2);
        a.setDuration(400);
        a.play(anLeft[0]).with(anLeft[1]).with(anRight[0]).with(anRight[1]).with(anMiddle[0]).with(anMiddle[1]);
        a.start();
    }
}
