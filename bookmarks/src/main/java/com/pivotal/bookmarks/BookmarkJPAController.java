package com.pivotal.bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BookmarkJPAController {

    @Autowired
    private BookmarkJPARepository bookmarkJPARepository;

    @GetMapping("/")
    public String index() {
        return "Bookmark Service is running";
    }

    @GetMapping("/jpa/bookmarks")
    public List<Bookmark> retrieveAllBookmarks() {
        return bookmarkJPARepository.findAll();
    }

    @GetMapping("/jpa/bookmarks/{id}")
    public Optional<Bookmark> retrieveBookmark(@PathVariable int id) {
        return bookmarkJPARepository.findById(id);
    }

    @DeleteMapping("/jpa/bookmarks/{id}")
    public void deleteBookmark(@PathVariable int id) {
        bookmarkJPARepository.deleteById(id);
    }

    @PostMapping("/jpa/bookmarks")
    public ResponseEntity<Object> saveBookmark(@RequestBody Bookmark bookmark) {

        if (bookmark.getAdded() == null) {
            bookmark.setAdded(new Date());
        }

        Bookmark newBookmark = bookmarkJPARepository.save(bookmark);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(newBookmark.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }
}
