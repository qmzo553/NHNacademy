package com.nhnacademy.student;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory {

    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) {
        for(Class<?> clazz : c) {
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                String method = requestMapping.method().name();
                String path = requestMapping.value();
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    beanMap.put(String.join("", method, path), instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getBean(String method, String path) {
        return beanMap.get(String.join("", method, path));
    }
}
