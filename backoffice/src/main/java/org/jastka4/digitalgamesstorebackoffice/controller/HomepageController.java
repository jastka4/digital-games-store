package org.jastka4.digitalgamesstorebackoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomepageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Simple Session controller which will return session ID backed by Spring Session API
     *
     * @param session
     * @return session ID
     */
    @GetMapping("/session-id")
    public String uid(HttpSession session) {
        return session.getId();
    }
}
