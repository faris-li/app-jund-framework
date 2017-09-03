package com.jund.framework.mvc.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created by zhijund on 2017/9/2.
 */
public class ExcelUtil {
	
	/**
	 * Excel校验起始行
	 */
	public static final int VERIFY_STARTROW = 3;
	
	/**
	 * 1,40 长度
	 */
	private static final String REGEX_NAME = "[a-zA-Z0-9_]{3,16}";

	/**
	 * 1,40 长度
	 */
	private static final String REGEX_CODE = "^[a-zA-Z0-9_]{1,40}$";
	
	/**
	 * 1,50 长度账号
	 */
	private static final String REGEX_NUM = "^[0-9]{1,50}$";
	
	/**
	 * 校验百分比(输入范围[0~99.99]的数字)
	 */
	private static final String REGEX_PERCENT = "^[0-9]\\d{0,1}(\\.\\d{1,2})$";
	
	/**
	 * decimal[20,10]
	 */
	private static final String REGEX_DECIMAL = "^[\\d]{0,20}\\.[\\d]{0,10}$";
	
	/**
	 * 校验邮件
	 */
	private static final String REGEX_MAIL = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))";
	
	/**
	 * 校验邮件是否合法
	 */
	private static final String REGEX_PHONE = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?";
	
	/**
	 * 日期默认格式
	 */
	public static String defaultPattern = "yyyy-MM-dd";

	/**
	 * 校验日期
	 * 
	 * @param dateString
	 * @return
	 */
	public static boolean verifyDate(String dateString) {
		return verifyDate(dateString, defaultPattern);
	}

	/**
	 * 校验日期
	 * 
	 * @param dateString
	 * @param regexPattern
	 * @return
	 */
	public static boolean verifyDate(String dateString, String regexPattern) {
		try {
			new SimpleDateFormat(regexPattern).parse(dateString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 校验Mail
	 * 
	 * @param phoneString
	 * @return
	 */
	public static boolean verifyPhone(String phoneString) {
		return verify(REGEX_PHONE, phoneString);
	}
	
	/**
	 * 校验Mail
	 * 
	 * @param phoneString
	 * @return
	 */
	public static boolean verifyPhone(String regex, String phoneString) {
		return verify(regex, phoneString);
	}
	
	/**
	 * 校验Mail
	 * 
	 * @param mailString
	 * @return
	 */
	public static boolean verifyMail(String mailString) {
		return verify(REGEX_MAIL, mailString);
	}

	/**
	 * 校验Code
	 * <p/>
	 * 规则：只可输入(1-10)位的英文字母、数字和下划线！
	 * 
	 * @param codeString
	 * @return
	 */
	public static boolean verifyCode(String codeString) {
		return verify(REGEX_CODE, codeString);
	}
	
	/**
	 * 校验Code
	 * 
	 * @param codeString
	 * @return
	 */
	public static boolean verifyCode(String regex, String codeString) {
		return verify(regex, codeString);
	}
	
	/**
	 * 校验百分比
	 * <p/>
	 * 规则：输入范围[0~99.99]的数字
	 * 
	 * @param percentString
	 * @return
	 */
	public static boolean verifyPercent(String percentString) {
		return verify(REGEX_PERCENT, percentString);
	}
	
	/**
	 * 校验Num
	 * 规则：只可输入[0-9](1-40)位的数字组成的账号！
	 * @param value
	 * @return
	 */
	public static boolean verifyNum(String value) {
		return verify(REGEX_NUM, value);
	}
	
	/**
	 *  校验decimal(20,10)
	 *  
	 * @param value
	 * @return
	 */
	public static boolean verifyDecimal(String value) {
		return verify(REGEX_DECIMAL, value);
	}

	/**
	 * 校验浮点数
	 * 
	 * @param doubleString
	 * @return
	 */
	public static boolean verifyDouble(String doubleString) {
		try {
			Double.parseDouble(doubleString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断是否为数字
	 * 
	 * @return
	 */
	public static boolean verifyDigital(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getCellStringValue(Cell cell) {
		if (cell == null)
			return null;
		String cellValue = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			if (cellValue == null || !StringUtils.hasText(cellValue.trim()))
				cellValue = null;
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			double numericCellValue = cell.getNumericCellValue();
			cellValue = new BigDecimal(numericCellValue).toPlainString(); 
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			boolean boolValue = cell.getBooleanCellValue();
			cellValue = String.valueOf(boolValue);
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return cellValue;
	}

	public static boolean verifyName(String regexValue) {
		return Pattern.matches(REGEX_NAME, regexValue);
	}

	public static boolean verifyName(String regex, String regexValue) {
		return Pattern.matches(regex, regexValue);
	}

	public static boolean verify(String regex, String regexValue) {
		return Pattern.matches(regex, regexValue);
	}

}
