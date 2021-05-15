package pl.lukakan.showstracker.cast.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lukakan.showstracker.cast.person.model.Gender;
import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.cast.role.model.Function;

@Controller
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public String getPersonsPage(Model model) {
        model.addAttribute("persons", personService.findAllPersons());
        model.addAttribute("functions", personService.getFunctions());
        return "/person/list";
    }


    @GetMapping("/person/add")
    public String addPersonForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("editmode", false);
        model.addAttribute("action", "add");
        return "/person/add";
    }

    @PostMapping("/person/add")
    public String addPerson(Person person) {
        personService.save(person);
        return "redirect:/movies";
    }

    @GetMapping("persons/function")
    public String getPersonsInFunctionPage(@RequestParam(name = "functionType") String functionType, Model model) {
        model.addAttribute("persons", personService.findAllPersonInFunction(functionType));
        model.addAttribute("functions", personService.getFunctions());
        return "person/list";
    }
}
