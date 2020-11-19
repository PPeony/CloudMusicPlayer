package com.music.cloudmusicplayer;

import com.music.cloudmusicplayer.util.CloudMusicUtil;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Arrays;

@SpringBootTest
class CloudMusicPlayerApplicationTests {

    @Test
    void contextLoads() {
        File file = new File("C:\\Users\\DELL\\Desktop\\一万次悲伤-逃跑计划.mp3");
        int res = getMp3TrackLength(file);
        System.out.println(res);


    }
    private int getMp3TrackLength(File mp3File) {
        try {
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
            return audioHeader.getTrackLength();
        } catch(Exception e) {
            return -1;
        }
    }

}
