package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// ApplicationContext接口就是一个Spring容器
		// ApplicationContext => HierarchicalBeanFactory => BeanFactory是Spring容器的顶层结构
		this.applicationContext = applicationContext;

	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
	}


}
