package com.fsoft.finalproject.statistic;

import com.fsoft.finalproject.response.ResponseProduct;
import com.fsoft.finalproject.response.ResponseTurnover;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheetQuantity;
    private XSSFSheet sheetTurnover;
    private List<ResponseProduct> listProduct;
    private List<ResponseTurnover> listTurnover;

    public CreateExcel(List<ResponseProduct> listResponse,List<ResponseTurnover> listTurnover) {
        this.listProduct = listResponse;
        this.listTurnover= listTurnover;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheetQuantity = workbook.createSheet("Quantity");
        sheetTurnover = workbook.createSheet("Turnover");

        Row rowSheetQuantity = sheetQuantity.createRow(0);
        Row rowSheetTurnover = sheetTurnover.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(rowSheetQuantity, 0, "ID Store", style);
        createCell(rowSheetQuantity, 1, "Province", style);
        createCell(rowSheetQuantity, 2, "District", style);
        createCell(rowSheetQuantity, 3, "Ward", style);
        createCell(rowSheetQuantity, 4, "Product Code", style);
        createCell(rowSheetQuantity, 5, "Product Name", style);
        createCell(rowSheetQuantity, 6, "Quantity Sold", style);
        createCell(rowSheetQuantity, 7, "Quantity Inventory", style);
        //========================
        createCell(rowSheetTurnover, 0, "ID Store", style);
        createCell(rowSheetTurnover, 1, "Province", style);
        createCell(rowSheetTurnover, 2, "District", style);
        createCell(rowSheetTurnover, 3, "Ward", style);
        createCell(rowSheetTurnover, 4, "Turnover", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheetQuantity.autoSizeColumn(columnCount);
        sheetTurnover.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;


        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        long storeId=0;
        for (ResponseProduct response : listProduct) {
            Row row = sheetQuantity.createRow(rowCount++);
            if(storeId == response.getIdStore()){
                int columnCount = 4;

//                createCell(row, columnCount++, String.valueOf(response.getIdStore()), style);
//                createCell(row, columnCount++, response.getProvince(), style);
//                createCell(row, columnCount++, response.getDistrict(), style);
//                createCell(row, columnCount++, response.getWard(), style);
                createCell(row, columnCount++, response.getProductCode(), style);
                createCell(row, columnCount++, response.getProductName(), style);
                createCell(row, columnCount++, String.valueOf(response.getQuantity()), style);
                createCell(row, columnCount++, String.valueOf(response.getInventory()), style);
            }
            else{
                int columnCount = 0;

                createCell(row, columnCount++, String.valueOf(response.getIdStore()), style);
                createCell(row, columnCount++, response.getProvince(), style);
                createCell(row, columnCount++, response.getDistrict(), style);
                createCell(row, columnCount++, response.getWard(), style);
                createCell(row, columnCount++, response.getProductCode(), style);
                createCell(row, columnCount++, response.getProductName(), style);
                createCell(row, columnCount++, String.valueOf(response.getQuantity()), style);
                createCell(row, columnCount++, String.valueOf(response.getInventory()), style);
                storeId = response.getIdStore();
            }


        }
        //===========================
        //Sheet 2====================
        int rowCount2 = 1;
        for (ResponseTurnover response : listTurnover) {
            Row row = sheetTurnover.createRow(rowCount2++);

            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(response.getIdStore()), style);
            createCell(row, columnCount++, response.getProvince(), style);
            createCell(row, columnCount++, response.getDistrict(), style);
            createCell(row, columnCount++, response.getWard(), style);
            createCell(row, columnCount, String.valueOf(response.getTotal()), style);

        }

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
