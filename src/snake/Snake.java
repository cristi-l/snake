package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    SnakeSegment head;
    List<SnakeSegment> snake;

    public Snake(int length) {
        head = new SnakeHead(400,300,9);
        snake = new ArrayList<>();
        snake.add(head);
        for(int i = 1; i<=length;i++){
            snake.add(new SnakeBody(snake.get(i-1),9));
        }
    }
    public List<SnakeSegment> getSnake(){
        return snake;
    }
    public void changeDirection(Direction d){
        head.direction=d;
    }

    public void grow() {
        snake.add(new SnakeBody(snake.get(snake.size()-1),9));
    }

    public void move() {
        head.move();
        for(int i=snake.size()-1;i>0;i--){
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
    }
}
