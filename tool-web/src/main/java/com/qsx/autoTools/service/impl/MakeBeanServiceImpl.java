package com.qsx.autoTools.service.impl;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.qsx.autoTools.bean.Field;
import com.qsx.autoTools.enums.FieldType;
import com.qsx.autoTools.service.MakeBeanService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shuxinqin
 * DATE : 18-3-20
 * TIME : 下午2:57
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.service.impl
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Service
public class MakeBeanServiceImpl implements MakeBeanService {


    @Override
    public String makeBeanByCreateTableString(String createTable) {
        List<String> createTableList = Splitter.on("\n").trimResults().splitToList(createTable);

        Map<String, String> fieldTypeMap = new HashMap<String, String>();
        for (FieldType e : FieldType.values()) {
            fieldTypeMap.put(e.getSqlType(), e.getJavaType());
        }
        String title = getTitle(createTableList.get(0));
        List<Field> fieldList = new ArrayList<Field>();
        for (String line : createTableList) {
            if (StringUtils.isEmpty(line)){
                continue;
            }
            Field field = new Field();
            String fieldName = getFieldName(line);
            String fieldType = getFieldType(line, fieldTypeMap);
            if (!Objects.equal(fieldName, null) && !Objects.equal(fieldType, null)) {
                field.setName(fieldName);
                field.setType(fieldType);
                field.setRemark(getFieldRemark(line));
                fieldList.add(field);
            }
        }
        return makeBean(title, fieldList, getFieldRemark(createTableList.get(createTableList.size() - 1)));
    }

    private String makeBean(String title, List<Field> fieldList, String titleRemark) {
        StringBuilder bean = new StringBuilder();
        bean.append(makeRemark(titleRemark)).append("@Data\n").append("public class ").append(title).append("Model").append(" {\n");
        for (Field field : fieldList) {
            bean.append("\t/**\n\t * ").append(field.getRemark()).append("\n\t */\n");
            bean.append("\tprivate ").append(field.getType()).append(" ").append(field.getName()).append(";\n");
        }
        return bean.append("}").toString();
    }

    private String makeRemark(String remark) {
        return ("/**\n * ").concat(remark).concat("\n */\n");
    }

    private String getTitle(String line) {
        char[] title = line.toCharArray();
        int flag = 0;
        StringBuilder bean = new StringBuilder();
        for (int i = 0; i < title.length; i++) {
            if (flag == 0) {
                if (title[i] == '`') {
                    flag = 1;
                }
            } else if (flag == 1) {
                bean.append((char) (title[i] - 32));
                flag = 2;
            } else if (flag == 2) {
                if (title[i] == '`') {
                    flag = 3;
                } else if (title[i] == '_') {
                    flag = 1;
                } else {
                    bean.append(title[i]);
                }
            }
        }
        return bean.toString();
    }

    private String getFieldName(String line) {
        if (line.length() <= 0 || line.charAt(0) != '`') {
            return null;
        }
        char[] title = line.toCharArray();
        int flag = 0;
        StringBuilder bean = new StringBuilder();
        for (int i = 0; i < title.length; i++) {
            if (flag == 0) {
                if (title[i] == '`') {
                    flag = 2;
                }
            } else if (flag == 1) {
                bean.append((char) (title[i] - 32));
                flag = 2;
            } else if (flag == 2) {
                if (title[i] == '`') {
                    flag = 3;
                } else if (title[i] == '_') {
                    flag = 1;
                } else {
                    bean.append(title[i]);
                }
            }
        }
        return bean.toString();
    }

    private String getFieldType(String line, Map<String, String> fieldTypeMap) {
        List<String> itemList = Splitter.on(" ").trimResults().splitToList(line);
        String sqlType = Splitter.on("(").trimResults().splitToList(itemList.get(1)).get(0);

        return fieldTypeMap.get(sqlType);
    }

    private String getFieldRemark(String line) {
        String remark;
        try {
            List<String> itemList = Splitter.on("'").trimResults().splitToList(line);
            remark = itemList.get(itemList.size() - 2);
        } catch (Exception e) {
            remark = "";
        }
        return remark;
    }
}
