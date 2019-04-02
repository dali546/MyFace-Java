package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softwire.training.myface.database.DatabaseService;
import softwire.training.myface.models.viewmodels.AllUsersViewModel;

import java.security.Principal;

@Controller
@RequestMapping(value = "/users")
public class UserListController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "")
    public ModelAndView getAllWalls(Principal principal) {

        AllUsersViewModel allUsersViewModel = new AllUsersViewModel();
        allUsersViewModel.loggedInUsername = principal.getName();
        allUsersViewModel.allUsernames = databaseService.guessAllUsernames();

        return new ModelAndView("users", "model", allUsersViewModel);
    }

}
