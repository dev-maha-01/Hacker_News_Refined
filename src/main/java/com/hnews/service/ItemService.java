package com.hnews.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.model.Item;

public interface ItemService {
	public List<Item> getAllItems(List<String>li) throws MalformedURLException, StreamReadException, DatabindException, IOException;
	public List<String> getTopListId() throws MalformedURLException, StreamReadException, DatabindException, IOException ;
	
	public Item getItemById(String id) throws MalformedURLException, StreamReadException, DatabindException, IOException; 
	

}
