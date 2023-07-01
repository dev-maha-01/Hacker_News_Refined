package com.hnews.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	
	/*
	 * id	The item's unique id.
deleted	true if the item is deleted.
type	The type of item. One of "job", "story", "comment", "poll", or "pollopt".
by	The username of the item's author.
time	Creation date of the item, in Unix Time.
text	The comment, story or poll text. HTML.
dead	true if the item is dead.
parent	The comment's parent: either another comment or the relevant story.
poll	The pollopt's associated poll.
kids	The ids of the item's comments, in ranked display order.
url	The URL of the story.
score	The story's score, or the votes for a pollopt.
title	The title of the story, poll or job. HTML.
parts	A list of related pollopts, in display order.
descendants	In the case of stories or polls, the total comment count.
*/
	
	private String id;
	private boolean deleted;
	private String type;	
	private String by;
	private String time;
	private String text; 
	private boolean dead;
	private String parent;
	private String poll;
	private List<String>kids=new ArrayList();
	private String url;
	private Integer score;
	private String title;
	private List<String>parts=new ArrayList<>();
	@Override
	public String toString() {
		return "Item [id=" + id + ", deleted=" + deleted + ", type=" + type + ", by=" + by + ", time=" + time
				+ ", text=" + text + ", dead=" + dead + ", parent=" + parent + ", poll=" + poll + ", kids=" + kids
				+ ", url=" + url + ", score=" + score + ", title=" + title + ", parts=" + parts + "]";
	}
	
	
	
}
