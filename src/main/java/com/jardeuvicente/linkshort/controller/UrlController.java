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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody Url bodyUrl) {
        String shortUrl = this.urlService.shortenUrl(bodyUrl.getLongUrl(), bodyUrl.getUser().getId());
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Url>> findAllByUserId(@PathVariable Long userId) throws IOException {
        List<Url> urls = this.urlService.findAllByUserId(userId);

        return ResponseEntity.ok().body(urls);
    }

    @GetMapping("/{code}")
    public void redirect(@PathVariable String code, HttpServletResponse response) throws IOException {
        String url = this.urlService.findLongUrl(code).getLongUrl();
        response.sendRedirect(url);
    }

    @DeleteMapping("/{id}")
    public void deleteUrl(@PathVariable Long id) {
        this.urlService.delete(id);
    }
}
