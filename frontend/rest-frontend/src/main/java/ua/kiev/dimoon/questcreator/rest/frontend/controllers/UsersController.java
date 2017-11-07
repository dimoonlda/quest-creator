package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.rest.frontend.service.NotificationService;
import ua.kiev.dimoon.questcreator.user.service.api.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private UserService userService;
    private DtoBuilder dtoBuilder;
    private NotificationService notificationService;

    public UsersController(UserService userService,
                           DtoBuilder dtoBuilder,
                           NotificationService notificationService) {
        this.userService = userService;
        this.dtoBuilder = dtoBuilder;
        this.notificationService = notificationService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<UserJpaEntity> users = userService.findAll();
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
        userService.findOne(userId)
                .ifPresent(
                        userEntity -> model.addAttribute("user", dtoBuilder.getUserDto(userEntity))
                );
        return "/users/view";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable Integer userId) {
        userService.findOne(userId)
                .ifPresent(
                        userEntity -> model.addAttribute("user", userEntity)
                );
        return "/users/form";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(Model model,
                           @ModelAttribute(value = "user") @Valid UserJpaEntity userEntity,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notificationService.addErrorMessageFromCode("users.form.error.wrongField");
            return "/users/form";
        }
        userService.save(userEntity);
        if (null != userEntity.getId()) {
            return "redirect:/users/view/" + userEntity.getId();
        } else {
            return "redirect:/users";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newUser(Model model, @ModelAttribute(value = "user") UserJpaEntity user) {
        return "/users/form";
    }
}
