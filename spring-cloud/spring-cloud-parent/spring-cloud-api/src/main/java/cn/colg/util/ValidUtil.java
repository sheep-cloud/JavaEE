package cn.colg.util;

import java.util.regex.Pattern;

import cn.hutool.core.util.StrUtil;

/**
 * 字段验证工具
 * 
 * <a href="http://www.runoob.com/java/java-regular-expressions.html">正则表达式</a>
 *
 * @author colg
 */
public final class ValidUtil {

    private ValidUtil() {}

    /** 数字 */
    public final static Pattern NUMBERS = Pattern.compile("^([+-]?)\\d*\\.?\\d+$");
    /** 整数 */
    public final static Pattern INTEGER = Pattern.compile("^[0-9]+$");
    /** 正整数 */
    public final static Pattern INTEGER_POSITIVE = Pattern.compile("^[1-9]\\d*$");
    /** 英文字母 、数字和下划线 */
    public final static Pattern GENERAL = Pattern.compile("^\\w+$");
    /** 单个中文汉字 */
    public final static Pattern CHINESE = Pattern.compile("[\u4E00-\u9FFF]");
    /** 中文汉字 */
    public final static Pattern CHINESES = Pattern.compile("[\u4E00-\u9FFF]+");
    /** 中文字、英文字母、数字和下划线 */
    public final static Pattern GENERAL_WITH_CHINESE = Pattern.compile("^[\u4E00-\u9FFF\\w]+$");
    /** 用户名 */
    public final static Pattern ACCOUNT = Pattern.compile("^[a-z0-9]{3,16}$");
    /** 密码 */
    public final static Pattern PASSWORD = Pattern.compile("^.{6,18}$");
    /** IP v4 */
    public final static Pattern IPV4 = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
    /** 货币 */
    public final static Pattern MONEY = Pattern.compile("^(\\d+(?:\\.\\d+)?)$");
    /** 邮箱，<a href="http://emailregex.com/">参考</a> */
    public final static Pattern EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    /** 移动电话/手机号码 */
    public final static Pattern MOBILE_PHONE = Pattern.compile("^134[0-8]\\d{7}$|^13[^4]\\d{8}$|^14[5-9]\\d{8}$|^15[^4]\\d{8}$|^16[6]\\d{8}$|^17[0-8]\\d{8}$|^18[\\d]{9}$|^19[8,9]\\d{8}$");
    /** 固定电话/座机 */
    public final static Pattern PHONE = Pattern.compile("^(\\d{3,4}-?)?\\d{7,9}$");
    /** 18位身份证号码 */
    public final static Pattern CITIZEN_ID = Pattern.compile("[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)");
    /** 邮编 */
    public final static Pattern ZIP_CODE = Pattern.compile("\\d{6}");
    /** 生日 */
    public final static Pattern BIRTHDAY = Pattern.compile("^(\\d{2,4})([/\\-\\.年]?)(\\d{1,2})([/\\-\\.月]?)(\\d{1,2})日?$");
    /** URL */
    public final static Pattern URL = Pattern.compile("(https://|http://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
    /** UUID */
    public final static Pattern UUID = Pattern.compile("^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$");
    /** 不带横线的UUID */
    public final static Pattern UUID_SIMPLE = Pattern.compile("^[0-9a-z]{32}$");
    /** 中国车牌号码 */
    public final static Pattern PLATE_NUMBER = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$");
    /** MAC地址 格式如：00-01-6C-06-A6-29 或 00:01:6C:06:A6:29 */
    public static final Pattern MAC = Pattern.compile("((?:[A-F0-9]{1,2}[:-]){5}[A-F0-9]{1,2})|(?:0x)(\\d{12})(?:.+ETHER)", Pattern.CASE_INSENSITIVE);

    /// ---------------------------------------------------------------------------------------------------- ----------------------------------------------------------------------------------------------------

    /**
     * 通过正则对象校验
     * 
     * @param pattern
     * @param value
     * @return
     */
    public static boolean isMatch(Pattern pattern, String value) {
        if (pattern == null || StrUtil.isEmpty(value)) {
            return false;
        }
        return pattern.matcher(value).matches();
    }

    public static boolean isNotMatch(Pattern pattern, String value) {
        return !isMatch(pattern, value);
    }

    /**
     * 通过正则表达式校验
     * 
     * @param regex
     * @param value
     * @return
     */
    public static boolean isMatch(String regex, String value) {
        if (StrUtil.isEmpty(regex)) {
            return false;
        }
        return isMatch(Pattern.compile(regex), value);
    }

    public static boolean isNotMatch(String regex, String value) {
        return !isMatch(regex, value);
    }

    /**
     * 验证是否为 <b>数字</b>
     * 
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        return isMatch(NUMBERS, value);
    }

    public static boolean isNotNumber(String value) {
        return !isNumber(value);
    }

    /**
     * 验证是否为 <b>整数</b>
     * 
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        return isMatch(INTEGER, value);
    }

    public static boolean isNotInteger(String value) {
        return !isInteger(value);
    }

    /**
     * 验证是否为 <b>正整数</b>
     * 
     * @param value
     * @return
     */
    public static boolean isIntegerPositive(String value) {
        return isMatch(INTEGER_POSITIVE, value);
    }

    public static boolean isNotIntegerPositive(String value) {
        return !isIntegerPositive(value);
    }

    /**
     * 验证是否为 <b>英文字母 、数字和下划线</b>
     * 
     * @param value
     * @return
     */
    public static boolean isGeneral(String value) {
        return isMatch(GENERAL, value);
    }

    public static boolean isNotGeneral(String value) {
        return !isGeneral(value);
    }

    /**
     * 验证是否为 <b>单个中文汉字</b>
     * 
     * @param value
     */
    public static boolean isChinese(String value) {
        return isMatch(CHINESE, value);
    }

    public static boolean isNotChinese(String value) {
        return !isChinese(value);
    }

    /**
     * 验证是否为 <b>中文汉字</b>
     * 
     * @param value
     * @return
     */
    public static boolean isChineses(String value) {
        return isMatch(CHINESES, value);
    }

    public static boolean isNotChineses(String value) {
        return !isChineses(value);
    }

    /**
     * 验证是否为 <b>中文字、英文字母、数字和下划线</b>
     * 
     * @param value
     * @return
     */
    public static boolean isGeneralWithChinese(String value) {
        return isMatch(GENERAL_WITH_CHINESE, value);
    }

    public static boolean isNotGeneralWithChinese(String value) {
        return !isGeneralWithChinese(value);
    }

    /**
     * 验证是否为 <b>用户名</b>
     * 
     * @param value
     * @return
     */
    public static boolean isAccount(String value) {
        return isMatch(ACCOUNT, value);
    }

    public static boolean isNotAccount(String value) {
        return !isAccount(value);
    }

    /**
     * 验证是否为 <b>密码</b>
     * 
     * @return
     */
    public static boolean isPassword(String value) {
        return isMatch(PASSWORD, value);
    }

    public static boolean isNotPassword(String value) {
        return !isPassword(value);
    }

    /**
     * 验证是否为 <b>IP v4</b>
     * 
     * @param value
     * @return
     */
    public static boolean isIPv4(String value) {
        return isMatch(IPV4, value);
    }

    public static boolean isNotIPv4(String value) {
        return !isIPv4(value);
    }

    /**
     * 验证是否为 <b>货币</b>
     * 
     * @param value
     * @return
     */
    public static boolean isMoney(String value) {
        return isMatch(MONEY, value);
    }

    public static boolean isNotMoney(String value) {
        return !isMoney(value);
    }

    /**
     * 验证是否为 <b>邮箱</b>
     * 
     * @param value
     * @return
     */
    public static boolean isEmail(String value) {
        return isMatch(EMAIL, value);
    }

    public static boolean isNotEmail(String value) {
        return !isEmail(value);
    }

    /**
     * 验证是否为 <b>移动电话/手机号码</b>
     * 
     * @param value
     * @return
     */
    public static boolean isMoblePhone(String value) {
        return isMatch(MOBILE_PHONE, value);
    }

    public static boolean isNotMoblePhone(String value) {
        return !isMoblePhone(value);
    }

    /**
     * 验证是否为 <b>固定电话/座机</b>
     * 
     * @param value
     * @return
     */
    public static boolean isPhone(String value) {
        return isMatch(PHONE, value);
    }

    public static boolean isNotPhone(String value) {
        return !isPhone(value);
    }

    /**
     * 验证是否为 <b>18位身份证号码</b>
     * 
     * @param value
     * @return
     */
    public static boolean isCitizenId(String value) {
        return isMatch(CITIZEN_ID, value);
    }

    public static boolean isNotCitizenId(String value) {
        return !isCitizenId(value);
    }

    /**
     * 验证是否为 <b>邮编</b>
     * 
     * @param value
     * @return
     */
    public static boolean isZipCode(String value) {
        return isMatch(ZIP_CODE, value);
    }

    public static boolean isNotZipCode(String value) {
        return !isZipCode(value);
    }

    /**
     * 验证是否为 <b>生日</b>
     * 
     * @param value
     * @return
     */
    public static boolean isBirthday(String value) {
        return isMatch(BIRTHDAY, value);
    }

    public static boolean isNotBirthday(String value) {
        return !isBirthday(value);
    }

    /**
     * 验证是否为 <b>URL</b>
     * 
     * @param value
     * @return
     */
    public static boolean isURL(String value) {
        return isMatch(URL, value);
    }

    public static boolean isNotURL(String value) {
        return !isURL(value);
    }

    /**
     * 验证是否为 <b>UUID</b>
     * 
     * @param value
     * @return
     */
    public static boolean isUUID(String value) {
        return isMatch(UUID, value) || isMatch(UUID_SIMPLE, value);
    }

    public static boolean isNotUUID(String value) {
        return !isUUID(value);
    }

    /**
     * 验证是否为 <b>中国车牌号码</b>
     * 
     * @param value
     * @return
     */
    public static boolean isPlateNumber(String value) {
        return isMatch(PLATE_NUMBER, value);
    }

    public static boolean isNotPlateNumber(String value) {
        return !isPlateNumber(value);
    }

    /**
     * 验证是否为 <b>MAC地址</b>
     * 
     * @param value
     * @return
     */
    public static boolean isMAC(String value) {
        return isMatch(MAC, value);
    }

    public static boolean isNotMAC(String value) {
        return !isMAC(value);
    }
}
