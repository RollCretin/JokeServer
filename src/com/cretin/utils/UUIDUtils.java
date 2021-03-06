package com.cretin.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 生成一个32位的不带-的不唯一的uuid
	 * @return
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getUuid());
	}
}
