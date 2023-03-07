package com.fsoft.finalproject.controller.util_controller;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ManufacturerDTO;
import com.fsoft.finalproject.dto.ProvinceDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ManufacturerEntity;
import com.fsoft.finalproject.entity.ProvinceEntity;
import com.fsoft.finalproject.repository.ManufacturerRepository;
import com.fsoft.finalproject.repository.ProductRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ImportManufacturerProduct {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired Converter converter;

    @PostMapping("import/manu-pct")
    public ResponseEntity<ResponseObject> mapReapExcelDataToDB(
            @RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheetProvince = workbook.getSheetAt(0);
        XSSFSheet worksheetDistrict = workbook.getSheetAt(1);

        importManufacturer(worksheetProvince);
        importProduct(worksheetDistrict);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(200, "Data is imported successful",null));
    }

    private void importProduct(XSSFSheet worksheet) {
//
//        // Get all rows
//        Iterator<Row> iterator = sheet.iterator();
//        while (iterator.hasNext()) {
//            Row nextRow = iterator.next();
//            if (nextRow.getRowNum() == 0) {
//                // Ignore header
//                continue;
//            }
//
//            // Get all cells
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//
//            // Read cells and set value for book object
//            Book book = new Book();
//            while (cellIterator.hasNext()) {
//                //Read cell
//                Cell cell = cellIterator.next();
//                Object cellValue = getCellValue(cell);
//                if (cellValue == null || cellValue.toString().isEmpty()) {
//                    continue;
//                }
//                // Set value for book object
//                int columnIndex = cell.getColumnIndex();
//                switch (columnIndex) {
//                    case COLUMN_INDEX_ID:
//                        book.setId(new BigDecimal((double) cellValue).intValue());
//                        break;
//                    case COLUMN_INDEX_TITLE:
//                        book.setTitle((String) getCellValue(cell));
//                        break;
//                    case COLUMN_INDEX_QUANTITY:
//                        book.setQuantity(new BigDecimal((double) cellValue).intValue());
//                        break;
//                    case COLUMN_INDEX_PRICE:
//                        book.setPrice((Double) getCellValue(cell));
//                        break;
//                    case COLUMN_INDEX_TOTAL:
//                        book.setTotalMoney((Double) getCellValue(cell));
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//            listBooks.add(book);
//        }
//
//        workbook.close();
//        inputStream.close();
//
//        return listBooks;
    }

    private void importManufacturer(XSSFSheet worksheet) {
        List<ManufacturerDTO> manufacturerDTOs = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            ManufacturerDTO tempManufacturer = new ManufacturerDTO();
            //System.out.println("---province----" +i);
            XSSFRow row = worksheet.getRow(i);

            tempManufacturer.setId((long) row.getCell(0).getNumericCellValue());
            tempManufacturer.setName(row.getCell(1).getStringCellValue());
            manufacturerDTOs.add(tempManufacturer);
        }

        List<ManufacturerEntity> manufacturerEntity = new ArrayList<>();

        for (ManufacturerDTO ManufacturerDTO : manufacturerDTOs) {
            manufacturerEntity.add(converter.toEntity(ManufacturerDTO));
        }

        manufacturerRepository.saveAll(manufacturerEntity);
    }

}
