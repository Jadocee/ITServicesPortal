package com.spacejaam.itservicesportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class LoginController {

//  private final ClientRepository clientRepository;
//  private final PasswordEncoder passwordEncoder;
//
//  @Autowired
//  public LoginController(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
//    this.clientRepository = clientRepository;
//    this.passwordEncoder = passwordEncoder;
//  }

  @GetMapping(value = "/login")
  public ModelAndView getLoginView() {
    final ModelAndView modelAndView = new ModelAndView("login");
//    clientRepository.save(new Client("dev", "dev", "dev@spacejaam.com", passwordEncoder.encode("password"), "000000", Role.USER.toString()));
//    System.out.println(clientRepository.getClientByEmail("dev@spacejaam.com").getFirstName());
    return modelAndView;
  }

  @GetMapping(value = "/login?error")
  public ModelAndView getLoginViewWithError() {
    final ModelAndView modelAndView = new ModelAndView("login");


//    modelAndView.addObject("pageTitle", "Login");
//    modelAndView.addObject("pageName", "login");
    return modelAndView;
  }

  @PostMapping(value = "/register/submit")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String registerAccount(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName, @RequestParam("email") String email,
      @RequestParam("contactNum") String contactNum) {
//    final Client newClient = new Client(firstName, lastName, email, password, contactNum);
    return "/login";
  }


}
