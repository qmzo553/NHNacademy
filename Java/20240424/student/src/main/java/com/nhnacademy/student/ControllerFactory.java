package com.nhnacademy.student;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class ControllerFactory {

    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) {
        for(Class<?> clazz : c) {
            if (hasRequestMapping(clazz)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                String method = requestMapping.method().name();
                String path = requestMapping.value();
                log.info("Initialzer : method: {}, path: {}", method, path);
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    beanMap.put(method + path, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean hasRequestMapping(Class<?> clazz) {
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);

        if(Objects.nonNull(requestMapping)) {
            return true;
        }

        return false;
    }

    public Object getBean(String method, String path) {
        return beanMap.get(method + path);
    }
}
