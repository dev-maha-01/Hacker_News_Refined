package com.hnews.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.dto.Story;

public interface StoryService {
	public List<Story> getTopStoryByScore() throws StreamReadException, DatabindException, MalformedURLException, IOException, IllegalAccessException, InvocationTargetException;
	public List<Story> getViewedStories() throws StreamReadException, DatabindException, MalformedURLException, IOException;
}
