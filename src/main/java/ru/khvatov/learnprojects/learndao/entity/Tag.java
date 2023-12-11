package ru.khvatov.learnprojects.learndao.entity;

import java.util.Objects;

public class Tag {
    private Long id;
    private Long accountId;

    private String name;

    public Tag(Long accountId, String name) {
        this(null, accountId, name);
    }

    public Tag(Long id, Long accountId, String name) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tag tag = (Tag) object;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
