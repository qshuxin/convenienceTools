package com.qsx;

import com.csvreader.CsvWriter;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by shuxinqin
 * DATE : 18-4-11
 * TIME : 上午11:50
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/dispatcher-servlet.xml" })
public class RateTest {

    @Test
    public void Rate() throws MalformedURLException {
        String urlStr = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
        String result = "";
        BufferedReader in ;
        URL url = new URL(urlStr);
        try {
            URLConnection connection = url.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void csv() throws IOException {
        List<String> a = new ArrayList<String>();
        a.add("aaa");
        a.add("bbb");
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(),',', Charset.forName("UTF-8"));
        String[] headers = {"姓名","年龄"};
        csvWriter.writeRecord(headers);
        csvWriter.write(a.get(0));
        csvWriter.write(a.get(1));
        csvWriter.endRecord();
        csvWriter.write(a.get(1));
        csvWriter.write(a.get(0));
        csvWriter.endRecord();
        csvWriter.close();

    }

    @Test
    public void space(){
        String a = " 110918618010501 ";
        System.out.println(StringUtils.trimWhitespace(a));
        System.out.println(" " == "　");
    }

    @Test
    public void POI2() throws IOException, InvalidFormatException {
        OutputStream os = null;
        String templatePath = "cashFlowStatement_upload_template.xlsx";

        String fileDir = RateTest.class.getResource("").getFile();

        String filePath = fileDir + "b.xlsx";

        System.out.println("excel template file:" + filePath);

        FileInputStream is = new FileInputStream(filePath);
        XLSTransformer transformer = new XLSTransformer();
        Map<String, String> map = new HashMap<String, String>();
        map.put("asd", "111");
        transformer.transformXLS(is, map);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("qwer", "22222");
        Workbook workbook = transformer.transformXLS(is, map2);
        workbook.write(new FileOutputStream(fileDir + "233333.xls"));

    }

    /**
     * 设置下载excel属性
     *
     * @param response
     * @param excelFileName
     * @throws UnsupportedEncodingException
     */
    protected void setExcelProperties(HttpServletResponse response, String excelFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        excelFileName = new String(excelFileName.getBytes("utf-8"), "iso-8859-1");
        response.setHeader("Content-Disposition", "inline;filename=" + excelFileName);
    }

    @Test
    public void jinzhi(){
        System.out.println(Integer.toHexString(6418));
    }

    @Test
    public void stream() {
        List<Integer> lists = new ArrayList<Integer>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        Optional<Integer> sum = lists.stream().reduce((a, b) -> a + b);
        if (sum.isPresent()) {
            System.out.println(sum.get());
        }
        Integer sum2 = lists.stream().reduce(3, (a, b) -> a + b);
        System.out.println(sum2);

        Optional<Integer> sum3 = lists.stream().reduce((a, b) -> a * b);
        if (sum3.isPresent()) {
            System.out.println(sum3.get());
        }
        Integer sum4 = lists.stream().reduce(3, (a, b) -> a * b);
        System.out.println(sum4);

        System.out.println(lists.parallelStream().reduce(2, (a, b) -> a * b, (a, b) -> a * b));
    }
}
