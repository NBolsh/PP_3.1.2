package kata.pp.PP_312.controller;


import kata.pp.PP_312.model.User;
import kata.pp.PP_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    private UserService userService;



    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping( "/users")
    public String showAllUsers(Model model){

        model.addAttribute("allUsers", userService.getAllUsers());

        return "/users/list";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user",new User());
        return "/users/userInfo";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult bindingResult){

        if (bindingResult.hasErrors()) { return "/users/userInfo"; }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String findUser(@RequestParam("userId") Long id,
                           Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "/users/userInfo";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
