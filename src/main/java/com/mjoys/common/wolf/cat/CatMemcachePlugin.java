package com.mjoys.common.wolf.cat;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CatMemcachePlugin {
	
	@Around("execution(* cn.com.duiba.wolf.cache.XMemcacheClient.*(..))")
	public Object memcachedJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getMethod().getName();
		if(CatInstance.isEnable()){
			Transaction transaction = null;
			if("get".equals(methodName)){
				transaction = Cat.newTransaction("Cache.memcached", methodName + ":" + methodName);
			}else{
				transaction = Cat.newTransaction("Cache.memcached", methodName);
			}
			try {
				Object cacheValue = joinPoint.proceed();
				if("get".equals(methodName) && cacheValue == null){
					Cat.logEvent("Cache.memcached", methodName + ":missed");
				}
				transaction.setStatus(Message.SUCCESS);
				return cacheValue;
			} catch (Throwable e) {
				Cat.logError(e);
				transaction.setStatus(e);
				throw e;
			}finally{
				transaction.complete();
			}
		}else{
			return joinPoint.proceed();
		}
	}

}
