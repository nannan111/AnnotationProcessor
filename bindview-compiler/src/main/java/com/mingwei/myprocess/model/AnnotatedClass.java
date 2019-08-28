package com.mingwei.myprocess.model;

import com.mingwei.myprocess.TypeUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * Created by mingwei on 12/10/16.
 * CSDN:    http://blog.csdn.net/u013045971
 * Github:  https://github.com/gumingwei
 */
public class AnnotatedClass {

    /**
     * 类名
     */
    public TypeElement mClassElement;
    /**
     * 成员变量
     */
    public List<BindField> mFiled;
    /**
     * 方法
     */
    public List<OnClickMethod> mMethod;
    /**
     * 元素帮助类
     */
    public Elements mElementUtils;

    public AnnotatedClass(TypeElement classElement, Elements elementUtils) {
        this.mClassElement = classElement;
        this.mElementUtils = elementUtils;
        this.mFiled = new ArrayList<>();
        this.mMethod = new ArrayList<>();
    }

    public String getFullClassName() {
        return mClassElement.getQualifiedName().toString();
    }

    public void addField(BindField field) {
        mFiled.add(field);
    }

    public void addMethod(OnClickMethod method) {
        mMethod.add(method);
    }

    public JavaFile generateFinder() {

        /**
         * 构建方法
         */
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(mClassElement.asType()), "host", FINAL)
                .addParameter(TypeName.OBJECT, "source")
                .addParameter(TypeUtil.FINDER, "finder");
        /**
         * 遍历添加类成员
         */
        methodBuilder.addStatement("$T typeface", TypeUtil.ANDROID_TYPEFACE);
//        methodBuilder.addStatement("$T isTextView", boolean.class);
        methodBuilder.addStatement("$T view", TypeUtil.ANDROID_VIEW);
//        methodBuilder.addStatement("$T listener1", String.class);
        for (BindField field1 : mFiled) {
//            methodBuilder.addStatement("listener1 = $L ", "\"aaacf\"");
//            methodBuilder.addStatement("finder.findTextView(source,$L).setText(listener1)",field.getResId());
            if (field1 instanceof BindViewFontsField) {
                BindViewFontsField field = (BindViewFontsField) field1;
                methodBuilder.addStatement("view=host.$N=($T)finder.findView(source,$L)", field.getFieldName(), ClassName.get(field.getFieldType()), field.getResId());
//            methodBuilder.addStatement("isTextView = finder.isTextView(source,view)");
                /*
                 * 添加判断方法
                 * 如果是TextView才去设置字体
                 */
//                methodBuilder.addCode("if (finder.isTextView(source,view)) {\n");
                methodBuilder.addStatement("typeface = finder.getTypeface(source,$L)", "\"" + field.getFontsPaths() + "\"");
                methodBuilder.addStatement("finder.findTextView(source,$L).setTypeface(typeface)", field.getResId());
//                methodBuilder.addCode("}\n");
            } else if (field1 instanceof BindViewField) {
                BindViewField field = (BindViewField) field1;
                methodBuilder.addStatement("view=host.$N=($T)finder.findView(source,$L)", field.getFieldName(), ClassName.get(field.getFieldType()), field.getResId());
            }
        }
        /**
         * 声明Listener
         */
        if (mMethod.size() > 0) {
            methodBuilder.addStatement("$T listener", TypeUtil.ONCLICK_LISTENER);
        }
        for (OnClickMethod method : mMethod) {

            if(method.getParam()!=null&&method.getParam().size()==1) {
                TypeSpec listener = TypeSpec.anonymousClassBuilder("")
                        .addSuperinterface(TypeUtil.ONCLICK_LISTENER)
                        .addMethod(MethodSpec.methodBuilder("onClick")
                                .addAnnotation(Override.class)
                                .addModifiers(PUBLIC)
                                .returns(TypeName.VOID)
                                .addParameter(TypeUtil.ANDROID_VIEW, "view")
                                .addStatement("host.$N(view)", method.getMethodName())
                                .build())
                        .build();
                methodBuilder.addStatement("listener = $L ", listener);
            }else{
                TypeSpec listener = TypeSpec.anonymousClassBuilder("")
                        .addSuperinterface(TypeUtil.ONCLICK_LISTENER)
                        .addMethod(MethodSpec.methodBuilder("onClick")
                                .addAnnotation(Override.class)
                                .addModifiers(PUBLIC)
                                .returns(TypeName.VOID)
                                .addParameter(TypeUtil.ANDROID_VIEW, "view")
                                .addStatement("host.$N()", method.getMethodName())
                                .build())
                        .build();
                methodBuilder.addStatement("listener = $L ", listener);
            }
            for (int id : method.ids) {
                methodBuilder.addStatement("finder.findView(source,$L).setOnClickListener(listener)", id);
            }
        }

        String packageName = getPackageName(mClassElement);
        String className = getClassName(mClassElement, packageName);
        ClassName bindClassName = ClassName.get(packageName, className);
        /**
         * 构建类
         */
        TypeSpec finderClass = TypeSpec.classBuilder(bindClassName.simpleName() + "$$Injector")
                .addModifiers(PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.INJECTOR, TypeName.get(mClassElement.asType())))
                .addMethod(methodBuilder.build())
                .build();

        return JavaFile.builder(packageName, finderClass).build();
    }

    public String getPackageName(TypeElement type) {
        return mElementUtils.getPackageOf(type).getQualifiedName().toString();
    }

    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }
}
