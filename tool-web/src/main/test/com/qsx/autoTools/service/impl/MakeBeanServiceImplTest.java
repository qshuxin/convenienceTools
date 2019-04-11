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
        String a = makeBeanService.makeBeanByCreateTableString("CREATE TABLE qfarmer.`gl_share_mapping` (\n"
                + "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',\n"
                + "  `from_bu` varchar(64) NOT NULL DEFAULT '' COMMENT '摊出BU',\n"
                + "  `to_bu` varchar(64) NOT NULL DEFAULT '' COMMENT '摊入BU',\n"
                + "  `budgetary_account` varchar(128) NOT NULL DEFAULT '' COMMENT '预算科目',\n"
                + "  `amount` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '金额',\n"
                + "  `period` int NOT NULL DEFAULT '0' COMMENT '期间',\n"
                + "  `create_by` varchar(30) NOT NULL DEFAULT '' COMMENT '创建人',\n"
                + "  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',\n"
                + "  PRIMARY KEY (`id`),\n" + "  KEY `idx_period`(`period`)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='BU摊出摊入关系表';");
        System.out.println(a);
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


    @Test
    public void sql(){
        String a = "Q0363\n" + "Q0364\n" + "Q0365\n";
        String b = "机票国内基础平台\n" + "网站运营中心\n" + "基础研发部\n";
        String c = "P&D\n" + "P&D\n" + "P&D\n";
        List list1 = Splitter.on("\n").splitToList(a);
        List list2 = Splitter.on("\n").splitToList(b);
        List list3 = Splitter.on("\n").splitToList(c);
        for ( int i =0 ; i<list1.size(); i ++){
            System.out.println("INSERT INTO qreaper.m_department(department_code, department_name, first_department_code, first_department_name, second_department_code, second_department_name, third_department_code, third_department_name, fourth_department_code, fourth_department_name, fifth_department_code, fifth_department_name, final_department_code, qunar_property, ctrip_property, effect_flag, create_by, create_time, last_update_by, last_update_time, expense_dept_code, expense_dept_name, expense_dept_property, dept_setid) VALUE\n"
                    + "  ('"+ list1.get(i)+"','"+ list2.get(i)+"', '"+ list1.get(i)+"','"+ list2.get(i)+"','','','','','','','','','"+ list1.get(i)+"','"+ list3.get(i)+"', '"+ list3.get(i)+"',1,'shuxin.qin',now(),'shuxin.qin',now(), '"+ list1.get(i)+"','"+ list2.get(i)+"','"+ list3.get(i)+"', 'QUNAR');\n"
                    + "INSERT INTO qreaper.m_department_bu_mapping (first_department_code, bu_code, effect_flag, create_by, create_time, last_update_by, last_update_time) VALUE\n"
                    + "  ('"+ list1.get(i)+"', 0,1, 'shuxin.qin', now(), 'shuxin.qin', now());");
        }
    }


    @Test
    public void sql2(){
        String a = "差旅必选服务包\n" + "差旅必选服务包";
        String b = "MOB\n" + "WEB\n";
        String c = "1213\n" + "1214\n";
        String d = "机票自营-机票辅营\n" + "机票自营-机票辅营\n";
        List list1 = Splitter.on("\n").splitToList(a);
        List list2 = Splitter.on("\n").splitToList(b);
        List list3 = Splitter.on("\n").splitToList(c);
        List list4 = Splitter.on("\n").splitToList(d);
        for ( int i =0 ; i<list1.size(); i ++){
            System.out.println("INSERT INTO qwatson.data_clear_rules (data_src, target_type, search_box, target, create_by, create_time, last_update_by, last_update_time) VALUES ('air_ancillary_confirmed', 'pcode', 'business_object-"+list1.get(i)+"-client-"+list2.get(i)+"', '"+list3.get(i)+"', '', now(), '', now());");
            System.out.println("INSERT INTO qwatson.data_clear_rules (data_src, target_type, search_box, target, create_by, create_time, last_update_by, last_update_time) VALUES ('air_ancillary_confirmed', 'fromCustomerCode', 'business_object-"+list1.get(i)+"-client-"+list2.get(i)+"', '"+list4.get(i)+"', '', now(), '', now());");
        }

//        for ( int i =0 ; i<list1.size(); i ++){
//            System.out.println("INSERT INTO qfarmer.`air_ticket_customer_rules`(data_src, company_code, customer_code, create_by, create_time) VALUE ('"+list1.get(i)+"', '"+list2.get(i)+"', '"+list3.get(i)+"', 'system', now());");
//        }
    }

    @Test
    public void sql4(){
        String a = "国内机票服务包立减活动\n" + "国内机票服务包立减活动";
        String b = "MOB\n" + "WEB\n";
        String c = "国内\n" + "国内\n";
        String d = "自营\n" + "自营\n";
        String e = "1213\n" + "1214\n";
        List list1 = Splitter.on("\n").splitToList(a);
        List list2 = Splitter.on("\n").splitToList(b);
        List list3 = Splitter.on("\n").splitToList(c);
        List list4 = Splitter.on("\n").splitToList(d);
        List list5 = Splitter.on("\n").splitToList(e);
        for ( int i =0 ; i<list1.size(); i ++){
            System.out.println("INSERT INTO qwatson.data_clear_rules (data_src, target_type, search_box, target, create_by, create_time, last_update_by, last_update_time) VALUES ('air_market_insert_source', 'pcode', 'activity_name-"+list1.get(i)+"-client-"+list2.get(i)+"-business_scope-"+list3.get(i)+"-remark-"+list4.get(i)+"', '"+list5.get(i)+"', 'shuxin.qin', now(), '', now());");
        }
    }

    @Test
    public void sql3(){
        for (int a = 2017; a<=2019; a++){
            for (int b = 1; b <= 12 ; b++){
                System.out.println("ALTER  TABLE  qwatson.`ce_qunar_prepay_"+String.valueOf(a*100+b)+"`  ADD  column (`business_scope` varchar(10) NOT NULL DEFAULT '' COMMENT '业务范围');");
            }
        }
        for (int a = 2017; a<=2019; a++){
            for (int b = 1; b <= 12 ; b++){
                System.out.println("ALTER  TABLE  qwatson.`qunar_ce_cash_"+String.valueOf(a*100+b)+"`  ADD  column (`business_scope` varchar(10) NOT NULL DEFAULT '' COMMENT '业务范围');");
            }
        }
        for (int a = 2017; a<=2019; a++){
            for (int b = 1; b <= 12 ; b++){
                System.out.println("ALTER  TABLE  qwatson.`qunar_ce_prepay_"+String.valueOf(a*100+b)+"`  ADD  column (`business_scope` varchar(10) NOT NULL DEFAULT '' COMMENT '业务范围');");
            }
        }
    }

    @Test
    public void test(){
        double a = 0;
        for (int i = 1;i<=10 ; i++){
            a = a * 0.2 + a;
            for (int j = 1; j<=12; j++){
                a = 5000*(0.2/12)*j + a ;
            }
            a = a + 5000*12 ;
            System.out.println(i + " " + a);
        }
        double b = 5000;
        for (int i = 1;i<=10 ; i++){
            for (int j = 1; j<=12; j++){
                b = b*(0.04/12) + b + 5000;
            }
            System.out.println(i + " " + b);
        }
         b = 600000;
        for (int i = 1;i<=10 ; i++){
            for (int j = 1; j<=12; j++){
                b = b*(0.04/12) +b;
            }
            System.out.println(i + " " + b);
        }
    }
}
