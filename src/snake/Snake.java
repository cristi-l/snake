//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    SnakeSegment head = new SnakeHead(5, 5, 10);
    List<SnakeSegment> snake = new ArrayList();

    public Snake(int length) {
        this.snake.add(this.head);

        for(int i = 1; i <= length; ++i) {
            this.snake.add(new SnakeBody((SnakeSegment)this.snake.get(i - 1)));
        }

    }

    public Direction getDirection() {
        return this.head.direction;
    }
    public List<SnakeSegment> getSnake() {
        return this.snake;
    }

    public void changeDirection(Direction d) {
        this.head.direction = d;
    }

    public void grow(int i) {
        while(i-- > 0) {
            this.snake.add(new SnakeBody((SnakeSegment)this.snake.get(this.snake.size() - 1)));
        }

    }

    public void shrink(int i) {
        while(this.snake.size() > i) {
            this.snake.remove(i);
        }

    }

    public void move() {
        this.head.move();

        for(int i = this.snake.size() - 1; i > 0; --i) {
            if (this.head.getX() == ((SnakeSegment)this.snake.get(i)).getX() && this.head.getY() == ((SnakeSegment)this.snake.get(i)).getY()) {
                this.shrink(i);
                System.out.println("Te-ai muscat!!! " + this.snake.size());
            } else {
                ((SnakeSegment)this.snake.get(i)).setX(((SnakeSegment)this.snake.get(i - 1)).getX());
                ((SnakeSegment)this.snake.get(i)).setY(((SnakeSegment)this.snake.get(i - 1)).getY());
            }
        }

    }
}
