package com.hnews.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.hnews.dto.Comment;

public interface CommentService {
	public List<Comment> getTopCommentsById(String id) throws StreamReadException, DatabindException, MalformedURLException, IOException;
}
