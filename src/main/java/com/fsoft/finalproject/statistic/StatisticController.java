package com.fsoft.finalproject.statistic;

import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class StatisticController {
    @Autowired private StatisticService statisticService;

    @GetMapping("/product/test-get-quantity")
    ResponseObject get(@RequestParam("day") int day, @RequestParam("month") int month, @RequestParam("year") int year) {
        return new ResponseObject(statisticService.getListResponseProduct(day, month, year));
    }

    @GetMapping("/product/test-get-turnover")
    ResponseObject get2(@RequestParam("day") int day,@RequestParam("month") int month,@RequestParam("year") int year) {
        return new ResponseObject(statisticService.getListTurnover(day,month,year));
    }
}
