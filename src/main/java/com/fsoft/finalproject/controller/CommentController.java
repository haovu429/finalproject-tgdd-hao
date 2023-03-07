package com.fsoft.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.CommentDTO;
import com.fsoft.finalproject.service.CommentService;

@RestController
@RequestMapping(path = "/api/v1")
public class CommentController {
  @Autowired private CommentService commentService;

  @PostMapping("/subComment")
  ResponseObject save(@RequestBody CommentDTO comment) {

    return commentService.save( comment);
  }

  @PutMapping("/subComment/{idSub}")
  ResponseObject update(@PathVariable("idSub") long idSub, @RequestBody CommentDTO comment) {

    return commentService.updateContent(idSub, comment);
  }

  @DeleteMapping("/subComment/{id}")
  ResponseObject delete(@PathVariable("id") long id) {

    return commentService.delete(id);
  }

  @GetMapping("/numLikeSubComment/{id}")
  ResponseObject getNumLikes(@PathVariable("id") long id) {
    return commentService.getLike(id);
  }

  @GetMapping("/updateIncreaseLikes/{id}")
  ResponseObject updateIncreaseLikes(@PathVariable("id") long id) {
    return commentService.updateIncreaseLikes(id);
  }

  @GetMapping("/updateDecreaseLikes/{id}")
  ResponseObject updateDecreaseLikes(@PathVariable("id") long id) {
    return commentService.updateDecreaseLikes(id);
  }

}
