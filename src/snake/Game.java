//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package snake;

import java.util.List;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {
    Snake s;
    World w;
    List<SnakeSegment> segments;
    Tile[][] map;
    TiledMap grassMap;

    public Game() {
        super("Snake");
    }

    public static void main(String[] arguments) {
        try {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(675, 675, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException var2) {
            var2.printStackTrace();
        }

    }

    public void init(GameContainer container) throws SlickException {
        this.w = new World(42, 42, 16);
        this.s = this.w.getSnake();
        this.segments = this.w.getSnakeSegments();
        this.map = this.w.getMap();
        this.w.loadMap("res/grassmap.tmx", "res");
    }

    public void update(GameContainer container, int delta) throws SlickException {
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
        }

    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        this.w.renderMap(0, 0);
        container.getGraphics().setColor(Color.blue);
        container.getGraphics().drawRoundRect((float)(((SnakeSegment)this.segments.get(0)).getX() * this.w.getSize()), (float)(((SnakeSegment)this.segments.get(0)).getY() * this.w.getSize()), (float)this.w.getSize(), (float)this.w.getSize(), this.w.getSize());
        container.getGraphics().setColor(Color.white);

        int i;
        for(i = 2; i < this.segments.size(); ++i) {
            container.getGraphics().drawRoundRect((float)(((SnakeSegment)this.segments.get(i)).getX() * this.w.getSize()), (float)(((SnakeSegment)this.segments.get(i)).getY() * this.w.getSize()), (float)this.w.getSize(), (float)this.w.getSize(), this.w.getSize());
        }

        for(i = 0; i < this.map.length; ++i) {
            for(int j = 0; j < this.map[i].length; ++j) {
                switch(this.map[i][j]) {
                    case food:
                        container.getGraphics().setColor(Color.red);
                        container.getGraphics().drawRoundRect((float)(i * this.w.getSize()), (float)(j * this.w.getSize()), (float)this.w.getSize(), (float)this.w.getSize(), this.w.getSize());
                        break;
                    case wall:
                        container.getGraphics().setColor(Color.darkGray);
                        container.getGraphics().drawRect((float)(i * this.w.getSize()), (float)(j * this.w.getSize()), (float)this.w.getSize(), (float)this.w.getSize());
                }
            }
        }

        this.w.update();
    }
}
