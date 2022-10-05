package com.spootify.spootify;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {
  @Autowired
  private SongRepository songRepository;

  @GetMapping("/songs")
  public List<Song> getAllSongs() {
    return songRepository.findAll();
  }

  @PostMapping("/songs")
  public Song createNewSong(@RequestBody Song songData) {
    return songRepository.save(songData);
  }

  @DeleteMapping("/songs/{id}")
  public String deleteSong(@PathVariable Integer id) {
    songRepository.deleteById(id);
    return "SUCCESSFULLY DELETED!";
  }

  @PatchMapping("/songs/{id}")
  public Song updateSong(@RequestBody Song songData, @PathVariable Integer id) {
    songData.id = id;
    return songRepository.save(songData);
  }
}

@Entity
class Song {
  @Id
  @GeneratedValue
  public Integer id;
  public String title;
  public Integer artistId;

  public Song() {
  }
}

interface SongRepository extends JpaRepository<Song, Integer> {
}