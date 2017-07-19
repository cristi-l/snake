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
    private Snake s = new Snake(4);
    private Random r = new Random();
    int size;
    int width;
    int height;
    int frameCount = 0;

    public World(int size, String ref, String tileSetLocation) throws SlickException {
        this.size = size;
        loadMap(ref,tileSetLocation);
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

    public void update() throws Exception {
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

    private void checkCollision() throws Exception {
        List<SnakeSegment> segments = this.s.getSnake();

        for(int i = 0; i < segments.size(); ++i) {
            int x = segments.get(i).getX();
            int y = segments.get(i).getY();
            if (this.map[x][y] == Tile.wall) {
                System.out.println("You hit a wall!!!");
                throw new Exception("Game Over!");
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

    public void reset(){
        this.s=new Snake(4);
    }
    public void loadMap(String ref, String tileSetLocation) throws SlickException {
        this.tiledMap = new TiledMap(ref, tileSetLocation);
        this.height = tiledMap.getHeight();
        this.width = tiledMap.getWidth();
        this.map = new Tile[width][height];

        for(int i = 0; i < this.map.length; ++i)
            for(int j = 0; j < this.map[i].length; ++j) {
                if (tiledMap.getTileProperty(tiledMap.getTileId(i, j, 0), "blocked", "").equals("true"))
                    this.map[i][j] = Tile.wall;
                else
                    this.map[i][j] = Tile.empty;
            }
    }
}
