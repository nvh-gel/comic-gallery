package com.eden.gallery.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ComicVM extends BaseVM {

    private String name;
    private String author;
    private String description;
}
