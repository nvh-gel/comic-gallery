package com.eden.gallery.service;

import com.eden.common.service.BaseService;
import com.eden.gallery.viewmodel.ComicVM;
import com.eden.gallery.viewmodel.SearchComicData;

import java.util.List;

public interface ComicService extends BaseService<ComicVM> {
    List<ComicVM> searchComic(SearchComicData searchData);
}
