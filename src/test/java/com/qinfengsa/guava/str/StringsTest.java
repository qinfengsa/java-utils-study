package com.qinfengsa.guava.str;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 6. 字符串处理[Strings]
 * 非常有用的字符串工具，包括分割、连接、填充等操作
 * @author qinfengsa
 * @date 2020/05/21 13:20
 */
@Slf4j
public class StringsTest {


    /**
     * 连接器[Joiner] 用分隔符把字符串序列连接起来
     */
    @Test
    public void testJoin() {
        Joiner joiner = Joiner.on(";").skipNulls();
        String join1 =   joiner.join("Harry", null, "Ron", "Hermione");
        log.debug("join1: {}",join1);
    }

    /**
     * 拆分器[Splitter]
     *
     */
    @Test
    public void testSplitter() {
        String test = "A;B;;D:K;;E";
        Splitter splitter1 = Splitter.on(';').omitEmptyStrings().limit(4);
        Iterable<String> iterable1 = splitter1.split(test);
        iterable1.forEach(log::debug);
        // Splitter.on(char) 按单个字符拆分   Splitter.on(‘;’)
        // Splitter.on(CharMatcher) 按字符匹配器拆分  Splitter.on(CharMatcher.BREAKING_WHITESPACE)
        // Splitter.on(String) 按字符串拆分    Splitter.on(“, “)
        // Splitter.on(Pattern) Splitter.onPattern(String) 按正则表达式拆分Splitter.onPattern(“\r?\n”)
        // Splitter.fixedLength(int) 按固定长度拆分；最后一段可能比给定长度短，但不会为空。 Splitter.fixedLength(3)

        // omitEmptyStrings() 从结果中自动忽略空字符串
        // trimResults() 移除结果字符串的前导空白和尾部空白
        // trimResults(CharMatcher) 给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
        // limit(int) 限制拆分出的字符串数量

    }


    /**
     * 字符匹配器[CharMatcher]
     */
    @Test
    public void testCharMatcher() {
        String test = "12324";
        String noControl = CharMatcher.javaIsoControl().removeFrom(test); // 移除control字符
        String theDigits = CharMatcher.any().retainFrom("1"); // 只保留数字字符
        log.debug("theDigits, {}",theDigits);
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom(test, ' ');
        // 去除两端的空格，并把中间的连续空格替换成单个空格
        // String noDigits = CharMatcher.any().replaceFrom(string, "*"); //用*号替换所有数字
        // String lowerAndDigit = CharMatcher.any().or(CharMatcher.).retainFrom(string);
        // 只保留数字和小写字母

        // collapseFrom(CharSequence,char)	把每组连续的匹配字符替换为特定字符。如WHITESPACE.collapseFrom(string, ‘ ‘)把字符串中的连续空白字符替换为单个空格。
        // matchesAllOf(CharSequence)	测试是否字符序列中的所有字符都匹配。
        // removeFrom(CharSequence)	从字符序列中移除所有匹配字符。
        // retainFrom(CharSequence)	在字符序列中保留匹配字符，移除其他字符。
        // trimFrom(CharSequence)	移除字符序列的前导匹配字符和尾部匹配字符。
        // replaceFrom(CharSequence,   CharSequence)	用特定字符序列替代匹配字符。

    }


    /**
     * 大小写格式[CaseFormat]
     * CaseFormat 被用来方便地在各种ASCII 大小写规范间转换字符串 可以用于 编写代码生成器
     * LOWER_CAMEL lowerCamel
     * LOWER_HYPHEN lower-hyphen
     * LOWER_UNDERSCORE lower_underscore
     * UPPER_CAMEL UpperCamel
     * UPPER_UNDERSCORE UPPER_UNDERSCORE
     */
    @Test
    public void testCaseFormat() {
        String name = "&caseMoTo";
        String a = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,name);
        log.debug("res:{}",a);


    }


    /**
     * Strings
     */
    @Test
    public void testStrings() {

        // nullToEmpty() 如果字符串对象为空，则返回空字符串""，否则就返回原字符串
        // emptyToNull() 如果输入的是空字符串，那么就返回null，否则返回原字符串
        // isNullOrEmpty() 如果输入的字符串对象是null或者输入的字符串内容为空，那么就返回true
        // padStart() 返回一个长度至少是minLength的字符串，如果string长度不够就在它前面添加若干个padChar，以使结果字符串长度为minLength
        String a = Strings.padStart("abdc",10,'F');
        log.debug("a :{}",a);
        // padEnd() 如果长度不够，在string后补padChar
        String b = Strings.padEnd("abdc",10,'B');
        log.debug("b :{}",b);
        // repeat() 将输入的字符串重复拼接count次
        String strRepeat = Strings.repeat("ABC",3);
        log.debug("repeat:{}",strRepeat);
        // commonPrefix() 查询两个字符串的最长公共前缀
        String str1 = "ABC KKKKD";
        String str2 = "ABC DLLLK";
        log.debug("prefix:{}",Strings.commonPrefix(str1,str2));

        // commonSuffix() 查询两个字符串的最长公共后缀
        String str3 = "ABC KKKKDSag";
        String str4 = "ABC DLLLKg";
        log.debug("suffix:{}",Strings.commonSuffix(str3,str4));

    }

}
