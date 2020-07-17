package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.puebla.monitoralertas.entity.AlarmaEntity;
import com.puebla.monitoralertas.entity.User;
import com.puebla.monitoralertas.repository.AlarmaRepository;
import com.puebla.monitoralertas.service.SecurityService;
import com.puebla.monitoralertas.service.UserService;
import com.puebla.monitoralertas.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
	@Autowired
	private AlarmaRepository alarmaRepository;

	@GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
	 
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping({"/","/welcome"})
    public String welcome(Model model) {
		List<AlarmaEntity> alarmas = alarmaRepository.findTop15ByOrderByAlarmaidDesc();
		model.addAttribute("alarmas", alarmas);

        return "monitorAlarmas";
    }
}