package com.spacejaam.itservicesportal.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class LoginController {

    /*private final ClientDAO clientDAO;
    private final PasswordEncoder encoder;
    @Autowired
    LoginController(ClientDAO clientDAO, PasswordEncoder encoder) {
        this.clientDAO = clientDAO;
        this.encoder = encoder;
    }*/

    @GetMapping(value = "/login")
    public ModelAndView getLoginView() {
        return new ModelAndView("login");
       /* clientDAO.insertClient(new Client(
                "Adolf",
                "Budden",
                "itstaff@test.com",
                encoder.encode("itstaff"),
                "0332265421",
                Role.ITSTAFF,
                true,
                true,
                true,
                true
        ));

        clientDAO.insertClient(new Client(
                "Jerry Seinfeld",
                "from The Bee Movie",
                "user@test.com",
                encoder.encode("user"),
                "4935584930",
                Role.USER,
                true,
                true,
                true,
                true
        ));*/
    }

    @GetMapping(value = "/login?error")
    public ModelAndView getLoginViewWithError(
            @RequestParam(value = "error", required = false) String error,
            ModelAndView modelAndView
    ) {
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    /*@PostMapping(value = "/register/submit")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerAccount(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                                  @RequestParam("contactNum") String contactNum) {

    }*/


}
