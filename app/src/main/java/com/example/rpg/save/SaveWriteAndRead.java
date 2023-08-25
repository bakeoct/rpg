package com.example.rpg.save;
import static com.example.rpg.Calc.Game.game;
import com.example.rpg.Calc.Game;

import java.io.*;

public class SaveWriteAndRead {
    public static SaveWriteAndRead saveWriteAndRead =new SaveWriteAndRead();
    public void write(){
        try (FileOutputStream file = new FileOutputStream("C:\\Users\\2009t\\IdeaProjects\\ex001\\src\\save.dat");
             BufferedOutputStream buffered = new BufferedOutputStream(file);
             ObjectOutputStream output = new ObjectOutputStream(buffered);){
            output.reset();
            output.writeObject(game);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Game read(){
        try (FileInputStream file = new FileInputStream("C:\\Users\\2009t\\IdeaProjects\\ex001\\src\\save.dat");
             BufferedInputStream buffered = new BufferedInputStream(file);
             ObjectInputStream input = new ObjectInputStream(buffered)){
            game = (Game) input.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }
}
