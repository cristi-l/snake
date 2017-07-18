//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package snake;

import java.util.List;
import java.util.Random;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World {
    Tile[][] map;
    TiledMap tiledMap;
    int[] levels = new int[]{0, 0, 46, 41, 36, 31, 26, 21, 16, 11, 1};
    Snake s = new Snake(4);
    Random r = new Random();
    int size;
    int width;
    int height;
    int frameCount = 0;

    public World(int width, int height, int size) {
        this.size = size;
        this.height = height;
        this.width = width;
        this.map = new Tile[width][height];

        for(int i = 0; i < this.map.length; ++i)
            for(int j = 0; j < this.map[i].length; ++j)
                this.map[i][j] = Tile.empty;

    }

    public void loadMap(String ref, String tileSetLocation) throws SlickException {
        this.tiledMap = new TiledMap(ref, tileSetLocation);
        for(int i = 0; i < this.map.length; ++i)
            for(int j = 0; j < this.map[i].length; ++j)
                if(tiledMap.getTileProperty(tiledMap.getTileId(i,j,0),"blocked","").equals("true"))
                    this.map[i][j] = Tile.wall;
        this.generateFood();
    }


    public void renderMap(int x, int y) {
        this.tiledMap.render(x, y);
    }

    private void generateFood() {
        int x,y;
        do {
            x = this.r.nextInt(this.height);
            y= this.r.nextInt(this.width);
        }while(this.map[x][y]!=Tile.empty);

        System.out.println("mancare la " + x+", "+y);
        this.map[x][y] = Tile.food;
    }

    public void update() {
        if (this.frameCount++ % this.getLevel(this.s.getSnake().size()) == 0) {
            this.s.move();
            this.checkCollision();
        }

    }

    public int getLevel(int size) {
        for(int i = 2; i < this.levels.length; ++i) {
            if (size > this.levels[i]) {
                return i;
            }
        }

        return 1;
    }

    public Tile[][] getMap() {
        return this.map;
    }

    public List<SnakeSegment> getSnakeSegments() {
        return this.s.getSnake();
    }

    public Snake getSnake() {
        return this.s;
    }

    private void checkCollision() {
        List<SnakeSegment> segments = this.s.getSnake();

        for(int i = 0; i < segments.size(); ++i) {
            int x = ((SnakeSegment)segments.get(i)).getX();
            int y = ((SnakeSegment)segments.get(i)).getY();
            if (this.map[x][y] == Tile.wall) {
                System.out.println("You hit a wall!!!");
            }

            if (this.map[x][y] == Tile.food) {
                this.map[x][y] = Tile.empty;
                this.s.grow(1);
                this.generateFood();
            }
        }

    }

    public int getSize() {
        return this.size;
    }
}
