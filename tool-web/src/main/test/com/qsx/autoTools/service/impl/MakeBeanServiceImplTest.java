package com.qsx.autoTools.service.impl;

import com.google.common.base.Splitter;
import com.qsx.autoTools.enums.FieldType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    }

}
