package com.mingwei.aptdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class Utls {



     public static void setFonts(Activity mcontext, TextView textView, String path){

         textView.setTypeface(Typeface.createFromAsset(mcontext.getAssets(), path));

     }
}
