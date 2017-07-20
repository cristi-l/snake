package snake;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;

public class Play extends BasicGameState {

    Snake s;
    World w;
    List<SnakeSegment> segments;
    Tile[][] map;

    Animation foodAnimation;

    SpriteSheet spriteSheet;
    Image food, head, horizontal, vertical, tail, northWest, northEast, southEast, southWest;
    public Play() {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.w = new World( 1,"res/map2.tmx", "res");
        this.s = this.w.getSnake();
        this.segments = this.w.getSnakeSegments();
        this.map = this.w.getMap();

        spriteSheet = new SpriteSheet(new Image("res/snakeset32.png"),32,32);
        food = spriteSheet.getSprite(3,2);
        head = spriteSheet.getSprite(1,2);
        horizontal = spriteSheet.getSprite(2,0);
        vertical = spriteSheet.getSprite(1,1);
        northWest= spriteSheet.getSprite(3,1);
        southWest= spriteSheet.getSprite(3,0);
        northEast= spriteSheet.getSprite(2,1);
        southEast= spriteSheet.getSprite(1,0);
        tail = spriteSheet.getSprite(0,2);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        this.w.renderMap(0, 0);
        int i = 1;
        switch (segments.get(0).direction){
            case SOUTH:
                head.setRotation(0);
                break;
            case NORTH:
                head.setRotation(180);
                break;
            case EAST:
                head.setRotation(270);
                break;
            case WEST:
                head.setRotation(90);
                break;
        }
        head.draw((float)(segments.get(i).getX() * this.w.getSize()),(float)(segments.get(i).getY() * this.w.getSize()));


        for(i = 2; i < this.segments.size()-1; i++)
        {
            int x = segments.get(i).getX(),y = segments.get(i).getY();
            Direction dCurrent = segments.get(i).direction, dNext=segments.get(i-1).direction;
            if(dCurrent==dNext)
                switch (dCurrent) {
                    case NORTH:
                        vertical.draw((float) (x* this.w.getSize()), (float) (y * this.w.getSize()));
                        break;
                    case SOUTH: {
                        vertical.setRotation(180);
                        vertical.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        break;
                    }
                    case WEST:
                        horizontal.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        break;
                    case EAST: {
                        horizontal.setRotation(180);
                        horizontal.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        break;
                    }
                }
                else {
                switch (dCurrent) {
                    case NORTH: {
                        if (dNext == Direction.WEST)
                            southWest.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        if (dNext == Direction.EAST)
                            southEast.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                    }
                    break;
                    case SOUTH: {
                        if (dNext == Direction.WEST)
                            northWest.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        if (dNext == Direction.EAST)
                            northEast.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                    }
                    break;

                    case EAST:{
                        if (dNext == Direction.NORTH)
                            northWest.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        if (dNext == Direction.SOUTH)
                            southWest.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                    }
                    break;
                    case WEST: {
                        if (dNext == Direction.NORTH)
                            northEast.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                        if (dNext == Direction.SOUTH)
                            southEast.draw((float) (x * this.w.getSize()), (float) (y * this.w.getSize()));
                    }
                    break;
                }
            }

        }
        switch (segments.get(i-1).direction){
            case SOUTH:
                tail.setRotation(270);
                break;
            case NORTH:
                tail.setRotation(90);
                break;
            case EAST:
                tail.setRotation(180);
                break;
            case WEST:
                tail.setRotation(0);
                break;
        }
        tail.draw((float)(segments.get(i).getX() * this.w.getSize()),(float)(segments.get(i).getY() * this.w.getSize()));
        for(i = 0; i < this.map.length; i++) {
            for(int j = 0; j < this.map[i].length; j++) {
                switch(this.map[i][j]) {
                    case food:
                        g.setColor(Color.red);
                        food.draw((float)(i * this.w.getSize()),(float)(j * this.w.getSize()));
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
