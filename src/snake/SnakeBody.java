//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package snake;

public class SnakeBody extends SnakeSegment {
    public SnakeBody() {
    }

    public SnakeBody(int x, int y, int size) {
        super(x, y, size);
    }

    public SnakeBody(SnakeSegment previous) {
        super(previous);
    }
}
