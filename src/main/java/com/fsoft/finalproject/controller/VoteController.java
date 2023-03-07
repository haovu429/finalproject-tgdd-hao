package com.fsoft.finalproject.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fsoft.finalproject.dto.VoteDTO;
import com.fsoft.finalproject.dto.ResponseObject;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class VoteController {
  @Autowired private VoteService voteService;

  @PostMapping("/vote")
  ResponseObject save(@Validated @RequestBody VoteDTO comment) {
    //comment.setNumSubComment(0);
    comment.setNumLike(0);
    comment.setNumUnlike(0);
    return voteService.save(comment);
  }

  @DeleteMapping("/vote/{id}")
  ResponseObject delete(@PathVariable long id) {
    return voteService.delete(id);
  }

  @PutMapping("/vote/{id}")
  ResponseObject updateContent(
      @PathVariable long id, @Validated @RequestBody VoteDTO comment, BindingResult bindingResult){
	  if(bindingResult.hasErrors()){
		  throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
	  }
    return voteService.updateContent(id, comment);
  }

  @PutMapping("/interactive/update/{id}")
  ResponseObject updateInteractive(@PathVariable long id,@RequestBody ObjectNode objectNode) {
    String type = objectNode.get("type").asText();
    String status = objectNode.get("status").asText();
    return voteService.updateInteractive(id,type,status);
  }

  @PostMapping("/comment/by-vote-id/{id}")
  ResponseObject getSubComment(@PathVariable long id,@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5, Sort.by("time").descending());
    return voteService.getCommentByVoteId(id,pageable);
  }

  @GetMapping("/vote/by-product-code/{code}")
  ResponseObject getVoteByProductCode(@PathVariable String code,@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5, Sort.by("time").descending());
    return voteService.getVoteByProductCode(code,pageable);
  }

  @GetMapping("/vote/by-product-id/{id}")
  ResponseObject getVoteByProductId(@PathVariable long id,@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5, Sort.by("time").descending());
    return voteService.getVoteByProductId(id,pageable);
  }

  @GetMapping("/vote/rate=0/by-product-id/{id}")
  ResponseObject getCommentByProductId(@PathVariable long id,@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5, Sort.by("time").descending());
    return voteService.getCommentByProductId(id,pageable);
  }


  @GetMapping("/interactive/by-comment-id/{id}")
  ResponseObject getInteractive(@PathVariable long id) {
    return voteService.getInteractive(id);
  }

  @GetMapping("/avgVote/by-product-code/{code}")
  ResponseObject getAvgVote(@PathVariable String code) {
    return voteService.getAvgVote(code);
  }

  @GetMapping("/totalVote/by-product-code/{code}")
  ResponseObject totalVote(@PathVariable String code) {
    return voteService.getNumVote(code);
  }

  @GetMapping("/avgVote/by-product-id/{id}")
  ResponseObject getAvgVote(@PathVariable long id) {
    return voteService.getAvgVote(id);
  }

  @GetMapping("/totalVote/by-product-id/{id}")
  ResponseObject totalVote(@PathVariable long id) {
    return voteService.getNumVote(id);
  }

  @GetMapping("/rating-static/by-product-id/{id}")
  ResponseObject getRatingStatistics(@PathVariable long id) {
    return voteService.getRatingStatistics(id);
  }

  @GetMapping("/quantity/by-product-id-and-ward-id/{idP}/{idW}")
  ResponseObject getQuantityProductByStore(@PathVariable("idP") long idP,@PathVariable("idW") long idW) {
    long result = voteService.getQuantityProductByAddressStore(idP,idW);
    return new ResponseObject(result);
  }


}
