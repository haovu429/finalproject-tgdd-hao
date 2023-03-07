package com.fsoft.finalproject.statistic;

import com.fsoft.finalproject.repository.ProductRepository;
import com.fsoft.finalproject.response.ResponseProduct;
import com.fsoft.finalproject.response.ResponseTurnover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired private ProductRepository productRepository;

    public List<ResponseProduct> getListResponseProduct(int day, int month, int year) {
        List<ResponseProduct> result = new ArrayList<>();
        List<String[]> list = productRepository.getProduct(day,month,year);
        for(String[] objArr : list)
        {
            long objStore = Long.parseLong(objArr[0]);
            String province = objArr[1];
            String district = objArr[2];
            String ward = objArr[3];
            String objCode = objArr[4];
            String objName = objArr[5];
            int quantity = 0;
            if(objArr[6] !=null){
                quantity = Integer.parseInt(objArr[6]);
            }

            int tk =  Integer.parseInt(objArr[7]);
            ResponseProduct product = new ResponseProduct(objStore,province,district,ward,objCode,objName,quantity,tk);
            result.add(product);

        }
        return result;
    }


    public List<ResponseTurnover> getListTurnover(int day, int month, int year) {
        List<ResponseTurnover> result = new ArrayList<>();
        List<String[]> list = productRepository.getTurnover(day, month, year);
        for(String[] objArr : list)
        {
            long objStore = Long.parseLong(objArr[0]);
            String province = objArr[1];
            String district = objArr[2];
            String ward = objArr[3];
            long total = 0;
            if(objArr[4] !=null){
                total = Long.parseLong(objArr[4]);
            }
            ResponseTurnover product = new ResponseTurnover(objStore,province,district,ward,total);
            result.add(product);

        }
        return result;
    }
}
