package com.qsx.autoTools.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.qsx.autoTools.enums.FieldType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuxinqin
 * DATE : 18-3-20
 * TIME : 下午3:04
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.service.impl
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/dispatcher-servlet.xml" })
public class MakeBeanServiceImplTest {

    @Resource
    private MakeBeanServiceImpl makeBeanService;
    @Test
    public void splitString(){
        makeBeanService.makeBeanByCreateTableString("CREATE TABLE `m_cash_flow` (\n"
                + "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',\n"
                + "  `cash_flow_code` varchar(20) NOT NULL DEFAULT '0' COMMENT '现金流量码',\n"
                + "  `cash_flow_name` varchar(20) NOT NULL DEFAULT '' COMMENT '现金流量码名称',\n"
                + "  `cash_flow_remark` varchar(30) NOT NULL DEFAULT '' COMMENT '现金流量码说明（备注）',\n"
                + "  `effect_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '生效状态 1生效，-1失效',\n"
                + "  `create_by` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',\n"
                + "  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',\n"
                + "  `last_update_by` varchar(20) NOT NULL DEFAULT '' COMMENT '最后更新人',\n"
                + "  `last_update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后更新时间',\n"
                + "  PRIMARY KEY (`id`)\n\n\n\n\n\n\n\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='现金流量码表';");
    }

    @Test
    public void test1(){
//        System.out.println(FieldType.valueOf());

        for(int i=2016;i<=2018;i++){
            for(int j = 1;j<=12;j++){
                int c= i*100+j;
                System.out.println("ALTER  TABLE  qfarmer.`gl_ledger_line_detail_"+c+"`  ADD  INDEX idx_gl_voucher_num_gl_line_no ( `gl_voucher_num`,`gl_line_no` );");
            }
        }
    }

    @Test
    public void list(){
        List<zxc> list = new ArrayList<zxc>();
        zxc zxc = new zxc();
        zxc.setA(1);
        list.add(zxc);
        List<zxc> b = Lists.transform(list, new Function<zxc, zxc>() {
            @Nullable
            public zxc apply(@Nullable zxc input) {
                 input.setA(input.getA()+1);
                 return input;
            }
        });
        System.out.println(b.get(0).getA());
        for(zxc input : b){
            input.setB(2);
        }
        for(zxc input : b){
            input.setB(3);
        }
        int z = 4;
        System.out.println(b.get(0).getA());
    }

    public class zxc{
        private Integer a;
        private Integer b;

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }
    }
}
