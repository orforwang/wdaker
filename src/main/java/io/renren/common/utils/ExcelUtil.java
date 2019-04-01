package io.renren.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by 王云龙 on 2018/8/24.
 * Modular  excel导出工具类
 * Description:
 */
public class ExcelUtil {
    /**
     * 保存Workbook到输出流
     * 单页数据导出，不涉及跨行跨列
     * @param titleList  表头
     * @param dataList  数据集（行List<单元格List<>>）
     * @param excelname  excel名字
     * @return
     */
    public static boolean exportWorkbook(Object[] titleList, List<List<Object>> dataList, String excelname, OutputStream os) throws IOException {
        try {
            //创建工作簿
            XSSFWorkbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet(excelname);
            if (titleList != null && titleList.length > 0){
                Row row = sheet.createRow(0);
                for (int k = 0; k < titleList.length; k++){
                    Cell cell = row.createCell(k);
                    cell.setCellStyle(titleStyle(wb));
                    cell.setCellValue(Util.toString(titleList[k]));
                }
            }

            //写入数据
            if (!dataList.isEmpty()){
                for(int i=0;i<dataList.size();i++){
                    Row row = sheet.createRow(i+1);
                    List<Object> rowData = dataList.get(i);
                    if (!rowData.isEmpty()) {
                        for (int j = 0; j < rowData.size(); j++) {
                            Cell cell = row.createCell(j);
                            cell.setCellStyle(normalStyle(wb));
                            cell.setCellValue(Util.toString(rowData.get(j)));
                        }
                    }
                }
            }
            wb.write(os);
        }catch (IOException ioe){
            ioe.printStackTrace();
            return false;
        }
        return true;
    }

    /***
     * 表头样式
     * @param wb
     * @return
     */
    private static CellStyle titleStyle(XSSFWorkbook wb){
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        titleStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        titleStyle.setRightBorderColor(HSSFColor.BLACK.index);
        titleStyle.setTopBorderColor(HSSFColor.BLACK.index);
        Font font = wb.createFont();
        font.setFontHeightInPoints((short)12);    // 将字体大小设置为22px
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); //字体加粗
        titleStyle.setFont(font);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    /***
     * 默认样式
     * @param wb
     * @return
     */
    private static CellStyle normalStyle(XSSFWorkbook wb){
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        cellStyle.setWrapText(true);                    // 设置单元格内容是否自动换行
        return cellStyle;
    }

}
