package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softwire.training.myface.models.viewmodels.DashboardViewModel;
import softwire.training.myface.security.UserPrincipal;
import softwire.training.myface.services.UsersService;

@Controller
@RequestMapping(value = "/users")
public class UserListController {

    private final UsersService usersService;

    @Autowired
    public UserListController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "")
    public ModelAndView getAllWalls(UsernamePasswordAuthenticationToken token) {
        DashboardViewModel dashBoardView = new DashboardViewModel();
        dashBoardView.setUsers(usersService.getAllUsers());
        dashBoardView.setLoggedInUser(((UserPrincipal) token.getPrincipal()).getUser());

        return new ModelAndView("users", "model", dashBoardView);
    }
}
