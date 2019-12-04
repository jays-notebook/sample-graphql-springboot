package com.yourbookmark.api.entity;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@ParametersAreNonnullByDefault
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_bookmark",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "bookmark_id"))
    private Set<BookMark> bookMarks;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<BookMark> getBookMarks() {
        return bookMarks;
    }

    public void setBookMarks(Set<BookMark> bookMarks) {
        this.bookMarks = bookMarks;
    }

    public boolean addBookMark(BookMark bookMark) {
        if (this.bookMarks == null) {
            this.bookMarks = new HashSet<>();
        }

        return this.bookMarks.add(bookMark);
    }
}
