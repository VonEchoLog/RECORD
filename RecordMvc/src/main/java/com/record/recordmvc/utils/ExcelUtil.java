package com.record.recordmvc.utils;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  FBY
 * @date  2022-1-14
 * @version 1.0
 * @Description:
 */

public class ExcelUtil {

    private XSSFSheet sheet;

    // 读取Excel文件内容
    // 初始化sheet

    ExcelUtil(String filePath, String fileName){
        FileInputStream fis = null;
        try {
            fis =new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fis);
            sheet = sheets.getSheet(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 根据行列索引获取单元格数据
    */
    public String getExcelDateByIndex(int row,int column){
        XSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }

    //打印excel数据
    public void readExcelData() {
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for (int j = 0; j < columns; j++) {
                String cell = row.getCell(j).toString();
                System.out.println(cell);
            }
        }
    }

    public static Map<String, List>  filling(ExcelUtil sheet1){
        //获取循环获取单元格内数据
        int rows=sheet1.sheet.getPhysicalNumberOfRows();
        Map<String ,List> map=new HashMap<String ,List>();

        String[] empty =new String[5];

        for(int i=1;i<rows;i++){
            XSSFRow row=sheet1.sheet.getRow(i);
            int columns=row.getPhysicalNumberOfCells();
            List columnCells =new ArrayList();
            for(int j=0;j<columns;j++){

                String cell =sheet1.getExcelDateByIndex(i,j);
                columnCells.add(cell);
            }
            String key =String.valueOf(i);
            map.put(key,columnCells);


        }

        return map;
    }
    public static void write (Map<String,List> map){
        String path ="E:/testWirte.txt";
        String line ="";
        try {
            FileWriter fw =new FileWriter(path);
            BufferedWriter bw =new BufferedWriter(fw);
            for(int i=1;i<= map.size();i++){
                List<String>  array =map.get(String.valueOf(i));
                line ="insert into 表A (A_COLUME,B_COLUME,C_COLUME,D_COLUME,E_COLUME) VALUES (";
                line +=array.get(0)+","+array.get(1)+","+array.get(2)+","+array.get(3)+","+array.get(4)+");";
                bw.write(line);
                bw.newLine();
            }

            bw.flush();
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //测试方法
    public static void main(String[] args){
        System.out.println("开始测试");
        ExcelUtil sheet1 =new ExcelUtil("E:/test.xlsx", "Sheet1");
        String line="";

        Map<String,List> map =filling(sheet1);

        write(map);

        System.out.println("执行完毕");
    }


}
