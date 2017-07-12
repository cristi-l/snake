package snake;

public abstract class SnakeSegment {
    int x,y;
    int size;
    Direction direction;

    public SnakeSegment() {

    }

    public SnakeSegment(int x, int y, int size){
        this.x=x;
        this.y=y;
        this.size = size;
        direction = Direction.WEST;
    }
    public int getSize(){
        return size;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public void move(){
        switch (direction){
            case EAST:
            {
                x=(x+size);
            }
                break;
            case WEST:
            {
                x=(x-size/3);
            }
                break;
            case NORTH:
            {
                y=(y-size/3);
            }
                break;
            case SOUTH:
            {
                y=(y+size/3);
            }
                break;
        }
    }
}
