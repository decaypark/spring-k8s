package server.auth.msa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import server.auth.msa.security.User;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class UserinfoController {

    @Autowired
    private server.auth.msa.security.UserDao UserDao;

    @ResponseBody
    @RequestMapping("/oauth/userinfo")
    public User userinfo(HttpServletRequest request){
        User user = UserDao.findByUsername("admin");
        return user;
    }

//    @ResponseBody
//    @RequestMapping("/oauth/userinfo")
//    public OAuthToken.response userinfo(HttpServletRequest request){
//
//        String cridentials = "custom1:auth_secret";
//        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
//        String encodingCredentials = new String(
//                Base64.encodeBase64(cridentials.getBytes())
//        );
//
//        User user = UserDao.findByUsername("admin");
////        return user;
//
////        Map<String, User> usermap = new Map<String, User>();
//
//        Map<String, Object> usermap = new HashMap<>();
//
//        usermap.put("username", user.getUsername());
//
//        OAuthToken.response oauthToken = Unirest.post("http://localhost:8095/oauth/userinfo")
//                .header("Authorization","Basic "+encodingCredentials)
//                .fields(usermap)
//                .asObject(OAuthToken.response.class).getBody();
//        return oauthToken;
//    }
}
