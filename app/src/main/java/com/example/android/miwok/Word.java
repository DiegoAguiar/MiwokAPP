package com.example.android.miwok;

import android.graphics.drawable.Drawable;

/**
 * Created by diego.almeida on 13/04/2017.
 */
public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImgMenu =NO_IMAGE_PROVIDED;
    private int mPronunciaAudio = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String miwokTranslation, int pronunciaMenu){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mPronunciaAudio = pronunciaMenu;

    }

    public Word(String defaultTranslation, String miwokTranslation, int imgMenu, int pronunciaMenu){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImgMenu = imgMenu;
        mPronunciaAudio = pronunciaMenu;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImgMenu(){
        return mImgMenu;
    }

    public boolean hasImage(){
        return mImgMenu != NO_IMAGE_PROVIDED;
    }

    public int getPronunciaAudio(){
        return mPronunciaAudio;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mImgMenu=" + mImgMenu +
                ", mPronunciaAudio=" + mPronunciaAudio +
                '}';
    }
}
