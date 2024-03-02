package com.jardeuvicente.linkshort.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.jardeuvicente.linkshort.model.Url;
import com.jardeuvicente.linkshort.model.Users;
import com.jardeuvicente.linkshort.repository.UrlRepository;
import com.jardeuvicente.linkshort.repository.UsersRepository;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UsersRepository usersRepository;

    public UrlService(UrlRepository urlRepository, UsersRepository usersRepository) {
        this.urlRepository = urlRepository;
        this.usersRepository = usersRepository;
    }

    public String shortenUrl(String longUrl, Long userId) {
        Url url = urlRepository.findByLongUrl(longUrl);
        Optional<Users> user = usersRepository.findById(userId);
        Date createdDate = new Date();

        if (url != null) {
            return url.getHash();
        }

        url = new Url();
        url.setLongUrl(longUrl);

        String hash = generateHash();
        while (urlRepository.findByHash(hash) != null) {
            hash = generateHash();
        }
        url.setHash(hash);
        url.setUser(user.get());
        url.setCreatedDate(createdDate);

        urlRepository.save(url);

        return hash;
    }

    public Url getLongUrl(String hash) {
        Url url = urlRepository.findByHash(hash);

        return url;
    }

    public List<Url> findAllUrl() {
        List<Url> url = urlRepository.findAll();

        return url;
    }

    public String deleteUrl(Long id) {
        urlRepository.deleteById(id);

        return "Url deletada com sucesso";
    }

    private String generateHash() {
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
