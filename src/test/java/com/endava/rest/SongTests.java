package com.endava.rest;

import com.endava.rest.models.Song;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.endava.rest.utils.Variables.HOSTNAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class SongTests {

    private static final String ENDPOINT = "/music/";

    @Test
    public void ShouldReturnAllSongs() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Song[]> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        Song[].class
                );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().length, is(greaterThan(1)));
    }

    @Test
    public void ShouldReturnASongById1() {
        RestTemplate restTemplate = new RestTemplate();

        Integer songId = 1;
        Song song = restTemplate.getForObject(HOSTNAME + ENDPOINT + songId, Song.class);

        assertThat(song.getId(), is(songId));
        assertThat(song.getArtist(), is("Florin Johnson"));
        assertThat(song.getSongName(), is("Inima de dor"));
    }

    @Test
    public void ShouldBeAbleToCreateASong() {
        RestTemplate restTemplate = new RestTemplate();

        Integer id = 4;
        String artist = "Valeriu Mare";
        String songName = "Jor si dale";

        Song newSong = new Song(id, artist, songName);
        HttpEntity<Song> httpEntity = new HttpEntity<>(newSong);
        ResponseEntity<Song> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT,
                        HttpMethod.POST,
                        httpEntity,
                        Song.class
                );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody().getId(), is(id));
        assertThat(responseEntity.getBody().getArtist(), is(artist));
        assertThat(responseEntity.getBody().getSongName(), is(songName));
    }

    @Test
    public void ShouldBeAbleToEditASongById() {
        RestTemplate restTemplate = new RestTemplate();

        Integer id = 2;
        Integer newId = 4;
        String newArtist = "Valeriu Mare";
        String newSongName = "Jor si dale";

        Song editedSong = new Song(newId, newArtist, newSongName);
        HttpEntity<Song> httpEntity = new HttpEntity<>(editedSong);
        ResponseEntity<Song> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT + id,
                        HttpMethod.PUT,
                        httpEntity,
                        Song.class
                );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(), is(newId));
        assertThat(responseEntity.getBody().getArtist(), is(newArtist));
        assertThat(responseEntity.getBody().getSongName(), is(newSongName));
    }

    @Test
    public void ShouldBeAbleToDeleteASongById() {
        RestTemplate restTemplate = new RestTemplate();

        Integer id = 1;

        restTemplate.delete(HOSTNAME + ENDPOINT + id);

        ResponseEntity<Song[]> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        Song[].class
                );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().length, is(equalTo(2)));
    }
}
