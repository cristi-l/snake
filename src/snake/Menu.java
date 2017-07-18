package snake;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

    Image startButton, pauseButton, continueButton, quitButton;

    public Menu(int i) {
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        startButton=new Image("res/Menu_Green_01.png");
        continueButton=new Image("res/Menu_Green_03.png");
        quitButton=new Image("res/Menu_Green_04.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        startButton.draw(gc.getWidth()/2-startButton.getWidth()/2,gc.getHeight()/2-startButton.getHeight());
        continueButton.draw(gc.getWidth()/2-continueButton.getWidth()/2,gc.getHeight()/2);
        quitButton.draw(gc.getWidth()/2-quitButton.getWidth()/2,gc.getHeight()/2+quitButton.getHeight());
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_S)){
            stateBasedGame.enterState(1);
        }
        if(input.isKeyDown(Input.KEY_C)){
            stateBasedGame.enterState(1);
        }
        if(input.isKeyDown(Input.KEY_Q)){
            System.exit(0);
        }

    }
}
