package com.sist.util;

public class SistUtil {
	public static boolean isImg(String fname) {
		boolean re=false;
		if(fname.endsWith(".jpg")||fname.endsWith(".gif")||fname.endsWith(".png")){
			re=true;
		}
		return re;
	}
}
