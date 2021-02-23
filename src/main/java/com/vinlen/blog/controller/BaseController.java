package com.vinlen.blog.controller;

import org.nutz.dao.Dao;

import javax.annotation.Resource;


public class BaseController {
	@Resource
	protected Dao dao;
}
