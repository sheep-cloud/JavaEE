package cn.colg.util;

import static cn.colg.util.ValidUtil.*;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

import cn.hutool.core.util.RandomUtil;

/**
 * 字段验证工具 测试
 *
 * @author colg
 */
public final class ValidUtilTest {
    
    private Pattern pattern = Pattern.compile("^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$");;

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isMatch(java.util.regex.Pattern, java.lang.String)}.
     */
    @Test
    public void testIsMatchPatternString() {
        String value = "2016-10-20";
        assertTrue(isMatch(pattern, value ));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotMatch(java.util.regex.Pattern, java.lang.String)}.
     */
    @Test
    public void testIsNotMatchPatternString() {
        String value = "2016-10-20";
        assertFalse(isNotMatch(pattern, value ));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isMatch(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testIsMatchStringString() {
        String regex = "<script[^>]*>[\\s\\S]*?<\\/[^>]*script>";
        String value = "<script type=\"text/javascript\" src=\"\"></script>";
        assertTrue(isMatch(regex, value));
        
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotMatch(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testIsNotMatchStringString() {
        String regex = "<script[^>]*>[\\s\\S]*?<\\/[^>]*script>";
        String value = "<script type=\"text/javascript\" src=\"\"></script>";
        assertFalse(isNotMatch(regex, value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNumber(java.lang.String)}.
     */
    @Test
    public void testIsNumber() {
        String value = "123456789";
        assertTrue(isNumber(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotNumber(java.lang.String)}.
     */
    @Test
    public void testIsNotNumber() {
        String value = "123456789";
        assertFalse(isNotNumber(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isInteger(java.lang.String)}.
     */
    @Test
    public void testIsInteger() {
        String value = Integer.MAX_VALUE + "";
        assertTrue(isInteger(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotInteger(java.lang.String)}.
     */
    @Test
    public void testIsNotInteger() {
        String value = Integer.MAX_VALUE + "";
        assertFalse(isNotInteger(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isIntegerPositive(java.lang.String)}.
     */
    @Test
    public void testIsIntegerPositive() {
        String value = "12";
        assertTrue(isIntegerPositive(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotIntegerPositive(java.lang.String)}.
     */
    @Test
    public void testIsNotIntegerPositive() {
        String value = "12";
        assertFalse(isNotIntegerPositive(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isGeneral(java.lang.String)}.
     */
    @Test
    public void testIsGeneral() {
        String value = "hello_1234";
        assertTrue(isGeneral(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotGeneral(java.lang.String)}.
     */
    @Test
    public void testIsNotGeneral() {
        String value = "hello_1234";
        assertFalse(isNotGeneral(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isChinese(java.lang.String)}.
     */
    @Test
    public void testIsChinese() {
        String value = "天";
        assertTrue(isChinese(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotChinese(java.lang.String)}.
     */
    @Test
    public void testIsNotChinese() {
        String value = "天";
        assertFalse(isNotChinese(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isChineses(java.lang.String)}.
     */
    @Test
    public void testIsChineses() {
        String value = "黑云下的天空";
        assertTrue(isChineses(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotChineses(java.lang.String)}.
     */
    @Test
    public void testIsNotChineses() {
        String value = "黑云下的天空";
        assertFalse(isNotChineses(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isGeneralWithChinese(java.lang.String)}.
     */
    @Test
    public void testIsGeneralWithChinese() {
        String value = "黑云下的天空_1234tech";
        assertTrue(isGeneralWithChinese(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotGeneralWithChinese(java.lang.String)}.
     */
    @Test
    public void testIsNotGeneralWithChinese() {
        String value = "黑云下的天空_1234tech";
        assertFalse(isNotGeneralWithChinese(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isAccount(java.lang.String)}.
     */
    @Test
    public void testIsAccount() {
        String value = "colg";
        assertTrue(isAccount(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotAccount(java.lang.String)}.
     */
    @Test
    public void testIsNotAccount() {
        String value = "colg";
        assertFalse(isNotAccount(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isPassword(java.lang.String)}.
     */
    @Test
    public void testIsPassword() {
        String value = "123456";
        assertTrue(isPassword(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotPassword(java.lang.String)}.
     */
    @Test
    public void testIsNotPassword() {
        String value = "123456";
        assertFalse(isNotPassword(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isIPv4(java.lang.String)}.
     */
    @Test
    public void testIsIPv4() {
        String value = "192.168.1.55";
        assertTrue(isIPv4(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotIPv4(java.lang.String)}.
     */
    @Test
    public void testIsNotIPv4() {
        String value = "192.168.1.55";
        assertFalse(isNotIPv4(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isMoney(java.lang.String)}.
     */
    @Test
    public void testIsMoney() {
        String value = "9999.99";
        assertTrue(isMoney(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotMoney(java.lang.String)}.
     */
    @Test
    public void testIsNotMoney() {
        String value = "9999.99";
        assertFalse(isNotMoney(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isEmail(java.lang.String)}.
     */
    @Test
    public void testIsEmail() {
        String value = "121529654@qq.com";
        assertTrue(isEmail(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotEmail(java.lang.String)}.
     */
    @Test
    public void testIsNotEmail() {
        String value = "121529654@qq.com";
        assertFalse(isNotEmail(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isMoblePhone(java.lang.String)}.
     */
    @Test
    public void testIsMoblePhone() {
        String value = "18727019986";
        assertTrue(isMoblePhone(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotMoblePhone(java.lang.String)}.
     */
    @Test
    public void testIsNotMoblePhone() {
        String value = "18727019986";
        assertFalse(isNotMoblePhone(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isPhone(java.lang.String)}.
     */
    @Test
    public void testIsPhone() {
        String value = "29873116";
        assertTrue(isPhone(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotPhone(java.lang.String)}.
     */
    @Test
    public void testIsNotPhone() {
        String value = "29873116";
        assertFalse(isNotPhone(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isCitizenId(java.lang.String)}.
     */
    @Test
    public void testIsCitizenId() {
        String value = "510903198607030949";
        assertTrue(isCitizenId(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotCitizenId(java.lang.String)}.
     */
    @Test
    public void testIsNotCitizenId() {
        String value = "510903198607030949";
        assertFalse(isNotCitizenId(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isZipCode(java.lang.String)}.
     */
    @Test
    public void testIsZipCode() {
        String value = "441000";
        assertTrue(isZipCode(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotZipCode(java.lang.String)}.
     */
    @Test
    public void testIsNotZipCode() {
        String value = "441000";
        assertFalse(isNotZipCode(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isBirthday(java.lang.String)}.
     */
    @Test
    public void testIsBirthday() {
        String value = "1989年7月14日";
        assertTrue(isBirthday(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotBirthday(java.lang.String)}.
     */
    @Test
    public void testIsNotBirthday() {
        String value = "1989年7月14日";
        assertFalse(isNotBirthday(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isURL(java.lang.String)}.
     */
    @Test
    public void testIsURL() {
        String value = "https://github.com/colg-cloud/JavaEE/blob/master/spring-cloud/spring-cloud-parent/spring-cloud-api/src/main/java/cn/colg/util/ValidUtil.java";
        assertTrue(isURL(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotURL(java.lang.String)}.
     */
    @Test
    public void testIsNotURL() {
        String value = "https://github.com/colg-cloud/JavaEE/blob/master/spring-cloud/spring-cloud-parent/spring-cloud-api/src/main/java/cn/colg/util/ValidUtil.java";
        assertFalse(isNotURL(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isUUID(java.lang.String)}.
     */
    @Test
    public void testIsUUID() {
        String value = RandomUtil.randomUUID();
        assertTrue(isUUID(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotUUID(java.lang.String)}.
     */
    @Test
    public void testIsNotUUID() {
        String value = RandomUtil.randomUUID();
        assertFalse(isNotUUID(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isPlateNumber(java.lang.String)}.
     */
    @Test
    public void testIsPlateNumber() {
        String value = "粤AD16816";
        assertTrue(isPlateNumber(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotPlateNumber(java.lang.String)}.
     */
    @Test
    public void testIsNotPlateNumber() {
        String value = "粤AD16816";
        assertFalse(isNotPlateNumber(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isMAC(java.lang.String)}.
     */
    @Test
    public void testIsMAC() {
        String value = "00:01:6C:06:A6:29";
        assertTrue(isMAC(value));
    }

    /**
     * Test method for {@link cn.colg.util.ValidUtil#isNotMAC(java.lang.String)}.
     */
    @Test
    public void testIsNotMAC() {
        String value = "00:01:6C:06:A6:29";
        assertFalse(isNotMAC(value));
    }

}
