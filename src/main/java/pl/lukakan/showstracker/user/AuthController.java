package pl.lukakan.showstracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukakan.showstracker.user.model.UserDto;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(name = "error", required = false) String error, Model model, @ModelAttribute(name = "feedback") String feedback) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "user/login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(UserDto userDto, RedirectAttributes redirectAttributes) {
        if (userService.isLoginUnique(userDto.getUserName())) {
            userService.registerUser(userDto);
            redirectAttributes.addFlashAttribute("feedback", "success");
        } else {
            redirectAttributes.addFlashAttribute("feedback", "fail");
        }
        return "redirect:/login";
    }


}
