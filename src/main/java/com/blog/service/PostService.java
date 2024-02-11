package com.blog.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.PostRepository;
import com.blog.vo.Post;

@Service
public class PostService {
	private static List<Post> posts; 
	
	@Autowired
	PostRepository postRepository;
	
	public Post getPost(Long id) {
		Post post = postRepository.findOne(id);
		
		return post;
	}
	
	public List<Post> getPosts() {
		List<Post> postList = postRepository.findPost();

		return postList;
	}
	
	public List<Post> getPostsOrderByUpdtAsc() {
		List<Post> postList = postRepository.findPostOrderByUpdtDateAsc();
		
		return postList;
	}
	
	public List<Post> getPostsOrderByRegDesc() {
		List<Post> postList = postRepository.findPostOrderByRegDateDesc();
		
		return postList;
	}
	
	public List<Post> searchPostByTitle(String query){
		List<Post> posts = postRepository.findPostLikeTitle(query);
		return posts;
	}
	
	public List<Post> searchPostByContent(String query){
		List<Post> posts = postRepository.findPostLikeContent(query);
		return posts;
	}
	
	public boolean savePost(Post post) {
		int result = postRepository.savePost(post);
		boolean isSuccess = true;
		
		if(result == 0) {
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
}
