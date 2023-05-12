package com.example.shorturlv3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortURL,Long> {
    ShortURL findByUser(String user);
    ShortURL findByShortUrlId(String id);

}
