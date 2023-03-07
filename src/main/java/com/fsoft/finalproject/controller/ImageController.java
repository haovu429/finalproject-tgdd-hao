/** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 5:22:35 AM */
package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.finalproject.dto.ImageDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.ImageService;

@RestController
@RequestMapping(path = "/api/v1")
public class ImageController {

  @Autowired private ImageService imageService;

  @GetMapping("/image")
  ResponseObject getAll() {
    return imageService.getAll();
  }

  @GetMapping("/image/{id}")
  ResponseObject getById(@PathVariable Long id) throws CustomException {
    return imageService.getOne(id);
  }

  @GetMapping("/image/by-product-id/{id}")
  ResponseObject getByProductId(@PathVariable Long id) {
    return imageService.getImageByProduct(id);
  }

  @PostMapping("/image/add")
  ResponseObject save(@Validated @RequestBody ImageDTO image, BindingResult bindingResult)
      throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return imageService.save(image);
  }

  @PutMapping("/image/update")
  ResponseObject update(@Validated @RequestBody ImageDTO image, BindingResult bindingResult)
      throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return imageService.update(image);
  }

  @DeleteMapping("/image/delete/by-id/{id}")
  public ResponseObject delete(@PathVariable Long id) throws CustomException {
    return imageService.delete(id);
  }

  @DeleteMapping("/image/delete/by-product-id/{id}")
  public ResponseObject deleteByProductId(@PathVariable Long id) throws CustomException {
    return imageService.deleteByProductId(id);
  }
}
