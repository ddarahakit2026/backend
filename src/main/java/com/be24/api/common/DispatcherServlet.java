package com.be24.api.common;

import com.be24.api.utils.JsonParser;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/")
// 파일 업로드 처릴 위해서 추가
@MultipartConfig
public class DispatcherServlet extends HttpServlet {

    private AppConfig appConfig;

    // 서블릿이 처음 만들어질 때 AppConfig 객체를 생성
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.appConfig = new AppConfig();
    }

    // get, post든 어떤 요청 메소드든 service() 메소드 실행
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트가 요청한 주소를 가져옴
        String uri = req.getRequestURI();

        // appConfig 통해서 해당 주소에 등록되어 있는 컨트롤러를 반환
        Controller controller = appConfig.getController(uri);

        // 컨트롤러가 없으면 404로 에러 처리
        if(controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 토큰을 확인하는 코드


        // 컨트롤러가 있으면 컨트롤러의 작업을 실행
        BaseResponse res = controller.process(req, resp);



        resp.getWriter().write(JsonParser.from(res));
    }
}
