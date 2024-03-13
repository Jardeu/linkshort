package com.jardeuvicente.linkshort.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.model.User;
import com.jardeuvicente.linkshort.repository.UrlRepository;
import com.jardeuvicente.linkshort.repository.UserRepository;

@ContextConfiguration
@SpringBootTest
public class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UrlService urlService;

    @InjectMocks
    private UserService userService;

    @Test
    public void testShortenUrl() {
        String longUrl = "https://www.example.com";

        String code = "abc123";

        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setCode(code);

        User user = new User();

        when(urlRepository.findByLongUrl(longUrl)).thenReturn(url);

        String result = urlService.shortenUrl(longUrl, user.getId());

        assertEquals(code, result);
    }

}
