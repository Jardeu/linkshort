package com.jardeuvicente.linkshort.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.model.User;
import com.jardeuvicente.linkshort.repository.UrlRepository;
import com.jardeuvicente.linkshort.service.exception.DataBindingViolationException;
import com.jardeuvicente.linkshort.service.exception.ObjectNotFoundException;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private UserService userService;

    public UrlService(UrlRepository urlRepository, UserService userService) {
        this.urlRepository = urlRepository;
        this.userService = userService;
    }

    @Transactional
    public String shortenUrl(String longUrl, Long userId) {
        Url url = this.urlRepository.findByLongUrl(longUrl);
        User user = this.userService.findById(userId);
        Date createdDate = new Date();

        if (url != null) {
            return url.getCode();
        }

        url = new Url();
        url.setLongUrl(longUrl);

        String code = generateCode();
        while (this.urlRepository.findByCode(code) != null) {
            code = generateCode();
        }
        url.setCode(code);
        url.setUser(user);
        url.setCreatedDate(createdDate);

        this.urlRepository.save(url);

        return code;
    }

    public Url findById(Long id) {
        Objects.requireNonNull(id, "Id can't be null!");

        Optional<Url> url = this.urlRepository.findById(id);
        return url.orElseThrow(() -> new ObjectNotFoundException(
                "Url not found!"));
    }

    public Url findLongUrl(String code) {
        Url url = this.urlRepository.findByCode(code);

        return url;
    }

    public List<Url> findAllByUserId(Long userId) {
        List<Url> urls = this.urlRepository.findByUser_Id(userId);

        return urls;
    }

    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Id can't be null!");

        try {
            this.urlRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Unable to delete as there are related entities!");
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
