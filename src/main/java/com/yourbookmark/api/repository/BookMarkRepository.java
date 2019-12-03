package com.yourbookmark.api.repository;

import com.yourbookmark.api.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
    BookMark findByBookmarkId(long id);
}
