package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.PromotionDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.entity.PromotionEntity;
import com.fsoft.finalproject.repository.ProductRepository;
import com.fsoft.finalproject.repository.PromotionRepository;
import com.fsoft.finalproject.service.PromotionService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

  @Autowired private PromotionRepository promotionRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private Converter converter;

  ModelMapper mapper = new ModelMapper();

  @Override
  public ResponseObject save(PromotionDTO promotion) {
    PromotionEntity promotionEntity = converter.toEntity(promotion);

    promotionEntity = promotionRepository.save(promotionEntity);

    return new ResponseObject(
        HttpStatus.OK.value(), "Create promotion successfully", converter.toDTO(promotionEntity));
  }

  @Override
  public ResponseObject updateProducts(long id, List<String> codes) {
    PromotionEntity promotionEntity =
        promotionRepository
            .findById(id)
            .map(
                promotion -> {
                  for (String i : codes) {
                    i = i.replace("\"", "");
                    ProductEntity proEntity = productRepository.getProductByCode(i);
                    // System.out.println(proEntity.getProductName());
                    if (proEntity != null) {
                      if (!promotion.getProductEntities().contains(proEntity)) {
                        promotion.getProductEntities().add(proEntity);
                        proEntity.getPromotionEntities().add(promotion);
                      }
                    } else {
                      System.out.println("NULL");
                    }
                  }
                  return promotionRepository.save(promotion);
                })
            .orElse(null);

    if (promotionEntity != null) {
      return new ResponseObject(
          HttpStatus.OK.value(), "Update promotion successfully", converter.toDTO(promotionEntity));
    } else {
      throw new RuntimeException("Promotion not found with id " + id);
    }
  }

  @Override
  public ResponseObject update(long id, PromotionDTO promotionDTO) {
    PromotionEntity newPromotion = converter.toEntity(promotionDTO);
    PromotionEntity promotionEntity =
        promotionRepository
            .findById(id)
            .map(
                promotion -> {
                  promotion.setDescription(newPromotion.getDescription());
                  promotion.setDiscount(newPromotion.getDiscount());
                  promotion.setEndDate(newPromotion.getEndDate());
                  promotion.setStartDay(newPromotion.getStartDay());
                  promotion.setType(newPromotion.getType());
                  return promotionRepository.save(promotion);
                })
            .orElse(null);

    if (promotionEntity != null) {
      return new ResponseObject(
          HttpStatus.OK.value(), "Update promotion successfully", converter.toDTO(promotionEntity));
    } else {
      throw new RuntimeException("Promotion not found with id " + id);
    }
  }

  @Override
  public ResponseObject deletePromotion(long id) {
    PromotionEntity promotion =
        promotionRepository
            .findById(id)
            .map(
                pro -> {
                  System.out.println(pro.getProductEntities().size());
                  for (int i = 0; i < pro.getProductEntities().size(); i++) {
                    ProductEntity u = pro.getProductEntities().get(i);
                    pro.removeProduct(u);
                    i--;
                  }
                  return promotionRepository.save(pro);
                })
            .orElse(null);
    if (promotion != null) {
      promotionRepository.delete(promotion);
      return new ResponseObject(HttpStatus.OK.value(), "Delete promotion successfully", "");
    } else {
      throw new RuntimeException("Promotion not found with id " + id);
    }
  }

  @Override
  public ResponseObject deletePromotionByProduct(long id, String product) {
    boolean exists = promotionRepository.existsById(id);
    if (exists) {
      ProductEntity productQuery = productRepository.getProductByCode(product);
      if (productQuery != null) {
        Optional<PromotionEntity> promotion =
            promotionRepository
                .findById(id)
                .map(
                    pro -> {
                      List<ProductEntity> products = pro.getProductEntities();
                      if (products.contains(productQuery)) {
                        pro.removeProduct(productQuery);
                        return promotionRepository.save(pro);
                      } else {
                        return null;
                      }
                    });

        if (promotion.isPresent()) {
          return new ResponseObject(HttpStatus.OK.value(), "Delete promotion  successfully", "");
        } else {
          throw new RuntimeException("Promotion not found with id " + id);
        }
      } else {
        throw new RuntimeException("Product not found with code " + product);
      }
    } else {
      throw new RuntimeException("Promotion not found with id " + id);
    }
  }

  @Override
  public ResponseObject getProductByPromotion(long id) {
    Optional<PromotionEntity> promotion = promotionRepository.findById(id);
    if (promotion.isPresent()) {
      List<ProductEntity> products = promotion.get().getProductEntities();
      JSONArray ja = new JSONArray();
      for (ProductEntity i : products) {
        JSONObject jo = new JSONObject();
        jo.put("id", i.getId());
        jo.put("code", i.getProductCode());
        jo.put("name", i.getProductName());
        ja.put(jo);
      }
      JSONObject result = new JSONObject();
      result.put("product", ja);

      return new ResponseObject(
          HttpStatus.OK.value(), "Get product by promotion successfully", result.toMap());

    } else {
      throw new RuntimeException("Promotion not found with id " + id);
    }
  }

  @Override
  public ResponseObject getAll() {
    List<PromotionEntity> promotions = promotionRepository.findAll();
    List<PromotionDTO> listDTO = new ArrayList<>();
    for (PromotionEntity i : promotions) {
      // PromotionDTO pro = promotionConverter.toDTO(i);
      PromotionDTO pro = mapper.map(i, PromotionDTO.class);
      listDTO.add(pro);
    }
    return new ResponseObject(HttpStatus.OK.value(), "Get all promotion successfully", listDTO);
  }

  @Override
  public ResponseObject getPromotionByPromoCode(String promoCode) {
    PromotionEntity promotion =
        promotionRepository.findByPromoCodeAndStartDateAndEndDate(promoCode);
    if (promotion == null) {
      throw new RuntimeException("Promotion not valid with promoCode " + promoCode);
    }
    return new ResponseObject(
        HttpStatus.OK.value(),
        "Get promotion by promoCode successfully",
        converter.toDTO(promotion));
  }

  @Override
  public ResponseObject getPromotionByProductId(long id) {
    ProductEntity product = productRepository.findOneById(id);
    if (product != null) {
      List<PromotionDTO> promotionDTOS = new ArrayList<>();
      for (PromotionEntity i : product.getPromotionEntities()) {
        PromotionDTO pro = converter.toDTO(i);
        promotionDTOS.add(pro);
      }
      return new ResponseObject(
          HttpStatus.OK.value(), "Get product by promotion successfully", promotionDTOS);
    } else {
      throw new RuntimeException("Promotion not found with id " + id);
    }
  }
}
