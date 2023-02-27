package com.group7.controller;

import com.group7.utils.common.R;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangYuyang
 * @Date: 2023/2/26-22:29
 * @Project: COMP3032J_FYP_Thesis_Group_7
 * @Package: com.group7.controller.auth
 * @Description:
 **/
@RestController
@RequestMapping(value = "/secure")
public class SecureController {
    private final Logger logger = LoggerFactory.getLogger(SecureController.class);

    @RequestMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        String id = request.getAttribute("id").toString();
        String username = request.getAttribute("username").toString();
        String openid = request.getAttribute("openid").toString();
        String email = request.getAttribute("email").toString();
        String role = request.getAttribute("role").toString();
        return R.ok()
                .data("id", id)
                .data("username", username)
                .data("openid", openid)
                .data("email", email)
                .data("role", role);
    }
}
