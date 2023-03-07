/** Hao Vu - haovu961@gmail.com - Aug 1, 2022 - 11:42:00 PM */
package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

  ResponseObject getOne(long id);

  Optional<ProductDTO> save(ProductDTO productDTO);

  ResponseObject update(ProductDTO productDTO);

  ResponseObject delete(long id);

  ResponseObject deleteByProductCode(String productCode);

  ResponseObject getAll();

  // ResponseObject getByPromotionId(long promotionId);

  ResponseObject getByStoreId(long storeId);

  ResponseObject getProductRateGreater(double rate);

  ResponseObject getProductRateLess(double rate);

  ResponseObject getProductPriceGreater(int price);

  ResponseObject getProductPriceLess(int price);

  ResponseObject getProductPriceBetween(int start, int end);

  ResponseObject getByManufacturer(long id);

  ResponseObject getByManufacturer(String name);

  ResponseObject getProductContainName(String name);

  ResponseObject getProductAmountByManufacturer(long id);

  ResponseObject getProductAmountByManufacturer(String name);

  ResponseObject getTotalAmountById(long id);

  ResponseObject getProductRate(long id);

  ResponseObject filterLaptop(
      List<Long> manufacturer,
      Double minPrice,
      Double maxPrice,
      List<String> screen,
      List<String> cpu,
      List<String> ram,
      List<String> hardDrive,
      List<String> other,
      int noOfRecords,
      int pageIndex,
      boolean sortByPrice);

  ResponseObject filterPhone(
      List<Long> manufacturer,
      Double minPrice,
      Double maxPrice,
      List<String> screen,
      List<String> cpu,
      List<String> ram,
      List<String> memory,
      List<String> other,
      int noOfRecords,
      int pageIndex,
      boolean sortByPrice);

  ResponseObject getConfigurationProduct(long id);

  ResponseObject getProductsByPromotion(long id, Optional<Integer> page);

  ResponseObject getProductsbyManufacture(long id, Optional<Integer> page);

  ResponseObject getProductsByCategory(String name, Optional<Integer> page);

  ResponseObject getTopSales();

  ResponseObject filterProduct(Map<String, String> conditions);

  ResponseObject getListProductByListItemInCart(List<ItemInCart> listItem);

  ResponseObject addListPromotionForProduct(List<String> listPromotionCode, long productId);

}
