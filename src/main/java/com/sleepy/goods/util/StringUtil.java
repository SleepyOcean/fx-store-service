package com.sleepy.goods.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleepy.goods.common.UserOperationIllegalException;
import com.sleepy.goods.dto.MapDTO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author gehoubao
 * @create 2019-04-25 13:44
 **/
public class StringUtil {
    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(Object string) {
        if (null == string || "".equals(string)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param string
     * @return
     */
    public static boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }

    /**
     * 判断多个字符串是否都不为空
     *
     * @param strings
     * @return
     */
    public static boolean stringsIsNotEmpty(String... strings) {
        for (String s : strings) {
            if (isNullOrEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 抛出指定信息的异常
     *
     * @param info
     * @return
     * @throws Exception
     */
    public static boolean throwExceptionInfo(String info) throws Exception {
        throw new Exception(info);
    }

    /**
     * 抛出用户操作的异常
     *
     * @param info
     * @return
     * @throws UserOperationIllegalException
     */
    public static boolean throwUserExceptionInfo(String info) throws UserOperationIllegalException {
        throw new UserOperationIllegalException(info);
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String getDateString(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 美化json字符串
     *
     * @param json
     * @return 美化后的json字符串
     * @throws IOException
     */
    public static String formatJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;
        obj = mapper.readValue(json, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    /**
     * 格式化http请求URL
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String formatUrl(String url) throws UnsupportedEncodingException {
        url = URLDecoder.decode(url, "utf-8").replaceAll("&amp;", "&");
        String dest = url;
        Pattern pat = CHINESE_PATTERN;
        Matcher mat = pat.matcher(url);
        while (mat.find()) {
            String s = mat.group();
            dest = dest.replaceAll(s, URLEncoder.encode(s, "utf-8"));
        }
        return dest;
    }

    /**
     * 获取不带‘-’的随机UUID
     *
     * @param intervalMark
     * @return
     */
    public static String getRandomUuid(String intervalMark) {
        return UUID.randomUUID().toString().replaceAll("-", intervalMark);
    }

    public static Map jsonObjectToMap(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        //循环转换
        Iterator it = jsonObject.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            data.put(entry.getKey(), entry.getValue());
        }
        return data;
    }

    /**
     * 格式化文件大小，输入文件大小（byte为单位），输出带单位的文件大小，如 10240 => 10M
     *
     * @param size
     * @return
     */
    public static String getFormatFileSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = BigDecimal.valueOf(kiloByte);
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = BigDecimal.valueOf(megaByte);
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = BigDecimal.valueOf(gigaByte);
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 从数字和字符组成的字符串中获取整数， 如 1024 bytes => 1024
     *
     * @param numStr
     * @return
     */
    public static int getIntegerNumFromString(String numStr) {
        String trimStr = numStr.replaceAll("[^0-9]", "").trim();
        int value = Integer.parseInt(StringUtil.isNullOrEmpty(trimStr) ? "0" : trimStr);
        return value;
    }

    /**
     * 获取指定位数的随机数字字符串
     *
     * @param bit
     * @return
     */
    public static String getRandomNumString(int bit) {
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bit; i++) {
            s.append(random.nextInt(9));
        }
        return s.toString();
    }

    public static Map<String, Object> getNewExtraMap(MapDTO... extras) {
        Map<String, Object> extra = new HashMap<>(extras.length);
        for (MapDTO mapDTO : extras) {
            extra.put(mapDTO.getKey(), mapDTO.getValue());
        }
        return extra;
    }

    public static Double formatPriceNum(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return new Double(df.format(price).toString());
    }

    public static String getSplitString(String splitSymbol, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            sb.append(splitSymbol);
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static Map<String, Object> newParamsMap(MapDTO... maps) {
        return getNewExtraMap(maps);
    }

    public static Date getDateAgoFromNow(int amount) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, Math.negateExact(amount));
        return c.getTime();
    }
}