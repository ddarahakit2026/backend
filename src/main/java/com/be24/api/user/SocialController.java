package com.be24.api.user;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.utils.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class SocialController implements Controller {
    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        // RedirectURI로 전달되는 code를 받는다.
        String code = req.getParameter("code");

        // 토큰 요청 API 호출
        //      HttpClient 이용해서 POST 방식으로 https://kauth.kakao.com/oauth/token에 요청 전송 (헤더, 바디는 문서 참고)
        //      access_token을 변수에 저장
        String accessToken = null;

        try {
            HttpClient client;
            client = HttpClient.newHttpClient();

            String data = "grant_type=authorization_code&client_id=바꿔주세요&redirect_uri=바꿔주세요"+code;
            HttpRequest httpReq = HttpRequest.newBuilder()
                    .uri(URI.create("https://kauth.kakao.com/oauth/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            HttpResponse<String> res = client.send(httpReq, HttpResponse.BodyHandlers.ofString());
            Map<String, String> tokenRes = JsonParser.from(res.body(), Map.class);

            accessToken = tokenRes.get("access_token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



        // 사용자 정보 조회 API 호출
        //      HttpClient 이용해서 GET 방식으로 https://kapi.kakao.com/v2/user/me에 요청 전송(헤더는 문서 참고)

        try {
            HttpClient client;
            client = HttpClient.newHttpClient();

            HttpRequest httpReq = HttpRequest.newBuilder()
                    .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .header("Authorization", "Bearer "+accessToken)
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(httpReq, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> userInfoRes = JsonParser.from(res.body(), Map.class);
            System.out.println(res.body());
            Long uesrNumber = (Long) userInfoRes.get("id");
            System.out.println(uesrNumber.toString());
            Map<String, String > properties = (Map) userInfoRes.get("properties");
            System.out.println(properties.get("nickname"));
            System.out.println(properties.get("profile_image"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // 회원가입 한 적 있나 없나 확인
        //  회원 가입을 했으면
        //      토큰 발급
        //  안했으면
        //      회원 가입 시키고 토큰 발급


        return null;
    }




    private String getKakaoToken(String code) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String body = "grant_type=authorization_code" +
                "&client_id=바꾸세요바꾸세요바꾸세요바꾸세요" +
                "&redirect_uri=http://localhost:8080/web02/user/kakao" +
                "&code=" + code;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kauth.kakao.com/oauth/token"))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(response.body(), Map.class);
        String accessToken = map.get("access_token");
        return accessToken;
    }

    private void getKakaoUser(String accessToken) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());

    }
}
