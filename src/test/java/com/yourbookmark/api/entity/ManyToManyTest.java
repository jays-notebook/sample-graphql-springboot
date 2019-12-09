package com.yourbookmark.api.entity;

import com.yourbookmark.api.repository.BookMarkRepository;
import com.yourbookmark.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class ManyToManyTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookMarkRepository bookMarkRepository;

    @Test
    void manyToManyTest() {
        String testUrl = "http://testdomain.com";

        BookMark bookMark1 = new BookMark("test #1 BookMark!!", testUrl, BookMarkCategory.BOOK);
        BookMark bookMark2 = new BookMark("test #2 BookMark!!", testUrl, BookMarkCategory.DEVELOP);
        BookMark bookMark3 = new BookMark("test #3 BookMark!!", testUrl, BookMarkCategory.ECONOMY);

        User user = new User("JY", 1000);

        bookMark1.addUser(user);
        bookMark2.addUser(user);
        bookMark3.addUser(user);
        user.addBookMark(bookMark1);
        user.addBookMark(bookMark2);
        user.addBookMark(bookMark3);

        userRepository.save(user);
        bookMarkRepository.save(bookMark1);
        bookMarkRepository.save(bookMark2);
        bookMarkRepository.save(bookMark3);

        Assertions.assertTrue(bookMark1.getUsers().contains(user));
    }

}
