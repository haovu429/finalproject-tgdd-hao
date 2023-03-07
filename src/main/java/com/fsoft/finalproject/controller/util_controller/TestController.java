package com.fsoft.finalproject.controller.util_controller;

import com.fsoft.finalproject.data.InitData;
import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class TestController {
    @GetMapping("test-init")
    @ResponseBody
    public ResponseObject test() {
        InitData initData = new InitData();
        initData.initData();
        return new ResponseObject(true);
    }
}
