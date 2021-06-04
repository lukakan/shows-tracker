package pl.lukakan.showstracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukakan.showstracker.user.model.UserDto;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profilePage(Authentication authentication, Model model) {
        String userName = authentication.getName();
        UserDto userDto = userService.findUserByName(userName);
        model.addAttribute("user", userDto);
        return "user/profile";
    }

    @GetMapping("/editdata")
    public String editUserDataForm(Authentication authentication, Model model) {
        String userName = authentication.getName();
        UserDto userDto = userService.findUserByName(userName);
        model.addAttribute("user", userDto);
        return "user/editdata";
    }

    @PostMapping("/editdata")
    public String editUserDataForm(Authentication authentication, UserDto editedUser, RedirectAttributes redirectAttributes) {
        String userName = authentication.getName();
        if (!(userName.equals(editedUser.getUserName()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Możesz edytować tylko konto: " + userName);
        } else {
            userService.updateUserDetails(userName, editedUser);
            redirectAttributes.addFlashAttribute("feedback", "success");
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/changePassword")
    public String changePasswordForm(Model model) {
        model.addAttribute("oldPassword", "");
        model.addAttribute("newPassword", "");
        return "user/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "newPassword") String newPassword,
                                 RedirectAttributes redirectAttributes,
                                 Authentication authentication) {
        String userName = authentication.getName();
        if (userService.isOldPasswordValid(userName, oldPassword)) {
            userService.updateUserPassword(userName, newPassword);
            redirectAttributes.addFlashAttribute("feedback", "success");
        } else {
            redirectAttributes.addFlashAttribute("feedback", "fail");
        }
        return "redirect:/user/profile";
    }

}
