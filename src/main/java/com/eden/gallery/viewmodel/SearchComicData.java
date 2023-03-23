package com.eden.gallery.viewmodel;

import com.eden.common.utils.Paging;
import lombok.Data;

@Data
public class SearchComicData {

    private ComicVM criteria;

    private Paging paging;
}
