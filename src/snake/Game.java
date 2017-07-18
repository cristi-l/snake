//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package snake;

import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

    public Game() {
        super("Snake");
        this.addState(new Menu(0));
        this.addState(new Play(1));
    }

    public static void main(String[] arguments) {
        try {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(675, 675, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException se) {
            se.printStackTrace();
        }

    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(0).init(gameContainer,this);
        this.getState(1).init(gameContainer,this);
        this.enterState(0);
    }
}
