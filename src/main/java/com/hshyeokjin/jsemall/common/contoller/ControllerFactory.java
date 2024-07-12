package com.hshyeokjin.jsemall.common.contoller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ControllerFactory {
    public static final String CONTEXT_NAME = "CONTROLLER_FACTORY";
    private final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void initialize(Set<Class<?>> c, ServletContext ctx) {
        log.debug("ControllerFactory initialize 메서드 실행");
        log.debug("ctx = {}", ctx.getContextPath());
        if (Objects.isNull(c)){
            log.debug("Ojects.isNull(c) : c = {}", c);
            return;
        }
        for (Class<?> cls : c) {
            log.debug("cls = {}", cls);
            try{
                BaseController controller = (BaseController) cls.getDeclaredConstructor().newInstance();
                log.debug("conteroller = {}", controller);

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
