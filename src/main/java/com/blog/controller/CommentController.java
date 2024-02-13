package com.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.CommentService;
import com.blog.vo.Comment;
import com.blog.vo.Post;
import com.blog.vo.Result;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	private static final Logger log = LoggerFactory.getLogger(PostController.class);
	
	@PostMapping("/comment")
	public Object savePost(HttpServletResponse response, @RequestBody Comment commentParam) {
		Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment());
		boolean isSuccess = commentService.saveComment(comment);
		
		if(isSuccess) {
			return new Result(200, "Success");
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new Result(500, "Fail");
		}
	}
	
	@GetMapping("/comment")
	public Comment getComment(@RequestParam("id") Long id) {
		Comment comment = commentService.getComment(id);
		return comment;
	}
	
	@GetMapping("/comments")
	public List<Comment> getComments(@RequestParam("post_id") Long postId){
		List<Comment> comments = commentService.getCommentList(postId);
		return comments;
	}
	
	@DeleteMapping("/comment")
	public Object deleteComment(HttpServletResponse response, @RequestParam("id") Long id) {
		boolean isSuccess = commentService.deleteComment(id);
		
		log.info("id ::: " + id);
		if(isSuccess) {
			return new Result (200, "Success");
		} else {
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
			return new Result(500, "Fail");
		}
	}
	
	@GetMapping("/comments/search")
	public List<Comment> searchCommentByPostIdAndQuery(@RequestParam("postId") Long postId, @RequestParam("query") String query){
		List<Comment> comments = commentService.searchCommentByPostIdAndQuery(postId, query);
		return comments;
	}
	
	
}
