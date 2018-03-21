package com.qsx.autoTools.service.impl;

import org.apache.http.client.utils.CloneUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuxinqin
 * DATE : 18-3-7
 * TIME : 下午4:16
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.service.impl
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/dispatcher-servlet.xml" })
public class FileServiceImplTest {


    @Test
    public void map() throws CloneNotSupportedException {
        aaa a = new aaa();
        a.setI(1);
        Map<String, aaa> b = new HashMap<String, aaa>();
        b.put("a", a);
        Map<String, aaa> c = new HashMap<String, aaa>();
        c = (Map<String, aaa>)CloneUtils.clone(b);
        aaa d = new aaa();
        d= c.get("a");
        d.setI(4);
        //c.put("a", 2);
        System.out.println(b.get("a").getI());
        System.out.println(c.get("a").getI());
    }

    class aaa{
        private Integer i ;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }

    }

}
