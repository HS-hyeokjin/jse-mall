package com.hshyeokjin.jsemall.common.contoller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BaseController {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
