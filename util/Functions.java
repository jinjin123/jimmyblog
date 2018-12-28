package com.jimmyblog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public class Functions {
	//get ip address
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forward-for");
		if(ipAddress == null || ipAddress.length() ==0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() ==0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() ==0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		if(ipAddress == null || ipAddress.length() >15 ) {
			if(ipAddress.indexOf(",") >0) {
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	//convert string  to md5
	public static String strToMd5(String str) {
		String md5Str = null;
		if(str != null && str.length() !=0) {
			try {
				MessageDigest md =  MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte b[] = md.digest();
				
				int i;
				StringBuffer  buf = new StringBuffer("");
				for(int offset = 0;offset < b.length;offset++) {
					i = b[offset];
					if(i<0) 
						i += 256;
					if(i<16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				
				//32
				md5Str =  buf.toString();
			}catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return md5Str;
	}
	
	//get head image from useraccount
	public static String getGravatar(String email) {
		String emailMd5 = strToMd5(email);
		String avatar = "http://cn.gravatar.com/avatar/"+emailMd5+"?s=128&d=identicon&r=PG";
		return avatar;
	}
}
