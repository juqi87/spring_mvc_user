package com.lw.fsx.util;

public class OpenFolder {
	public static void browsePath(String strPath) {
		String[] execString = new String[2];
		String filePath = null;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) { 
			// Window System;
			execString[0] = "explorer";
			try {
				filePath = strPath.replace("/", "\\");
			} catch (Exception ex) {
				filePath = strPath;
			}
		} else { // Unix or Linux;
			execString[0] = "netscape";
			filePath = strPath;
		}
		execString[1] = filePath;
		try {
			Runtime.getRuntime().exec(execString);
		} catch (Exception ex) {
			System.out.println("异常啦...");
		}
	}

	public static void main(String[] args) {
		OpenFolder.browsePath("E:\\");
	}
}
