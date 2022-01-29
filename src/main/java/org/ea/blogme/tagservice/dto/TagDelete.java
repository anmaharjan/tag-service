package org.ea.blogme.tagservice.dto;

import lombok.Data;

@Data
public class TagDelete {
    private Long blogId;
    private Long tagId;
}
