package com.cursosdedesarrollo.apirestjpa.entities.relations.manytooneuni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postsuni")
public class PostUniController {

    @Autowired
    private PostUniRepository postRepository;

    @Autowired
    private CommentUniRepository commentRepository;

    @GetMapping("/")
    public List<PostUni> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/")
    public PostUni createPost(@RequestBody PostUni post) {
        return postRepository.save(post);
    }

    @PostMapping("/{postId}/comments/")
    public PostUni addCommentToPost(
            @PathVariable("postId") Long postId,
            @RequestBody CommentUni comment) {
        PostUni post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.addComment(comment);
        return postRepository.save(post);
    }
}
