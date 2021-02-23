package com.vinlen.blog.controller;

import com.vinlen.blog.bean.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController extends BaseController{
	@RequestMapping("/article/search")
	public List login(@RequestParam("name") String name){
		//假装返回数据
		ArrayList<Article> articles = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			articles.add(new Article((long)i,String.valueOf(i),"这是第"+i+"篇"));
		}
		return articles;
	}

	@RequestMapping("/article/edit")
	public Article edit(@RequestParam("id") Long id){
		//return dao.fetch(Article.class, id);
		return new Article("1","1");
	}

}
