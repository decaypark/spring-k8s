package msa.client.test1.controller;

import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import msa.client.test1.vo.Member;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import msa.client.test1.security.OAuthToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private WebClient webClient;

    @RequestMapping("/login/oauth2/code/custom")
    public OAuthToken.response code(@RequestParam String code){

        String cridentials = "custom1:auth_secret";
        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
        String encodingCredentials = new String(
                Base64.encodeBase64(cridentials.getBytes())
        );

        String requestCode = code;
        OAuthToken.request.accessToken request = new OAuthToken.request.accessToken(){{
            setCode(requestCode);
            setGrant_type("authorization_code");
//            setRedirect_uri("http://localhost:8095/callback");
            setRedirect_uri("http://172.20.63.120:9102/login/oauth2/code/custom");
        }};

        // oauth 서버에 http 통신으로 토큰 발행
        OAuthToken.response oauthToken = Unirest.post("http://localhost:8095/oauth/token")
                .header("Authorization","Basic "+encodingCredentials)
                .fields(request.getMapData())
                .asObject(OAuthToken.response.class).getBody();


        return oauthToken;
    }

//    @RequestMapping("/callback")
//    public OAuthToken.response code(@RequestParam String code){
//
//        String cridentials = "auth_id:auth_secret";
//        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
//        String encodingCredentials = new String(
//                Base64.encodeBase64(cridentials.getBytes())
//        );
//        String requestCode = code;
//        OAuthToken.request.accessToken request = new OAuthToken.request.accessToken(){{
//            setCode(requestCode);
//            setGrant_type("authorization_code");
//            setRedirect_uri("http://localhost:8095/callback");
////            setRedirect_uri("http://172.20.63.120:8095/callback");
//        }};
//        // oauth 서버에 http 통신으로 토큰 발행
//        OAuthToken.response oauthToken = Unirest.post("http://localhost:8095/oauth/token")
//                .header("Authorization","Basic "+encodingCredentials)
//                .fields(request.getMapData())
//                .asObject(OAuthToken.response.class).getBody();
//        return oauthToken;
//    }
}
