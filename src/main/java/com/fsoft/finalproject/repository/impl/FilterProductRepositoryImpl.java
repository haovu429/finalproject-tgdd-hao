package com.fsoft.finalproject.repository.impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.repository.FilterProductRepository;
import com.fsoft.finalproject.utils.Constant;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FilterProductRepositoryImpl implements FilterProductRepository {

    @Autowired
    Converter converter;

    @PersistenceContext
    EntityManager entityManager;

    public ResponseObject filterProduct(Map<String, String> conditions){
        Map<String, String> filters = Constant.FILTER_PRODUCT;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<ProductEntity> q = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = q.from(ProductEntity.class);

        String manufacturer = conditions.get("m");
        if(manufacturer != null){
            predicates.add(cb.equal(root.get("manufacturerEntity").get("id"), manufacturer));
            conditions.remove("m");
        }

        String minValue = conditions.get("min");
        String maxValue = conditions.get("max");

        if(minValue != null){
            predicates.add(cb.greaterThan(root.get("price"), minValue));
            conditions.remove("min");
        }

        if(maxValue != null){
            predicates.add(cb.lessThan(root.get("price"), maxValue));
            conditions.remove("max");
        }

        conditions.entrySet().stream()
                .forEach(entry -> {
                    if(filters.containsKey(entry.getKey())){
                        predicates.add(cb.equal(root.get(filters.get(entry.getKey())), entry.getValue()));
                    }
                });

        q.where(predicates.toArray(new Predicate[0]));
        List<ProductEntity> products = entityManager.createQuery(q).getResultList();

        return new ResponseObject(products.stream().map(p -> converter.toDTO(p)));
    }
}
