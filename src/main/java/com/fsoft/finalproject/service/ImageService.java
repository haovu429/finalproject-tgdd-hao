/** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 3:43:18 AM */
package com.fsoft.finalproject.service;

import com.fsoft.finalproject.exception.CustomException;

import com.fsoft.finalproject.dto.ImageDTO;
import com.fsoft.finalproject.dto.ResponseObject;

public interface ImageService {
  ResponseObject getOne(long id) throws CustomException;

  ResponseObject save(ImageDTO image) throws CustomException;

  ResponseObject update(ImageDTO image) throws CustomException;

  ResponseObject delete(long id) throws CustomException;

  ResponseObject deleteByProductId(Long id) throws CustomException;

  ResponseObject getAll();

  ResponseObject getImageByProduct(long productId);

  void deleteImageByProduct(long productId);
}
