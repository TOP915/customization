package com.thinkgem.jeesite.modules.cus.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class JsonUtil {

	private static Logger logger = Logger.getLogger(JsonUtil.class);

	private static ObjectMapper mapper = new ObjectMapper();

	private static ObjectMapper getMapper() {
		return mapper;
	}

	public static <T> T readValue(String val, Class<T> entity) {
		T result = null;
		try {
			result = getMapper().readValue(val, entity);
		} catch (Exception e) {
			logger.error("readValue exception:e=" + e.getMessage());
		}
		return result;
	}
	
	public static String toJson(Object entity) {
		String result = null;
		try {
			result = getMapper().writeValueAsString(entity);
		} catch (Exception e) {
			logger.error("readValue exception:e=" + e.getMessage());
		}
		return result;
	}
	
	public static String toJson(Object entity , boolean pretty) {
		String result = null;
		if(pretty){
			try {
				result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
			} catch (Exception e) {
				logger.error("readValue exception:e=" + e.getMessage());
			}
		}else{
			result = toJson(entity);
		}
		return result;
	}
}
