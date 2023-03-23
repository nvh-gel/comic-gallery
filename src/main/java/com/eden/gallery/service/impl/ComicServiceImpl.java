package com.eden.gallery.service.impl;

import com.eden.common.utils.Paging;
import com.eden.common.utils.ResponseModel;
import com.eden.gallery.mapper.ComicMapper;
import com.eden.gallery.model.Comic;
import com.eden.gallery.repository.ComicRepository;
import com.eden.gallery.search.ComicSpecificationBuilder;
import com.eden.gallery.search.SpecificationBuilder;
import com.eden.gallery.service.ComicService;
import com.eden.gallery.viewmodel.ComicVM;
import com.eden.gallery.viewmodel.SearchComicData;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ComicServiceImpl implements ComicService {

    private ComicRepository comicRepository;

    private final ComicMapper mapper = Mappers.getMapper(ComicMapper.class);

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public String createOnQueue(ComicVM comicVM) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ComicVM> findAll() {

        return mapper.toViewModel(comicRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComicVM findById(Long aLong) {

        return mapper.toViewModel(comicRepository.findById(aLong).orElse(null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ComicVM update(ComicVM comicVM) {

        Comic exist = comicRepository.findById(comicVM.getId()).orElse(null);
        if (exist == null) {
            return null;
        }
        Comic toUpdate = mapper.toModel(comicVM);
        mapper.mapUpdate(exist, toUpdate);
        exist.setUpdatedAt(LocalDateTime.now());
        Comic updated = comicRepository.save(exist);
        return mapper.toViewModel(updated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateOnQueue(ComicVM comicVM) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComicVM delete(Long aLong) {

        Comic exist = comicRepository.findById(aLong).orElse(null);
        if (exist == null) {
            return null;
        }
        exist.setUpdatedAt(LocalDateTime.now());
        exist.setDeleted(true);
        comicRepository.deleteById(aLong);
        return mapper.toViewModel(exist);
    }

    /**
     * {@inheritDoc}
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
    public Page<Comic> searchComic(SearchComicData searchData) {

        Paging paging = Paging.fromPaging(searchData.getPaging());
        Pageable pageable = PageRequest.of(paging.getPage() - 1,
                paging.getPageSize(),
                Sort.Direction.valueOf(paging.getOrder()),
                paging.getSortBy());
        SpecificationBuilder<Comic> specBuilder = new ComicSpecificationBuilder(searchData.getCriteria());
        return comicRepository.findAll(specBuilder.build(), pageable);
    }

    @Override
    public ResponseModel toSearchResponse(Page<Comic> searchResult) {

        ResponseModel response = ResponseModel.ok(mapper.toViewModel(searchResult.stream().toList()));
        Sort.Order order = searchResult.getSort().stream().findFirst().orElse(null);
        Paging pagingData = new Paging(searchResult.getNumber() + 1,
                searchResult.getSize(),
                searchResult.getNumberOfElements(),
                searchResult.getTotalPages(),
                null, null);
        if (order != null) {
            pagingData.setSortBy(order.getProperty());
            pagingData.setOrder(order.getDirection().name());
        }
        response.setExtra(pagingData);
        return response;
    }


}
