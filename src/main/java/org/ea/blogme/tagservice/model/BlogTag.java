package org.ea.blogme.tagservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "blog_tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogTag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "blog_id")
    private Long blogId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tag tag;

    public BlogTag(Long blogId){
        this.blogId = blogId;
    }
}
