package com.be24.api.orders;

import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.utils.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class OrdersController implements Controller {
    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        // paymentId를 전달 받는다.
        Map<String, String> reqDto = JsonParser.from(req, Map.class);
        String paymentId = reqDto.get("paymentId");

        System.out.println(paymentId);

        // 포트원에 paymentId로 결제한 정보를 조회
        //     https://api.portone.io/payments/{paymentId}에 GET 방식으로 요청
        //      인증 관련 API를 제외한 모든 API는 HTTP Authorization 헤더로 아래 형식의 인증 정보를 전달해주셔야 합니다.
        //        Authorization: PortOne MY_API_SECRET
        //      결제 정보 조회


        try {
            HttpClient client;
            client = HttpClient.newHttpClient();

            String secretKey = System.getenv("PORTONE_SECRET_KEY");
            HttpRequest httpReq = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.portone.io/payments/"+paymentId))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "PortOne "+secretKey)
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(httpReq, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.body());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 결제한 정보랑 실제 상품 가격이랑 비교 후
        //      일치하면 주문 생성
        //      일치하지 않으면 환불



        return null;
    }
}
