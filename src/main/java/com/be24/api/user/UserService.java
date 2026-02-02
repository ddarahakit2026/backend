package com.be24.api.user;

import com.be24.api.user.model.*;
import com.be24.api.utils.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSignupDtoRes signup(UserSignupDto dto) {
        UserSignupDtoRes returnDto = userRepository.save(dto);
        return returnDto;
    }

    public UserLoginDtoRes login(UserLoginDto dto) {
        UserLoginDtoRes returnDto = userRepository.findByEmailAndPasssword(dto);

        return returnDto;
    }

    public UserLoginDtoRes kakaoLogin(String code) throws IOException, InterruptedException {
        String accessToken = getKakaoToken(code);
        UserKakaoLoginDtoRes kakaoUser =  getKakaoUser(accessToken);

        if(kakaoUser.getNickname() != null) {
            UserLoginDto loginDto = new UserLoginDto(""+kakaoUser.getId(), "kakao");
            UserLoginDtoRes loginDtoRes = userRepository.findByEmailAndPasssword(loginDto);
            if(loginDtoRes == null) {
                UserSignupDto dto = new UserSignupDto(""+kakaoUser.getId(), kakaoUser.getNickname(), "kakao");
                UserSignupDtoRes signupDtoRes = userRepository.save(dto);

                return new UserLoginDtoRes(signupDtoRes.getIdx(), signupDtoRes.getEmail(), signupDtoRes.getName());
            }

            return loginDtoRes;
        }

        return null;
    }


    private String getKakaoToken(String code) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String body = "grant_type=authorization_code" +
                "&client_id=" + System.getenv("KAKAO_CLIENT_ID") +
                "&redirect_uri=" + System.getenv("KAKAO_REDIRECT_URI") +
                "&code=" + code;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kauth.kakao.com/oauth/token"))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, String> map = JsonParser.from(response.body(), Map.class);
        String accessToken = map.get("access_token");
        return accessToken;
    }

    private UserKakaoLoginDtoRes getKakaoUser(String accessToken) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        Map<String, Object> map = JsonParser.from(response.body(), Map.class);

        Long id = (Long) map.get("id");
        Map<String, String> properties = (Map) map.get("properties");
        String nickname = properties.get("nickname");

        UserKakaoLoginDtoRes returnDto = new UserKakaoLoginDtoRes(id, nickname);


        return returnDto;
    }
}
