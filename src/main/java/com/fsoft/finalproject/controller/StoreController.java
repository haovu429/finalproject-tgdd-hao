package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.StoreDTO;
import com.fsoft.finalproject.exception.InvalidException;
import com.fsoft.finalproject.service.AddressService;
import com.fsoft.finalproject.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class StoreController {
  @Autowired
  private StoreService storeService;

  @Autowired
  private AddressService addressService;

  /**
   * get all store within pagination
   *
   * @param page
   * @return Page<StoreDTO>
   */
  @GetMapping("stores")
  @ResponseBody
  public ResponseObject findAllStore(@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    Page<StoreDTO> listStores = storeService.findAll(pageable);
    return new ResponseObject(HttpStatus.OK.value(), "success", listStores);
  }

  /**
   * get store by id and return null in response body if not present
   *
   * @param id
   * @return Optional<StoreDTO>
   */
  @GetMapping("store")
  @ResponseBody
  public ResponseObject findByID(@RequestParam("id") Long id) {
    Optional<StoreDTO> store = storeService.findById(id);
    if (!store.isPresent()) {
      throw new RuntimeException("Store not found");
    } else {
      return new ResponseObject(HttpStatus.OK.value(), "success", store.get());
    }
  }


  @DeleteMapping("store/{id}")
  public ResponseObject deleteByID(@PathVariable("id") Long id) throws InvalidException {
    boolean res = storeService.deleteById(id);
    if (!res) {
      throw new RuntimeException("Not found store with id: " + id);
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", "");
  }

  /**
   * save new Store to database
   *
   * @param storeDTO
   * @return
   */
  @PostMapping("store")
  public ResponseObject save(@RequestBody StoreDTO storeDTO) throws InvalidException {
    Optional res = Optional.of(storeService.save(storeDTO));
    if (!res.isPresent()) {
      throw new RuntimeException("Some error occured");
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", res.get());
  }

  /**
   * update store info to database
   *
   * @param storeDTO
   * @return
   */
  @PutMapping("store/{id}")
  public ResponseObject update(@PathVariable("id") long id, @RequestBody @Validated StoreDTO storeDTO, BindingResult result) throws InvalidException {
    Optional<StoreDTO> res = storeService.update(id, storeDTO);
    if (!res.isPresent()) {
      throw new RuntimeException("Some error occured");
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", res.get());

  }


  @GetMapping(value = "cart", params = {"districtId", "provinceId"})
  public ResponseObject findStoreInCart(@RequestParam("districtId") long districtId, @RequestParam("provinceId") long provinceId, @RequestBody List<ItemInCart> listItemInCart) {
    return storeService.findStoreByAddressAndQuantityProductInCart(provinceId, districtId, listItemInCart);
  }
}
