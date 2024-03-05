package com.jardeuvicente.linkshort.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody Url bodyUrl) {
        String shortUrl = urlService.shortenUrl(bodyUrl.getLongUrl(), bodyUrl.getUser().getId());
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }

    @GetMapping("/urls")
    public List<Url> listUrls() throws IOException {
        List<Url> listUrls = urlService.findAllUrl();

        return listUrls;
    }

    @GetMapping("/{hash}")
    public void redirect(@PathVariable String hash, HttpServletResponse response) throws IOException {
        String url = urlService.getLongUrl(hash).getLongUrl();
        response.sendRedirect(url);
    }

    @DeleteMapping("/urls/delete/{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable Long id) {
        String deleteMessage = urlService.deleteUrl(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }
}
