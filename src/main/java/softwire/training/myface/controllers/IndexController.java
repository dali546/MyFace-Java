package softwire.training.myface.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/")
    ModelAndView index() {
        return new ModelAndView("index");
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public RedirectView logout() {
        return new RedirectView("/");
    }

    @RequestMapping("/home")
    ModelAndView home() {
        return new ModelAndView("home");
    }
}
