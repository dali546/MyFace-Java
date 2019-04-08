package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import softwire.training.myface.services.AuthService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam("username") String username, @RequestParam("password") String password) {
        return authService.login(username, password);
    }

    @RequestMapping("/2Z5SIQQUHCWO2NJU1KX5")
    @ResponseBody
    public boolean loginTest(HttpServletRequest request) {
        return authService.refresh(request.getRemoteUser()) != null;
    }
}
