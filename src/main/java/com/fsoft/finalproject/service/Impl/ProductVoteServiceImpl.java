//package com.fsoft.finalproject.service.Impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import com.fsoft.finalproject.converter.ProductVoteConverter;
//import com.fsoft.finalproject.dto.CustomerDTO;
//import com.fsoft.finalproject.dto.ResponseObject;
//import com.fsoft.finalproject.entity.CustomerEntity;
//import com.fsoft.finalproject.entity.ProductEntity;
//import com.fsoft.finalproject.entity.ProductVoteEntity;
//import com.fsoft.finalproject.repository.CustomerRepository;
//import com.fsoft.finalproject.repository.ProductRepository;
//import com.fsoft.finalproject.repository.ProductVoteRepository;
//import com.fsoft.finalproject.service.ProductVoteService;
//
//@Service
//public class ProductVoteServiceImpl implements ProductVoteService {
//
//	@Autowired
//	private ProductVoteRepository productVoteRepository;
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@Autowired
//	private ProductVoteConverter productVoteConverter;
//
//	@Autowired
//	private CustomerRepository customerRepository;
//
//	ModelMapper mapper = new ModelMapper();
//
//	@Override
//	public ResponseObject save(String  code,String phone,float rate) {
//		//Cast DTO --> Entity
//		System.out.println("=====0==========");
//		ProductVoteEntity voteEntity = new ProductVoteEntity();
//		voteEntity.setRate(rate);
//		//Get Phone Customer
//		//Get List Customer By Phone
//		List<CustomerDTO> customerDTOs = customerRepository.getCustomerByPhone(phone);
//
//		System.out.println("===="+customerDTOs.size());
//
//		//System.out.println("===="+customer.getFullName());
//		System.out.println("==========2=====");
//		//Get Product By code
//		ProductEntity product = productRepository.getProductByCode(code);
//		System.out.println("=========3======");
//		//Kiem Tra Ket qua Tren
//		if (product == null) {
//			throw new RuntimeException("Product not found");
//		} else if (customerDTOs.size()==0) {
//			throw new RuntimeException("Customer not found");
//		} else {
//			CustomerEntity customer = customerRepository.findById(2L).orElse(null);
//			System.out.println("=========4======");
//			//Thoa Man
//			//Get Vote Tu Phone Customer And Code Product ==> Kiem Tra Da Ton Tai Chua
//			List<ProductVoteDTO> voteDTOs = productVoteRepository.getVoteByCustomerAndProduct(phone,
//					product.getProductCode());
//
//			if (voteDTOs.size() == 0) {
//				//Neu Chua Ton Tai Phone Va Code
//				//Add Theo QUAn He
//				voteEntity.setCustomerEntity(customer);
//				voteEntity.setProductEntity(product);
//
//				product.getProductVoteEntities().add(voteEntity);
//				//1-1
//				//customer.setProductVoteEntity(voteEntity);
//
//				voteEntity = productVoteRepository.save(voteEntity);
//				return new ResponseObject(HttpStatus.OK.value(), "Save Success", productVoteConverter.toDTO(voteEntity));
//			} else {
//				throw new RuntimeException("Product Vote Exist");
//			}
//		}
//
//	}
//
//	@Override
//	public ResponseObject update(String code, String phone, float rate) {
//		//Tim Kiem Theo Code Product Va Phone Customer
//		List<ProductVoteDTO> voteDTOs= productVoteRepository.getVoteByCustomerAndProduct(phone,
//				code);
//		if(voteDTOs.size()==0) {
//			//Khong Tim Thay
//			throw new RuntimeException("Product Vote Not Found");
//		}
//		else {
//			//Tim Duoc
//			ProductVoteEntity voteEntity = productVoteRepository.findById(voteDTOs.get(0).getId()).map(vote ->{
//				vote.setRate(rate);
//				return productVoteRepository.save(vote);
//			}).orElse(null);
//			return new ResponseObject(HttpStatus.OK.value(), "Update Success", productVoteConverter.toDTO(voteEntity));
//		}
//
//	}
//
//	@Override
//	public ResponseObject delete(String  code,String phone) {
//		List<ProductVoteDTO> voteDTOs = productVoteRepository.getVoteByCustomerAndProduct(phone,
//				code);
//		if(voteDTOs.size()==0) {
//			throw new RuntimeException("Cannot Find Product Code = " + code+ " AND phone Customer = "+phone);
//		}
//		else {
//
//			productVoteRepository.deleteById(voteDTOs.get(0).getId());
//			return new ResponseObject(HttpStatus.OK.value(), "Delete Successfully Product Code = " + code+ " AND phone Customer = "+phone, "");
//		}
//	}
//
//	@Override
//	public ResponseObject getVoteByProduct(String code) {
//		//Get List Id Vote By Product
//		List<ProductVoteDTO> listVote = productVoteRepository.getVoteByProduct2(code);
//
//		//Get Product By Code ==> Check List Vote If empty
//		ProductEntity product = productRepository.getProductByCode(code);
//
//		System.out.println("============"+listVote.size());
//		if (listVote.size() != 0) {
//			List<ProductVoteDTO> listVoteDTO = new ArrayList<>();
//			//Duyet Tung Id Vote De Tim Object
//			for(ProductVoteDTO i: listVote) {
//				//Get Object By Id
//				ProductVoteEntity vote = productVoteRepository.findById(i.getId()).orElse(null);
//				//Cast
//				ProductVoteDTO voteDTO = productVoteConverter.toDTO(vote);
//				//Add List
//				listVoteDTO.add(voteDTO);
//			}
//			return new ResponseObject(HttpStatus.OK.value(), "Get Success", listVoteDTO);
//		}
//		else if(product!=null) {
//			return new ResponseObject(HttpStatus.OK.value(), "Product Not Vote", "");
//		}
//		else {
//			throw new RuntimeException("Product Not Found");
//		}
//
////		List<ProductVoteDTO> list = productVoteRepository.getVoteByProduct2(code);
////		System.out.println("====="+list.size());
////		return null;
//	}
//
//}
