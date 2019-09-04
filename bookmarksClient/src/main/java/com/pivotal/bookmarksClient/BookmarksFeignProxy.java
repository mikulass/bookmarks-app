package com.pivotal.bookmarksClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bookmarks-service")
public interface BookmarksFeignProxy {

    @GetMapping("/jpa/bookmarks")
    public List<Bookmark> retrieveAllBookmarks();

    @PostMapping("/jpa/bookmarks")
    public ResponseEntity<Object> saveBookmark(@RequestBody Bookmark bookmark);

    @DeleteMapping("/jpa/bookmarks/{id}")
    public void deleteBookmark(@PathVariable int id);

}
