package com.eden.gallery.service;

import com.eden.common.service.BaseService;
import com.eden.common.utils.ResponseModel;
import com.eden.gallery.model.Comic;
import com.eden.gallery.viewmodel.ComicVM;
import com.eden.gallery.viewmodel.SearchComicData;
import org.springframework.data.domain.Page;

public interface ComicService extends BaseService<ComicVM> {
    Page<Comic> searchComic(SearchComicData searchData);

    ResponseModel toSearchResponse(Page<Comic> searchResult);
}
