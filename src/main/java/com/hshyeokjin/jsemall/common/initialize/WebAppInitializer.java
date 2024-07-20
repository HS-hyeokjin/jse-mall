package com.hshyeokjin.jsemall.common.initialize;

import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.common.contoller.ControllerFactory;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.util.Set;

@HandlesTypes(value = {BaseController.class})
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.initialize(c,ctx);
    }
}