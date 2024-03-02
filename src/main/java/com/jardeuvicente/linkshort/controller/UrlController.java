package com.jardeuvicente.linkshort.controller;

import java.io.IOException;
import java.util.List;

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
    public String shortenUrl(@RequestBody Url bodyUrl) {
        return urlService.shortenUrl(bodyUrl.getLongUrl(), bodyUrl.getUser().getId());
    }

    @GetMapping("/urls")
    public List<Url> listUrls() throws IOException {
        List<Url> url = urlService.findAllUrl();

        return url;
    }

    @GetMapping("/{hash}")
    public Url redirect(@PathVariable String hash, HttpServletResponse response) throws IOException {
        Url url = urlService.getLongUrl(hash);

        return url;
    }

    @DeleteMapping("/urls/delete/{id}")
    public String deleteUrl(@PathVariable Long id) throws IOException {
        return urlService.deleteUrl(id);
    }
}
