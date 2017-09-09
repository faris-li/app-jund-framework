package com.jund.framework.security.util;

import com.jund.framework.mvc.RestConst;
import com.jund.framework.mvc.response.RestException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created by zhijund on 2017/9/2.
 */
public final class AuthenticationUtil {
	
	 public static Authentication getAuthentication(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if (null == authentication){
		 	throw new RestException(RestConst.ReturnCode.USER_NOFOUND,"用户未认证！");
		 }
		 return authentication;
	 }

}
