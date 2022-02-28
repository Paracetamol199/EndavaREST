package com.endava.rest.controller;

import com.endava.rest.models.Song;
import com.endava.rest.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class SongController {

    @GetMapping("")
    public List<Song> getAllSongs() {
        return SongService.getAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById (@PathVariable(value = "id") Integer id) {
        return SongService.getSongById(id);
    }

    @PostMapping("")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        return SongService.addSong(song);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> editSongById(@RequestBody Song song, @PathVariable(value = "id") Integer id) {
        return SongService.editSongById(song, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSongById (@PathVariable(value = "id") Integer id) {
        SongService.deleteSong(id);
    }

}
