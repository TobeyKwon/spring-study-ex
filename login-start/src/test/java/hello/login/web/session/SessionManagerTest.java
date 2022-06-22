package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    @Autowired SessionManager sessionManager;

    @Test
    public void sessionTest() throws Exception {

        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        sessionManager.createSession(member, response);

        // 요청에 응답쿠키 생성
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object result = sessionManager.getSession(request);
        Assertions.assertEquals(member, result);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        Assertions.assertEquals(null, expired);
    }
}