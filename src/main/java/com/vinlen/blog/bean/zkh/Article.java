package com.vinlen.blog.bean.zkh;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("w_article")
public class Article {

    @Id
    private Long id;

    private Long typeId;

    private String title;

    private String content;

    private String url;

    private String reader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }
}
