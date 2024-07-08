package com.hshyeokjin.jsemall.common.contoller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ControllerFactory {
    public static final String CONTEXT_NAME = "CONTROLLER_FACTORY";
    private final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void initialize(Set<Class<?>> c, ServletContext ctx) {
        if (Objects.isNull(c)){
            return;
        }
        for (Class<?> cls : c) {
            try{
                BaseController controller = (BaseController) cls.getDeclaredConstructor().newInstance();

                if(cls.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping = cls.getAnnotation(RequestMapping.class);
                    String[] paths = requestMapping.value();
                    for (String path : paths) {
                        String key = requestMapping.method().name() + "-" + path;
                        beanMap.put(key, controller);
                    }
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        ctx.setAttribute(CONTEXT_NAME,this);
    }

    public Object getController(HttpServletRequest request) {
        String method = request.getMethod();
        String path = request.getServletPath();
        String key = method.toUpperCase() + "-" + path;
        Object controller = beanMap.get(key);
        if (Objects.isNull(controller)){
            System.out.println("controller 못찾음");
        }
        return controller;
    }

    public Object getController(String method, String path) {
        String key = method.toUpperCase() + "-" + path;
        Object controller = beanMap.get(key);
        if (Objects.isNull(controller)){
            System.out.println("controller 못찾음");
        }
        return controller;
    }

}
