package com.fsoft.finalproject.data;

import com.fsoft.finalproject.entity.ImageEntity;
import com.fsoft.finalproject.entity.LaptopEntity;
import com.fsoft.finalproject.entity.ManufacturerEntity;
import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitData {

  @Autowired private LaptopRepository laptopRepository;

  @Autowired private ManufacturerRepository manufacturerRepository;

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
  @Autowired private VoteRepository voteRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private ImageRepository imageRepository;

  public void initData() {
    ManufacturerEntity manufacturer1 = new ManufacturerEntity(0, "Xiaomi", new ArrayList<>());
    ManufacturerEntity manufacturer2 = new ManufacturerEntity(0, "Asus", new ArrayList<>());
    ManufacturerEntity manufacturer3 = new ManufacturerEntity(0, "Dell", new ArrayList<>());
    ManufacturerEntity manufacturer4 = new ManufacturerEntity(0, "Apple", new ArrayList<>());
    ManufacturerEntity manufacturer5 = new ManufacturerEntity(0, "Samsung", new ArrayList<>());

    List<ManufacturerEntity> manufacturerEntities = new ArrayList<>();
    manufacturerEntities.add(manufacturer1);
    manufacturerEntities.add(manufacturer2);
    manufacturerEntities.add(manufacturer3);
    manufacturerEntities.add(manufacturer4);
    manufacturerEntities.add(manufacturer5);

    manufacturerRepository.saveAll(manufacturerEntities);

    ProductEntity product1 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X5320",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer1,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product2 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X50444",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer4,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product3 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X504112",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer2,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product4 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X50323",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer3,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product5 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X50231",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer1,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product6 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X501332",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer3,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product7 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X5230",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            25000000,
            "Android",
            manufacturer2,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product8 =
        new ProductEntity(
            0,
            "Macbook 2022",
            "mac2022v1",
            "Máy tính, Nhập Khẩu",
            "Chơi game siêu mượt",
            18000000,
            "Android",
            manufacturer4,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product9 =
        new ProductEntity(
            0,
            "Xiaomi A50",
            "X5043",
            "Điện thoại, Nhập Khẩu",
            "Chơi game siêu mượt",
            20000000,
            "Android",
            manufacturer1,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Dung lượng pin không được lớn",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ProductEntity product10 =
        new ProductEntity(
            0,
            "Dell Vostro 3400",
            "dell3400v",
            "Máy tính, Nhập Khẩu",
            "Chơi LOL siêu mượt",
            31000000,
            "Android",
            manufacturer3,
            2,
            "chưa cập nhật",
            "Hiệu năng mạnh mẽ, tiết kiệm điện, nhỏ gọn",
            "Bộ nhớ ít",
            "Dây Sạc, tai nghe",
            "Chưa cập nhật",
            5,
            4,
            null,
            null,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>());

    ImageEntity image1 = new ImageEntity(0, "abc123", product1);
    ImageEntity image2 = new ImageEntity(0, "abc1234", product1);
    ImageEntity image3 = new ImageEntity(0, "abc12345", product1);

    List<ImageEntity> imageEntities = new ArrayList<>();
    imageEntities.add(image1);
    imageEntities.add(image2);
    imageEntities.add(image3);

    imageRepository.saveAll(imageEntities);

    LaptopEntity laptop1 =
        new LaptopEntity(
            0, "i7", "16GB", "512SSD", "13.4 inch", null, null, null, null, null, product1);

    LaptopEntity laptop2 =
        new LaptopEntity(
            0, "i7", "16GB", "512SSD", "16.5 inch", null, null, null, null, null, product2);

    LaptopEntity laptop3 =
        new LaptopEntity(0, "i7", "32GB", "2TB", "15 inch", null, null, null, null, null, product3);

    LaptopEntity laptop4 =
        new LaptopEntity(
            0, "i7", "8GB", "1TB", "13.4 inch", null, null, null, null, null, product4);
    LaptopEntity laptop5 =
        new LaptopEntity(
            0, "i7", "16GB", "250SSD", "16 inch", null, null, null, null, null, product5);
    List<LaptopEntity> laptopEntities = new ArrayList<>();
    laptopEntities.add(laptop1);
    laptopEntities.add(laptop2);
    laptopEntities.add(laptop3);
    laptopEntities.add(laptop4);
    laptopEntities.add(laptop5);

    laptopRepository.saveAll(laptopEntities);
  }
}
