package fury.templatewise.login.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by asura on 2015-08-20.
 */
@Controller
public class GoogleLoginController {

    @RequestMapping(value = "/login/google")
    public String loginGoogle(ModelMap model) {


        return "social/logingoogle";
    }

}
