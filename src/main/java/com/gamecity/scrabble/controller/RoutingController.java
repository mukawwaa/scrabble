package com.gamecity.scrabble.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoutingController
{
    private enum Pages
    {
        login, /* */
        content
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model)
    {
        if (error != null)
        {
            model.addAttribute("error", "Kullanıcı adı veya şifre hatalı!");
        }

        if (logout != null)
        {
            model.addAttribute("message", "Başarıyla çıkış yaptınız.");
        }
        return Pages.login.name();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String forbidden(Model model)
    {
        return "403";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(Model model)
    {
        return Pages.content.name();
    }

    @RequestMapping(value = "/v/**", method = RequestMethod.GET)
    public String views(Model model)
    {
        return Pages.content.name();
    }
}
