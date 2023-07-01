package com.hnews.dto;

import java.util.List;

import com.hnews.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Story {
	
	/*{
		  "by" : "dhouston",
		  "descendants" : 71,
		  "id" : 8863,
		  "kids" : [ 8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958, 9005, 9671, 8940, 9067, 8908, 9055, 8865, 8881, 8872, 8873, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8878, 8870, 8980, 8934, 8876 ],
		  "score" : 111,
		  "time" : 1175714200,
		  "title" : "My YC app: Dropbox - Throw away your USB drive",
		  "type" : "story",
		  "url" : "http://www.getdropbox.com/u/2/screencast.html"
		}*/
	
	private String title;
	private String url;
	private Integer score;
	private String time;
	private String by;
	
	//the order of all fiels contructor is improt while creating obj in ItemRepository getTopStoryByScore
	public Story(String title, String url, Integer score, String time, String by) {
		super();
		this.title = title;
		this.url = url;
		this.score = score;
		this.time = time;
		this.by = by;
	}
	
	public Story(Item i) {
		super();
		this.title = i.getTitle();
		this.url = i.getUrl();
		this.score = i.getScore();
		this.time = i.getTime();
		this.by = i.getBy();
	}
	
	
	
}
