package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.user.service.api.services.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private UserService userService;
    private DtoBuilder dtoBuilder;

    public UsersController(UserService userService,
                           DtoBuilder dtoBuilder) {
        this.userService = userService;
        this.dtoBuilder = dtoBuilder;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<UserJpaEntity> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/users/all";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String viewUserInfo(@PathVariable Integer userId, Model model) {
        userService.findUserById(userId)
                .ifPresent(
                        userEntity -> model.addAttribute("user", dtoBuilder.getUserDto(userEntity))
                );
        return "/users/view";
    }
}
