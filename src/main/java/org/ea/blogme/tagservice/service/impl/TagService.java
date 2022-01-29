package org.ea.blogme.tagservice.service.impl;

import org.ea.blogme.tagservice.dto.BlogResponse;
import org.ea.blogme.tagservice.dto.TagDelete;
import org.ea.blogme.tagservice.dto.TagSave;
import org.ea.blogme.tagservice.model.BlogTag;
import org.ea.blogme.tagservice.model.Tag;
import org.ea.blogme.tagservice.repository.TagRepository;
import org.ea.blogme.tagservice.service.ITagService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements ITagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void save(TagSave dto) {
        if(!dto.getTags().isEmpty()){
            List<Tag> tags = new ArrayList<>();
            dto.getTags().forEach(title -> {
                Tag tag = tagRepository.getByTitle(title.toLowerCase());
                if(tag == null){
                    tag = new Tag(title.toLowerCase());
                }
                if(!tagRepository.existsInBlogTagByTagIdAndBlogId(dto.getBlogId(), tag.getId())) {
                    tag.addBlogTag(new BlogTag(dto.getBlogId()));
                }
                tags.add(tag);
            });
            tagRepository.saveAll(tags);
        }
    }

    @Override
    public BlogResponse findAllBlogIdByTagTitle(String title) {
        return new BlogResponse(tagRepository.findAllBlogIdByTag_Title(title));
    }

    @Override
    public BlogResponse findAllBlogIdByTagId(long id) {
        return new BlogResponse(tagRepository.findAllBlogIdByTag_Id(id));
    }

    @Override
    @Transactional
    @Modifying
    public void deleteFromBlogTagByTagIdAndBlogId(TagDelete tagDelete) {
        tagRepository.deleteFromBlogTagByTagIdAndBlogId(tagDelete.getTagId(), tagDelete.getBlogId());
    }
}
