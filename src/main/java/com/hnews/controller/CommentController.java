package com.hnews.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.dto.Comment;
import com.hnews.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService cServ;
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<List<Comment>> getTopComments(@PathVariable("id") String id) throws StreamReadException, DatabindException, MalformedURLException, IOException{
		List<Comment> cList=cServ.getTopCommentsById(id);
		
		return new ResponseEntity<>(cList,HttpStatus.OK);
	}
	
}
