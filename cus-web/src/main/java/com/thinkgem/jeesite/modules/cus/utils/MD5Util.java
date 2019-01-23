package com.thinkgem.jeesite.modules.cus.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName: MD5Util
 * @Description: MD5Util
 * @author: dengyn
 * @date: 2018/8/16 17:08
 */
public class MD5Util {

    private static Logger logger = Logger.getLogger(MD5Util.class);

    /**
     * @Title: getMD5
     * @Description:获取字符串的MD5值
     * @param source
     * @throws Exception
     * @return: String
     */
    public static String getMD5(String source)
    {
        logger.info("---getMD5----source:"+source);
        return DigestUtils.md5Hex(source);
    }
    /**
     * @Title: getFileMD5
     * @Description:获取文件的MD5值
     * @param filePath
     * @throws Exception
     * @return: String
     */
    public static String getFileMD5(String filePath)
    {
        logger.info("getFileMD5---start---filePath:"+filePath);
        String result = "";
        try {
            result = DigestUtils.md5Hex(new FileInputStream(filePath));
        }catch (FileNotFoundException e)
        {
            logger.error(filePath+"----FileNotFoundException----");
        }catch (IOException e)
        {
            logger.error(filePath+"----IOException----");
        }
        logger.info(filePath+"---getFileMD5---end");
        return result;
    }

}
