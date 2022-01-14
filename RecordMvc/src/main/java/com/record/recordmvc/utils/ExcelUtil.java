package com.record.recordmvc.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;

import java.io.*;

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
    public void write (String line ){
        String path ="";

        try {
            BufferedWriter bw =new BufferedWriter(new FileWriter(path));
            bw.write(line);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试方法
    public static void main(String[] args){
        System.out.println("开始测试");
        ExcelUtil sheet1 =new ExcelUtil("E:/test.xlsx", "Sheet1");

        //获取第二行第4列
        String cell2 = sheet1.getExcelDateByIndex(1, 0);
        System.out.println(cell2);
    }


}
