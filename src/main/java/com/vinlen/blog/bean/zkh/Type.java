package com.vinlen.blog.bean.zkh;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

//小分类
@Table("w_type")
public class Type {

    @Id
    private Long id;

    private Long categoryId;

    private String name;

    private String anotherName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }
}
