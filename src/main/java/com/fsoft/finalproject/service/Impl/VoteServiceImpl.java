package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.BillDetailDTO;
import com.fsoft.finalproject.dto.CommentDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.VoteDTO;
import com.fsoft.finalproject.entity.CommentEntity;
import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.entity.ProductStoreEntity;
import com.fsoft.finalproject.entity.VoteEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.CommentRepository;
import com.fsoft.finalproject.repository.ProductRepository;
import com.fsoft.finalproject.repository.ProductStoreRepository;
import com.fsoft.finalproject.repository.VoteRepository;
import com.fsoft.finalproject.service.VoteService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoteServiceImpl implements VoteService {
  @Autowired private VoteRepository voteRepository;

  @Autowired private CommentRepository commentRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private Converter converter;
  @Autowired private ProductStoreRepository productStoreRepository;

  ModelMapper mapper = new ModelMapper();

  @Override
  public ResponseObject save(VoteDTO voteDTO) {
    Date date = new Date();
    voteDTO.setTime(date);

    ProductEntity product = productRepository.getProductByCode(voteDTO.getProductCode());
    if (product == null) {
      throw new CustomException( "Cannot Find Product Code = " + voteDTO.getProductCode());
    } else {
      if(voteDTO.getRate() >10 || voteDTO.getRate() < 0 || (voteDTO.getRate() > 0 && voteDTO.getRate() <1)){
        throw new CustomException( "Rating greater than 1 and less than 10");
      }
      VoteEntity voteEntity = converter.toEntity(voteDTO);
      voteEntity = voteRepository.save(voteEntity);
      return new ResponseObject(converter.toDTO(voteEntity));
    }
  }

  @Override
  public ResponseObject updateContent(long id, VoteDTO voteDTO) {
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    VoteEntity voteEntity =
            voteRepository
                    .findById(id)
                    .map(
                            comment -> {
                              comment.setContent(voteDTO.getContent());
                              comment.setImage(voteDTO.getImage());
                              comment.setTime(date);
                              return voteRepository.save(comment);
                            })
                    .orElse(null);

    if (voteEntity == null) {
      throw new CustomException( "Cannot Find Id Comment = " + id);
    } else {
      return new ResponseObject(converter.toDTO(voteEntity));
    }
  }

  @Override
  public ResponseObject updateInteractive(long id, String type, String change) throws CustomException {
    VoteEntity voteEntity =
            voteRepository
                    .findById(id)
                    .map(
                            vote -> {
                              if (type.equals("like")) {
                                if(change.equals("increase")){
                                  vote.setNumLike(vote.getNumLike()+1);
                                }
                                else if(change.equals("decrease")){
                                  vote.setNumLike(vote.getNumLike()-1);
                                }
                              }
                              else if(type.equals("unlike")){
                                if(change.equals("increase")){
                                  vote.setNumUnlike(vote.getNumUnlike()+1);
                                }
                                else if(change.equals("decrease")){
                                  vote.setNumUnlike(vote.getNumUnlike()-1);
                                }
                              }
                              return voteRepository.save(vote);
                            })
                    .orElse(null);

    if (voteEntity == null) {
      throw new CustomException( "Cannot Find Id Comment = " + id);
    } else {
      return new ResponseObject(converter.toDTO(voteEntity));
    }
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = voteRepository.existsById(id);
    if (exists) {
      voteRepository.deleteById(id);
      return new ResponseObject(200, "Deleted Id Comment = " + id, null);
    } else {
      throw new CustomException("Cannot Find Id Comment = " + id);
    }
  }

  @Override
  public ResponseObject getCommentByVoteId(long id, Pageable pageable) {

    List<CommentDTO> comments = commentRepository.getCommentByVoteId(id,pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());
    Optional<VoteEntity> vote = voteRepository.findById(id);
    if (vote.isPresent()) {
      return new ResponseObject(comments);
    } else {
      throw new CustomException("Cannot Find Id Vote = " + id);
    }
  }

  @Override
  public ResponseObject getVoteByProductCode(String code, Pageable pageable) throws CustomException {
    List<VoteDTO> votes = voteRepository.getVoteByProductCode(code,pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());
    ProductEntity product = productRepository.getProductByCode(code);
    if (product != null) {
      return new ResponseObject(votes);
    } else {
      throw new CustomException("Cannot Find Code Product = " + code);
    }
  }

//  @Override
//  public ResponseEntity<ResponseObject> updateNumSubComment(long id, String type) {
//    VoteEntity voteEntity =
//        voteRepository
//            .findById(id)
//            .map(
//                comment -> {
//                  if (type.equals("increase")) {
//                    comment.setNumSubComment(comment.getNumSubComment() + 1);
//                  } else if (type.equals("decrease")) {
//                    comment.setNumSubComment(comment.getNumSubComment() - 1);
//                  }
//                  return voteRepository.save(comment);
//                })
//            .orElse(null);
//
//    if (voteEntity == null) {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND)
//          .body(new ResponseObject("404", "Cannot Find Id Comment = " + id, ""));
//    } else {
//      return ResponseEntity.status(HttpStatus.OK)
//          .body(
//              new ResponseObject(
//                  "200",
//                  "Update NumSubComment Successfully",
//                  commentConverter.toDTO(voteEntity)));
//    }
//  }

  @Override
  public ResponseObject getInteractive(long id) {
    VoteEntity voteEntity = voteRepository.findById(id).orElse(null);
    if (voteEntity != null) {
      int likes = voteEntity.getNumLike();
      int unLikes = voteEntity.getNumUnlike();
      JSONObject jo = new JSONObject();
      jo.put("num_like", likes);
      jo.put("num_unLikes", unLikes);
      return new ResponseObject(jo.toMap());
    } else {
      throw new CustomException("Cannot Find Id Comment = " + id);
    }
  }

  @Override
  public ResponseObject getAvgVote(String code) throws CustomException {
    ProductEntity product = productRepository.getByProductCode(code);
    if (product != null) {
      Double avg=0.0;
      int count =0;
      for(VoteEntity i : product.getVoteEntities()){
        if(i.getRate()!=0) {
          avg = avg + i.getRate();
          count++;
        }
      }
      avg = avg/count;
      return new ResponseObject(avg);
    } else {
      throw new CustomException("Cannot Find Code Product = " + code);
    }
  }

  @Override
  public ResponseObject getNumVote(String code) throws CustomException {
    ProductEntity product = productRepository.getByProductCode(code);
    if (product != null) {
      int count=0;
      for(VoteEntity i : product.getVoteEntities()){
        if(i.getRate()!=0) {
          count++;
        }
      }
      return new ResponseObject(count);
    } else {
      throw new CustomException("Cannot Find Code Product = " + code);
    }
  }

  @Override
  public ResponseObject getAvgVote(long id) throws CustomException {
    ProductEntity product = productRepository.findOneById(id);
    if (product != null) {
      Double avg=0.0;
      int count =0;
      for(VoteEntity i : product.getVoteEntities()){
        if(i.getRate()!=0) {
          avg = avg + i.getRate();
          count++;
        }
      }
      avg = avg/count;
      return new ResponseObject(avg);
    } else {
      throw new CustomException("Cannot Find id Product = " + id);
    }
  }

  @Override
  public ResponseObject getNumVote(long id) throws CustomException {
    ProductEntity product = productRepository.findOneById(id);
    if (product != null) {
      int count=0;
      for(VoteEntity i : product.getVoteEntities()){
        if(i.getRate()!=0) {
          count++;
        }
      }
      return new ResponseObject(count);
    } else {
      throw new CustomException("Cannot Find id Product = " + id);
    }
  }

  @Override
  public ResponseObject getVoteByProductId(long id, Pageable pageable) throws CustomException {
    List<VoteDTO> votes = voteRepository.getVoteByProductId(id,pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());
    ProductEntity product = productRepository.findOneById(id);
    if (product!=null) {
      return new ResponseObject(votes);
    } else {
      throw new CustomException("Cannot Find id Product = " + id);
    }
  }

  @Override
  public ResponseObject getCommentByProductId(long id, Pageable pageable) throws CustomException {
    List<VoteDTO> votes = voteRepository.getCommentByProductId(id,pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());
    ProductEntity product = productRepository.findOneById(id);
    if (product!=null) {
      return new ResponseObject(votes);
    } else {
      throw new CustomException("Cannot Find id Product = " + id);
    }
  }

  @Override
  public ResponseObject getRatingStatistics(long id) throws CustomException {
    ProductEntity product = productRepository.findOneById(id);
    if (product != null) {
      int verygood=0;
      int good = 0;
      int medium=0;
      int bad =0;
      for(VoteEntity i : product.getVoteEntities()){
        if(i.getRate()>= 8.5) {
          verygood++;
        }
        else if(i.getRate() >=7 && i.getRate()<8.5){
          good ++;
        }
        else if(i.getRate() >=5 && i.getRate()<7 ){
          medium++;
        }
        else if(i.getRate() >=1 && i.getRate()<5 ){
          bad++;
        }
      }
    //Tinh Phan Tram
      int total = verygood + good + bad + medium;
      Double percentVeryGood = Double.valueOf(verygood)/total *100;
      Double percentGood = Double.valueOf(good)/total *100;
      Double percentMedium = Double.valueOf(medium)/total *100;
      Double percentBad = Double.valueOf(bad)/total *100;

      //Return JsonObject
      JSONArray ja = new JSONArray();

      JSONObject jVery = new JSONObject();
      jVery.put("num", verygood);
      jVery.put("percent", percentVeryGood);
      JSONObject jSonVery = new JSONObject();
      jSonVery.put("8.5-10.0",jVery);
      //========
      JSONObject jVGood = new JSONObject();
      jVGood.put("num", good);
      jVGood.put("percent", percentGood);
      JSONObject jSonGood = new JSONObject();
      jSonVery.put("7-8.5",jVGood);
      //=========
      JSONObject jVMedium = new JSONObject();
      jVMedium.put("num", medium);
      jVMedium.put("percent", percentMedium);
      JSONObject jSonMedium = new JSONObject();
      jSonVery.put("5-7",jVMedium);
      //=========
      JSONObject jBad = new JSONObject();
      jBad.put("num", bad);
      jBad.put("percent", percentBad);
      JSONObject jSonBad = new JSONObject();
      jSonVery.put("1-5",jBad);

      //=========
      ja.put(jVery);
      ja.put(jVGood);
      ja.put(jVMedium);
      ja.put(jBad);
      //========
      JSONObject results = new JSONObject();
      results.put("rating",ja);


      return new ResponseObject(results.toMap());
    } else {
      throw new CustomException("Cannot Find id Product = " + id);
    }
  }

  @Override
  public long getQuantityProductByAddressStore(long idProduct, long idWard) {
    ProductStoreEntity store = productStoreRepository.getQuantityProductInStore(idProduct, idWard);
    if(store!=null) {
      System.out.println("======================"+store.getQuantity());
      return store.getQuantity();
    }
    else{
      return -1;
    }
  }

}
