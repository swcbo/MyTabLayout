package tablayout.huangbo.com.tablayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangb on 2017/4/12.
 * 弹性view 包裹布局都能弹性拉动
 */

public class HbElasticView extends ScrollView {

    /**
     * 是否可滑动
     */
    private boolean isCanScrool = true;

    /**
     * 回弹速度
     */
    private int mSpeed = 500;


    /**
     * 最大拖动距离
     */
    private int mMaxHeight = 600;

    /**
     * 移动系数
     */
    private float mCoefficient = 0f;
    //布局初始位置
    private float mViewY = 0;
    //手指初始位置
    private float mStartY = 0;

    //移动距离
    private float mMoveY = 0;

    //上一次Y的位置
    private float mLastY = 0;

    private boolean isMove = false;


    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;

    private boolean isStart = false;

    //向上滑动
    private boolean isScrollUp = true;
    //向下滑动
    private boolean isScrollDown = true;

    /**
     * 上一次滑动的位置
     */
    private float mNextY = 0;


    private boolean isAnimation = false;


    public HbElasticView(Context context) {
        this(context, null, 0);
    }

    public HbElasticView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HbElasticView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //是否初始化完(防止多次)
        if (isStart) {
            return;
        }
        isStart = true;
        //获取最初高度
        mViewY = getY();

    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /**
         * 判断是否滑动到顶部
         */
        if (getScrollY() == 0) {
            isScrolledToTop = true;
            isScrolledToBottom = false;
            /**
             * 判断是否滑动到底部
             */
        } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
            isScrolledToBottom = true;
            isScrolledToTop = false;
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        checkMoveDirection(event.getRawY());
        /**
         * 滑动监听
         */
        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:
                if (!isAnimation) {
                    clearAnimation();
                    isAnimation = false;
                    //初始化位置
                    mStartY = event.getRawY();
                    mLastY = event.getRawY();
                    mMoveY = 0;
                }
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                if ((isScrolledToTop && isScrollDown || isScrolledToBottom && isScrollUp) && !isAnimation) {
                    if (Math.abs(event.getRawY() - mStartY) < mMaxHeight) {
                        isMove = true;
                        /**
                         * 阻尼系数不为0时
                         */
                        if (mCoefficient > 0) {
                            //设置位置(移动距离等于手指移动距离乘以阻尼系数)
                            setY(mViewY + (event.getRawY() - mStartY) * mCoefficient);
                            /**
                             * 阻尼系数为0时
                             */
                        } else {
                            //计算应该移动的距离(移动距离乘以移动距离占总移动百分比)
                            mMoveY += (event.getRawY() - mLastY) * (float) (1 - ((double) Math.abs(event.getRawY() - mStartY) * 100 / mMaxHeight) / 100);
                            setY(mViewY + mMoveY);
                            //记录上次位置
                            mLastY = event.getRawY();
                        }
                        Log.e("last", "mLastY----" + mLastY + "    mStartY---" + mStartY);
                    } else {
                        isMove = false;
                    }
                } else {
                    if (!isMove) {
                        mStartY = event.getRawY();
                        mLastY = event.getRawY();
                        mMoveY = 0;
                    }
                }
                break;
            //向上
            case MotionEvent.ACTION_UP:
                moveView();
                isMove = false;
                break;
        }
        return isAnimation ? isAnimation : super.onTouchEvent(event);
    }


    /**
     * view回弹 动画
     */
    private void moveView() {
        isAnimation = true;
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, mViewY - getY());
        translateAnimation.setDuration(mSpeed);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearAnimation();
                setY(mViewY);
                isAnimation = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(translateAnimation);
    }


    /**
     * 检查滑动方向
     */
    private void checkMoveDirection(float rawY) {
        /**
         * 向上滑动
         */
        if (mNextY - rawY > 0) {
            isScrollUp = true;
            isScrollDown = false;
            /**
             * 向下滑动
             */
        } else {
            isScrollDown = true;
            isScrollUp = false;
        }
        /**
         * 保存上次滑动的位置
         */
        mNextY = rawY;

    }


}
