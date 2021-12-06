package com.wuge.chat.controller;

import com.wuge.chat.Local.LocalUser;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class test {
    @RequestMapping("test")
    @PreAuthorize("hasAuthority('USER')")
    public void ss(HttpServletRequest Request){
        System.out.println(LocalUser.getUser());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(6666);
    }
}
