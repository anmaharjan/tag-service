package org.ea.blogme.tagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogResponse {
    private List<Long> blogId = new ArrayList<>();
}
