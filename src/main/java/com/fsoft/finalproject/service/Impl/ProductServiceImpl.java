package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;

import com.fsoft.finalproject.dto.*;

import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ManufacturerEntity;

import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.entity.PromotionEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.model.TableQuery;
import com.fsoft.finalproject.repository.ManufacturerRepository;
import com.fsoft.finalproject.repository.ProductRepository;
import com.fsoft.finalproject.repository.PromotionRepository;
import com.fsoft.finalproject.service.ProductService;
import com.fsoft.finalproject.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.function.Function;

import java.util.Set;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private ProductRepository productRepository;

  @Autowired private Converter converter;

  @Autowired private ManufacturerRepository manufacturerRepository;

  @Autowired private PromotionRepository promotionRepository;

  @PersistenceContext public EntityManager em;

  @Override
  public ResponseObject getOne(long id) {
    boolean exists = productRepository.existsById(id);

    if (exists) {
      ProductEntity product = productRepository.getReferenceById(id);

      return new ResponseObject(200, "Info of product", converter.toDTO(product));
    }

    throw new CustomException("Cannot find product with id = " + id);
  }

  @Override
  public Optional<ProductDTO> save(ProductDTO productDTO) {
    // Check name exists
    if (checkProductCodeExists(productDTO.getProductCode(), null)) {
      throw new CustomException("Product code is existed");
    }

    // casting provinceDTO to ProvinceEntity
    ProductEntity productEntity = converter.toEntity(productDTO);

    // save province
    productEntity.setId(0);
    productRepository.save(productEntity);

    Optional<ProductDTO> result = Optional.of(converter.toDTO(productEntity));

    // return add Province success
    return result;
  }

  public boolean checkSave(ProductDTO productDTO) {
    // Check name exists
    if (checkProductCodeExists(productDTO.getProductCode(), null)) {
      return false;
    }
    return true;
  }

  @Override
  public ResponseObject update(ProductDTO productDTO) {
    // Check name exists
    if (checkProductCodeExists(productDTO.getProductCode(), Optional.of(productDTO.getId()))) {
      throw new CustomException("Product code is already exists");
    }

    // Check manufacturer exists
    if (!manufacturerRepository.existsById(productDTO.getManufacturerId())) {
      throw new CustomException("Manufacturer isn't exists");
    }

    boolean exists = productRepository.existsById(productDTO.getId());

    if (exists) {
      ProductEntity product = converter.toEntity(productDTO);

      productRepository.save(product);
      return new ResponseObject(converter.toDTO(product));
    }

    throw new CustomException("Cannot find product with id = " + productDTO.getId());
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = productRepository.existsById(id);
    if (exists) {
      productRepository.deleteById(id);
      return new ResponseObject(200, "Delete product Successfully", null);
    }
    throw new CustomException("Cannot find product with id =" + id);
  }

  @Override
  public ResponseObject deleteByProductCode(String productCode) {
    ProductEntity productEntity = productRepository.getByProductCode(productCode);
    if (productEntity != null) {
      productRepository.delete(productEntity);
      return new ResponseObject(200, "Delete product Successfully", null);
    }
    throw new CustomException("Cannot find Province with product code : ");
  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca ProductEntity
    List<ProductEntity> productEntities = productRepository.findAll();
    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }

    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject getByStoreId(long storeId) {
    // Lay Tat Ca ProductEntity has store id = storeId
    List<ProductEntity> productEntities = productRepository.getByStoreId(storeId);
    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject getProductRateGreater(double rate) {
    // Lay Tat Ca ProductEntity has rate > rate
    List<ProductEntity> productEntities = productRepository.getProductRateGreater(rate);
    // productEntities.stream().map(Product::)
    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject getProductRateLess(double rate) {
    // Lay Tat Ca ProductEntity has rate > rate
    List<ProductEntity> productEntities = productRepository.getProductRateLess(rate);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject getByManufacturer(long id) {
    // Lay Tat Ca ProductEntity has rate > rate
    List<ProductEntity> productEntities = productRepository.getByManufacturerId(id);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    if (productDTOs.size() > 0) {
      return new ResponseObject(productDTOs);
    } else {
      throw new CustomException("Not found product have manufacturer id " + id);
    }
  }

  @Override
  public ResponseObject getByManufacturer(String name) {
    // Lay Tat Ca ProductEntity has rate > rate
    List<ProductEntity> productEntities = productRepository.getByManufacturerName(name);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }

    if (productDTOs.size() > 0) {
      return new ResponseObject(productDTOs);
    } else {
      throw new CustomException("Not found product have manufacturer name : " + name);
    }
  }

  @Override
  public ResponseObject getProductContainName(String name) {
    // Lay Tat Ca ProductEntity contain name
    List<ProductEntity> productEntities = productRepository.getProductContainName(name);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }

    if (productDTOs.size() > 0) {
      return new ResponseObject(productDTOs);
    } else {
      throw new CustomException("Not found product have manufacturer contain name : " + name);
    }
  }

  @Override
  public ResponseObject getProductAmountByManufacturer(long id) {
    try {
      Optional<Long> amount = productRepository.getProductAmountByManufacturerId(id);
      if (amount.isEmpty()) {
        throw new CustomException(
            "Undistributed product or cannot find manufacturer with id : " + id);
      }
      return new ResponseObject(amount.get());
    } catch (Exception e) {
      e.printStackTrace();
    }

    throw new CustomException("Undistributed product or cannot find manufacturer with id : " + id);
  }

  @Override
  public ResponseObject getProductAmountByManufacturer(String name) {
    try {
      Optional<Long> amount = productRepository.getProductAmountByManufacturerName(name);
      return new ResponseObject(amount.get());
    } catch (Exception e) {
      e.printStackTrace();
    }

    throw new CustomException(
        "Undistributed product or cannot find manufacturer with name : " + name);
  }

  @Override
  public ResponseObject getTotalAmountById(long id) {
    try {
      Optional<Long> amount = productRepository.getTotalAmountById(id);
      if (amount.isEmpty()) {
        throw new CustomException("Undistributed product or cannot find product with id : " + id);
      }
      return new ResponseObject(amount.get());
    } catch (Exception e) {
      e.printStackTrace();
    }

    throw new CustomException("Undistributed product or cannot find product with id : " + id);
  }

  @Override
  public ResponseObject getProductRate(long id) {
    try {
      Optional<Double> rate = productRepository.getProductRate(id);
      if (rate.isEmpty()) {
        throw new CustomException("Error or This product has no reviews yet! try later  ");
      }
      return new ResponseObject(rate.get());
    } catch (Exception e) {
      e.printStackTrace();
    }

    throw new CustomException("Error or This product has no reviews yet! try later ");
  }

  @Override
  public ResponseObject getConfigurationProduct(long id) {
    ProductEntity product = productRepository.findOneById(id);
    if (product != null) {
      if (product.getLaptopEntity() != null) {
        LaptopDTO laptop = converter.toDTO(product.getLaptopEntity());
        return new ResponseObject(laptop);
      } else if (product.getPhoneEntity() != null) {
        PhoneDTO phone = converter.toDTO(product.getPhoneEntity());
        return new ResponseObject(phone);
      } else {
        return new ResponseObject("");
      }
    } else {
      throw new CustomException("Undistributed product or cannot find product with id : " + id);
    }
  }

  public ResponseObject getListProductByListItemInCart(List<ItemInCart> listItem) {
    List<Long> listId =
        listItem.stream().map(ItemInCart::getProductId).collect(Collectors.toList());
    List<ProductEntity> productEntities = productRepository.getProductByListId(listId);
    List<ProductDTO> productDTOs =
        productEntities.stream()
            .map(productEntity -> converter.toDTO(productEntity))
            .collect(Collectors.toList());
    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject addListPromotionForProduct(List<String> listPromoCode, long productId) {
    //    todo: add list promotion for product
    ProductEntity productEntity = productRepository.findOneById(productId);
    if (productEntity == null) {
      throw new CustomException("Product not found");
    }
    Set<PromotionEntity> promotionEntities =
        productEntity.getPromotionEntities().stream().collect(Collectors.toSet());

    for (String promoCode : listPromoCode) {
      PromotionEntity promotionEntity =
          promotionRepository.findByPromoCodeAndStartDateAndEndDate(promoCode);
      if (promotionEntity == null) {
        throw new CustomException("Promotion not found or expired with code : " + promoCode);
      }
      promotionEntities.add(promotionEntity);
    }
    productEntity.setPromotionEntities(promotionEntities.stream().collect(Collectors.toList()));
    productRepository.save(productEntity);
    return new ResponseObject(converter.toDTO(productEntity));
  }

  public boolean checkProductCodeExists(String productCode, Optional<Long> updateId) {
    // allow updating with old product_code

    // Save
    if (updateId == null) {
      updateId = Optional.of(-1L);
    }
    // Update
    long count = productRepository.countByProductCodeWithUpdateId(productCode, updateId.get());
    if (count > 0) {
      return true;
    }
    return false;
  }

  @Override
  public ResponseObject getProductPriceGreater(int price) {
    // Lay Tat Ca ProductEntity has price > :price
    List<ProductEntity> productEntities = productRepository.getProductPriceGreater(price);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject getProductPriceLess(int price) {
    // Lay Tat Ca ProductEntity has price < :price
    List<ProductEntity> productEntities = productRepository.getProductPriceLess(price);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    return new ResponseObject(productDTOs);
  }

  @Override
  public ResponseObject getProductPriceBetween(int start, int end) {
    // Lay Tat Ca ProductEntity has price < :price
    List<ProductEntity> productEntities = productRepository.getProductPriceBetween(start, end);

    // Tao ProductDTO de tra ve Json
    List<ProductDTO> productDTOs = new ArrayList<>();

    // Cast ProductEntity --> ProductDTO
    for (ProductEntity productEntity : productEntities) {
      ProductDTO productDTO = converter.toDTO(productEntity);
      productDTOs.add(productDTO);
    }
    return new ResponseObject(productDTOs);
  }

  private String filterMan(Long manufacturerId) {
    String filter = " p.manufacturerEntity.id = " + manufacturerId + " ";
    return filter;
  }

  private String filterManufacturer(List<Long> manufacturerIds) {
    if (manufacturerIds == null || manufacturerIds.isEmpty()) {
      return "";
    }

    StringBuilder query = new StringBuilder(" AND (");
    query.append(filterMan(manufacturerIds.get(0)));

    if (manufacturerIds.size() > 1) {
      for (int i = 1; i < manufacturerIds.size(); i++) {
        query.append(" OR " + filterMan(manufacturerIds.get(i)));
      }
    }

    query.append(")");

    return query.toString();
  }

  private String containmentCondition(String tableName, String fieldName, String text) {
    String filter = " " + tableName + "." + fieldName + " LIKE '%" + text + "%' ";
    return filter;
  }

  private String filterContain(String tableName, String fieldName, List<String> texts) {
    if (texts == null || texts.isEmpty()) {
      return "";
    }

    StringBuilder query = new StringBuilder(" AND ( ");
    query.append(containmentCondition(tableName, fieldName, texts.get(0)));

    if (texts.size() > 1) {
      for (int i = 1; i < texts.size(); i++) {
        query.append(" OR " + containmentCondition(tableName, fieldName, texts.get(i)));
      }
    }

    query.append(")");
    return query.toString();
  }

  // Filter in one table with key of Other
  private String filterContainOtherOneTable(
      String tableName, List<String> fieldNames, List<String> texts) {
    if (texts == null || texts.isEmpty() || fieldNames == null || fieldNames.isEmpty()) {
      return "";
    }

    StringBuilder query = new StringBuilder("");
    for (int i = 0; i < fieldNames.size(); i++) {
      // first field not " OR "
      if (i == 0) {
        query.append(containmentCondition(tableName, fieldNames.get(i), texts.get(0)));
      } else {
        query.append(" OR " + containmentCondition(tableName, fieldNames.get(i), texts.get(0)));
      }

      if (texts.size() > 1) {
        for (int j = 1; j < texts.size(); j++) {
          query.append(" OR " + containmentCondition(tableName, fieldNames.get(i), texts.get(j)));
        }
      }
    }
    return query.toString();
  }

  // Filter in multi table with key of Other
  private String filterContainOther(List<TableQuery> tableQueryList, List<String> texts) {
    if (tableQueryList == null || tableQueryList.isEmpty() || texts == null || texts.isEmpty()) {
      return "";
    }

    StringBuilder query = new StringBuilder(" AND ( ");
    // first field not " OR "
    query.append(
        filterContainOtherOneTable(
            tableQueryList.get(0).getName(), tableQueryList.get(0).getFieldQuery(), texts));

    for (int i = 1; i < tableQueryList.size(); i++) {
      query.append(
          " OR "
              + filterContainOtherOneTable(
                  tableQueryList.get(i).getName(), tableQueryList.get(i).getFieldQuery(), texts));
    }

    query.append(" ) ");

    return query.toString();
  }

  private String filterPrice(Double minPrice, Double maxPrice) {
    String filter = " AND p.price > " + minPrice + " AND p.price <" + maxPrice + " ";
    return filter;
  }

  @Override
  public ResponseObject filterLaptop(
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
      boolean sortByPrice) {
    StringBuilder strQuery =
        new StringBuilder("SELECT p FROM ProductEntity p, LaptopEntity l WHERE p.id = l.id ");
    String filterManufacturer = filterManufacturer(manufacturer);
    strQuery.append(filterManufacturer);
    strQuery.append(filterPrice(minPrice, maxPrice));
    strQuery.append(filterContain("l", "screen", screen));
    strQuery.append(filterContain("l", "cpu", cpu));
    strQuery.append(filterContain("l", "ram", ram));
    strQuery.append(filterContain("l", "hardDrive", hardDrive));

    //    strQuery.append(" AND ( ");
    //    List<String> fieldOthersForProduct = new ArrayList<>();
    //    fieldOthersForProduct.add("productName");
    //    fieldOthersForProduct.add("description");
    //    fieldOthersForProduct.add("category");
    //    strQuery.append(filterContainOtherOneTable("p", fieldOthersForProduct, other));
    //
    //    strQuery.append(" OR ");
    //
    //    List<String> fieldOthersForLaptop = new ArrayList<>();
    //    fieldOthersForLaptop.add("design");
    //    fieldOthersForLaptop.add("graphicsCard");
    //    strQuery.append(filterContainOtherOneTable("l", fieldOthersForLaptop, other));
    //
    //    strQuery.append(" ) ");

    List<String> fieldOthersForProduct = new ArrayList<>();
    fieldOthersForProduct.add("productName");
    fieldOthersForProduct.add("description");
    fieldOthersForProduct.add("category");
    TableQuery productTb = new TableQuery("p", fieldOthersForProduct);

    List<String> fieldOthersForLaptop = new ArrayList<>();
    fieldOthersForLaptop.add("design");
    fieldOthersForLaptop.add("graphicsCard");
    TableQuery laptopTb = new TableQuery("l", fieldOthersForLaptop);

    List<TableQuery> tableQueryList = new ArrayList<>();
    tableQueryList.add(productTb);
    tableQueryList.add(laptopTb);

    strQuery.append(filterContainOther(tableQueryList, other));

    if (sortByPrice) {
      strQuery.append("ORDER BY p.price ASC NULLS LAST");
    }

    List result =
        em.createQuery(strQuery.toString())
            .setMaxResults(noOfRecords)
            .setFirstResult(pageIndex * noOfRecords)
            .getResultList();
    ;

    List<ProductEntity> list = result;
    List<Product_LaptopDTO> product_laptopDTOList = new ArrayList<>();
    for (ProductEntity e : list) {
      Product_LaptopDTO product_laptopDTO = new Product_LaptopDTO();
      product_laptopDTO.setProductDTO(converter.toDTO(e));
      product_laptopDTO.setLaptopDTO(converter.toDTO(e.getLaptopEntity()));
      product_laptopDTOList.add(product_laptopDTO);
    }
    return new ResponseObject(product_laptopDTOList);
  }

  @Override
  public ResponseObject filterPhone(
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
      boolean sortByPrice) {
    StringBuilder strQuery =
        new StringBuilder("SELECT p FROM ProductEntity p, PhoneEntity ph WHERE p.id = ph.id ");
    String filterManufacturer = filterManufacturer(manufacturer);
    strQuery.append(filterManufacturer);
    strQuery.append(filterPrice(minPrice, maxPrice));
    strQuery.append(filterContain("ph", "screen", screen));
    strQuery.append(filterContain("ph", "cpu", cpu));
    strQuery.append(filterContain("ph", "ram", ram));
    strQuery.append(filterContain("ph", "memory", memory));

    List<String> fieldOthersForProduct = new ArrayList<>();
    fieldOthersForProduct.add("productName");
    fieldOthersForProduct.add("description");
    fieldOthersForProduct.add("category");
    TableQuery productTb = new TableQuery("p", fieldOthersForProduct);

    List<String> fieldOthersForPhone = new ArrayList<>();
    fieldOthersForPhone.add("charge");
    fieldOthersForPhone.add("chip");
    fieldOthersForPhone.add("camera");
    fieldOthersForPhone.add("fontCamera");
    TableQuery laptopTb = new TableQuery("ph", fieldOthersForPhone);

    List<TableQuery> tableQueryList = new ArrayList<>();
    tableQueryList.add(productTb);
    tableQueryList.add(laptopTb);

    strQuery.append(filterContainOther(tableQueryList, other));

    if (sortByPrice) {
      strQuery.append("ORDER BY p.price ASC NULLS LAST");
    }

    List result =
        em.createQuery(strQuery.toString())
            .setMaxResults(noOfRecords)
            .setFirstResult(pageIndex * noOfRecords)
            .getResultList();

    List<ProductEntity> list = (List<ProductEntity>) result;
    List<Product_PhoneDTO> product_phoneDTOList = new ArrayList<>();
    for (ProductEntity e : list) {
      Product_PhoneDTO product_phoneDTO = new Product_PhoneDTO();
      product_phoneDTO.setProductDTO(converter.toDTO(e));
      product_phoneDTO.setPhoneDTO(converter.toDTO(e.getPhoneEntity()));
      product_phoneDTOList.add(product_phoneDTO);
    }
    return new ResponseObject(product_phoneDTOList);
  }

  @Override
  public ResponseObject getProductsByPromotion(long id, Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    Page<ProductEntity> entities = productRepository.findAllProductsByPromotion(id, pageable);
    Page<ProductDTO> dtos =
        entities.map(
            new Function<ProductEntity, ProductDTO>() {
              @Override
              public ProductDTO apply(ProductEntity productEntity) {
                return converter.toDTO(productEntity);
              }
            });

    return new ResponseObject(dtos);
  }

  @Override
  public ResponseObject getProductsbyManufacture(long id, Optional<Integer> page) {

    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    Page<ProductEntity> entities = productRepository.findAllByManufacturerEntity_Id(id, pageable);
    Page<ProductDTO> dtos =
        entities.map(
            new Function<ProductEntity, ProductDTO>() {
              @Override
              public ProductDTO apply(ProductEntity productEntity) {
                return converter.toDTO(productEntity);
              }
            });
    return new ResponseObject(dtos);
  }

  @Override
  public ResponseObject getProductsByCategory(String name, Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    Page<ProductEntity> entities = productRepository.findAllByCategory(name, pageable);
    Page<ProductDTO> dtos =
        entities.map(
            new Function<ProductEntity, ProductDTO>() {
              @Override
              public ProductDTO apply(ProductEntity productEntity) {
                return converter.toDTO(productEntity);
              }
            });
    return new ResponseObject(dtos);
  }

  @Override
  public ResponseObject getTopSales() {
    List<ProductEntity> entities = productRepository.findTopProductSale();
    List<ProductDTO> dtos =
        entities.stream().map(p -> converter.toDTO(p)).collect(Collectors.toList());
    return new ResponseObject(dtos);
  }

  @Override
  public ResponseObject filterProduct(Map<String, String> conditions) {

    Map<String, String> filters = Constant.FILTER_PRODUCT;

    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("select * from product where ");
    conditions.entrySet().stream()
        .forEach(
            set -> {
              if (filters.containsKey(set.getKey())
                  && set.getKey() != "min"
                  && set.getKey() != "max") {
                queryBuilder.append(filters.get(set.getKey()) + " = " + set.getValue() + " , ");
              }
            });

    String minValue = conditions.get("min");
    if (minValue != null && minValue != "") {
      queryBuilder.append("price" + " >= " + conditions.get("min") + " ");
    }

    if (conditions.containsKey("max")) {
      String maxValue = conditions.get("max");
      if (maxValue != null && maxValue != "") {
        queryBuilder.append("and price <= " + conditions.get("max"));
      }
    }

    if (queryBuilder.charAt(queryBuilder.length() - 1) == ',') {
      queryBuilder.deleteCharAt(queryBuilder.length() - 1);
    }

    return null;
  }
}
