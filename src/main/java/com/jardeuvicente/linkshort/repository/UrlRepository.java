package com.jardeuvicente.linkshort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jardeuvicente.linkshort.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByHash(String hash);

    Url findByLongUrl(String longUrl);
}
