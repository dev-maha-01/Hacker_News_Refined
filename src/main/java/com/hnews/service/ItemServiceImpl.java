package com.hnews.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnews.model.Item;

@Service
public class ItemServiceImpl implements ItemService{
	

	@Override
	public List<Item> getAllItems(List<String> li)
			throws MalformedURLException, StreamReadException, DatabindException, IOException {
		//get the item acc to their id and adds them to itemList 
				ObjectMapper objMapper=new ObjectMapper();
				URL myUrl=null;
				
				List<Item> iList=new ArrayList<>();
				for(int i=0;i<Math.min(li.size(), 20);i++) {
					String str="https://hacker-news.firebaseio.com/v0/item/"+li.get(i)+".json";
					 myUrl = new URL(str);
					Item item=objMapper.readValue(myUrl, Item.class);
					iList.add(item);
				}
		
		
		return iList;
	}

	@Override
	public List<String> getTopListId() throws StreamReadException, DatabindException, IOException {

		ObjectMapper objMapper=new ObjectMapper();
		
		//gets the list of top stories id
		URL myUrl=new URL("https://hacker-news.firebaseio.com/v0/topstories.json");
		List<Integer>topListOfId=objMapper.readValue(myUrl, ArrayList.class);
		List<String> out=topListOfId.stream().map(x->x+"").collect(Collectors.toList());
		return out;
	}

	@Override
	public Item getItemById(String id) throws StreamReadException, DatabindException, IOException {

		ObjectMapper objMapper=new ObjectMapper();
		
		URL url=new URL("https://hacker-news.firebaseio.com/v0/item/"+id+".json");
		
		Item item=objMapper.readValue(url, Item.class);
		
		return item;
	}
	
	

}
