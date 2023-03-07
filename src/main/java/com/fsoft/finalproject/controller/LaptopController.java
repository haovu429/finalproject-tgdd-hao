package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.CreateLapTopDTO;
import com.fsoft.finalproject.dto.LaptopDTO;
import com.fsoft.finalproject.dto.ProductLaptopFilterDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.LaptopService;
import com.fsoft.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class LaptopController {
  @Autowired LaptopService laptopService;

  @Autowired ProductService productService;

  @GetMapping("laptops")
  @ResponseBody
  public ResponseObject findAll(@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    return laptopService.findAll(pageable);
  }

  @DeleteMapping("laptop/{id}")
  public ResponseObject delete(@PathVariable("id") long id) throws CustomException {
    return laptopService.deleteById(id);
  }

  @PutMapping("laptop/{id}")
  public ResponseObject updateLaptop(
      @PathVariable("id") long id,
      @Validated @RequestBody LaptopDTO dto,
      BindingResult bindingResult)
      throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return laptopService.updateLaptop(dto, id);
  }

  @PostMapping("laptop")
  public ResponseObject saveLaptop(
      @Validated @RequestBody CreateLapTopDTO lapTopDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return laptopService.savelaptop(lapTopDTO);
  }

  @PostMapping("/laptop/filter")
  public ResponseObject filterLaptop(@RequestBody ProductLaptopFilterDTO filterDTO) {
    return productService.filterLaptop(
        filterDTO.getManufacturer(),
        filterDTO.getMinPrice(),
        filterDTO.getMaxPrice(),
        filterDTO.getScreen(),
        filterDTO.getCpu(),
        filterDTO.getRam(),
        filterDTO.getHardDrive(),
        filterDTO.getOther(),
        filterDTO.getNoOfRecords(),
        filterDTO.getPageIndex(),
        filterDTO.isSortByPrice());
  }
}
