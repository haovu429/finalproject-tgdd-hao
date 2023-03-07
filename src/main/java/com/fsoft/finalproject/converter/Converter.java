package com.fsoft.finalproject.converter;

import com.fsoft.finalproject.dto.*;
import com.fsoft.finalproject.entity.*;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.exception.CustomerException;
import com.fsoft.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Converter {

  @Autowired private AddressRepository addressRepository;
  @Autowired private ProvinceRepository provinceRepository;
  @Autowired private DistrictRepository districtRepository;
  @Autowired private WardRepository wardRepository;
  @Autowired private BillRepository billRepository;
  @Autowired private ProductRepository productRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private StoreRepository storeRepository;
  @Autowired private PaymentRepository paymentRepository;
  @Autowired private OrderRepository orderRepository;
  @Autowired private ManufacturerRepository manufacturerRepository;
  @Autowired private VoteRepository voteRepository;
  @Autowired private RoleRepository roleRepository;

  public AddressDTO toDTO(AddressEntity entity) {
    AddressDTO addressDTO = new AddressDTO();
    // convert fields
    addressDTO.setId(entity.getId());
    if (entity.getWardEntity() != null) {
      addressDTO.setWardId(entity.getWardEntity().getId());
    }
    addressDTO.setNumHouse(entity.getNumHouse());

    return addressDTO;
  }

  public AddressResponseDTO toAddressResponseDTO(AddressEntity entity) {
    AddressResponseDTO addressDTO = new AddressResponseDTO();
    // convert fields
    addressDTO.setId(entity.getId());
    //    Ward
    WardDTO wardDTO = toDTO(entity.getWardEntity());
    addressDTO.setWard(wardDTO);
    //   District
    DistrictDTO districtDTO = toDTO(entity.getWardEntity().getDistrictEntity());
    addressDTO.setDistrict(districtDTO);
    //   Province
    ProvinceDTO provinceDTO = toDTO(entity.getWardEntity().getDistrictEntity().getProvinceEntity());
    addressDTO.setProvince(provinceDTO);

    addressDTO.setNumHouse(entity.getNumHouse());

    return addressDTO;
  }

  public AddressEntity toEntity(AddressDTO dto) throws CustomerException {
    AddressEntity addressEntity = new AddressEntity();
    // convert fields
    addressEntity.setId(dto.getId());

    Optional<WardEntity> ward = wardRepository.findById(dto.getWardId());
    if (ward.isPresent()) {
      addressEntity.setWardEntity(ward.get());
    } else {
      throw new CustomerException("Can't convert to entity");
    }

    addressEntity.setNumHouse(dto.getNumHouse());

    return addressEntity;
  }

  public AddressEntity toEntity(CustomerAddressDTO dto) throws CustomException {
    AddressEntity addressEntity = new AddressEntity();
    // convert fields
    addressEntity.setId(dto.getId());
    Optional<WardEntity> ward = wardRepository.findById(dto.getWardId());
    if (ward.isPresent()) {
      addressEntity.setWardEntity(ward.get());
    } else {
      throw new CustomException("Can't convert Product in VoteEntity");
    }
    addressEntity.setNumHouse(dto.getNumHouse());

    Optional<CustomerEntity> customer = customerRepository.findById(dto.getCustomerId());
    if (customer.isPresent()) {
      addressEntity.setCustomerEntity(customer.get());
    } else {
      throw new CustomException("Can't convert Product in VoteEntity");
    }

    return addressEntity;
  }

  // Bill
  public BillDTO toDTO(BillEntity entity) {
    BillDTO billDTO = new BillDTO();
    billDTO.setId(entity.getId());
    billDTO.setBillCode(entity.getBillCode());
    billDTO.setStatus(entity.getStatus());
    billDTO.setStoreAddress(entity.getStoreAddress());
    billDTO.setTotalPrice(entity.getTotalPrice());
    billDTO.setCustomerName(entity.getCustomerName());
    billDTO.setCustomerPhone(entity.getCustomerPhone());
    billDTO.setReceiverAddress(entity.getReceiverAddress());
    billDTO.setReceivingTime(entity.getReceivingTime());
    billDTO.setNote(entity.getNote());

    return billDTO;
  }

  public BillEntity toEntity(BillDTO dto) {
    BillEntity billEntity = new BillEntity();
    billEntity.setId(dto.getId());
    billEntity.setBillCode(dto.getBillCode());
    billEntity.setStatus(dto.getStatus());
    billEntity.setStoreAddress(dto.getStoreAddress());
    billEntity.setTotalPrice(dto.getTotalPrice());
    billEntity.setCustomerName(dto.getCustomerName());
    billEntity.setReceiverAddress(dto.getReceiverAddress());
    billEntity.setReceivingTime(dto.getReceivingTime());
    billEntity.setNote(dto.getNote());

    return billEntity;
  }

  // BillDetail
  public BillDetailDTO toDTO(BillDetailEntity entity) {
    BillDetailDTO billDetailDTO = new BillDetailDTO();
    billDetailDTO.setId(entity.getId());
    billDetailDTO.setProductImage(entity.getProductImage());
    billDetailDTO.setProductName(entity.getProductName());
    billDetailDTO.setOriginPrice(entity.getOriginPrice());
    billDetailDTO.setSalePrice(entity.getSalePrice());
    if (entity.getBillEntity() != null) {
      billDetailDTO.setBillId(entity.getBillEntity().getId());
    }

    return billDetailDTO;
  }

  public BillDetailEntity toEntity(BillDetailDTO dto) {
    BillDetailEntity billDetailEntity = new BillDetailEntity();
    billDetailEntity.setId(dto.getId());
    billDetailEntity.setProductImage(dto.getProductImage());
    billDetailEntity.setProductName(dto.getProductName());
    billDetailEntity.setOriginPrice(dto.getOriginPrice());
    billDetailEntity.setSalePrice(dto.getSalePrice());

    Optional<BillEntity> billEntity = billRepository.findById(dto.getId());
    if (billEntity.isPresent()) {
      billDetailEntity.setBillEntity(billEntity.get());
    } else {
      throw new CustomerException("Can't convert bill in bill detail");
    }

    return billDetailEntity;
  }

  // Comment
  public VoteDTO toDTO(VoteEntity entity) {
    VoteDTO voteDTO = new VoteDTO();
    voteDTO.setId(entity.getId());
    voteDTO.setGuestName(entity.getGuestName());
    voteDTO.setGuestPhone(entity.getGuestPhone());
    voteDTO.setContent(entity.getContent());
    voteDTO.setNumLike(entity.getNumLike());
    voteDTO.setNumUnlike(entity.getNumUnlike());
    voteDTO.setRate(entity.getRate());
    voteDTO.setTime(entity.getTime());
    voteDTO.setImage(entity.getImage());
    if (entity.getProductEntity() != null) {
      voteDTO.setProductCode(entity.getProductEntity().getProductCode());
    }

    return voteDTO;
  }

  public VoteEntity toEntity(VoteDTO dto) throws CustomerException {
    VoteEntity voteEntity = new VoteEntity();
    voteEntity.setGuestName(dto.getGuestName());
    voteEntity.setGuestPhone(dto.getGuestPhone());
    voteEntity.setContent(dto.getContent());
    voteEntity.setRate(dto.getRate());
    voteEntity.setNumLike(dto.getNumLike());
    voteEntity.setNumUnlike(dto.getNumUnlike());
    voteEntity.setTime(dto.getTime());
    voteEntity.setImage(dto.getImage());

    ProductEntity product = productRepository.getProductByCode(dto.getProductCode());
    if (product != null) {
      voteEntity.setProductEntity(product);
    } else {
      throw new CustomerException("Can't convert Product in Vote");
    }

    return voteEntity;
  }

  // Customer
  public CustomerDTO toDTO(CustomerEntity entity) {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setId(entity.getId());
    customerDTO.setFullName(entity.getFullName());
    customerDTO.setEmail(entity.getEmail());
    customerDTO.setPhone(entity.getPhone());
    customerDTO.setGender(entity.getGender());

    return customerDTO;
  }

  public CustomerEntity toEntity(CustomerDTO dto) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(dto.getId());
    customerEntity.setFullName(dto.getFullName());
    customerEntity.setEmail(dto.getEmail());
    customerEntity.setPhone(dto.getPhone());
    customerEntity.setGender(dto.getGender());

    return customerEntity;
  }

  // District
  public DistrictDTO toDTO(DistrictEntity entity) {
    DistrictDTO districtDTO = new DistrictDTO();
    districtDTO.setId(entity.getId());
    districtDTO.setName(entity.getName());
    if (entity.getProvinceEntity() != null) {
      districtDTO.setProvinceId(entity.getProvinceEntity().getId());
    }

    return districtDTO;
  }

  public DistrictEntity toEntity(DistrictDTO dto) {
    DistrictEntity districtEntity = new DistrictEntity();
    districtEntity.setId(dto.getId());
    districtEntity.setName(dto.getName());

    Optional<ProvinceEntity> province = provinceRepository.findById(dto.getProvinceId());
    if (province.isPresent()) {
      districtEntity.setProvinceEntity(province.get());
    } else {
      throw new CustomerException("Can't convert Province in District");
    }
    return districtEntity;
  }

  // Image
  public ImageDTO toDTO(ImageEntity entity) {
    ImageDTO imageDTO = new ImageDTO();
    imageDTO.setId(entity.getId());
    imageDTO.setLink(entity.getLink());
    if (entity.getProductEntity() != null) {
      imageDTO.setProductId(entity.getProductEntity().getId());
    }
    return imageDTO;
  }

  public ImageEntity toEntity(ImageDTO dto) {
    ImageEntity imageEntity = new ImageEntity();
    imageEntity.setId(dto.getId());
    imageEntity.setLink(dto.getLink());

    Optional<ProductEntity> product = productRepository.findById(dto.getProductId());
    if (product.isPresent()) {
      imageEntity.setProductEntity(product.get());
    } else {
      throw new CustomerException("Can't convert Product in Image");
    }

    return imageEntity;
  }

  // Laptop
  public LaptopDTO toDTO(LaptopEntity entity) {
    LaptopDTO laptopDTO = new LaptopDTO();
    laptopDTO.setId(entity.getId());
    laptopDTO.setCpu(entity.getCpu());
    laptopDTO.setRam(entity.getRam());
    laptopDTO.setHardDrive(entity.getHardDrive());
    laptopDTO.setGraphicsCard(entity.getGraphicsCard());
    laptopDTO.setConnectionPort(entity.getConnectionPort());
    laptopDTO.setDesign(entity.getDesign());
    laptopDTO.setSizeWeight(entity.getSizeWeight());
    laptopDTO.setReleaseDate(entity.getReleaseDate());
    laptopDTO.setScreen(entity.getScreen());
    return laptopDTO;
  }

  public LaptopEntity toEntity(LaptopDTO dto) {
    LaptopEntity laptopEntity = new LaptopEntity();
    laptopEntity.setId(dto.getId());
    laptopEntity.setCpu(dto.getCpu());
    laptopEntity.setRam(dto.getRam());
    laptopEntity.setHardDrive(dto.getHardDrive());
    laptopEntity.setScreen(dto.getScreen());
    laptopEntity.setGraphicsCard(dto.getGraphicsCard());
    laptopEntity.setConnectionPort(dto.getConnectionPort());
    laptopEntity.setDesign(dto.getDesign());
    laptopEntity.setSizeWeight(dto.getSizeWeight());
    laptopEntity.setReleaseDate(dto.getReleaseDate());

    if (dto.getId() > 0) {
      Optional<ProductEntity> product = productRepository.findById(dto.getId());
      if (product.isPresent()) {
        laptopEntity.setProductEntity(product.get());
      } else {
        throw new CustomerException("Can't convert Product in Laptop");
      }
    }
    return laptopEntity;
  }

  // Manufacturer
  public ManufacturerDTO toDTO(ManufacturerEntity entity) {
    ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
    manufacturerDTO.setId(entity.getId());
    manufacturerDTO.setName(entity.getName());
    return manufacturerDTO;
  }

  public ManufacturerEntity toEntity(ManufacturerDTO dto) {
    ManufacturerEntity manufacturerEntity = new ManufacturerEntity();
    manufacturerEntity.setId(dto.getId());
    manufacturerEntity.setName(dto.getName());
    return manufacturerEntity;
  }

  // Order
  public OrderDTO toDTO(OrderEntity entity) {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setId(entity.getId());
    orderDTO.setDate(entity.getDate());
    orderDTO.setTotal(entity.getTotal());
    orderDTO.setOrderCode(entity.getOrderCode());
    orderDTO.setStatus(entity.getStatus());
    orderDTO.setNote(entity.getNote());
    if (entity.getStoreEntity() != null) {
      orderDTO.setStoreId(entity.getStoreEntity().getId());
    }

    if (entity.getCustomerEntity() != null) {
      orderDTO.setCustomerId(entity.getCustomerEntity().getId());
    }

    if (entity.getPaymentEntity() != null) {
      orderDTO.setCustomerId(entity.getPaymentEntity().getId());
    }

    return orderDTO;
  }

  public OrderEntity toEntity(OrderDTO dto) {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setId(dto.getId());
    orderEntity.setDate(dto.getDate());
    orderEntity.setTotal(dto.getTotal());
    orderEntity.setOrderCode(dto.getOrderCode());
    orderEntity.setStatus(dto.getStatus());
    orderEntity.setNote(dto.getNote());

    Optional<StoreEntity> store = storeRepository.findById(dto.getId());
    if (store.isPresent()) {
      orderEntity.setStoreEntity(store.get());
    } else {
      throw new CustomerException("Can't convert Store in Order");
    }

    Optional<CustomerEntity> customer = customerRepository.findById(dto.getCustomerId());
    if (customer.isPresent()) {
      orderEntity.setCustomerEntity(customer.get());
    } else {
      throw new CustomerException("Can't convert Customer in Order");
    }

    Optional<PaymentEntity> payment = paymentRepository.findById(dto.getPaymentId());
    if (payment.isPresent()) {
      orderEntity.setPaymentEntity(payment.get());
    } else {
      throw new CustomerException("Can't convert Payment in Order");
    }
    return orderEntity;
  }

  // OrderDetail
  public OrderDetailDTO toDTO(OrderDetailEntity entity) {
    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
    orderDetailDTO.setId(entity.getId());
    orderDetailDTO.setQuantity(entity.getQuantity());
    orderDetailDTO.setPrice(entity.getPrice());

    if (entity.getProductEntity() != null) {
      orderDetailDTO.setProductId(entity.getProductEntity().getId());
    }
    if (entity.getOrderEntity() != null) {
      orderDetailDTO.setOrderId(entity.getOrderEntity().getId());
    }

    return orderDetailDTO;
  }

  public OrderDetailEntity toEntity(OrderDetailDTO dto) {
    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
    orderDetailEntity.setId(dto.getId());
    orderDetailEntity.setQuantity(dto.getQuantity());
    orderDetailEntity.setPrice(dto.getPrice());

    Optional<ProductEntity> product = productRepository.findById(dto.getProductId());
    if (product.isPresent()) {
      orderDetailEntity.setProductEntity(product.get());
    } else {
      throw new CustomerException("Can't convert Product in OrderDetail");
    }

    Optional<OrderEntity> order = orderRepository.findById(dto.getOrderId());
    if (order.isPresent()) {
      orderDetailEntity.setOrderEntity(order.get());
    } else {
      throw new CustomerException("Can't convert Order in OrderDetail");
    }
    return orderDetailEntity;
  }

  // Payment
  public PaymentDTO toDTO(PaymentEntity entity) {
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setId(entity.getId());
    paymentDTO.setName(entity.getName());
    return paymentDTO;
  }

  public PaymentEntity toEntity(PaymentDTO dto) {
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setId(dto.getId());
    paymentEntity.setName(dto.getName());
    return paymentEntity;
  }

  // Phone
  public PhoneDTO toDTO(PhoneEntity entity) {
    PhoneDTO phoneDTO = new PhoneDTO();
    phoneDTO.setId(entity.getId());
    phoneDTO.setScreen(entity.getScreen());
    phoneDTO.setCamera(entity.getCamera());
    phoneDTO.setFontCamera(entity.getFontCamera());
    phoneDTO.setCpu(entity.getCpu());
    phoneDTO.setRam(entity.getRam());
    phoneDTO.setMemory(entity.getMemory());
    phoneDTO.setChip(entity.getChip());
    phoneDTO.setPhoneJack(entity.getPhoneJack());
    phoneDTO.setCharge(entity.getCharge());
    phoneDTO.setPower(entity.getPower());
    phoneDTO.setSize(entity.getSize());
    return phoneDTO;
  }

  public PhoneEntity toEntity(PhoneDTO dto) {
    PhoneEntity phoneEntity = new PhoneEntity();
    phoneEntity.setId(dto.getId());
    phoneEntity.setScreen(dto.getScreen());
    phoneEntity.setCamera(dto.getCamera());
    phoneEntity.setFontCamera(dto.getFontCamera());
    phoneEntity.setCpu(dto.getCpu());
    phoneEntity.setRam(dto.getRam());
    phoneEntity.setMemory(dto.getMemory());
    phoneEntity.setChip(dto.getChip());
    phoneEntity.setPhoneJack(dto.getPhoneJack());
    phoneEntity.setCharge(dto.getCharge());
    phoneEntity.setPower(dto.getPower());
    phoneEntity.setSize(dto.getSize());
    return phoneEntity;
  }

  // Product
  public ProductDTO toDTO(ProductEntity entity) {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId(entity.getId());
    productDTO.setProductName(entity.getProductName());
    productDTO.setProductCode(entity.getProductCode());
    productDTO.setCategory(entity.getCategory());
    productDTO.setDescription(entity.getDescription());
    productDTO.setPrice(entity.getPrice());
    productDTO.setOs(entity.getOs());
    if (entity.getManufacturerEntity() != null) {
      productDTO.setManufacturerId(entity.getManufacturerEntity().getId());
    }

    productDTO.setWarranty(entity.getWarranty());
    productDTO.setStandardKit(entity.getStandardKit());
    productDTO.setAdvantages(entity.getAdvantages());
    productDTO.setDisadvantages(entity.getDisadvantages());
    productDTO.setIncluded(entity.getIncluded());
    productDTO.setArticle(entity.getArticle());
    productDTO.setNumSidePhoto(entity.getNumSidePhoto());
    productDTO.setNumView(entity.getNumView());
    //    convert Promotion to DTO
    List<PromotionDTO> promotionDTOs =
        entity.getPromotionEntities().stream()
            .map(promotionEntity -> toDTO(promotionEntity))
            .collect(Collectors.toList());
    productDTO.setPromotions(promotionDTOs);
    return productDTO;
  }

  public ProductEntity toEntity(ProductDTO dto) {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setId(dto.getId());
    productEntity.setProductName(dto.getProductName());
    productEntity.setProductCode(dto.getProductCode());
    productEntity.setCategory(dto.getCategory());
    productEntity.setDescription(dto.getDescription());
    productEntity.setPrice(dto.getPrice());
    productEntity.setOs(dto.getOs());
    productEntity.setWarranty(dto.getWarranty());
    productEntity.setStandardKit(dto.getStandardKit());
    productEntity.setAdvantages(dto.getAdvantages());
    productEntity.setDisadvantages(dto.getDisadvantages());
    productEntity.setIncluded(dto.getIncluded());
    productEntity.setArticle(dto.getArticle());
    productEntity.setNumSidePhoto(dto.getNumSidePhoto());
    productEntity.setNumView(dto.getNumView());

    Optional<ManufacturerEntity> manufacturer =
        manufacturerRepository.findById(dto.getManufacturerId());
    if (manufacturer.isPresent()) {
      productEntity.setManufacturerEntity(manufacturer.get());
    } else {
      throw new CustomerException("Can't convert Manufacturer in Product");
    }

    return productEntity;
  }

  // ProductStore
  public ProductStoreDTO toDTO(ProductStoreEntity entity) {
    ProductStoreDTO productStoreDTO = new ProductStoreDTO();
    productStoreDTO.setId(entity.getId());
    if (entity.getStoreEntity() != null) {
      productStoreDTO.setStoreId(entity.getStoreEntity().getId());
    }
    if (entity.getProductEntity() != null) {
      productStoreDTO.setProductId(entity.getProductEntity().getId());
    }

    return productStoreDTO;
  }

  public ProductStoreEntity toEntity(ProductStoreDTO dto) {
    ProductStoreEntity productStoreEntity = new ProductStoreEntity();
    productStoreEntity.setId(dto.getId());

    Optional<StoreEntity> store = storeRepository.findById(dto.getStoreId());
    if (store.isPresent()) {
      productStoreEntity.setStoreEntity(store.get());
    } else {
      throw new CustomerException("Can't convert Store in ProductStore");
    }

    Optional<ProductEntity> product = productRepository.findById(dto.getProductId());
    if (product.isPresent()) {
      productStoreEntity.setProductEntity(product.get());
    } else {
      throw new CustomerException("Can't convert Product in ProductStore");
    }
    return productStoreEntity;
  }

  // Promotion
  public PromotionDTO toDTO(PromotionEntity entity) {
    PromotionDTO promotionDTO = new PromotionDTO();
    promotionDTO.setId(entity.getId());
    promotionDTO.setStartDay(entity.getStartDay());
    promotionDTO.setEndDate(entity.getEndDate());
    promotionDTO.setDiscount(entity.getDiscount());
    promotionDTO.setDescription(entity.getDescription());
    promotionDTO.setType(entity.getType());
    promotionDTO.setPromoCode(entity.getPromoCode());
    return promotionDTO;
  }

  public PromotionEntity toEntity(PromotionDTO dto) {
    PromotionEntity promotionEntity = new PromotionEntity();
    promotionEntity.setId(dto.getId());
    promotionEntity.setStartDay(dto.getStartDay());
    promotionEntity.setEndDate(dto.getEndDate());
    promotionEntity.setDiscount(dto.getDiscount());
    promotionEntity.setDescription(dto.getDescription());
    promotionEntity.setType(dto.getType());
    promotionEntity.setPromoCode(dto.getPromoCode());
    return promotionEntity;
  }

  // Province
  public ProvinceDTO toDTO(ProvinceEntity entity) {
    ProvinceDTO provinceDTO = new ProvinceDTO();
    provinceDTO.setId(entity.getId());
    provinceDTO.setName(entity.getName());
    return provinceDTO;
  }

  public ProvinceEntity toEntity(ProvinceDTO dto) {
    ProvinceEntity provinceEntity = new ProvinceEntity();
    provinceEntity.setId(dto.getId());
    provinceEntity.setName(dto.getName());
    return provinceEntity;
  }

  // Store
  public StoreDTO toDTO(StoreEntity entity) {
    StoreDTO storeDTO = new StoreDTO();
    storeDTO.setId(entity.getId());
    storeDTO.setName(entity.getName());
    storeDTO.setPhone(entity.getPhone());
    storeDTO.setFax(entity.getFax());
    storeDTO.setEmail(entity.getEmail());
    storeDTO.setImage(entity.getImage());
    storeDTO.setDescription(entity.getDescription());
    storeDTO.setOpenHour(entity.getOpenHour());
    storeDTO.setIat(entity.getIat());
    storeDTO.setIng(entity.getIng());
    if (entity.getAddressEntity() != null) {
      storeDTO.setAddressId(entity.getAddressEntity().getId());
    }
    return storeDTO;
  }

  public StoreEntity toEntity(StoreDTO dto) {
    StoreEntity storeEntity = new StoreEntity();
    storeEntity.setId(dto.getId());
    storeEntity.setName(dto.getName());
    storeEntity.setPhone(dto.getPhone());
    storeEntity.setFax(dto.getFax());
    storeEntity.setEmail(dto.getEmail());
    storeEntity.setImage(dto.getImage());
    storeEntity.setDescription(dto.getDescription());
    storeEntity.setOpenHour(dto.getOpenHour());
    storeEntity.setIat(dto.getIat());
    storeEntity.setIng(dto.getIng());

    Optional<AddressEntity> address = addressRepository.findById(dto.getAddressId());
    if (address.isPresent()) {
      storeEntity.setAddressEntity(address.get());
    } else {
      throw new CustomerException("Can't convert Address in Store");
    }
    return storeEntity;
  }

  // SubComment
  public CommentDTO toDTO(CommentEntity entity) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(entity.getId());
    commentDTO.setGuestName(entity.getGuestName());
    commentDTO.setGuestPhone(entity.getGuestPhone());
    commentDTO.setContent(entity.getContent());
    commentDTO.setNumLike(entity.getNumLike());
    commentDTO.setTime(entity.getTime());
    commentDTO.setImage(entity.getImage());
    if (entity.getVoteEntity() != null) {
      commentDTO.setVoteId(entity.getVoteEntity().getId());
    }

    return commentDTO;
  }

  public CommentEntity toEntity(CommentDTO dto) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setId(dto.getId());
    commentEntity.setGuestName(dto.getGuestName());
    commentEntity.setGuestPhone(dto.getGuestPhone());
    commentEntity.setContent(dto.getContent());
    commentEntity.setNumLike(dto.getNumLike());
    commentEntity.setTime(dto.getTime());
    commentEntity.setImage(dto.getImage());

    Optional<VoteEntity> vote = voteRepository.findById(dto.getVoteId());
    if (vote.isPresent()) {
      commentEntity.setVoteEntity(vote.get());
    } else {
      throw new CustomerException("Can't convert Vote in Comment");
    }

    return commentEntity;
  }

  // User
  public UserDTO toDTO(UserEntity entity) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(entity.getId());
    userDTO.setEmail(entity.getEmail());
    userDTO.setName(entity.getName());
    userDTO.setPassword(entity.getPassword());
    userDTO.setAvatar(entity.getAvatar());
    userDTO.setIsActive(entity.getIsActive());

    return userDTO;
  }

  public UserEntity toEntity(UserDTO dto) {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(dto.getId());
    userEntity.setEmail(dto.getEmail());
    userEntity.setName(dto.getName());
    userEntity.setPassword(dto.getPassword());
    userEntity.setAvatar(dto.getAvatar());
    userEntity.setIsActive(dto.getIsActive());
    //    List<RoleEntity> roleEntities = dto.getRoles().stream().map(role -> toEntity(role))
    //            .collect(Collectors.toList());

    List<RoleEntity> roleEntities = new ArrayList<>();
    for (String code : dto.getRoleCodes()) {
      if (roleRepository.existsById(code)) {
        roleEntities.add(roleRepository.findOneByCode(code));
      } else {
        throw new CustomException("Can't convert! Not found role has role code = " + code);
      }
    }
    userEntity.setRoles(roleEntities);

    return userEntity;
  }

  //  Role
  public RoleDTO toDTO(RoleEntity entity) {
    RoleDTO roleDTO = new RoleDTO();
    roleDTO.setCode(entity.getCode());
    roleDTO.setName(entity.getName());
    return roleDTO;
  }

  public RoleEntity toEntity(RoleDTO dto) {
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setCode(dto.getCode());
    roleEntity.setName(dto.getName());
    return roleEntity;
  }

  // Ward
  public WardDTO toDTO(WardEntity entity) {
    WardDTO wardDTO = new WardDTO();
    wardDTO.setId(entity.getId());
    wardDTO.setName(entity.getName());
    if (entity.getDistrictEntity() != null) {
      wardDTO.setDistrictId(entity.getDistrictEntity().getId());
    }

    return wardDTO;
  }

  public WardEntity toEntity(WardDTO dto) {
    WardEntity wardEntity = new WardEntity();
    wardEntity.setId(dto.getId());
    wardEntity.setName(dto.getName());

    Optional<DistrictEntity> district = districtRepository.findById(dto.getDistrictId());
    if (district.isPresent()) {
      wardEntity.setDistrictEntity(district.get());
    } else {
      throw new CustomerException("Can't convert District in Ward");
    }
    return wardEntity;
  }
}
