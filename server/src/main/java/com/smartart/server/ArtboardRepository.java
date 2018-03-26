package com.smartart.server;
import org.springframework.data.repository.CrudRepository;
/**
 * @author rwendell
 */
public interface ArtboardRepository extends CrudRepository<Artboard, Long> {
    public Artboard findByArtboardName(String artboard);

}
