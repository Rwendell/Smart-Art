package com.smartart.server;
import org.springframework.data.repository.CrudRepository;
import com.smartart.server.ArtBoard;

public interface ArtBoardRepository extends CrudRepository<ArtBoard, Long> {
}
