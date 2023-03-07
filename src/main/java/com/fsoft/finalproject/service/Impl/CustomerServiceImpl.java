package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.CustomerDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.CustomerEntity;
import com.fsoft.finalproject.entity.OTP;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.mail.EmailService;
import com.fsoft.finalproject.mail.Mail;
import com.fsoft.finalproject.mail.OTPUtils;
import com.fsoft.finalproject.repository.CustomerRepository;
import com.fsoft.finalproject.repository.OTPRepository;
import com.fsoft.finalproject.service.CustomerService;
import com.fsoft.finalproject.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private Converter converter;

  @Autowired private EmailService emailService;

  @Autowired private OTPService otpService;

  @Autowired private OTPRepository otpRepository;
  @Override
  public ResponseObject getAll(Pageable pageable) {
    List<CustomerDTO> customerDTOS =
        customerRepository.findAll(pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());

    return new ResponseObject(customerDTOS);
  }

  @Override
  public ResponseObject getById(Long id) {
    CustomerEntity customerEntity = customerRepository.findOneById(id);
    if (customerEntity == null) {
      throw new CustomException("Customer not found");
    } else {
      return new ResponseObject(converter.toDTO(customerEntity));
    }
  }

  @Override
  public ResponseObject save(CustomerDTO customerDTO) {
    CustomerEntity customerEntity = converter.toEntity(customerDTO);
    // check exist email or phone
    CustomerEntity customerEntityExist = customerRepository.findOneByEmail(customerDTO.getEmail());
    if (customerEntityExist != null) {
      throw new CustomException("Email already exist");
    }
    customerEntityExist = customerRepository.findOneByPhone(customerDTO.getPhone());
    if (customerEntityExist != null) {
      throw new CustomException("Phone already exist");
    }
    customerEntity = customerRepository.save(customerEntity);
    return new ResponseObject(converter.toDTO(customerEntity));
  }

  @Override
  public ResponseObject update(Long id, CustomerDTO customerDTO) {
    CustomerEntity customerEntityUpdate = converter.toEntity(customerDTO);
    CustomerEntity customerEntity = customerRepository.findOneById(id);
    if (customerEntity == null) {
      throw new CustomException("Customer not found");
    } else {
      customerEntityUpdate.setId(customerEntity.getId());
      customerEntity = customerRepository.save(customerEntityUpdate);
      return new ResponseObject(converter.toDTO(customerEntity));
    }
  }

  @Override
  public ResponseObject delete(Long id) {
    CustomerEntity customerEntity = customerRepository.findOneById(id);
    if (customerEntity == null) {
      throw new CustomException("Customer not found");
    } else {
      customerRepository.delete(customerEntity);
      return new ResponseObject(200, "Delete customer successfully", null);
    }
  }

  @Override
  public ResponseObject getByEmail(String email) {
    CustomerEntity customerEntity = customerRepository.findOneByEmail(email);
    if (customerEntity == null) {
      throw new CustomException("Customer not found");
    } else {
      return new ResponseObject(converter.toDTO(customerEntity));
    }
  }

  @Override
  public ResponseObject getByPhone(String phone) {
    CustomerEntity customerEntity = customerRepository.findOneByPhone(phone);
    if (customerEntity == null) {
      throw new CustomException("Customer not found");
    } else {
      return new ResponseObject(converter.toDTO(customerEntity));
    }
  }

  @Override
  public ResponseObject sendOTPLogin(String mail){
    CustomerEntity entity = customerRepository.findOneByEmail(mail);
    if(entity == null){
      throw new CustomException("Can not find user with mail "+ mail);
    }
    List<OTP> otps = otpRepository.findOTPsByCustomerMail(mail);

    List<OTP> validOtps = otps.stream()
            .filter(p -> new Date(p.getCreateDate().getTime() + OTPUtils.OTP_TIME).after(new Date()))
            .collect(Collectors.toList());

    if(validOtps.size() != 0 || !validOtps.isEmpty()){
      throw new CustomException("You just send request otp to login");
    }

    String otp = OTPUtils.generateOtp();
    otpService.saveOTP(otp, entity.getEmail());

    Mail newMail = emailService.createOTPMail(entity, "YÊU CẦU ĐĂNG NHẬP",otp );

    emailService.sendMail(newMail, "mail/otp-customer-login");
    return new ResponseObject(200, "send mail successful", null);
  }

  @Override
  public ResponseObject validateOtp(String otp){
    OTP otpEntity = otpRepository.findByOtp(otp);
    if(otpEntity == null){
      throw new CustomException("Can not find OTP");
    }

    CustomerEntity customer = otpService.findUSerByOTP(otp);
    if(customer == null){
        throw new CustomException("OTP invalid because can not find customer");
    }

    Date date = new Date(otpEntity.getCreateDate().getTime() + OTPUtils.OTP_TIME);
    if(date.before(new Date())){
      throw new CustomException("OTP is expired");
    }

    return new ResponseObject(200, "login successful", null);
  }

}
