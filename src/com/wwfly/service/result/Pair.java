package com.wwfly.service.result;

import java.io.Serializable;

/**
 * 值对
 * 方法需要返回两个值时，可使用Pair减少代码量
 * @author zhangli
 *
 * @param <F>
 * @param <S>
 */
public final class Pair<F, S> implements Serializable
{
	private static final long serialVersionUID = 1L;
	public F first;
	public S second;
	
	public Pair(F f, S s)
	{
		this.first = f;
		this.second = s;
	}
	
	/**
	 * 通过值创建值对
	 * @param <FT>
	 * @param <ST>
	 * @param f
	 * @param s
	 * @return
	 */
	public static <FT, ST> Pair<FT, ST> makePair(FT f, ST s)
	{
		return new Pair<FT, ST>(f, s);
	}
	
	private static <T> boolean eq(T o1, T o2)
	{
		return o1 == null ? o2 == null : o1.equals(o2);
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object o)
	{
		Pair<F, S> pr = (Pair<F, S>) o;
		if(pr == null)
			return false;
		return eq(first, pr.first)
				&& eq(second, pr.second);
	}
	
	private int h(Object o)
	{
		return o == null ? 0 : o.hashCode();
	}
	
	public int hashCode()
	{
		int seed = h(first);
		seed ^= h(second) + 0x9e3779b9 + (seed << 6) + (seed >> 2);
		return seed;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{")
			.append(first)
			.append(", ")
			.append(second)
			.append("}");
		return sb.toString();
	}
}
