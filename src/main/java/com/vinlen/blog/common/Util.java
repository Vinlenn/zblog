package com.vinlen.blog.common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.nutz.lang.util.NutMap;

import java.util.Date;
import java.util.HashMap;

public class Util {

	//对于servlet来说，每一个请求都是一个线程，所以使用ThreadLocal 线程存储类来存储用户关键信息
	static final ThreadLocal<HashMap<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

	//set expire-date
	private static final long EXPIRE_DATE = 30 * 60 * 1000;

	private static final String TOKEN_SECRET = "EWQB0202HJFBJSKFBUIQECZ";

	public static String createToken(Long userId) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis + EXPIRE_DATE);
		System.out.println(millis);
		System.out.println(date.getTime());
		HashMap<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		return JWT.create().withHeader(map).withClaim("userId", userId).withExpiresAt(date).sign(Algorithm.HMAC256(TOKEN_SECRET));
	}

	public static boolean verifyToken(String token) {
		JWTVerifier build = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
		try {
			DecodedJWT verify = build.verify(token);
			Integer userId = verify.getClaim("userId").asInt();
			HashMap<String, Object> map = new HashMap<>();
			map.put("userId", userId);
			THREAD_LOCAL.remove();
			THREAD_LOCAL.set(map);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("token失效，请重新登录");
		}
	}

	public static long getUserId() {
		return Long.parseLong(String.valueOf(THREAD_LOCAL.get().get("userId")));
	}

	public static void isTrue(boolean expression, String msg) {
		if (!expression)
			throw new RuntimeException(msg);
	}

	public static void notNull(Object object, String msg) {
		isTrue(object != null, msg);
	}




}
