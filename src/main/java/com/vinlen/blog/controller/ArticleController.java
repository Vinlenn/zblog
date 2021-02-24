package com.vinlen.blog.controller;

import com.vinlen.blog.bean.Article;
import com.vinlen.blog.common.Request;
import com.vinlen.blog.common.Util;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.util.NutMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArticleController extends BaseController {
	@RequestMapping("/article/search")
	public NutMap search(@RequestParam("name") String name, @RequestParam("page") int page) {
		Cnd cnd = "".equals(name) ? Cnd.NEW() : Cnd.where("title", "like", "%" + name + "%");
		return NutMap.NEW().setv("list", dao.query(Article.class, cnd.and("author","=",Util.getUserId()), new Pager(page, 10))).setv("count", dao.count(Article.class, cnd));
	}

	@RequestMapping("/article/edit")
	public Article edit(@RequestParam("id") Long id) {
		return dao.fetch(Article.class, id);
	}

	@RequestMapping("/article/save")
	public boolean save(@RequestBody Request request) {
		return dao.insert(new Article(request.getString("title"), request.getString("content"), Util.getUserId())) != null;
	}

	@RequestMapping("/article/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return dao.delete(Article.class, id) > 0;
	}

}
