package ivana.onlineshop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    @RequestMapping(value = {"/about"})
    public String about(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "about";
    }
}
