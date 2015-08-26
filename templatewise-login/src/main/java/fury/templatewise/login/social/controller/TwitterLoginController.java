package fury.templatewise.login.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by asura on 2015-08-21.
 */
@Controller
public class TwitterLoginController {

    @RequestMapping(value = "/login/twitterCheck")
    public String loginTwitterCheck(HttpServletRequest request) {

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("ztvVN4IJ5cgJLMACtkW9rZppm", "pFo7JJE4fL8taSg5o0MSmR49I5EN3a8c7CX2nFRbtoTS2gCz5l");

        RequestToken requestToken = null;
        try {
            requestToken = twitter.getOAuthRequestToken();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("ReqToken", requestToken); //요청 토큰 정보를 세션에 구움

        System. out.print("requestToken.getAuthenticationURL() :" + requestToken.getAuthenticationURL());

        return "redirect:"+requestToken.getAuthenticationURL();
    }

    @RequestMapping(value = "/login/twitter")
    public String loginTwitter(HttpServletRequest request) {

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("ztvVN4IJ5cgJLMACtkW9rZppm", "pFo7JJE4fL8taSg5o0MSmR49I5EN3a8c7CX2nFRbtoTS2gCz5l");
        String oauthToken = request.getParameter("oauth_token"); //요청토큰 일치여부 확인을 위한 token key
        String oauthVerifier = request.getParameter("oauth_verifier"); //인증 검증키
        RequestToken reqToken = (RequestToken)request.getSession().getAttribute("ReqToken"); //위에서 세션 저장한 요청 토큰 정보
        //트위터 인증 시도
        if (reqToken.getToken().equals(oauthToken)) { //요청 일치여부 확인
            try {
                AccessToken accessToken = twitter.getOAuthAccessToken(reqToken, oauthVerifier); //검증
                twitter.setOAuthAccessToken(accessToken); //인증정보 저장
            } catch (TwitterException e) {
                e.printStackTrace();
                //트위터인증실패
            }
        }

        return "social/logintwitter";
    }
}

