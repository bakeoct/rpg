package com.example.rpg.Calc;

//import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound implements Serializable {
    public static final File ON_STONE_AUDIO = new File("./src/sound/アスファルトの上を歩く1.wav");
    public static final File ON_WOOD_AUDIO = new File("./src/sound/フローリングの上を歩く1.wav");
    public static final File IN_SEA_AUDIO = new File("./src/sound/水に浸かりながら歩く.wav");
    public static final File ON_GRAVEL_AUDIO = new File("./src/sound/砂利の上を歩く.wav");
    public static final File ON_GLASS_AUDIO = new File("./src/sound/芝生の上を歩く.wav");
    public static final File ON_FALLEN_LEAVES_AUDIO = new File("./src/sound/落ち葉の上を歩く.wav");
    public static final File OPEN_DOOR_AUDIO = new File("./src/sound/ドアを開ける3.wav");
    public static final File OPEN_TREASURE_CHEST_AUDIO = new File("./src/sound/ドアを開ける1_1.wav");
    public static void startAudio(File data_audio) {
//        Clip clip = createClip(data_audio);
//        //ここで再生メソッドの呼び出し
//        clip.start();
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Clip createClip(File path) {
//        //指定されたURLのオーディオ入力ストリームを取得
//        try (AudioInputStream ais = AudioSystem.getAudioInputStream(path)){
//
//            //ファイルの形式取得
//            AudioFormat af = ais.getFormat();
//
//            //単一のオーディオ形式を含む指定した情報からデータラインの情報オブジェクトを構築
//            DataLine.Info dataLine = new DataLine.Info(Clip.class,af);
//
//            //指定された Line.Info オブジェクトの記述に一致するラインを取得
//            Clip c = (Clip)AudioSystem.getLine(dataLine);
//
//            //再生準備完了
//            c.open(ais);
//
//            return c;
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
