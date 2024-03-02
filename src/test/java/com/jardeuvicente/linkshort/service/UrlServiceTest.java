package com.jardeuvicente.linkshort.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.model.Users;
import com.jardeuvicente.linkshort.repository.UrlRepository;
import com.jardeuvicente.linkshort.repository.UsersRepository;

@ContextConfiguration
@SpringBootTest
public class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UrlService urlService;

    @InjectMocks
    private UsersService userService;

    @Test
    public void testShortenUrl() {
        String longUrl = "https://www.example.com";

        String hash = "abc123";

        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setHash(hash);

        Users user = new Users();

        when(urlRepository.findByLongUrl(longUrl)).thenReturn(url);

        String result = urlService.shortenUrl(longUrl, user.getId());

        assertEquals(hash, result);
    }

}
