package snake;

import org.newdawn.slick.*;

import java.util.List;

/**
 * @author panos
 */
public class Game extends BasicGame
{
    public Game()
    {
        super("Snake");
    }

    public static void main(String[] arguments)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(10);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    Snake s;
    List<SnakeSegment> segments;
    @Override
    public void init(GameContainer container) throws SlickException
    {
        s = new Snake(3);
        segments = s.getSnake();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        segments = s.getSnake();


    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
        Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP))
        {
            s.changeDirection(Direction.NORTH);
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            s.changeDirection(Direction.SOUTH);
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            s.changeDirection(Direction.WEST);
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            s.changeDirection(Direction.EAST);
        }
        else if (input.isKeyDown(Input.KEY_ENTER))
        {
            s.grow();
        }

        container.getGraphics().setColor(Color.red);
        container.getGraphics().drawRoundRect(segments.get(0).getX(),segments.get(0).getY(),9,9,9);
        container.getGraphics().setColor(Color.white);
        for(int i = 2;i<segments.size();i++)
        {
            container.getGraphics().drawRoundRect(segments.get(i).getX(),segments.get(i).getY(),9,9,9);
        }
        s.move();
    }
}