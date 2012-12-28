package cn.yang.sina.weibo.beans;

import android.graphics.Bitmap;

public class BaseEmotions {

	private Bitmap bmpEmotion;
	private String emotionName;

	public BaseEmotions(){
		
	}
	public BaseEmotions(Bitmap bmpEmotion, String emotionName) {
		super();
		this.bmpEmotion = bmpEmotion;
		this.emotionName = emotionName;
	}
	public Bitmap getBmpEmotion() {
		return bmpEmotion;
	}
	public void setBmpEmotion(Bitmap bmpEmotion) {
		this.bmpEmotion = bmpEmotion;
	}
	public String getEmotionName() {
		return emotionName;
	}
	public void setEmotionName(String emotionName) {
		this.emotionName = emotionName;
	}
}
