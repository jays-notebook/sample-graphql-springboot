package com.yourbookmark.api.entity;

import javax.annotation.Nullable;
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
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    private String title;

    private String url;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bookMarks")
    private Set<User> users;

    private BookMarkCategory category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "bookmark_hashtag",
            joinColumns = @JoinColumn(name = "bookmark_id"),
            inverseJoinColumns = @JoinColumn(name = "hash_tag_id"))
    @Nullable
    private Set<HashTag> hashTags;


    public BookMark() {
    }

    public BookMark(String title, String url, BookMarkCategory category) {
        this.title = title;
        this.url = url;
        this.category = category;
    }

    public BookMark(String title, String url, BookMarkCategory category, User user) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.addUser(user);
    }

    public Long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public BookMarkCategory getCategory() {
        return category;
    }

    public void setCategory(BookMarkCategory category) {
        this.category = category;
    }

    @Nullable
    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public boolean addUser(User user) {
        if (this.users == null) {
            this.users = new HashSet<>();
        }
        return this.users.add(user);
    }

    public boolean addHashTag(HashTag hashTag) {
        if (this.hashTags == null) {
            this.hashTags = new HashSet<>();
        }
        return this.hashTags.add(hashTag);
    }
}
