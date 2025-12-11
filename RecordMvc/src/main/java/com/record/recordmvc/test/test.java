package com.record.recordmvc.test;

import org.apache.poi.xwpf.usermodel.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * 遍历文档中的占位符,并替换
 *
 */
public class test {
    public static void main(String[] args) {

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = df.format(ldt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    }

    /** 替换表格中的占位符 */
    private void replaceTables(XWPFDocument document, Map<String, Object> dataMap) {
        // 遍历所有表格
        for (XWPFTable table : document.getTables()) {
            // 获取第一行的第一个单元格，检查是否包含 "{{table:xxx}}" 标识
            XWPFTableRow firstRow = table.getRow(1);
            if (firstRow != null && firstRow.getCell(0) != null) {
                String firstCellText = firstRow.getCell(0).getText();
                if (firstCellText.startsWith("{{table:")) {  // 提取标识符的键，例如 "task"
                    String key = firstCellText.substring(8, firstCellText.length() - 2).trim();
                    List<Map<String, Object>> tableData = (List<Map<String, Object>>) dataMap.get(key);
                    if (tableData != null) {
                        // 填充数据
                        for (Map<String, Object> rowData : tableData) {
                            XWPFTableRow newRow = table.createRow();
                            int cellIndex = 0;
                            for (XWPFTableCell cell : newRow.getTableCells()) {
                                // 获取对应的键名并填充值
                                if(cellIndex==0){
                                    cell.setText(String.valueOf(table.getNumberOfRows()-2));
                                    cellIndex++;
                                }else{
                                    String placeholder = table.getRow(1).getCell(cellIndex).getText();
                                    String value = rowData.getOrDefault(placeholder.replace("${", "").replace("}", ""), "").toString();
                                    cell.setText(value);
                                    cellIndex++;
                                }
                            }
                        }
                        // 删除模板中的占位符行
                        table.removeRow(1);
                    }
                }
            }
        }
    }


    /** 替换段落中的占位符 */
    private void replaceTextInParagraph(XWPFDocument document, Map<String, Object> dataMap) {
        // 遍历所有段落
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(run.getTextPosition());
                if (text != null) {
                    // 替换文本中的占位符
                    String replacedText = replaceText(text, dataMap);
                    run.setText(replacedText, 0);
                }
            }
        }
    }

    /** 替换文本中的占位符 */
    private String replaceText(String text, Map<String, Object> dataMap) {
        String result = text;
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            result = result.replace(placeholder, entry.getValue().toString());
        }
        return result;
    }



}
