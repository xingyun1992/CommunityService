package com.sz.communityservice.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.communityservice.R;


public class HomeBottomButton extends RelativeLayout {
	@SuppressWarnings("unused")
	private static final String TAG = "[ZHUANG]HomeBottomButton";
	private String textValue;
	public HomeBottomButton(Context context) {
		super(context);
	}
	
	public HomeBottomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(attrs, context);
	}

	protected void initView(AttributeSet attrs, Context context) {
		inflate(context, R.layout.home_bottom_button_layout, this);
		setClickable(true);
		setFocusable(true);
		Drawable	drawable	= null;
				textValue	= null;
		if (attrs != null) {
			final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HomeBottomButton);
			drawable	= a.getDrawable(R.styleable.HomeBottomButton_home_bottom_button_icon);
			textValue	= a.getString(R.styleable.HomeBottomButton_home_bottom_button_text);
			a.recycle();

		}
		ImageView iv = ((ImageView) findViewById(R.id.home_bottom_button_image));
		iv.setImageDrawable(drawable);
		iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		((TextView) findViewById(R.id.home_bottom_button_text)).setText(textValue);
		((TextView) findViewById(R.id.home_bottom_button_text)).setTextSize(12);
	}
	public String getText(){
		return textValue;
	}
}
