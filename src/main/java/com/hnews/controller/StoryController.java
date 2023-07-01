package com.hnews.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.dto.Story;
import com.hnews.service.StoryService;

@RestController
public class StoryController {
	@Autowired
	private StoryService sServ;
	
	@GetMapping("/top-stories")
	public ResponseEntity<List<Story>> getTopStoryByScore() throws StreamReadException, DatabindException, MalformedURLException, IllegalAccessException, InvocationTargetException, IOException{
		List<Story>sList=sServ.getTopStoryByScore();
		return new ResponseEntity<>(sList,HttpStatus.OK);
	}
	
	@GetMapping("/past-stories")
	public ResponseEntity<List<Story>> getAllPastStories() throws StreamReadException, DatabindException, MalformedURLException, IOException{
		List<Story>sList=sServ.getViewedStories();
		
		return new ResponseEntity<>(sList,HttpStatus.OK);
	}
}
