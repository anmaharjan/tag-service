package org.ea.blogme.tagservice.repository;

import org.ea.blogme.tagservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag getByTitle(String title);

    @Query("SELECT count(bt)>0 FROM BlogTag bt " +
            "where bt.blogId=:blogId and bt.tag.id=:tagId")
    boolean existsInBlogTagByTagIdAndBlogId(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    @Query("SELECT distinct bt.blogId FROM Tag t " +
            "inner join t.blogTags bt " +
            "where t.title=:title")
    List<Long> findAllBlogIdByTag_Title(String title);

    @Query("SELECT distinct bt.blogId FROM Tag t " +
            "inner join t.blogTags bt " +
            "where t.id=:tagId")
    List<Long> findAllBlogIdByTag_Id(@Param("tagId") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM BlogTag bt " +
            "where bt.tag.id=:tagId and bt.blogId=:blogId")
    void deleteFromBlogTagByTagIdAndBlogId(Long tagId, Long blogId);
}
