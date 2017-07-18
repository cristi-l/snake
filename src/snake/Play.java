package snake;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.List;

public class Play extends BasicGameState {

    Snake s;
    World w;
    List<SnakeSegment> segments;
    Tile[][] map;
    TiledMap grassMap;
    Image foodImg[];
    Animation foodAnimation;

    public Play(int i) {
    }
    //State gameState = State.Loaded;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.w = new World(42, 42, 16);
        this.s = this.w.getSnake();
        this.segments = this.w.getSnakeSegments();
        this.map = this.w.getMap();
        this.w.loadMap("res/map1.tmx", "res");
        foodImg = new Image[1];
        foodImg[0] = new Image("res/Apple.png");
        foodAnimation=new Animation(foodImg,1);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
       /* switch (gameState){
            case Loaded:{
                gameState=State.Running;
            }
            break;
            case Paused:{

            }
            break;
            case Running:{*/
                this.w.renderMap(0, 0);
                g.setColor(Color.blue);
                g.fillRoundRect((float)((this.segments.get(0)).getX() * this.w.getSize()),
                        (float)((this.segments.get(0)).getY() * this.w.getSize()),
                        (float)this.w.getSize(),
                        (float)this.w.getSize(),
                        this.w.getSize());
                g.setColor(Color.white);

                for(int i = 2; i < this.segments.size(); ++i) {
                    g.fillRoundRect((float)((this.segments.get(i)).getX() * this.w.getSize()),
                            (float)((this.segments.get(i)).getY() * this.w.getSize()),
                            (float)this.w.getSize(),
                            (float)this.w.getSize(),
                            this.w.getSize());
                }
                for(int i = 0; i < this.map.length; ++i) {
                    for(int j = 0; j < this.map[i].length; ++j) {
                        switch(this.map[i][j]) {
                            case food:
                                g.setColor(Color.red);
                                foodAnimation.draw((float)(i * this.w.getSize()),(float)(j * this.w.getSize()));
                                break;
                            default:
                                break;
                        }
                    }
                }
                this.w.update();
           /* }
            break;
            case Stopped:{

            }
            break;
        }*/
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyDown(200)) {
            this.s.changeDirection(Direction.NORTH);
        } else if (input.isKeyDown(208)) {
            this.s.changeDirection(Direction.SOUTH);
        } else if (input.isKeyDown(203)) {
            this.s.changeDirection(Direction.WEST);
        } else if (input.isKeyDown(205)) {
            this.s.changeDirection(Direction.EAST);
        } else if (input.isKeyDown(1)) {
            container.exit();
        } else if (input.isKeyDown(Input.KEY_P)) {
            stateBasedGame.enterState(0);
        }
    }
}
