package com.hnews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hnews.model.ViewedItems;

public interface ViewedItemsRepo extends JpaRepository<ViewedItems, String>{
	@Query("select  vi.viewed  from ViewedItems vi")
	public List<String> getAllViewedItemsId();
	
}
