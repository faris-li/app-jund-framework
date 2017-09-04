package com.jund.framework.jpa;

/**
 * Created by 段志军 on 2017/9/4.
 */
public abstract class JpaConst {

    public static class ErrorCode {
        public static final String DATABASE_NOFOUND = "3000";
        public static final String DATABASE_INITIAL = "3001";
        public static final String DATABASE_POOL_TYPE = "3002";
        public static final String DATABASE_CONNECTION = "3003";
    }

    public static class Database {
        public static final String DBCP = "dbcp";
        public static final String C3P0 = "c3p0";
    }
}
