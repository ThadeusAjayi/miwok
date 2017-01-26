package com.example.android.miwok;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import static android.R.attr.visibility;

/**
 * Created by jayson surface on 21/01/2017.
 */

public class Word {

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mResource = N0_IMAGE_PROVIDED;

    private static final int N0_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String miwokTranslation, int Resource){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mResource = Resource;
    }

    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

//Default translation
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
//Miwok translation
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    //Image resource
    public int getmResource(){
        return mResource;
    }

    boolean hasImage(){
        return mResource != N0_IMAGE_PROVIDED;
    }
}
