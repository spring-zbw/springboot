package com.zbw.springboot.util;

/**
 * Created by 郑博文 on 2019/11/25.
 */

import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.zbw.springboot.pojo.ExportUser;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Excel工具类
 */
public class ExcelUtil {


    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private static PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();

    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    /**
     *
     * @Description: 根据国家代码和手机号  手机归属地
     * @date 2015-7-13 上午11:33:18
     * @param @param phoneNumber
     * @param @param countryCode
     * @param @return    参数
     * @throws
     */
    public static  String getGeo(String phoneNumber, String countryCode){

        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);

        PhoneNumber pn = new PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);
        return geocoder.getDescriptionForNumber(pn, Locale.CHINESE);
    }
    /**
     * Excel表格导出
     * @param response HttpServletResponse对象
     * @param excelData Excel表格的数据，封装为List<List<String>>
     * @param sheetName sheet的名字
     * @param fileName 导出Excel的文件名
     * @param columnWidth Excel表格的宽度，建议为15
     * @throws IOException 抛IO异常
     */
    public static void exportExcel(HttpServletResponse response,
                                   List<ExportUser> excelData,
                                   String sheetName,
                                   String fileName,
                                   int columnWidth) throws IOException {

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);
        List<String> head = new ArrayList<>();
        head.add("约见号");
        head.add("姓名");
        head.add("注册时间");
        head.add("手机");
        head.add("手机归属地");
        head.add("用户类别");
        head.add("微信凭证");

        //写入List<List<String>>中的数据
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < head.size(); i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(head.get(i));
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }
        int rowIndex = 1;
        HSSFRow row = sheet.createRow(1);
        for(ExportUser data : excelData){
            //创建一个row行，然后自增1
            HSSFRow rows = sheet.createRow(rowIndex++);
            //遍历添加本行数据
            List<String> list=new ArrayList<>();
            list.add(data.getId().toString());
            list.add(data.getUserName());
            list.add(data.getRegisterDate());
            list.add(data.getMobile());
            if(data.getMobile()!= null&&!"".equals(data.getMobile().trim())){
                String geo = getGeo(data.getMobile(), "86");
                list.add(geo);
            }else {
                list.add("");
            }
            if (data.getMobile()!= null&&!"".equals(data.getMobile().trim())) {
                list.add("正常用户");
            }else {
                list.add("微信用户");
            }
            list.add((data.getWechatCredentials()));
            for (int i = 0; i <list.size(); i++) {
                //创建一个单元格
                HSSFCell cell = rows.createCell(i);

                //创建一个内容对象
                HSSFRichTextString text = new HSSFRichTextString(list.get(i));

                //将内容对象的文字内容写入到单元格中
                cell.setCellValue(text);
            }
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }

}
