package org.ea.blogme.tagservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 75)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tag")
    private List<BlogTag> blogTags = new ArrayList<>();

    public Tag(String title){
        this.title = title;
    }

    public void addBlogTag(BlogTag blogTag){
        blogTag.setTag(this);
        this.blogTags.add(blogTag);
    }
}
