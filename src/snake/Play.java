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
    private boolean isOver;

    public Play() {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.w = new World( 16,"res/map2.tmx", "res");
        this.s = this.w.getSnake();
        this.segments = this.w.getSnakeSegments();
        this.map = this.w.getMap();
        foodImg = new Image[1];
        foodImg[0] = new Image("res/Apple.png");
        foodAnimation=new Animation(foodImg,1);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

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
        try {
            this.w.update();
        } catch (Exception e) {
            w.reset();
            s=w.getSnake();
            this.segments = this.w.getSnakeSegments();
            stateBasedGame.enterState(0);
        }

    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = container.getInput();

            if (input.isKeyDown(Input.KEY_UP)) {
                if(s.getDirection()!=Direction.SOUTH)
                this.s.changeDirection(Direction.NORTH);
            } else if (input.isKeyDown(Input.KEY_DOWN)) {
                if(s.getDirection()!=Direction.NORTH)
                this.s.changeDirection(Direction.SOUTH);
            } else if (input.isKeyDown(Input.KEY_LEFT)) {
                if(s.getDirection()!=Direction.EAST)
                this.s.changeDirection(Direction.WEST);
            } else if (input.isKeyDown(Input.KEY_RIGHT)) {
                if(s.getDirection()!=Direction.WEST)
                this.s.changeDirection(Direction.EAST);
            } else if (input.isKeyDown(1)) {
                container.exit();
            } else if (input.isKeyDown(Input.KEY_P)) {
                stateBasedGame.enterState(0);
            }

    }
}
