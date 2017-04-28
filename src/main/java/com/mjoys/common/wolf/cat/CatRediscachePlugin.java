package com.mjoys.common.wolf.cat;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.mjoys.common.wolf.model.ReturnValue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CatRediscachePlugin {
	
	@Around("execution(public * com.mjoys.common.wolf.redis.ShardJedisClient.*(..))")
	public Object redisJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getMethod().getName();
		if(CatInstance.isEnable()){
			Transaction transaction = Cat.newTransaction("Cache.redis", methodName);
			try {
				Object cacheValue = joinPoint.proceed();
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
			try{
				return joinPoint.proceed();
			}catch(Throwable e){
				return new ReturnValue();
			}

		}
	}
	
}
