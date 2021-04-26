package com.vinlen.blog.controller;

import com.vinlen.blog.bean.zkh.Category;
import com.vinlen.blog.bean.zkh.Type;
import com.vinlen.blog.common.ListResult;
import com.vinlen.blog.common.Request;
import com.vinlen.blog.common.Result;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TypeController extends BaseController {
    @RequestMapping("/type/list")
    public Result  list (@RequestBody Request request){
        Pager pager = new Pager(request.getInt("pageNumber"), request.getInt("pageSize"));
        Cnd cnd= request.get("id")!=null && request.getInt("id")!=0?Cnd.where("categoryId","=",request.getLong("id")):null;
        int count = dao.count(Type.class, cnd);
        List<Type> query = dao.query(Type.class, cnd, pager);
        List<Long> ids = query.stream().map(Type::getCategoryId).distinct().collect(Collectors.toList());
        List<Category> categories = dao.query(Category.class, Cnd.where("id", "in", ids));

        List<NutMap> result = query.stream().map(type -> {
            NutMap map = Lang.obj2nutmap(type);
            map.setv("categoryName", categories.stream().filter(category -> category.getId().equals(type.getCategoryId())).collect(Collectors.toList()).get(0).getName());
            return map;
        }).collect(Collectors.toList());
        return Result.ok("success", Json.toJson(new ListResult<NutMap>(count,result)));
    }
}