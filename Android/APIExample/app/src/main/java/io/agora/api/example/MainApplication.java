package io.agora.api.example;

import android.app.Application;
import android.content.Context;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;

import io.agora.api.example.annotation.Example;
import io.agora.api.example.common.model.Examples;
import io.agora.api.example.utils.ClassUtils;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initExamples();
    }

    private void initExamples() {
        try {
            Set<String> packageName = ClassUtils.getFileNameByPackageName(this, "io.agora.api.example.examples");
            for (String name : packageName) {
                Class<?> aClass = Class.forName(name);
                Annotation[] declaredAnnotations = aClass.getAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                    if (annotation instanceof Example) {
                        Example example = (Example) annotation;
                        Examples.addItem(example);
                    }
                }
            }
            Examples.sortItem();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
