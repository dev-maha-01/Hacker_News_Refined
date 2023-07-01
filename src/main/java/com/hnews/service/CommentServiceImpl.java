package com.hnews.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.dto.Comment;
import com.hnews.model.Item;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private ItemService iServ;
	
	@Override
	public List<Comment> getTopCommentsById(String id) throws StreamReadException, DatabindException, MalformedURLException, IOException {
		// gets the item
		Item item=iServ.getItemById(id);
		
		//takes all tht kids i.e comments
		List<String> comments=item.getKids();
		
		//gets all the items of kids
		List<Item> itemList=iServ.getAllItems(comments);
		
		List<Comment> topComments=itemList.stream()
											.filter(i->i.getType().equals("comment"))				//filters all the comments
											.sorted((x,y)->y.getKids().size()-x.getKids().size())   // sorts them on size of kids
											.limit(10)												//limits the top 10
											.map(i->new Comment(i.getBy(),i.getText()))				//converts item to comment
											.collect(Collectors.toList());							//collects them in list
		
		return topComments;
	}
	
}
