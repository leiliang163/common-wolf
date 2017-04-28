package com.mjoys.common.wolf.cat;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/27 14:41.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.*;

@Intercepts({@Signature(
        method = "query",
        type = Executor.class,
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        method = "update",
        type = Executor.class,
        args = {MappedStatement.class, Object.class}
)})
public class CatMybatisPlugin implements Interceptor {
    public CatMybatisPlugin() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        if(!CatInstance.isEnable()) {
            return invocation.proceed();
        } else {
            MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
            String[] strArr = mappedStatement.getId().split("\\.");
            String class_method = strArr[strArr.length - 2] + "." + strArr[strArr.length - 1];
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            String method = sqlCommandType.name().toLowerCase();
            Transaction t = Cat.newTransaction("SQL", class_method);
            Cat.logEvent("SQL.Method", method);
            Object returnObj = null;

            try {
                returnObj = invocation.proceed();
                t.setStatus("0");
            } catch (Exception var13) {
                t.setStatus(var13.getClass().getSimpleName());
                Cat.logError(var13);
                throw var13;
            } finally {
                t.complete();
            }

            return returnObj;
        }
    }

    public String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if(parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", this.getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                Iterator i$ = parameterMappings.iterator();

                while(i$.hasNext()) {
                    ParameterMapping parameterMapping = (ParameterMapping)i$.next();
                    String propertyName = parameterMapping.getProperty();
                    Object obj;
                    if(metaObject.hasGetter(propertyName)) {
                        obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", this.getParameterValue(obj));
                    } else if(boundSql.hasAdditionalParameter(propertyName)) {
                        obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", this.getParameterValue(obj));
                    }
                }
            }
        }

        return sql;
    }

    private String getParameterValue(Object obj) {
        String value = null;
        if(obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if(obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(2, 2, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else if(obj != null) {
            value = obj.toString();
        } else {
            value = "";
        }

        return value;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties arg0) {
    }
}
