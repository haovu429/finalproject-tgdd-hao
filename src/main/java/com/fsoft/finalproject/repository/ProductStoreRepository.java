package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.ProductStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import java.util.Optional;

public interface ProductStoreRepository extends JpaRepository<ProductStoreEntity, Long> {
  @Query(
      "SELECT c FROM ProductStoreEntity c WHERE c.productEntity.id = :param1 AND c.storeEntity.addressEntity.wardEntity.id=:param2")
  public ProductStoreEntity getQuantityProductInStore(
      @Param("param1") long idProduct, @Param("param2") long idWard);

  //  Get list  product in store
  @Query("SELECT pS FROM ProductStoreEntity pS WHERE pS.storeEntity.id = :storeId")
  List<ProductStoreEntity> getProductInStore(long storeId);

  // Get By product id and store id
  @Query(
      "SELECT pS FROM ProductStoreEntity pS WHERE pS.productEntity.id = :productId AND pS.storeEntity.id = :storeId")
  ProductStoreEntity getByProductIdAndStoreId(long productId, long storeId);
}
