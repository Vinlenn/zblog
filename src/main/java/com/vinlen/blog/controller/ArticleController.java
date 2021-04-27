package com.vinlen.blog.controller;

import com.vinlen.blog.bean.zkh.Article;
import com.vinlen.blog.bean.zkh.Category;
import com.vinlen.blog.bean.zkh.Type;
import com.vinlen.blog.common.ListResult;
import com.vinlen.blog.common.Request;
import com.vinlen.blog.common.Result;
import com.vinlen.blog.common.Util;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleController extends BaseController {
    @RequestMapping("/article/list")
    public Result list(@RequestBody Request request) {
        Cnd cnd = request.get("id")==null?Cnd.NEW():Cnd.where("typeId", "=", request.getLong("id"));
        Pager pager = new Pager(request.getInt("pageNumber"), request.getInt("pageSize"));
        int count = dao.count(Article.class, cnd);
        List<Article> query = dao.query(Article.class, cnd.orderBy("reader", "asc"), pager);
        List<Long> ids = query.stream().map(Article::getTypeId).distinct().collect(Collectors.toList());
        List<Type> types = dao.query(Type.class, Cnd.where("id", "in", ids));
        List<NutMap> result = query.stream().map(article -> {
            NutMap map = Lang.obj2nutmap(article);
            map.setv("typeName", types.stream().filter(type -> type.getId().equals(article.getTypeId())).collect(Collectors.toList()).get(0).getName());
            return map;
        }).collect(Collectors.toList());
        return Result.ok("success", Json.toJson(new ListResult<NutMap>(count, result)));
    }

    @RequestMapping("/article/edit")
    public Result edit(@RequestBody Request request) {
        Article article = dao.fetch(Article.class, request.getInt("id"));
        NutMap map = Lang.obj2nutmap(article);
        Type type = dao.fetch(Type.class, article.getTypeId());
        Category category = dao.fetch(Category.class, type.getCategoryId());
        map.setv("typeName", type.getName());
        map.setv("anotherName", type.getAnotherName());
        map.setv("categoryName", category.getName());
        return Result.ok("success", map);
    }

    @RequestMapping("/article/reader")
    public Result reader(@RequestBody Request request) {
        Article article = dao.fetch(Article.class, request.getInt("id"));
        article.setReader("是");
        dao.update(article);
        return Result.ok("success");
    }

    @RequestMapping("/article/add")
    public Result add(@RequestBody Request request){
        Article article = new Article();
        article.setReader("否");
        article.setContent(request.getString("content"));
        article.setTitle(request.getString("title"));
        article.setUrl(request.getString("url"));
        article.setTypeId(request.getLong("typeId"));
        dao.insert(article);
        return Result.ok("success");
    }


}
