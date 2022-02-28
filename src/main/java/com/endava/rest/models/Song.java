package com.endava.rest.models;

public class Song {

    private Integer id;
    private String artist;
    private String songName;

    public Song() {
    }

    public Song(Integer id, String artist, String name) {
        this.id = id;
        this.artist = artist;
        this.songName = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
