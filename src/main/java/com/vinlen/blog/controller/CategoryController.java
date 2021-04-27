package com.vinlen.blog.controller;

import com.vinlen.blog.bean.zkh.Category;
import com.vinlen.blog.common.ListResult;
import com.vinlen.blog.common.Request;
import com.vinlen.blog.common.Result;
import org.nutz.dao.pager.Pager;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.Filters;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController extends BaseController {

    @RequestMapping("/category/list")
    public Result list(@RequestBody Request request){
        List<Category> query = dao.query(Category.class, null,new Pager(request.getInt("pageNumber"),request.getInt("pageSize")));
        int count = dao.count(Category.class, null);
        return Result.ok("success", Json.toJson(new ListResult<Category>(count,query)));
    }

    @RequestMapping("/category/add")
    public Result add(@RequestBody Request request){
        Category category = new Category(request.getString("name"));
        dao.insert(category);
        return Result.ok("success");
    }

}
