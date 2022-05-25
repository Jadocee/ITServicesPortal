package com.spacejaam.itservicesportal.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getLoginView() {
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
        return "login";
    }

    @GetMapping(value = "/login?error")
    public ModelAndView getLoginViewWithError() {
        final ModelAndView modelAndView = new ModelAndView("login");
        // TODO handle login error
        return modelAndView;
    }

    /*@PostMapping(value = "/register/submit")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerAccount(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                                  @RequestParam("contactNum") String contactNum) {

    }*/


}
