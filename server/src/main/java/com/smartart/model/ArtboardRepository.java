package com.smartart.model;

import org.springframework.data.repository.CrudRepository;

/**
 * @author rwendell
 */
public interface ArtboardRepository extends CrudRepository<Artboard, Long> {
    public Artboard findByArtboardName(String artboard);
    public Artboard findByArtboardId(Long artboardID);
    public Artboard findByIsPrivate(Long isPrivate);

}
