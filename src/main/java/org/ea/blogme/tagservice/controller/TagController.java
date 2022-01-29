package org.ea.blogme.tagservice.controller;

import org.ea.blogme.tagservice.dto.BlogResponse;
import org.ea.blogme.tagservice.dto.TagDelete;
import org.ea.blogme.tagservice.dto.TagSave;
import org.ea.blogme.tagservice.service.ITagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final ITagService tagService;

    public TagController(ITagService tagService){
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TagSave dto){
        tagService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/title/blogs")
    public ResponseEntity<BlogResponse> findAllBlogByTagTitle(@RequestParam String title){
        return ResponseEntity.ok(tagService.findAllBlogIdByTagTitle(title));
    }

    @GetMapping("/id/blogs")
    public ResponseEntity<BlogResponse> findAllBlogByTagId(@RequestParam Long tagId){
        return ResponseEntity.ok(tagService.findAllBlogIdByTagId(tagId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromBlogTagByTagIdAndBlogId(@RequestBody TagDelete tagDelete){
        tagService.deleteFromBlogTagByTagIdAndBlogId(tagDelete);
        return ResponseEntity.ok().build();
    }
}
