package com.jund.framework.test.suite;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhijund on 2017/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseSuite {

    public ObjectMapper mapper = new ObjectMapper();

    public String toJsonString(Object target) throws Exception{
        return mapper.writeValueAsString(target);
    }

    public abstract void testBaseCase();
}
