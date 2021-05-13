package pl.lukakan.showstracker.cast.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lukakan.showstracker.cast.role.model.Role;
import pl.lukakan.showstracker.show.MovieService;

@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/movie/{id}/role/add")
    public String addRoleToMovieForm(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("persons", roleService.getPersons());
        model.addAttribute("functions", roleService.getFunctions());
        model.addAttribute("movieId", id);
        return "role/add";
    }

    @PostMapping("/movie/{id}/role/add")
    public String addRoleToMovie(@PathVariable(name = "id") Long movieId, Role role) {
        roleService.save(role, movieId);
        return "redirect:/movies/" + movieId + "/edit";
    }
}
