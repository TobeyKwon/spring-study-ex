package hello.itemservice.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage() throws Exception {
        String result = ms.getMessage("hello", null, null);
        assertEquals("안녕", result);
    }

    @Test
    public void notFoundMessageCode() throws Exception {
        assertThrows(NoSuchMessageException.class, () -> {
            String no_code = ms.getMessage("no_code", null, null);
        });
    }

    @Test
    public void notFoundMessageCodeDefaultMessage() throws Exception {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertEquals("기본 메시지", result);
    }

    @Test
    public void argumentMessage() throws Exception {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, "기본 메시지", null);
        assertEquals("안녕 Spring", result);
    }

    @Test
    public void defaultLang() throws Exception {
        assertEquals("안녕", ms.getMessage("hello", null, null));
        assertEquals("안녕", ms.getMessage("hello", null, Locale.KOREA));
    }

    @Test
    public void enLang() throws Exception {
        assertEquals("hello", ms.getMessage("hello", null, Locale.ENGLISH));
    }
}
