package com.projects.blogapplication.controller;

import com.projects.blogapplication.configs.Constants;
import com.projects.blogapplication.payloads.ApiResponse;
import com.projects.blogapplication.payloads.PostDTO;
import com.projects.blogapplication.payloads.PostResponse;
import com.projects.blogapplication.services.FileService;
import com.projects.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
@ResponseBody
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDTO createdPost = this.postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<PostDTO>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDTO> postDTOList = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDTO>>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDTO> postDTOList = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostbyId(@PathVariable Integer postId) {
        PostDTO postDTO = this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getUpdatedPost(@RequestBody PostDTO postDTO,
                                                  @PathVariable Integer postId) {

        PostDTO postDTO1 = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<PostDTO>(postDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post has been deleted successfully", true);
    }

    @GetMapping("/post/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDTO> results = this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDTO>>(results, HttpStatus.OK);
    }

    @Value("${project.image}")
    private String path;
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

        PostDTO postDTO = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDTO.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatePost(postDTO, postId);

        return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
    }
}
