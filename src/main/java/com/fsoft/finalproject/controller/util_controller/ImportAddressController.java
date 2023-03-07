package com.fsoft.finalproject.controller.util_controller;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.DistrictDTO;
import com.fsoft.finalproject.dto.ProvinceDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.WardDTO;
import com.fsoft.finalproject.entity.DistrictEntity;
import com.fsoft.finalproject.entity.ProvinceEntity;
import com.fsoft.finalproject.entity.WardEntity;
import com.fsoft.finalproject.repository.DistrictRepository;
import com.fsoft.finalproject.repository.ProvinceRepository;
import com.fsoft.finalproject.repository.WardRepository;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ImportAddressController {

  @Autowired private ProvinceRepository provinceRepository;

  @Autowired private DistrictRepository districtRepository;

  @Autowired private WardRepository wardRepository;

  @Autowired
  private Converter converter;

  @PostMapping("import")
  public ResponseEntity<ResponseObject> mapReapExcelDataToDB(
      @RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
    XSSFSheet worksheetProvince = workbook.getSheetAt(0);
    XSSFSheet worksheetDistrict = workbook.getSheetAt(1);
    XSSFSheet worksheetWard = workbook.getSheetAt(2);

    importProvince(worksheetProvince);
    importDistrict(worksheetDistrict);
    importWard(worksheetWard);

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ResponseObject(200, "Data is imported successful",null));
  }

  public void importProvince(XSSFSheet worksheet){
    List<ProvinceDTO> provinceDTOs = new ArrayList<>();
    for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
      ProvinceDTO tempProvince = new ProvinceDTO();
      //System.out.println("---province----" +i);
      XSSFRow row = worksheet.getRow(i);

      tempProvince.setId((long) row.getCell(0).getNumericCellValue());
      tempProvince.setName(row.getCell(1).getStringCellValue());
      provinceDTOs.add(tempProvince);
    }

    List<ProvinceEntity> provinceEntities = new ArrayList<>();

    for (ProvinceDTO provinceDTO : provinceDTOs) {
      provinceEntities.add(converter.toEntity(provinceDTO));
    }

    provinceRepository.saveAll(provinceEntities);
  }

  public void importDistrict(XSSFSheet worksheet){
    List<DistrictDTO> districtDTOs = new ArrayList<>();
    for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
      DistrictDTO tempDistrict = new DistrictDTO();
      //System.out.println("---district----" +i);
      XSSFRow row = worksheet.getRow(i);

      tempDistrict.setId((long) row.getCell(0).getNumericCellValue());
      tempDistrict.setName(row.getCell(1).getStringCellValue());
      tempDistrict.setProvinceId((long) row.getCell(2).getNumericCellValue());
      districtDTOs.add(tempDistrict);
    }

    List<DistrictEntity> districtEntities = new ArrayList<>();

    for (DistrictDTO districtDTO : districtDTOs) {

      districtEntities.add(converter.toEntity(districtDTO));
      //districtRepository.save(converter.toEntity(districtDTO));
    }
    districtRepository.saveAll(districtEntities);
  }

  public void importWard(XSSFSheet worksheet){
    List<WardDTO> wardDTOs = new ArrayList<>();
    for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
      WardDTO tempWard = new WardDTO();
      //System.out.println("---ward----" +i);
      XSSFRow row = worksheet.getRow(i);
      tempWard.setId((long) row.getCell(0).getNumericCellValue());
      tempWard.setName(row.getCell(1).getStringCellValue());
      tempWard.setDistrictId((long) row.getCell(2).getNumericCellValue());
      wardDTOs.add(tempWard);
    }

    List<WardEntity> wardEntities = new ArrayList<>();

    for (WardDTO wardDTO : wardDTOs) {
      wardEntities.add(converter.toEntity(wardDTO));
    }

    wardRepository.saveAll(wardEntities);
  }
}
