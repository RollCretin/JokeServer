package com.cretin.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cretin.utils.MD5Utils;
import com.cretin.utils.UUIDUtils;

import sun.security.provider.MD5;

@Controller
public class JsonTest {
//	@Autowired
//	private CustomerUserMapper customerUserMapper;
//
//	// //请求json串(商品信息)，输出json(商品信息)
//	// //@RequestBody将请求的商品信息的json串转成itemsCustom对象
//	// //@ResponseBody将itemsCustom转成json输出
//	@RequestMapping("/requestJson")
//	public @ResponseBody CustomerUser requestJson() {
//		CustomerUser itemsCustom = new CustomerUser();
//		itemsCustom.setUserId(UUIDUtils.getUuid());
//		itemsCustom.setUsername("穆仙念");
//		itemsCustom.setNickname("Cretin");
//		try {
//			itemsCustom.setPassword(MD5Utils.EncoderByMd5("123455"));
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		customerUserMapper.insert(itemsCustom);
//		// @ResponseBody将itemsCustom转成json输出
//		return itemsCustom;
//	}
	//
	// //请求key/value，输出json
	// @RequestMapping("/responseJson")
	// public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){
	//
	// //@ResponseBody将itemsCustom转成json输出
	// return itemsCustom;
	// }

}
