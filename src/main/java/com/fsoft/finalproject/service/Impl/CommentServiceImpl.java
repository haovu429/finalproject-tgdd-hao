package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.CommentDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.CommentEntity;
import com.fsoft.finalproject.entity.VoteEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.CommentRepository;
import com.fsoft.finalproject.repository.VoteRepository;
import com.fsoft.finalproject.service.CommentService;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired private CommentRepository commentRepository;

  @Autowired private VoteRepository voteRepository;

  @Autowired private Converter converter;

  ModelMapper mapper = new ModelMapper();

  //  @Override
  //  public ResponseEntity<ResponseObject> save(long id, CommentDTO comment) {
  //    VoteEntity voteEntity = voteRepository.findById(id).orElse(null);
  //    if (voteEntity != null) {
  //      Date date = new Date();
  //      CommentEntity subComment = mapper.map(comment, CommentEntity.class);
  //      subComment.setVoteEntity(voteEntity);
  //      subComment.setTime(date);
  //      // ===
  //      voteEntity.getCommentEntities().add(subComment);
  //
  //      subComment = commentRepository.save(subComment);
  //      voteService.updateNumSubComment(id, "increase");
  //      return ResponseEntity.status(HttpStatus.OK)
  //          .body(
  //              new ResponseObject(
  //                  "200",
  //                  "Add Sub Comment Successfully With Comment Id=" + id,
  //                  mapper.map(subComment, CommentDTO.class)));
  //    } else {
  //      return ResponseEntity.status(HttpStatus.NOT_FOUND)
  //          .body(new ResponseObject("404", "Cannot Find Comment Id = " + id, ""));
  //    }
  //  }

  // Hao sua
  @Override
  public ResponseObject save( CommentDTO comment) {
    VoteEntity voteEntity = voteRepository.findById(comment.getVoteId()).orElse(null);
    if (voteEntity != null) {
      Date date = new Date();
      comment.setNumLike(0);
      comment.setTime(date);
      CommentEntity subComment = converter.toEntity(comment);
      voteEntity.getCommentEntities().add(subComment);
      subComment = commentRepository.save(subComment);
      return new ResponseObject(converter.toDTO(subComment));
    } else {
      throw new CustomException("Cannot Find Comment Id = " + comment.getVoteId());
    }
  }

  @Override
  public ResponseObject updateContent(long id_sub, CommentDTO comment) {

    Date date = new Date();
    CommentEntity subComment =
        commentRepository
            .findById(id_sub)
            .map(
                sub -> {
                  sub.setContent(comment.getContent());
                  sub.setImage(comment.getImage());
                  sub.setTime(date);
                  return commentRepository.save(sub);
                })
            .orElse(null);

    if (subComment != null) {
      return new ResponseObject(converter.toDTO(subComment));
    } else {
      throw new CustomException("Cannot Find SubComment Id = " + id_sub);
    }
  }

  //  @Override
  //  public ResponseEntity<ResponseObject> delete(long id_sub) {
  //    // TODO Auto-generated method stub
  //    boolean exists = commentRepository.existsById(id_sub);
  //    if (exists) {
  //      CommentEntity subComment = commentRepository.findById(id_sub).orElse(null);
  //      commentRepository.deleteById(id_sub);
  //      voteService.updateNumSubComment(subComment.getVoteEntity().getId(), "decrease");
  //      return ResponseEntity.status(HttpStatus.OK)
  //          .body(
  //              new ResponseObject("200", "Delete Sub Comment Successfully With  Id=" + id_sub,
  // ""));
  //    } else {
  //      return ResponseEntity.status(HttpStatus.NOT_FOUND)
  //          .body(new ResponseObject("404", "Cannot Find SubComment Id = " + id_sub, ""));
  //    }
  //  }

  // Hao sua
  @Override
  public ResponseObject delete(long id_sub) {
    // TODO Auto-generated method stub
    boolean exists = commentRepository.existsById(id_sub);
    if (exists) {
      //CommentEntity subComment = commentRepository.findById(id_sub).orElse(null);
      commentRepository.deleteById(id_sub);
      // voteService.updateNumSubComment(subComment.getVoteEntity().getId(), "decrease");
      return new ResponseObject(200, "Delete Sub Comment Successfully With  Id=" + id_sub, null);
    } else {
      throw new CustomException("Cannot Find SubComment Id = " + id_sub);
    }
  }

  @Override
  public ResponseObject getLike(long id_sub) {
    CommentEntity subComment = commentRepository.findById(id_sub).orElse(null);
    if (subComment != null) {
      int likes = subComment.getNumLike();
      JSONObject jo = new JSONObject();
      jo.put("num_like", likes);
      return new ResponseObject(jo.toMap());
    } else {
      throw new CustomException("Cannot Find SubComment Id = " + id_sub);
    }
  }

  @Override
  public ResponseObject updateIncreaseLikes(long id_sub) {
    CommentEntity subComment =
        commentRepository
            .findById(id_sub)
            .map(
                sub -> {
                  sub.setNumLike(sub.getNumLike() + 1);
                  return commentRepository.save(sub);
                })
            .orElse(null);

    if (subComment != null) {
      return new ResponseObject( subComment.getNumLike());
    } else {
      throw new CustomException("Cannot Find SubComment Id = " + id_sub);
    }
  }

  @Override
  public ResponseObject updateDecreaseLikes(long id_sub) {
    CommentEntity subComment =
        commentRepository
            .findById(id_sub)
            .map(
                sub -> {
                  sub.setNumLike(sub.getNumLike() - 1);
                  return commentRepository.save(sub);
                })
            .orElse(null);

    if (subComment != null) {
      return new ResponseObject(subComment.getNumLike());
    } else {
      throw new CustomException("Cannot Find SubComment Id = " + id_sub);
    }
  }
}
