package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    public void SessionManagerTest() throws Exception {



        //세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = Member.builder().build();
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //then
        Object result = sessionManager.getSession(request);
        Assertions.assertEquals(result, member);
    }
}
