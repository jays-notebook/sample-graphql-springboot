package com.yourbookmark.api;

import com.yourbookmark.api.entity.BookMark;
import com.yourbookmark.api.entity.BookMarkCategory;
import com.yourbookmark.api.entity.User;
import com.yourbookmark.api.repository.BookMarkRepository;
import com.yourbookmark.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.stream.Stream;

@EnableAspectJAutoProxy
@SpringBootApplication
public class YourBookMarkApiApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookMarkRepository bookMarkRepository;

    public YourBookMarkApiApplication(UserRepository userRepository, BookMarkRepository bookMarkRepository) {
        this.userRepository = userRepository;
        this.bookMarkRepository = bookMarkRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(YourBookMarkApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String sampleUrl = "http://testdomain.com";
        User user = new User("Jay", 100);

        BookMark bookMark1 = new BookMark("test #1 BookMark!!", sampleUrl, BookMarkCategory.BOOK, user);
        BookMark bookMark2 = new BookMark("test #2 BookMark!!", sampleUrl, BookMarkCategory.DEVELOP, user);
        BookMark bookMark3 = new BookMark("test #3 BookMark!!", sampleUrl, BookMarkCategory.ECONOMY, user);

        user.addBookMark(bookMark1);
        user.addBookMark(bookMark2);
        user.addBookMark(bookMark3);
        userRepository.save(user);

        Stream.of(bookMark1, bookMark2, bookMark3).forEach(bookMarkRepository::save);
    }
}
