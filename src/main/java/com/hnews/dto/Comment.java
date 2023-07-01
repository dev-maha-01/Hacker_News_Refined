package com.hnews.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
	private String by;
	private String text;
	
	public Comment(String by,String text) {
		super();
		this.by=by;
		this.text=text;
	}
}
