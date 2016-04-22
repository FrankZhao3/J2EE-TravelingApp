/**
 * 创建时间：2013-6-28
 */
package com.wwfly.cache;

import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @auth:zhangli
 * 
 * @desc:json对象转换
 */

public class JsonUtil {
	/**
	 * 将对象转成 json字串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Gson gson = creatGson();
		return gson.toJson(obj);
	}

	/**
	 * 将json转换成对象
	 * 
	 * @param str
	 * @param classes
	 * @return
	 */
	public static <F> F toObj(String str, Class<F> classes) {
		Gson gson = creatGson();
		try {
			F obj = gson.fromJson(str, classes);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反回List泛型集合
	 * 
	 * @param str
	 * @param f
	 * @return
	 */
	public static <V> List<V> toList(String str, List<V> f) {
		Gson gson = creatGson();
		try {
			return gson.fromJson(str, new TypeToken<List<V>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换为Map泛型集合
	 * 
	 * @param str
	 * @param map
	 * @return
	 */
	public static <K, V> Map<K, V> toMap(String str, Map<K, V> map) {
		Gson gson = creatGson();
		try {
			return gson.fromJson(str, new TypeToken<Map<K, V>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Gson creatGson() {
		return new GsonBuilder().enableComplexMapKeySerialization()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}
}
