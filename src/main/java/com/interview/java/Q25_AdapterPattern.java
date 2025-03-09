package com.interview.java;

// Target Interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee
class VLCPlayer {
    void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private VLCPlayer vlcPlayer = new VLCPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            vlcPlayer.playVLC(fileName);
        } else {
            System.out.println("Invalid format: " + audioType);
        }
    }
}

public class Q25_AdapterPattern {
    public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter();
        player.play("vlc", "movie.vlc");
    }
}
