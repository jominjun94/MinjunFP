package com.cos.Mproject.Script;

public class Script2 {
	
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("</script>");
		return sb.toString();
	}
}
