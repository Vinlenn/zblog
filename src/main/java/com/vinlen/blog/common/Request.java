package com.vinlen.blog.common;

import java.util.LinkedHashMap;

public class Request extends LinkedHashMap<String, Object> {

	public int getInt(String key) {
		Object obj = this.get(key);
		return Integer.parseInt(String.valueOf(obj));
	}

	public Long getLong(String key) {
		Object obj = this.get(key);
		return Long.parseLong(String.valueOf(obj));
	}

	public String getString(String key) {
		Object obj = this.get(key);
		return String.valueOf(obj);
	}


}