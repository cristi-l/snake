package snake;

public class SnakeBody extends SnakeSegment {
    public SnakeBody(){
        super();
    }
    public SnakeBody(int x, int y, int size){
        super(x,y,size);
    }
    public SnakeBody(SnakeSegment previous,int size){
        super(previous.getX()+size,previous.getY(),size);
    }
}
