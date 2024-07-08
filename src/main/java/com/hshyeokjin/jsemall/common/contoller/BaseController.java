package com.hshyeokjin.jsemall.common.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BaseController {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
