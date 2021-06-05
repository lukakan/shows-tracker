package pl.lukakan.showstracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String accessDenied(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Brak dostępu dla ciebie. 403 na Twarz");
        return "redirect:/error";
    }

    @GetMapping("error")
    public String errorPage(Model model, @ModelAttribute(name = "msg") String msg) {
        model.addAttribute("msg", msg);
        return "error";
    }
}
