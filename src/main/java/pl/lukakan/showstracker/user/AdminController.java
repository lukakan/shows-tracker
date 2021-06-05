package pl.lukakan.showstracker.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lukakan.showstracker.user.model.Role;
import pl.lukakan.showstracker.user.model.UserDto;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String adminPanel(Model model, Authentication authentication) {
        String userName = authentication.getName();
        Set<UserDto> users = userService.findAllExceptCurrent(userName);
        model.addAttribute("users", users);
        model.addAttribute("admin", Role.ROLE_ADMIN);
        return "/admin/panel";
    }

    @GetMapping("/users/{id}/promote")
    public String promoteToAdmin(@PathVariable(name = "id") Long id) {
        userService.promoteUserToAdmin(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/degrade")
    public String removeAdmin(@PathVariable(name = "id") Long id) {
        userService.degradeAdmin(id);
        return "redirect:/admin/users";
    }
}
