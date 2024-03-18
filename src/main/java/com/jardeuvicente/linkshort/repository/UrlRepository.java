package com.jardeuvicente.linkshort.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jardeuvicente.linkshort.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByCode(String code);

    Url findByLongUrl(String longUrl);

    List<Url> findByUser_Id(Long id);
}
