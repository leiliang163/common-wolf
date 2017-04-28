package com.mjoys.common.wolf.cat;

import com.dianping.cat.Cat;
import com.dianping.cat.servlet.CatFilter;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by wenqi.huang on 2016/11/7.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CatAutoConfiguration {

    //ConditionalOnClass这个注解必须加在类上，如果加在方法上的话，会因为找不到这些类而报错：https://github.com/spring-projects/spring-boot/issues/1733
    @Configuration
    @ConditionalOnClass({Servlet.class, FilterRegistrationBean.class, CatFilter.class})
    @ConditionalOnWebApplication
    public static class CatHttpFilterConfiguration{
        /**
         * 注入cat监控的Filter
         * @return
         */
        @Bean
        public FilterRegistrationBean catHttpFilterConfigurer(){
            CatFilter catFilter = new CatFilter();
            FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(catFilter);
            List<String> urlPatterns=new ArrayList<String>();
            urlPatterns.add("/*");//拦截路径，可以添加多个
            registrationBean.setUrlPatterns(urlPatterns);
            registrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD));
            registrationBean.setOrder(1);
            return registrationBean;
        }
    }

    @Configuration
    @ConditionalOnClass({SqlSessionTemplate.class, SqlSessionFactoryBean.class, SqlSessionFactory.class,CatMybatisPlugin.class})
    public static class MyBatisPostProcessorConfiguration{

        /**
         * 声明后置处理器，spring全部bean初始化完成后调用，给所有SqlSessionBean注入CatMybatisPlugin plugin，监控sql的执行
         * @return
         */
        @Bean
        public BeanPostProcessor myBatisPostProcessorConfigurer(){
            return new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }

                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    SqlSessionFactory s = null;
                    if(bean instanceof SqlSessionFactory){
                        s = (SqlSessionFactory)bean;
                    }
                    if(bean instanceof SqlSessionTemplate){
                        s = ((SqlSessionTemplate)bean).getSqlSessionFactory();
                    }
                    if(s == null){
                        return bean;
                    }

                    boolean hasCatPlugin = false;
                    if(s.getConfiguration().getInterceptors() != null && !s.getConfiguration().getInterceptors().isEmpty()) {
                        for (Interceptor plugin : s.getConfiguration().getInterceptors()) {
                            if (plugin instanceof CatMybatisPlugin) {
                                hasCatPlugin = true;
                                break;
                            }
                        }
                    }else{
                    }

                    if (!hasCatPlugin) {
                        s.getConfiguration().addInterceptor(new CatMybatisPlugin());
                    }

                    return bean;
                }
            };
        }
    }
    
    @Configuration
    @ConditionalOnClass({Cat.class})
    public static class InitCatServer implements ApplicationListener {

		@Override
		public void onApplicationEvent(ApplicationEvent arg0) {
			//初始化cat监控
			Cat.getManager().isCatEnabled();
		}
    	
    }

}
