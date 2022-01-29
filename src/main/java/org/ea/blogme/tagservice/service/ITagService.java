package org.ea.blogme.tagservice.service;

import org.ea.blogme.tagservice.dto.BlogResponse;
import org.ea.blogme.tagservice.dto.TagDelete;
import org.ea.blogme.tagservice.dto.TagSave;

public interface ITagService {
    void save(TagSave dto);

    BlogResponse findAllBlogIdByTagTitle(String title);

    BlogResponse findAllBlogIdByTagId(long id);

    void deleteFromBlogTagByTagIdAndBlogId(TagDelete tagDelete);
}
