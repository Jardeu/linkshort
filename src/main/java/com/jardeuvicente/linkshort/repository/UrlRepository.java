package com.jardeuvicente.linkshort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jardeuvicente.linkshort.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByCode(String code);

    Url findByLongUrl(String longUrl);
}
