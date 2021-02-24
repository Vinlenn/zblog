package com.vinlen.blog.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("zb_article")
public class Article {

    @Id
	private Long id;

    @Column
	private String title;

	@Column
	private String content;

	@Column
	private Long author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public Article(String title, String content, Long author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}


	public Article() {
	}
}
