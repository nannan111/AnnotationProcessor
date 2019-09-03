package com.mingwei.myapi.finder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mingwei on 12/16/16.
 * CSDN:    http://blog.csdn.net/u013045971
 * Github:  https://github.com/gumingwei
 */
public class ActivityFInder implements Finder {
    @Override
    public Context getContext(Object source) {
        return (Activity) source;
    }

    @Override
    public View findView(Object source, int id) {
        return ((Activity) source).findViewById(id);
    }

    @Override
    public TextView findTextView(Object source, int id) {
        return (TextView) ((Activity) source).findViewById(id);
    }

    @Override
    public Typeface getTypeface(Object source, String path) {
        if(source instanceof Activity){
            return Typeface.createFromAsset(((Activity) source).getAssets(), path);
        }else{
            return Typeface.createFromAsset((getContext(source)).getAssets(), path);
        }

    }
    @Override
    public boolean isTextView(Object source, View view) {
        return view instanceof TextView;
    }
}
