package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;
import com.blog.vo.Post;

@Service
public class CommentService {
	
	@Autowired
	CommentJpaRepository commentJpaRepository;
	
	public Comment getComment(Long id) {
		Comment comment = commentJpaRepository.findOneById(id);
		
		return comment;
	}
	
	public List<Comment> getCommentList(Long postId){
		List<Comment> comments = commentJpaRepository.findAllByPostId(postId);
		
		return comments;
	}
	
	public boolean deleteComment(Long id) {
		Comment result = commentJpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		commentJpaRepository.deleteById(id);
		return true;
	}
	
	public boolean saveComment(Comment comment) {
		Comment result = commentJpaRepository.save(comment);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	public List<Comment> searchCommentByPostIdAndQuery(Long postId, String query){
		List<Comment> comments = commentJpaRepository.findByPostIdAndQueryContainingOrderByRegDateDesc(postId, query);
		return comments;
	}
	
}
