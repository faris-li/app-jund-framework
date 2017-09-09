package com.jund.framework.jpa.database;

import com.jund.framework.core.exception.JundRuntimeException;
import com.jund.framework.jpa.JpaConst;
import org.springframework.orm.jpa.vendor.Database;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 段志军 on 2017/9/4.
 */
public abstract class DialectFactory {

    private static ReentrantLock lock = new ReentrantLock();

    public static Map<String, Database> databases = new HashMap<String, Database>();

    static {
        databases.put("h2", Database.H2);
        databases.put("mysql", Database.MYSQL);
        databases.put("db2", Database.DB2);
        databases.put("oracle", Database.ORACLE);
    }

    public static Database getDatabaseType(String databaseType) {
        lock.lock();
        try{
            if (databases.containsKey(databaseType)) {
                return databases.get(databaseType);
            }
            throw new JundRuntimeException(JpaConst.ErrorCode.DATABASE_NOFOUND, "请选择数据库类型！");
        }finally {
            lock.unlock();
        }
    }

}
