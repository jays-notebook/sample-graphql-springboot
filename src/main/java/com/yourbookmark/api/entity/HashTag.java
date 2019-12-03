package com.yourbookmark.api.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class HashTag {

    public HashTag() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hashTagId;

    private String hashTagName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "hashTags")
    private Set<BookMark> bookMarks;

    public HashTag(String hashTagName, Set<BookMark> bookMarks) {
        this.hashTagName = hashTagName;
        this.bookMarks = bookMarks;
    }

    public long getHashTagId() {
        return hashTagId;
    }

    public void setHashTagId(long hashTagId) {
        this.hashTagId = hashTagId;
    }

    public String getHashTagName() {
        return hashTagName;
    }

    public void setHashTagName(String hashTagName) {
        this.hashTagName = hashTagName;
    }

    public Set<BookMark> getBookMarks() {
        return bookMarks;
    }

    public void setBookMarks(Set<BookMark> bookMarks) {
        this.bookMarks = bookMarks;
    }

    public void addBookMark(BookMark bookMark) {
        if (this.bookMarks == null) {
            this.bookMarks = new HashSet<>();
        }
        this.bookMarks.add(bookMark);
    }
}
