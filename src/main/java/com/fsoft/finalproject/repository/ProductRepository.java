/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:53:12 PM */
package com.fsoft.finalproject.repository;

import java.util.List;
import java.util.Optional;

import com.fsoft.finalproject.dto.ItemInCart;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.finalproject.entity.ProductEntity;

@Scope(value = "singleton")
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  // @Modifying
  @Transactional
  @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %?1%")
  List<ProductEntity> getProductContainName(String name);

  // @Modifying
  @Transactional
  @Query(
      "SELECT COUNT(p) FROM ProductEntity p WHERE p.productCode = :product_code AND p.id <> :updateId")
  long countByProductCodeWithUpdateId(
      @Param("product_code") String product_code, @Param("updateId") long updateId);

  @Transactional
  @Query("SELECT p FROM ProductEntity p WHERE p.productCode = ?1")
  ProductEntity getByProductCode(String product_code);

  @Query(
      "SELECT pct FROM ProductEntity pct, ProductStoreEntity ps WHERE pct.id = ps.productEntity.id AND ps.storeEntity = :storeId")
  List<ProductEntity> getByStoreId(long storeId);

  @Query(
      "SELECT pct FROM ProductEntity pct, VoteEntity pv WHERE pct.id = pv.productEntity.id GROUP BY pv.productEntity HAVING AVG(pv.rate) > :rate")
  List<ProductEntity> getProductRateGreater(double rate);

  @Query(
      "SELECT pct FROM ProductEntity pct, VoteEntity pv WHERE pct.id = pv.productEntity.id GROUP BY pv.productEntity HAVING AVG(pv.rate) < :rate")
  List<ProductEntity> getProductRateLess(@Param("rate") double rate);

  @Query("SELECT pct FROM ProductEntity pct WHERE pct.price > :price")
  List<ProductEntity> getProductPriceGreater(int price);

  @Query("SELECT pct FROM ProductEntity pct WHERE pct.price < :price")
  List<ProductEntity> getProductPriceLess(@Param("price") int price);

  @Query("SELECT pct FROM ProductEntity pct WHERE pct.price > :start AND pct.price < :end")
  List<ProductEntity> getProductPriceBetween(@Param("start") int start, @Param("end") int end);

  @Query("SELECT pct FROM ProductEntity pct WHERE pct.manufacturerEntity.id = :id")
  List<ProductEntity> getByManufacturerId(long id);

  @Query("SELECT pct FROM ProductEntity pct WHERE pct.manufacturerEntity.name = :name")
  List<ProductEntity> getByManufacturerName(String name);

  @Transactional
  @Query("SELECT COUNT(pct) as amount FROM ProductEntity pct WHERE pct.manufacturerEntity.id = :id")
  Optional<Long> getProductAmountByManufacturerId(long id);

  @Transactional
  @Query(
      "SELECT COUNT(pct) as amount FROM ProductEntity pct WHERE pct.manufacturerEntity.name = :name")
  Optional<Long> getProductAmountByManufacturerName(String name);

  @Transactional
  @Query(
      "SELECT SUM(ps.quantity) as total FROM ProductStoreEntity ps WHERE ps.productEntity.id = :id")
  Optional<Long> getTotalAmountById(long id);

  @Query(
      "SELECT AVG(pv.rate) FROM ProductEntity pct, VoteEntity pv WHERE pct.id = pv.productEntity.id AND pct.id = :id")
  Optional<Double> getProductRate(long id);

  ProductEntity findOneById(long id);

  @Query(value = "SELECT u FROM ProductEntity u WHERE u.productCode = ?1")
  ProductEntity getProductByCode(String code);

  @Modifying
  @Query(
      value =
          "with p as (SELECT s.address_id,t.product_id,t.quantity,s.id as store_id\n"
              + "\t\t\tFROM finalintern_db.store s\n"
              + "\t\t\tleft join\n"
              + "\t\t\t\t(SELECT a.product_id,sum(a.quantity) as quantity,b.store_id \n"
              + "\t\t\t\tFROM finalintern_db.order_detail a \n"
              + "\t\t\t\tright join  finalintern_db.orders b \n"
              + "\t\t\t\ton a.order_id = b.id \n"
              + "\t\t\t\tWHERE DAY(b.order_date) = ?1 AND MONTH(b.order_date) = ?2 AND YEAR(b.order_date) = ?3 \n"
              + "\t\t\t\tgroup by a.product_id,b.store_id) t\n"
              + " \n"
              + "\t\t\ton s.id = t.store_id),\n"
              + "\ta as (\tSELECT t7.store_id,t8.Province,t8.District,t8.Ward,t7.id, t7.product_code,t7.name,t7.Inventory\n"
              + "\t\t\tFROM (\n"
              + "\t\t\tSELECT pro.product_code,pro.name,pro.id,re.quantity as Inventory,re.store_id\n"
              + "\t\t\tFROM finalintern_db.product pro \n"
              + "\t\t\tLEFT JOIN finalintern_db.product_store re \n"
              + "\t\t\ton pro.id = re.product_id \n"
              + "\t\t\torder by re.store_id , pro.product_code desc) t7\n"
              + "\t\tjoin\n"
              + "\t\t\t(SELECT st.id,t6.Province,t6.District,t6.Ward\n"
              + "\t\t\t\t\tFROM finalintern_db.store st\n"
              + "\t\t\t\t\tjoin\n"
              + "\t\t\t\t\t\t(SELECT ad.id,t2.Province,t2.District,t2.Ward\n"
              + "\t\t\t\t\t\t\t\tFROM finalintern_db.address ad\n"
              + "\t\t\t\t\t\t\t\tjoin \n"
              + "\t\t\t\t\t\t\t\t\t(SELECT t1.Province,t1.District,wa.name as Ward,wa.id\n"
              + "\t\t\t\t\t\t\t\t\tFROM finalintern_db.ward wa\n"
              + "\t\t\t\t\t\t\t\t\tjoin \n"
              + "\t\t\t\t\t\t\t\t\t\t(SELECT pr.name as Province,di.name as District,di.id \n"
              + "\t\t\t\t\t\t\t\t\t\tFROM finalintern_db.district di \n"
              + "\t\t\t\t\t\t\t\t\t\tjoin finalintern_db.province pr \n"
              + "\t\t\t\t\t\t\t\t\t\ton di.province_id=pr.id) t1\n"
              + "\t\t\t\t\t\t\t\t\ton wa.district_id = t1.id) t2\n"
              + "\t\t\t\t\t\t\t\ton ad.ward_id = t2.id ) t6\n"
              + "\t\t\t\t\ton t6.id = st.address_id) t8\n"
              + "\t\ton t7.store_id = t8.id     )\n"
              + "SELECT a.store_id,a.Province,a.District,a.Ward, a.product_code,a.name,p.quantity,a.Inventory \n"
              + "FROM   p right join a\n"
              + "on p.store_id = a.store_id AND p.product_id = a.id  ",
      nativeQuery = true)
  List<String[]> getProduct(int day, int month, int year);

  @Modifying
  @Query(
      value =
          "with table1 as (SELECT sum(a.price) as total,b.store_id \n"
              + "\t\t\t\tFROM finalintern_db.order_detail a \n"
              + "\t\t\t\tright join  finalintern_db.orders b \n"
              + "\t\t\t\ton a.order_id = b.id \n"
              + "\t\t\t\tWHERE DAY(b.order_date) = ?1 AND MONTH(b.order_date) = ?2 AND YEAR(b.order_date) = ?3 \n"
              + "\t\t\t\tgroup by b.store_id),\n"
              + "      table2 as (SELECT st.id,t6.Province,t6.District,t6.Ward\n"
              + "\t\t\t\t\tFROM finalintern_db.store st\n"
              + "\t\t\t\t\tjoin\n"
              + "\t\t\t\t\t\t(SELECT ad.id,t2.Province,t2.District,t2.Ward\n"
              + "\t\t\t\t\t\t\t\tFROM finalintern_db.address ad\n"
              + "\t\t\t\t\t\t\t\tjoin \n"
              + "\t\t\t\t\t\t\t\t\t(SELECT t1.Province,t1.District,wa.name as Ward,wa.id\n"
              + "\t\t\t\t\t\t\t\t\tFROM finalintern_db.ward wa\n"
              + "\t\t\t\t\t\t\t\t\tjoin \n"
              + "\t\t\t\t\t\t\t\t\t\t(SELECT pr.name as Province,di.name as District,di.id \n"
              + "\t\t\t\t\t\t\t\t\t\tFROM finalintern_db.district di \n"
              + "\t\t\t\t\t\t\t\t\t\tjoin finalintern_db.province pr \n"
              + "\t\t\t\t\t\t\t\t\t\ton di.province_id=pr.id) t1\n"
              + "\t\t\t\t\t\t\t\t\ton wa.district_id = t1.id) t2\n"
              + "\t\t\t\t\t\t\t\ton ad.ward_id = t2.id ) t6\n"
              + "\t\t\t\t\ton t6.id = st.address_id)\n"
              + "SELECT table2.id,table2.Province,table2.District,table2.Ward,table1.total\n"
              + "FROM table1 \n"
              + "right join table2\n"
              + "on table1.store_id = table2.id",
      nativeQuery = true)
  List<String[]> getTurnover(int day, int month, int year);

  @Query("select p from ProductEntity as p join p.promotionEntities as u where u.id = :id ")
  Page<ProductEntity> findAllProductsByPromotion(@Param("id") long id, Pageable pageable);

  @Query("select p from ProductEntity as p where p.manufacturerEntity.id = :id")
  Page<ProductEntity> findAllByManufacturerEntity_Id(long id, Pageable pageable);

  Page<ProductEntity> findAllByCategory(String category, Pageable pageable);

  @Query(
      value =
          "select p.* "
              + "from product as p, (select o.product_id, sum(quantity) as sales "
              + "from order_detail as o "
              + "group by o.product_id) as n "
              + "where p.id = n.product_id "
              + "order by n.sales desc "
              + "limit 10",
      nativeQuery = true)
  List<ProductEntity> findTopProductSale();

  //  Get list Product by list item in cart
  @Query("SELECT p FROM ProductEntity p WHERE p.id IN (:listId)")
  List<ProductEntity> getProductByListId(@Param("listId") List<Long> listId);
}
