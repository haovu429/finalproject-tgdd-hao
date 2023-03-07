/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:49:27 PM */
package com.fsoft.finalproject.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.finalproject.entity.ImageEntity;

@Scope(value = "singleton")
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

  /*
   * @Modifying
   *
   * @Query("select im " + "from Image as im " + "JOIN FETCH im.productEntity p "
   * + "where d.id = :id") List<ImageEntity> getImageByProduct(@Param("title")
   * long productId);
   */
  @Transactional
  @Modifying
  @Query("SELECT i FROM ImageEntity i WHERE i.productEntity.id = ?1")
  List<ImageEntity> getByProduct(long productId);

  /*
   * @Query("delete " + "from Image as im " + "JOIN FETCH im.productEntity p " +
   * "where d.id = ?1")
   */
  @Transactional
  @Modifying
  @Query("DELETE FROM ImageEntity i WHERE i.productEntity.id = ?1")
  void deleteByProduct(long productId);

  boolean existsImageEntitiesByProductEntityId(long productId);
}
