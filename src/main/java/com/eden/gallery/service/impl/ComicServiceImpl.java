package com.eden.gallery.service.impl;

import com.eden.common.utils.Paging;
import com.eden.gallery.mapper.ComicMapper;
import com.eden.gallery.model.Comic;
import com.eden.gallery.repository.ComicRepository;
import com.eden.gallery.service.ComicService;
import com.eden.gallery.viewmodel.ComicVM;
import com.eden.gallery.viewmodel.SearchComicData;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ComicServiceImpl implements ComicService {

    private ComicRepository comicRepository;

    private final ComicMapper mapper = Mappers.getMapper(ComicMapper.class);

    /**
     * @param comicVM
     * @return
     */
    @Override
    @Transactional
    public ComicVM create(ComicVM comicVM) {

        Comic comic = mapper.toModel(comicVM);
        comic.setUuid(UUID.randomUUID());
        comic.setCreatedAt(LocalDateTime.now());
        comic.setUpdatedAt(LocalDateTime.now());
        Comic result = comicRepository.save(comic);
        return mapper.toViewModel(result);
    }

    /**
     * @param comicVM
     * @return
     */
    @Override
    public String createOnQueue(ComicVM comicVM) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<ComicVM> findAll() {

        return mapper.toViewModel(comicRepository.findAll());
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public ComicVM findById(Long aLong) {

        return mapper.toViewModel(comicRepository.findById(aLong).orElse(null));
    }

    /**
     * @param comicVM
     * @return
     */
    @Override
    public ComicVM update(ComicVM comicVM) {
        return null;
    }

    /**
     * @param comicVM
     * @return
     */
    @Override
    public String updateOnQueue(ComicVM comicVM) {
        return null;
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public ComicVM delete(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public String deleteOnQueue(Long aLong) {
        return null;
    }

    @Autowired
    public void setComicRepository(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    @Override
    public List<ComicVM> searchComic(SearchComicData searchData) {

        Paging paging = null == searchData.getPaging() ? new Paging() : searchData.getPaging();
        return null;
    }
}
