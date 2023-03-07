package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.CommentDTO;

public interface CommentService {
	ResponseObject save(CommentDTO comment);
	ResponseObject updateContent(long id_sub, CommentDTO comment);
	ResponseObject delete(long id_sub);

	ResponseObject getLike(long id_sub);
	ResponseObject updateIncreaseLikes(long id_sub);
	ResponseObject updateDecreaseLikes(long id_sub);

}
