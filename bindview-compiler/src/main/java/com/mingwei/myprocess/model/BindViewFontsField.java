package com.mingwei.myprocess.model;

import com.mingwe.myanno.BindView;
import com.mingwe.myanno.BindViewFonts;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by mingwei on 12/10/16.
 * CSDN:    http://blog.csdn.net/u013045971
 * Github:  https://github.com/gumingwei
 * 被BindView注解标记的字段的模型类
 */
public class BindViewFontsField extends BindField{

    private VariableElement mFieldElement;

    private int mResId;
    private String fontsPaths;

    public BindViewFontsField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {
            throw new IllegalArgumentException(String.format("Only field can be annotated with @%s",
                    BindViewFonts.class.getSimpleName()));
        }
        mFieldElement = (VariableElement) element;
        BindViewFonts bindView = mFieldElement.getAnnotation(BindViewFonts.class);
        mResId = bindView.value();
        fontsPaths=bindView.fontsValue();
        if (mResId < 0||fontsPaths==null) {
            throw new IllegalArgumentException(String.format("value() in %s for field % is not valid",
                    BindViewFonts.class.getSimpleName(), mFieldElement.getSimpleName()));
        }
    }

    public Name getFieldName() {
        return mFieldElement.getSimpleName();
    }

    public int getResId() {
        return mResId;
    }

    public String getFontsPaths() {
        return fontsPaths;
    }

    public void setFontsPaths(String fontsPaths) {
        this.fontsPaths = fontsPaths;
    }

    public TypeMirror getFieldType() {
        return mFieldElement.asType();
    }
}