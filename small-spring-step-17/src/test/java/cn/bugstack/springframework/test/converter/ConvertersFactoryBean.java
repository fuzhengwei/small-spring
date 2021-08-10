package cn.bugstack.springframework.test.converter;

import cn.bugstack.springframework.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author derekyi
 * @date 2021/1/17
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

	@Override
	public Set<?> getObject() throws Exception {
		HashSet<Object> converters = new HashSet<>();
		StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
		converters.add(stringToLocalDateConverter);
		return converters;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
