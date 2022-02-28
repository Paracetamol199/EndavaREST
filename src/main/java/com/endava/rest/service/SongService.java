package com.endava.rest.service;

import com.endava.rest.exception.ExmployeeResourceException;
import com.endava.rest.models.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class SongService {

    private static List<Song> songs = new ArrayList<Song>() {
        {
            add(new Song(1, "Florin Johnson", "Inima de dor"));
            add(new Song(2, "Ilie Noroc", "Falling Hearts"));
        }
    };

    public static ResponseEntity<Song> addSong(Song song) {
        songs.add(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    public static ResponseEntity<Song> editSongById(Song song, Integer id) {
        Song extractedSong = songs
                .stream()
                .filter(songId -> songId.getId().equals(id))
                .findFirst()
                .get();
        int index = songs.indexOf(extractedSong);
        songs.set(index, song);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    public static void deleteSong(Integer id) {
        songs.removeIf(song -> song.getId().equals(id));
    }

    public static ResponseEntity<Song> getSongById(Integer id) {
        return songs
                .stream()
                .filter(song -> song.getId().equals(id))
                .findFirst()
                .map(song -> new ResponseEntity<>(song, HttpStatus.OK))
                .orElseThrow(() -> new ExmployeeResourceException("Nu am putut sa gasim o melodie dupa ID-ul introdus"));
    }

    public static List<Song> getAllSongs() {
        return songs;
    }
}
