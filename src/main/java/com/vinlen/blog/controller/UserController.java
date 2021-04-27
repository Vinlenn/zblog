package com.vinlen.blog.controller;

import com.vinlen.blog.bean.User;
import com.vinlen.blog.bean.zkh.Category;
import com.vinlen.blog.bean.zkh.Type;
import com.vinlen.blog.common.Request;
import com.vinlen.blog.common.Result;
import com.vinlen.blog.common.Util;
import org.nutz.dao.Cnd;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
    @RequestMapping("/user/login")
    public Result login(@RequestBody Request request) {
        User fetch = dao.fetch(User.class, Cnd.where("name", "=", request.getString("name")).and("password", "=", request.getString("password")));
        return fetch == null ? Result.error("用户名或密码错误") : Result.ok("success", Util.createToken(fetch.getId()));
    }

    @RequestMapping("/user/edit")
    public Result edit() {
        long userId = Util.getUserId();
        return userId==0?Result.error("token失效，请重新登录"):Result.ok("success",dao.fetch(User.class, userId));
    }
}
