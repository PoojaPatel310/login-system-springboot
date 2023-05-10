package com.aurosoft.loginsystem;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Helper {

    public static boolean checkUserRole(){
        String role="";
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        if (session.getAttribute("urole") != null){
            role = session.getAttribute("urole").toString();
        }
        System.out.println(role.equalsIgnoreCase("USER"));
        return role.equalsIgnoreCase("USER");
    }



    public static boolean checkAdminRole(){
        String role="";
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        if (session.getAttribute("urole") != null){
            role = session.getAttribute("urole").toString();
        }
        return role.equalsIgnoreCase("ADMIN");
    }

}
