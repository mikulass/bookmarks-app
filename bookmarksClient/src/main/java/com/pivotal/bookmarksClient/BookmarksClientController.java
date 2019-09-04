package com.pivotal.bookmarksClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookmarksClientController {

    @Autowired
    private BookmarksFeignProxy proxy;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showRunning(Model uiModel) {
        return "running";
    }

    @RequestMapping(value = "/bookmarks", method = RequestMethod.GET)
    public String showBookmarks(Model uiModel) {
        List<Bookmark> bookmarks = proxy.retrieveAllBookmarks();
        uiModel.addAttribute("bookmarks", bookmarks);
        uiModel.addAttribute("newBookmark", new Bookmark());
        return "bookmarks";
    }

    @GetMapping("/delete")
    public String deleteBookmark(@RequestParam(name = "id") int id, Model model) {
        proxy.deleteBookmark(id);
        model.addAttribute("id", id);
        return "bookmarkDeleted";
    }

    @RequestMapping(value = "/saveBookmark", method = RequestMethod.POST)
    public String saveBookmark(@ModelAttribute Bookmark bookmark, BindingResult errors, Model model) {
        ResponseEntity<Object> responseEntity = proxy.saveBookmark(bookmark);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("name", bookmark.getName());
            return "bookmarkSaved";
        }
        return "bookmarkError";
    }

    @RequestMapping("/kill")
    public void kill() {
        System.exit(1);
    }

}
