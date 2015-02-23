package com.github.zs.rikit;

import java.util.Arrays;


public class Identifier<T extends Identifiable> implements Comparable<Identifier<T>> {

	private T theObject;
	private byte[] id;
	
	public Identifier(T object)
	{
		this.theObject = object;
		id = object.generateId();
	}
	
	public byte[] getId()
	{
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identifier other = (Identifier) obj;
		if (!Arrays.equals(id, other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (byte by:id)
		{
			String hex = String.format("%02X", by);
			sb.append(hex);
		}
		return sb.toString();
	}

	@Override
	public int compareTo(Identifier<T> other) {
		if (other == null)
			throw new IllegalArgumentException("parameter cannot be null");
		if (other.getClass() != getClass())
		{
			throw new IllegalArgumentException("parameter class is " + other.getClass() + ", expected class is " + getClass());
		}
		if (this == other)
		{
			return 0;
		}
		byte[] otherId = other.getId();
		if (id.length != otherId.length)
		{
			throw new IllegalArgumentException("length of parameter id is " + otherId.length + ". expected length is " + id.length);
		}
		for (int i=0;i<otherId.length;i++)
		{
			if (id[i] > otherId[i])
			{
				return 1;
			} 
			if (id[i] < otherId[i])
			{
				return -1;
			}
		}
		return 0;
	}

	public T getTheObject() {
		return theObject;
	}
}
