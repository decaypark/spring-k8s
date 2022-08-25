package msa.client.test1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import msa.client.test1.vo.Member;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FooClientController {

    // http://localhost:8081/sso-resource-server/api/foos/
    @Value("${resourceserver.api.url}")
    private String fooApiUrl;

    @Autowired
    private WebClient webClient;

    @ResponseBody
    @GetMapping("/api/members")
    public List<Member> getApiMembers(HttpServletRequest request) {

        WebClient webClient = WebClient
                .builder()
                .baseUrl(fooApiUrl)
//                .defaultCookie("쿠키","쿠키값")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Flux<Member> response  = webClient.get()
                .uri("/users")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjEyNDY0ODksInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiVVNFUiIsIkFETUlOIl0sImp0aSI6ImNiYWVlZmQwLThkYzctNDIwYi1hOGFkLWUyZDk3NDkyNTVlYSIsImNsaWVudF9pZCI6ImN1c3RvbTEiLCJzY29wZSI6WyJyZWFkIl19.VOZh8HrNTBq1XRcvcjAL9hoKO87d6vgvnUY9oIuiK6c")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Member.class)
                ;

        List<Member> members = response.collect(Collectors.toList()).block();

        return members;
    }

    @RequestMapping("/members")
    public String getMembers(Model model) {

        WebClient webClient = WebClient
                .builder()
                .baseUrl(fooApiUrl)
//                .defaultCookie("쿠키","쿠키값")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Flux<Member> response  = webClient.get()
                .uri("/users")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjEyNDY0ODksInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiVVNFUiIsIkFETUlOIl0sImp0aSI6ImNiYWVlZmQwLThkYzctNDIwYi1hOGFkLWUyZDk3NDkyNTVlYSIsImNsaWVudF9pZCI6ImN1c3RvbTEiLCJzY29wZSI6WyJyZWFkIl19.VOZh8HrNTBq1XRcvcjAL9hoKO87d6vgvnUY9oIuiK6c")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Member.class)
                ;

        List<Member> members = response.collect(Collectors.toList()).block();

        model.addAttribute("members", members);
        return "members";
    }

}
