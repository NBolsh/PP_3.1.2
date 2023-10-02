package kata.pp.PP_312.controllers;


import kata.pp.PP_312.model.User;
import kata.pp.PP_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private UserService userService;



    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model){

        model.addAttribute("allUsers", userService.getAllUsers());

        return "/users/list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user",new User());
        return "/users/userInfo";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult bindingResult){

        if (bindingResult.hasErrors()) { return "/users/userInfo"; }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String findUser(@RequestParam("userId") Long id,
                           Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "/users/userInfo";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
