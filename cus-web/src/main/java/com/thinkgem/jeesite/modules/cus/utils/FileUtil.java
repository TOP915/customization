package com.thinkgem.jeesite.modules.cus.utils;

import cus.utils.JsonUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class);

	public static void checkLocalFilePath(String localFilePath) {
		logger.info("checkLocalFilePath start...path=" + localFilePath);
		File file = new File(localFilePath);
		if (file.exists()) {
			if (file.isDirectory()) {
				logger.info("dir exists:" + localFilePath);
			} else {
				logger.info("the same name file exists, can not create dir:" + localFilePath);
			}
		} else {
			logger.info("dir not exists, create it:" + localFilePath);
			file.mkdirs();
		}
	}

	public static void clearDir(String localFilePath) {
		logger.info("clearDir start...path=" + localFilePath);
		File file = new File(localFilePath);
		if (file.exists()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				String subFileName = null;
				File temp = null;
				for (String fileName : files) {
					subFileName = localFilePath + "/" + fileName;
					temp = new File(subFileName);
					if (temp.isDirectory()) {
						clearDir(subFileName);
						temp.delete();
					} else {
						temp.delete();
					}
				}
			} else {
				logger.info("it is not a dir");
			}
		} else {
			logger.info("dir not exists");
		}
		logger.info("clearDir success...path=" + localFilePath);
	}

	public static void deleteFile(String fileName) {
		logger.info("removeFile start...fileName=" + fileName);
		File file = new File(fileName);
		if (file != null && file.exists()) {
			file.delete();
		} else {
			logger.info("dir not exists");
		}
	}

	public static boolean copyFile(String fromFilePath, String toFilePath) {
		boolean result = false;
		logger.info("copyFile start...fromFilePath=" + fromFilePath + "&toFilePath=" + toFilePath);
		if (fromFilePath != null && !"".equals(fromFilePath) && toFilePath != null && !"".equals(toFilePath)) {
			String toFileName = getFileName(toFilePath);
			String toFileDir = toFilePath.replace(toFileName, "");
			checkLocalFilePath(toFileDir);
			File s = new File(fromFilePath);
			File t = new File(toFilePath);
			FileInputStream fi = null;
			FileOutputStream fo = null;
			FileChannel in = null;
			FileChannel out = null;
			try {
				fi = new FileInputStream(s);
				fo = new FileOutputStream(t);
				in = fi.getChannel();// 得到对应的文件通道
				out = fo.getChannel();// 得到对应的文件通道
				in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
				result = true;
			} catch (IOException e) {
				logger.error("copyFile exception:e=" + e.getMessage());
			} finally {
				try {
					fi.close();
					in.close();
					fo.close();
					out.close();
				} catch (IOException e) {
					logger.error("copyFile exception:e=" + e.getMessage());
				}
			}
		} else {
			logger.error("params error!");
		}
		return result;
	}

	public static String getFileName(String path) {
		return (path == null || "".equals(path)) ? "" : path.substring(path.lastIndexOf("/") + 1);
	}

	public static String[] listFileName(String path) {
		logger.info("listFileName start:path=" + path);
		String[] result = null;
		if (path != null) {
			try {
				File pathFile = new File(path);
				if (pathFile != null && pathFile.exists()) {
					result = pathFile.list();
				}
			} catch (Exception e) {
				logger.error("exception=" + e.getMessage());
			}
		}
		return result;
	}

	public static List<Map<String, Object>> listFile(String path) {
		logger.info("listFileName start:path=" + path);
		List<Map<String, Object>> result = null;
		if (path != null && !"".equals(path)) {
			try {
				File pathFile = new File(path);
				if (pathFile != null && pathFile.exists()) {
					File[] files = pathFile.listFiles();
					if (files != null && files.length > 0) {
						result = new ArrayList<Map<String, Object>>();
						Map<String, Object> fileMap = null;
						for (File file : files) {
							fileMap = new HashMap<String, Object>();
							fileMap.put("name", file.getName());
							fileMap.put("size", file.length());
							fileMap.put("type", file.isDirectory() ? "1" : "0");
							result.add(fileMap);
						}
					}
				}
			} catch (Exception e) {
				logger.error("exception=" + e.getMessage());
			}
		}
		return result;
	}

	public static long getFileSize(String path) {
		logger.info("getFileSize start:path=" + path);
		long result = 0;
		if (path != null) {
			try {
				File pathFile = new File(path);
				if (pathFile != null && pathFile.exists()) {
					result = pathFile.isDirectory() ? 0 : pathFile.length();
				}
			} catch (Exception e) {
				logger.error("exception=" + e.getMessage());
			}
		}
		return result;
	}

	public static String getFileContent(String filePath, String encoded) {
		logger.info("getFileContent start:path=" + filePath + "&encoded=" + encoded);
		String result = null;
		if (filePath != null && !"".equals(filePath)) {
			result = getFileContent(new File(filePath), encoded);
		}
		return result;
	}

	public static String getFileContent(File file, String encoded) {
		logger.info("getFileContent start:file=" + file.getPath() + "&encoded=" + encoded);
		String result = null;
		try {
			if (file.exists()) {
				StringBuffer sb = new StringBuffer();
				BufferedReader r = new BufferedReader(
						(encoded == null || "".equals(encoded)) ? new InputStreamReader(new FileInputStream(file))
								: new InputStreamReader(new FileInputStream(file), encoded));
				String s = new String();
				while ((s = r.readLine()) != null) {
					sb.append(s);
				}
				result = sb.toString();
			}
		} catch (Exception ex) {
			logger.error("read file content error", ex);
		}
		return result;
	}

	public static String getPrettyPath(String path) {
		if (path != null && !"".equals(path)) {
			if (path.indexOf("//") >= 0) {
				path = getPrettyPath(path.replaceAll("//", "/"));
			}
		}
		return path;
	}

	public static void main(String[] args) {
		// copyFile("E:/aa.jpg", "E:/a/b/c/bb.jpg");
		System.out.println(JsonUtil.toJson(listFileName("e://dic")));
	}

}
