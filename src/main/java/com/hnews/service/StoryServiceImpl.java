package com.hnews.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.dto.Story;
import com.hnews.model.Item;
import com.hnews.model.ViewedItems;
import com.hnews.repository.ViewedItemsRepo;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private ItemService iServ;
	
	@Autowired
	private ViewedItemsRepo viRepo;
	
	@Override
	@Cacheable("stories")
	public List<Story> getTopStoryByScore() throws StreamReadException, DatabindException, MalformedURLException, IOException, IllegalAccessException, InvocationTargetException {
		
		//gets the list of items id
		List<String>itemIdList=iServ.getTopListId();
		
		//gets the item from itemToplist
		List<Item>itemList=iServ.getAllItems(itemIdList);
		
		//filters and sorts them
		itemList=itemList.stream()
				.filter(x->x.getType().equals("story"))        //filters by type="story"
				.sorted((a,b)->b.getScore()-a.getScore())		//sorts them in descending
				.limit(10) 										//limits to size=10
				.collect(Collectors.toList());

		
		// converts them to story and creates list of stories
		// IMP also stores the visited items
		
		List<Story>storyList=new ArrayList<>();
		List<ViewedItems> viList=new ArrayList<>();
		LocalDateTime currTime=LocalDateTime.now();
		for(Item item : itemList) {
			//adds to viewed list
			ViewedItems vItem=new ViewedItems();
			vItem.setViewed(item.getId());
			vItem.setLastViewed(currTime);
			viList.add(vItem);
			
			//adds to returning list of stories
			Story story=new Story(item);
			//BeanUtils.copyProperties(item, story); //not working
			
			storyList.add(story);
		}
		
		//saves all the viwedItems id
		viRepo.saveAll(viList);
		
		return storyList;
	}
	
	@CacheEvict(value = "stories", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.top-storiesTTL}")
    public void emptyStoriesCache() {
    }

	@Override
	public List<Story> getViewedStories() throws StreamReadException, DatabindException, MalformedURLException, IOException {
		//first takes all the ids of viewed items
		List<String>viewedIdList=viRepo.getAllViewedItemsId();
		
		//get all the items
		List<Item> allViewedItems=iServ.getAllItems(viewedIdList);
		
		//filter type= "story" then conver to story object and collect them
		List<Story> storyList=allViewedItems.stream()
											.filter(x->x.getType().equals("story"))
											.map(x->new Story(x))
											.collect(Collectors.toList());
		
		return storyList;
	}

	

}
