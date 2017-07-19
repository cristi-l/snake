package snake;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

    Image startButton, continueButton, quitButton;
    int bWidth, bHeight, bX, bY;
    public Menu() {
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        startButton=new Image("res/Menu_Green_01.png");
        continueButton=new Image("res/Menu_Green_03.png");
        quitButton=new Image("res/Menu_Green_04.png");
        bHeight=startButton.getHeight();
        bWidth=startButton.getWidth();
        bX = gc.getWidth()/2-startButton.getWidth()/2;
        bY = gc.getHeight()/2-startButton.getHeight();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        startButton.draw(bX,bY-bHeight);
        continueButton.draw(bX,bY);
        quitButton.draw(bX,bY+bHeight);
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
        int mouseX=input.getMouseX();
        int mouseY=input.getMouseY();
        if(input.isMouseButtonDown(0)) {
            if (mouseX > bX && mouseX < bX + bWidth && mouseY > bY - bHeight && mouseY < bY){
                System.out.println("click start");
                stateBasedGame.enterState(1);
            }

            if (mouseX > bX && mouseX < bX + bWidth && mouseY > bY && mouseY < bY + bHeight){
                System.out.println("click continue");
                stateBasedGame.enterState(1);
            }

            if (mouseX > bX && mouseX < bX + bWidth && mouseY > bY + bHeight && mouseY < bY + 2 * bHeight){
                System.out.println("click quit");
                System.exit(0);
            }

        }

    }
}
