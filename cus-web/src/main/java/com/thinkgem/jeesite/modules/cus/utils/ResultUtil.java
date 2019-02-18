package com.thinkgem.jeesite.modules.cus.utils;

import com.thinkgem.jeesite.modules.cus.common.ConstantsWeb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
	/**
	 * @Title: resultMessage
	 * @Description: 返回结果map构建
	 * @param optcode 操作结果码
	 * @param optInfo 操作结果信息
	 * @throws Exception
	 * @return: String
	 */
	public static Map resultMessage(Map map,int optcode,String optInfo){
		if(map == null){
			map = new HashMap();
		}else{
			map.put(ConstantsWeb.OPT_CODE,optcode == 0 ? ConstantsWeb.UNKNOWN_ERROR :optcode);
			map.put(ConstantsWeb.OPT_INFO,optInfo);
		}
		return map ;
	}
}
