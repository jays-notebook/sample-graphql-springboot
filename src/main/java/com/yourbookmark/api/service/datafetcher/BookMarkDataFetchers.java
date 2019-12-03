package com.yourbookmark.api.service.datafetcher;

import com.yourbookmark.api.entity.BookMark;
import com.yourbookmark.api.repository.BookMarkRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMarkDataFetchers {
    private final BookMarkRepository bookMarkRepository;

    public BookMarkDataFetchers(BookMarkRepository bookMarkRepository) {
        this.bookMarkRepository = bookMarkRepository;
    }

    public DataFetcher<BookMark> getBookMark() {
        return environment -> {
            int id = Integer.parseInt(environment.getArgument("id"));
            return bookMarkRepository.findByBookmarkId(id);
        };
    }

    public DataFetcher<List<BookMark>> getAllBookMarks() {
        return environment -> bookMarkRepository.findAll();
    }
}
