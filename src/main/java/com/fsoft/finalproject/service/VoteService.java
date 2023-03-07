package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.CommentDTO;
import com.fsoft.finalproject.dto.VoteDTO;
import com.fsoft.finalproject.exception.CustomException;

import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.data.domain.Pageable;

public interface VoteService {
	ResponseObject save(VoteDTO voteDTO) throws CustomException;

	ResponseObject updateContent(long  id, VoteDTO voteDTO);

	ResponseObject updateInteractive(long  id, String type,String change) throws CustomException;
	ResponseObject delete(long id) throws CustomException;
	ResponseObject getCommentByVoteId(long id, Pageable pageable) throws CustomException;

	ResponseObject getVoteByProductCode(String code, Pageable pageable) throws CustomException;
	ResponseObject getInteractive(long id) throws CustomException;

	ResponseObject getAvgVote(String code) throws CustomException;

	ResponseObject getNumVote(String code) throws CustomException;

	ResponseObject getAvgVote(long id) throws CustomException;

	ResponseObject getNumVote(long  id) throws CustomException;

	ResponseObject getVoteByProductId(long id, Pageable pageable) throws CustomException;

	ResponseObject getCommentByProductId(long id, Pageable pageable) throws CustomException;
	ResponseObject getRatingStatistics(long id) throws CustomException;

	long getQuantityProductByAddressStore(long idProduct, long idWard);






}
