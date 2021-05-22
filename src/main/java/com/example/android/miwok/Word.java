package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.Image;

public class Word {

    private  String MiwokTranslation;
    private  String DefaultTranslation;
    public static  final int NO_IMAGE_PROVIDED=-1;
    private  int mAudioResource;
    private int mImageResourceId=NO_IMAGE_PROVIDED;


    public Word(String DefaultTranslation,String MiwokTranslation,int mAudioResource) {
        this.MiwokTranslation=MiwokTranslation;
        this.DefaultTranslation=DefaultTranslation;
        this.mAudioResource=mAudioResource;
    }
    public Word(String DefaultTranslation,String MiwokTranslation,int mImageResourceId,int mAudioResource){
        this.MiwokTranslation=MiwokTranslation;
        this.DefaultTranslation=DefaultTranslation;
        this.mImageResourceId=mImageResourceId;
        this.mAudioResource=mAudioResource;
    }

    public  String getMiwokTranslation() {
        return MiwokTranslation;
    }

    public  void setMiwokTranslation(String miwokTranslation) {
        MiwokTranslation = miwokTranslation;
    }

    public  String getDefaultTranslation() {
        return DefaultTranslation;
    }

    public  void setDefaultTranslation(String defaultTranslation) {
        DefaultTranslation = defaultTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResource() {
        return mAudioResource;
    }

    public boolean hasImage(){
return mImageResourceId!=NO_IMAGE_PROVIDED;

    }

}
