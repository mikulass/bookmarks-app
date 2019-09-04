package com.pivotal.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkJPARepository extends JpaRepository<Bookmark, Integer> {

}
