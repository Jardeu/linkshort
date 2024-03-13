package com.jardeuvicente.linkshort.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.model.User;
import com.jardeuvicente.linkshort.repository.UrlRepository;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private UserService userService;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public String shortenUrl(String longUrl, Long userId) {
        Url url = urlRepository.findByLongUrl(longUrl);
        User user = userService.findById(userId);
        Date createdDate = new Date();

        if (url != null) {
            return url.getCode();
        }

        url = new Url();
        url.setLongUrl(longUrl);

        String code = generateCode();
        while (urlRepository.findByCode(code) != null) {
            code = generateCode();
        }
        url.setCode(code);
        url.setUser(user);
        url.setCreatedDate(createdDate);

        urlRepository.save(url);

        return code;
    }

    public Url findById(Long id) {
        Optional<Url> url = this.urlRepository.findById(id);
        return url.orElseThrow(() -> new RuntimeException(
                "Url não encontrada!"));
    }

    public Url findLongUrl(String code) {
        Url url = urlRepository.findByCode(code);

        return url;
    }

    public List<Url> findAll() {
        List<Url> url = urlRepository.findAll();

        return url;
    }

    @Transactional
    public void delete(Long id) {
        findById(id);

        try {
            this.urlRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");
        }
    }

    private String generateCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());

            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }
}
