package com.nex3z.shalarm.presentation.ui.misc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nex3z.shalarm.R;

public class ToggleButton {
    private static final String LOG_TAG = ToggleButton.class.getSimpleName();

    private static final long DEFAULT_ANIMATION_DURATION = 150;
    private static final int DEFAULT_CHECKED_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_UNCHECKED_TEXT_COLOR = Color.BLACK;

    private boolean mIsChecked;
    private float mButtonSize;
    private int mCheckedTextColor = DEFAULT_CHECKED_TEXT_COLOR;
    private int mUncheckedTextColor = DEFAULT_UNCHECKED_TEXT_COLOR;

    private long mAnimationDuration = DEFAULT_ANIMATION_DURATION;
    private Animation mExpand;
    private Animation mShrink;

    private View mRootView;
    private ImageView mIvCheckedBg;
    private TextView mTvText;

    public ToggleButton(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.item_toggle_button, null);

        mIvCheckedBg = (ImageView)mRootView.findViewById(R.id.iv_background);
        mIvCheckedBg.setVisibility(View.INVISIBLE);

        mTvText = (TextView)mRootView.findViewById(R.id.tv_text);

        mExpand = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mExpand.setDuration(mAnimationDuration);
        mExpand.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTextColor();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        mShrink = new ScaleAnimation(1, 0, 1, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mShrink.setDuration(mAnimationDuration);
        mShrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvCheckedBg.setVisibility(View.INVISIBLE);
                updateTextColor();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mRootView.setOnClickListener(listener);
    }

    public View getView() {
        return mRootView;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean isChecked) {
        mIsChecked = isChecked;
        mIvCheckedBg.setVisibility(mIsChecked ? View.VISIBLE : View.INVISIBLE);
        updateTextColor();
    }

    public void setChecked(boolean isChecked, boolean animate) {
        if (!animate) {
            setChecked(isChecked);
        } else {
            mIsChecked = isChecked;
            if (mIsChecked) {
                mIvCheckedBg.setVisibility(View.VISIBLE);
                mIvCheckedBg.startAnimation(mExpand);
            } else {
                mIvCheckedBg.setVisibility(View.VISIBLE);
                mIvCheckedBg.startAnimation(mShrink);
            }
        }
    }

    public String getText() {
        return mTvText.getText().toString();
    }

    public void setText(String text) {
        mTvText.setText(text);
    }

    public void setTextSizePx(float pixels) {
        mTvText.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixels);
    }

    public void setTextSizeSp(float sp) {
        mTvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
    }

    public float getTextSize() {
        return mTvText.getTextSize();
    }

    public void setTextColor(int color) {
        mTvText.setTextColor(color);
    }

    public int getTextColor() {
        return mTvText.getCurrentTextColor();
    }

    public int getCheckedTextColor() {
        return mCheckedTextColor;
    }

    public void setCheckedTextColor(int checkedTextColor) {
        mCheckedTextColor = checkedTextColor;
        updateTextColor();
    }

    public int getUncheckedTextColor() {
        return mUncheckedTextColor;
    }

    public void setUncheckedTextColor(int uncheckedTextColor) {
        mUncheckedTextColor = uncheckedTextColor;
        updateTextColor();
    }

    public void setButtonSize(float size) {
        mButtonSize = size;
        mRootView.setLayoutParams(
                new LinearLayout.LayoutParams((int) mButtonSize, (int) mButtonSize));
    }

    public float getButtonSize() {
        return mButtonSize;
    }

    public Drawable getCheckedBackgroundDrawable() {
        return mIvCheckedBg.getDrawable();
    }

    public void setCheckedBackgroundDrawable(Drawable drawable) {
        mIvCheckedBg.setImageDrawable(drawable);
    }

    public long getAnimationDuration() {
        return mAnimationDuration;
    }

    public void setAnimationDuration(long animationDuration) {
        mAnimationDuration = animationDuration;
        mShrink.setDuration(mAnimationDuration);
        mExpand.setDuration(mAnimationDuration);
    }

    private void updateTextColor() {
        if (mIsChecked) {
            mTvText.setTextColor(mCheckedTextColor);
        } else {
            mTvText.setTextColor(mUncheckedTextColor);
        }
    }

}
