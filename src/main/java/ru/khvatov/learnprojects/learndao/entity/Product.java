package ru.khvatov.learnprojects.learndao.entity;

import java.util.Objects;

public class Product {
    private Long id;
    private Long accountId;

    private Long tagId;

    private String name;

    private Integer availableCount;
    private Integer comfortCount;

    public Product(Long accountId,
                   Long tagId,
                   String name,
                   Integer availableCount,
                   Integer comfortCount) {
        this(null, accountId, tagId, name, availableCount, comfortCount);
    }

    public Product(Long id,
                   Long accountId,
                   Long tagId,
                   String name,
                   Integer availableCount,
                   Integer comfortCount) {
        this.id = id;
        this.accountId = accountId;
        this.tagId = tagId;
        this.name = name;
        this.availableCount = availableCount;
        this.comfortCount = comfortCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", tagId=" + tagId +
                ", name='" + name + '\'' +
                ", availableCount=" + availableCount +
                ", comfortCount=" + comfortCount +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(id, product.id);
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

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getComfortCount() {
        return comfortCount;
    }

    public void setComfortCount(Integer comfortCount) {
        this.comfortCount = comfortCount;
    }
}
