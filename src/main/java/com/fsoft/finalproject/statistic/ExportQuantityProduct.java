package com.fsoft.finalproject.statistic;

import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.response.ResponseProduct;
import com.fsoft.finalproject.statistic.CreateExcel;
import com.fsoft.finalproject.mail.EmailService;
import com.fsoft.finalproject.response.ResponseTurnover;
import com.fsoft.finalproject.service.VoteService;
import com.fsoft.finalproject.statistic.StatisticService;
import com.fsoft.finalproject.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/v1/")
public class ExportQuantityProduct {

    @Autowired private StatisticService  statisticService;
    @Autowired private EmailService emailService;

    @GetMapping("/export-excel-sold-quantity")
    public void exportExcel(HttpServletResponse response,@RequestParam("day") int day,@RequestParam("month") int month,@RequestParam("year") int year) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ResponseProduct> listResponse = statisticService.getListResponseProduct(day, month, year);
        List<ResponseTurnover> listTurnover = statisticService.getListTurnover(day, month, year);

        CreateExcel excelExporter = new CreateExcel(listResponse,listTurnover);

        excelExporter.export(response);
    }

    @GetMapping("/send-mail")
    public void sendMail(@RequestParam("email") String email,@RequestParam("day") int day,@RequestParam("month") int month,@RequestParam("year") int year){
        Matcher matcher = Pattern.compile(Constant.EMAIL_REGEX).matcher(email);
        boolean check = matcher.matches();
        if(check){
            String url = "http://localhost:8080/api/v1/export-excel-sold-quantity?day="+day+"&month="+month+"&year="+year;
            emailService.sendEmail(email,"Mail Thong Ke",url);
        }
        else{
            throw new CustomException("Email not correct");
        }
    }

}
