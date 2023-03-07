package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductPhoneFilterDTO {
    List<Long> manufacturer;
    Double minPrice;
    Double maxPrice;
    List<String> screen;
    List<String> cpu;
    List<String> ram;
    List<String> memory;
    List<String> other;
    int noOfRecords;
    int pageIndex;
    boolean sortByPrice;

}
