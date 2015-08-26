package fury.templatewise.login.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by asura on 2015-08-21.
 */
@Controller
public class FacebookLoginController {
    @RequestMapping(value = "/login/facebook")
    public String loginFacebook(ModelMap model) {

        return "social/loginfacebook";
    }
}
