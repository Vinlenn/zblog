package com.vinlen.blog.controller;

import com.vinlen.blog.bean.User;
import com.vinlen.blog.common.Request;
import com.vinlen.blog.common.Util;
import org.nutz.dao.Cnd;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
	@RequestMapping("/user/login")
	public String login(@RequestBody Request request){
		User fetch = dao.fetch(User.class, Cnd.where("name", "=", request.getString("name")).and("password", "=", request.getString("password")));
		return Util.createToken(fetch.getId());
	}

}
