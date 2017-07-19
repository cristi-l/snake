//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package snake;

public abstract class SnakeSegment {
    int x;
    int y;
    int size;
    Direction direction;

    public SnakeSegment() {
    }

    public SnakeSegment(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = Direction.SOUTH;
    }
    public SnakeSegment(SnakeSegment previous) {
        this.direction = previous.direction;
        switch(previous.direction) {
            case NORTH:
                this.x = previous.x;
                this.y = previous.y + 1;
                break;
            case SOUTH:
                this.x = previous.x;
                this.y = previous.y - 1;
                break;
            case WEST:
                this.x = previous.x + 1;
                this.y = previous.y;
                break;
            case EAST:
                this.x = previous.x - 1;
                this.y = previous.y;
        }

    }

    public int getSize() {
        return this.size;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        if (this.x < x) {
            this.direction = Direction.EAST;
        }

        if (this.x > x) {
            this.direction = Direction.WEST;
        }

        this.x = x;
    }

    public void setY(int y) {
        if (this.y < y) {
            this.direction = Direction.SOUTH;
        }

        if (this.y > y) {
            this.direction = Direction.NORTH;
        }

        this.y = y;
    }

    public void move() {
        switch(this.direction) {
            case NORTH:
                --this.y;
                break;
            case SOUTH:
                ++this.y;
                break;
            case WEST:
                --this.x;
                break;
            case EAST:
                ++this.x;
        }

    }
}
