package com.jund.framework.jpa.util;

import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 段志军 on 2017/9/4.
 */
public class HqlUtil {

    public static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, "hql:" + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    public static String removeOrders(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W\\s\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()){
            m.appendReplacement(sb,"");
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
