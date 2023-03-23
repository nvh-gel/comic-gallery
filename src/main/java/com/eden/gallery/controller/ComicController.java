package com.eden.gallery.controller;

import com.eden.common.utils.ResponseModel;
import com.eden.gallery.service.ComicService;
import com.eden.gallery.viewmodel.ComicVM;
import com.eden.gallery.viewmodel.SearchComicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comic")
public class ComicController {

    private ComicService comicService;

    @PostMapping
    ResponseModel createComic(@RequestBody ComicVM request) {

        return ResponseModel.created(comicService.create(request));
    }

    @GetMapping
    ResponseModel getAllComics() {

        return ResponseModel.ok(comicService.findAll());
    }

    @GetMapping("/{id}")
    ResponseModel getComicById(@PathVariable Long id) {

        ComicVM result = comicService.findById(id);
        if (null == result) {
            return ResponseModel.notFound();
        }
        return ResponseModel.ok(comicService.findById(id));
    }

    @GetMapping("/search")
    ResponseModel searchComic(@RequestBody SearchComicData searchData) {

        return comicService.toSearchResponse(comicService.searchComic(searchData));
    }

    @PutMapping
    ResponseModel updateComic(@RequestBody ComicVM request) {

        return ResponseModel.updated(comicService.update(request));
    }

    @DeleteMapping("/{id}")
    ResponseModel deleteComic(@PathVariable Long id) {

        return ResponseModel.deleted(comicService.delete(id));
    }

    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }
}
