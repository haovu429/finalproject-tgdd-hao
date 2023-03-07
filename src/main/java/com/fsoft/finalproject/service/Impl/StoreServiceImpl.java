package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.AddressResponseDTO;
import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.StoreDTO;
import com.fsoft.finalproject.entity.AddressEntity;
import com.fsoft.finalproject.entity.ProductStoreEntity;
import com.fsoft.finalproject.entity.StoreEntity;
import com.fsoft.finalproject.exception.InvalidException;
import com.fsoft.finalproject.repository.AddressRepository;
import com.fsoft.finalproject.repository.ProductStoreRepository;
import com.fsoft.finalproject.repository.StoreRepository;
import com.fsoft.finalproject.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
  @Autowired
  private StoreRepository storeRepository;
  @Autowired
  private Converter converter;

  @Autowired
  private ProductStoreRepository productStoreRepository;

  @Override
  public Page<StoreDTO> findAll(Pageable pageable) {
    Page<StoreEntity> entities = storeRepository.findAll(pageable);
    return entities.map(
            new Function<StoreEntity, StoreDTO>() {
              @Override
              public StoreDTO apply(StoreEntity storeEntity) {
                return converter.toDTO(storeEntity);
              }
            });
  }

  //  @Override
  //  public Optional<StoreDTO> save(StoreDTO dto) throws InvalidException {
  //    // check mail and phone and fax to make sure not duplicate phone and mail
  //    Optional<StoreEntity> found =
  //        storeRepository.findStoreEntityByEmailOrPhoneOrFax(
  //            dto.getEmail(), dto.getPhone(), dto.getFax());
  //    if (found.isPresent()) {
  //      throw new InvalidException("Email or Phone or Fax was existed");
  //    }
  //
  //    // check address to make sure no duplicate address
  //    List<Long> addresses =
  //        addressRepository.checkStoreAddressExist(
  //            dto.getAddress().getProvince().getId(),
  //            dto.getAddress().getDistrict().getId(),
  //            dto.getAddress().getWard().getId(),
  //            dto.getAddress().getNumHouse());
  //    if (!addresses.isEmpty()) {
  //      throw new InvalidException("Address was existed");
  //    }
  //    AddressEntity newAddress = converter.toEntity(dto.getAddress());
  //    AddressEntity resSaveAddress = addressRepository.save(newAddress);
  //
  //    StoreEntity entity = storeConverter.toEntity(dto);
  //    entity.setAddressEntity(resSaveAddress);
  //    StoreEntity res = storeRepository.save(entity);
  //
  //    return Optional.of(storeConverter.toStoreDTO(res));
  //  }

  // Hao sua
  @Override
  public Optional<StoreDTO> save(StoreDTO dto) throws InvalidException {
    // check mail and phone and fax to make sure not duplicate phone and mail
    Optional<StoreEntity> found =
            storeRepository.findStoreEntityByEmailOrPhoneOrFax(
                    dto.getEmail(), dto.getPhone(), dto.getFax());
    if (found.isPresent()) {
      throw new InvalidException("Email or Phone or Fax was existed");
    }

    // check address to make sure no duplicate address
    //    List<Long> addresses =
    //            addressRepository.checkStoreAddressExist(
    //                    dto.getAddress().getProvince().getId(),
    //                    dto.getAddress().getDistrict().getId(),
    //                    dto.getAddress().getWard().getId(),
    //                    dto.getAddress().getNumHouse());
    //    if (!addresses.isEmpty()) {
    //      throw new InvalidException("Address was existed");
    //    }
    //    AddressEntity newAddress = converter.toEntity(dto.getAddress());
    //    AddressEntity resSaveAddress = addressRepository.save(newAddress);

    StoreEntity entity = converter.toEntity(dto);
    // entity.setAddressEntity(resSaveAddress);
    StoreEntity res = storeRepository.save(entity);

    return Optional.of(converter.toDTO(res));
  }

  @Override
  public Optional<StoreDTO> findById(Long aLong) {
    Optional<StoreEntity> entity = storeRepository.findById(aLong);
    if (!entity.isPresent()) return Optional.ofNullable(null);
    return Optional.of(converter.toDTO(entity.get()));
  }

  //  @Override
  //  public Optional<StoreDTO> update(long id, StoreDTO dto) throws InvalidException {
  //    // check entity existed
  //    Optional<StoreEntity> found = storeRepository.findById(id);
  //    if (!found.isPresent()) {
  //      throw new InvalidException("No Store is found");
  //    }
  //
  //    // check mail, phone and fax
  //    Optional<StoreEntity> checkEntity =
  //        storeRepository.findStoreEntityByEmailOrPhoneOrFax(
  //            dto.getEmail(), dto.getPhone(), dto.getFax());
  //    if (checkEntity.isPresent() && checkEntity.get().getId() != id) {
  //      throw new InvalidException("Mail or Phone or Fax is existed");
  //    }
  //
  //    // check address to make sure address was existed
  //    List<Long> addresses =
  //        addressRepository.checkStoreAddressExist(
  //            dto.getAddress().getProvince().getId(),
  //            dto.getAddress().getDistrict().getId(),
  //            dto.getAddress().getWard().getId(),
  //            dto.getAddress().getNumHouse());
  //
  //    if (!addresses.isEmpty()) {
  //      if (!addresses.contains(id)) throw new InvalidException("Address was existed");
  //    }
  //
  //    AddressEntity newAddress = converter.toEntity(dto.getAddress());
  //    AddressEntity resSaveAddress = addressRepository.save(newAddress);
  //
  //    StoreEntity newEntity = storeConverter.toEntity(dto);
  //    newEntity.setId(id);
  //    newEntity.setAddressEntity(resSaveAddress);
  //    StoreEntity res = storeRepository.save(newEntity);
  //    return Optional.ofNullable(storeConverter.toStoreDTO(res));
  //  }

  @Override
  public Optional<StoreDTO> update(long id, StoreDTO dto) throws InvalidException {
    // check entity existed
    Optional<StoreEntity> found = storeRepository.findById(id);
    if (!found.isPresent()) {
      throw new InvalidException("No Store is found");
    }

    // check mail, phone and fax
    Optional<StoreEntity> checkEntity =
            storeRepository.findStoreEntityByEmailOrPhoneOrFax(
                    dto.getEmail(), dto.getPhone(), dto.getFax());
    if (checkEntity.isPresent() && checkEntity.get().getId() != id) {
      throw new InvalidException("Mail or Phone or Fax is existed");
    }

    // check address to make sure address was existed
    //    List<Long> addresses =
    //            addressRepository.checkStoreAddressExist(
    //                    dto.getAddress().getProvince().getId(),
    //                    dto.getAddress().getDistrict().getId(),
    //                    dto.getAddress().getWard().getId(),
    //                    dto.getAddress().getNumHouse());
    //
    //    if (!addresses.isEmpty()) {
    //      if (!addresses.contains(id)) throw new InvalidException("Address was existed");
    //    }
    //
    //    AddressEntity newAddress = converter.toEntity(dto.getAddress());
    //    AddressEntity resSaveAddress = addressRepository.save(newAddress);

    StoreEntity newEntity = converter.toEntity(dto);
    newEntity.setId(id);
    // newEntity.setAddressEntity(resSaveAddress);
    StoreEntity res = storeRepository.save(newEntity);
    return Optional.ofNullable(converter.toDTO(res));
  }

  @Override
  public boolean deleteById(long id) throws InvalidException {
    Optional<StoreEntity> found = storeRepository.findById(id);
    if (!found.isPresent()) {
      throw new InvalidException("can not found store with id: " + id);
    }
    storeRepository.delete(found.get());
    return true;
  }

  @Override
  public void delete(StoreEntity entity) {
    storeRepository.delete(entity);
  }

  @Override
  public ResponseObject findStoreByDistrict(long districtId) {
    List<StoreEntity> stores = storeRepository.findStoreEntityByDistrict(districtId);
    if (stores.isEmpty()) {
      return new ResponseObject("404", 200, "Not found store");
    }
    List<StoreDTO> storeDTOs = stores.stream().map(store -> converter.toDTO(store)).collect(Collectors.toList());
    return new ResponseObject(storeDTOs);
  }


  @Override
  public ResponseObject findStoreByAddressAndQuantityProductInCart(long province, long districtId, List<ItemInCart> listItems) {
    List<StoreEntity> stores = new ArrayList<>();
    if (districtId != 0) {
      stores = storeRepository.findStoreEntityByDistrict(districtId);
    } else {
      stores = storeRepository.findStoreEntityByProvince(province);
    }
    if (stores.isEmpty()) {
      return new ResponseObject("404", 200, "Not found store");
    }
    List<Long> listStoreIdInclude = getIdStoreAfterFilter(stores, listItems);

    if (listStoreIdInclude.isEmpty()) {
      return new ResponseObject("404", 200, "Not found store");
    }

//    Get address of store
    List<AddressEntity> addressEntities = storeRepository.findAddressEntityByStoreIds(listStoreIdInclude);
    List<AddressResponseDTO> addressResponseDTOs =
            addressEntities.stream().map(address -> converter.toAddressResponseDTO(address)).collect(Collectors.toList());

    return new ResponseObject(addressResponseDTOs);
  }

  public List<Long> getIdStoreAfterFilter(List<StoreEntity> storeEntities, List<ItemInCart> listItems) {
    List<Long> listStoreId = storeEntities.stream().map(store -> store.getId()).collect(Collectors.toList());
//  get product in store
    Map<Long, List<ItemInCart>> mapStoreProduct = new HashMap<>();
    for (Long storeId : listStoreId) {
      List<ItemInCart> listProduct = new ArrayList<>();

      List<ProductStoreEntity> productStoreEntities = productStoreRepository.getProductInStore(storeId);
      for (ProductStoreEntity productStoreEntity : productStoreEntities) {
        ItemInCart itemInCart = new ItemInCart();
        itemInCart.setProductId(productStoreEntity.getProductEntity().getId());
        itemInCart.setQuantity(productStoreEntity.getQuantity());

        listProduct.add(itemInCart);
      }
//      storeid - list product(id, quantity)
      mapStoreProduct.put(storeId, listProduct);
    }
//    check mapStoreProduct include listItems
    List<Long> listStoreIdInclude = new ArrayList<>();
    for (Map.Entry<Long, List<ItemInCart>> entry : mapStoreProduct.entrySet()) {
//      if (entry.getValue().containsAll(listItems)) {
//        listStoreIdInclude.add(entry.getKey());
//      }
      if (checkStoreValid(entry.getValue(), listItems)) {
        listStoreIdInclude.add(entry.getKey());
      }
    }
    return listStoreIdInclude;

  }

  public Boolean checkStoreValid(List<ItemInCart> listItemInStore, List<ItemInCart> listItemInCart) {
//    covert list to map to compare
    Map<Long, Integer> mapItemInStore = new HashMap<>(listItemInStore.size());
    for (ItemInCart itemInStore : listItemInStore) {
      mapItemInStore.put(itemInStore.getProductId(), itemInStore.getQuantity());
    }
    Map<Long, Integer> mapItemInCart = new HashMap<>(listItemInCart.size());
    for (ItemInCart itemInCart : listItemInCart) {
      mapItemInCart.put(itemInCart.getProductId(), itemInCart.getQuantity());
    }
//    Compare
    for (Map.Entry<Long, Integer> entry : mapItemInCart.entrySet()) {
      if (!mapItemInStore.containsKey(entry.getKey())) {
        return false;
      } else if (mapItemInStore.get(entry.getKey()) < entry.getValue()) {
        return false;
      }
    }

    return true;
  }
}
