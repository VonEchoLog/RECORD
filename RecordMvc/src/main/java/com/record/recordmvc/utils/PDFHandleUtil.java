package com.record.recordmvc.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @Author: Echo
 * @Date: 2025/12/4 15:37
 * @Description: word 与PDF 处理
 */
public class PDFHandleUtil {
    public static void main(String[] args) {
        PDFHandleUtil test =new PDFHandleUtil();
        String pdfPath ="C:/execute/ims.pdf";
        String output ="C:/execute/";
        test.convertPdfToImages(pdfPath,output,"png",300);
    }

    public static void convertPdfToImages(String pdfPath, String outputDir,
                                          String imageFormat, int dpi) {
        PDDocument document = null;
        try {
            // 加载PDF文档
            document = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(document);

            // 创建输出目录
            File outputDirectory = new File(outputDir);
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            // 逐页渲染为图片
            for (int page = 0; page < document.getNumberOfPages(); page++) {
                // 渲染页面为BufferedImage
                BufferedImage image = renderer.renderImageWithDPI(page, dpi);

                // 生成输出文件名
                String outputPath = String.format("%s/page_%d.%s",
                        outputDir, page + 1, imageFormat);

                // 保存图片
                ImageIO.write(image, imageFormat, new File(outputPath));
                System.out.println("已生成: " + outputPath);
            }

            System.out.println("转换完成，共转换 " + document.getNumberOfPages() + " 页");

        } catch (IOException e) {
            System.err.println("转换失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        /**
         * 转换Word文档为PDF（支持.docx和.doc）
         * @param wordPath Word文件路径
         * @param pdfPath PDF输出路径
         */

        public static void convertWordToPdf(String wordPath, String pdfPath) {
            if (wordPath.toLowerCase().endsWith(".docx")) {
                convertDocxToPdf(wordPath, pdfPath);
            } else if (wordPath.toLowerCase().endsWith(".doc")) {
                convertDocToPdf(wordPath, pdfPath);
            } else {
                throw new IllegalArgumentException("不支持的文件格式，仅支持.doc和.docx");
            }
        }

        /**
         * 转换.docx为PDF
         */
        private static void convertDocxToPdf(String docxPath, String pdfPath) {
            try (FileInputStream fis = new FileInputStream(docxPath);
                 XWPFDocument document = new XWPFDocument(fis)) {

                // 创建PDF文档
                Document pdfDoc = new Document();
                PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfPath));
                pdfDoc.open();

                // 设置中文字体（需要系统中文字体或指定字体文件）
                String fontPath = "C:/Windows/Fonts/simsun.ttc"; // 宋体
                BaseFont baseFont = BaseFont.createFont(fontPath + ",1",
                        BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font chineseFont = new Font(baseFont, 12, Font.NORMAL);

                // 处理文档内容
                for (XWPFParagraph paragraph : document.getParagraphs()) {
                    String text = paragraph.getText();
                    if (text != null && !text.trim().isEmpty()) {
                        pdfDoc.add(new Paragraph(text, chineseFont));
                    }
                }

                // 处理表格
                for (XWPFTable table : document.getTables()) {
                    for (XWPFTableRow row : table.getRows()) {
                        StringBuilder rowText = new StringBuilder();
                        for (XWPFTableCell cell : row.getTableCells()) {
                            rowText.append(cell.getText()).append("\t");
                        }
                        pdfDoc.add(new Paragraph(rowText.toString(), chineseFont));
                    }
                    pdfDoc.add(new Paragraph(" ")); // 表格后空行
                }

                // 处理图片（简化处理）
                // 注意：图片处理需要更复杂的逻辑

                pdfDoc.close();
                System.out.println("转换完成: " + pdfPath);

            } catch (Exception e) {
                System.err.println("转换失败: " + e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * 转换.doc为PDF
         */
        private static void convertDocToPdf(String docPath, String pdfPath) {
            try (FileInputStream fis = new FileInputStream(docPath);
                 HWPFDocument document = new HWPFDocument(fis)) {

                WordExtractor extractor = new WordExtractor(document);
                String text = extractor.getText();

                Document pdfDoc = new Document();
                PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfPath));
                pdfDoc.open();

                // 设置中文字体
                String fontPath = "C:/Windows/Fonts/simsun.ttc";
                BaseFont baseFont = BaseFont.createFont(fontPath + ",1",
                        BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font chineseFont = new Font(baseFont, 12, Font.NORMAL);

                // 添加文本到PDF
                pdfDoc.add(new Paragraph(text, chineseFont));

                pdfDoc.close();
                System.out.println("转换完成: " + pdfPath);

            } catch (Exception e) {
                System.err.println("转换失败: " + e.getMessage());
                e.printStackTrace();
            }
        }

}
