package com.jund.framework.core.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.ClassUtils;

/**
 * Created by zhijund on 2017/9/2.
 */
public class BeanNameGenerator extends AnnotationBeanNameGenerator {

	protected String buildDefaultBeanName(BeanDefinition definition) {
		return String.format("%s.%s", ClassUtils.getPackageName(definition.getBeanClassName()), ClassUtils.getShortName(definition.getBeanClassName()));
	}
	
}
