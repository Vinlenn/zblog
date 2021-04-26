package com.vinlen.blog.bean.zkh;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

//大分类
@Table("w_category")
public class Category {

    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
