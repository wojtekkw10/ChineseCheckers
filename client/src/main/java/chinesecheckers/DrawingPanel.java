package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DrawingPanel extends JPanel {
    private JFrame frame;
    private Board board;

    DrawingPanel(JFrame frame, Board board)
    {
        this.board = board;
        this.frame = frame;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 800);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent ( g );
        g.setColor ( Color.RED );
        frame.getContentPane().removeAll();
        System.out.print("LOL123\n");

        board.getSampleBoard();
        ArrayList<PlayingSpace> PS = board.getBoard();

        for(int i=0; i<PS.size(); i++)
        {
            int x=PS.get(i).getX();
            int y=PS.get(i).getY();
            int radius = 100;

            g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
        }
        System.out.print("LOL123444\n");
    }
}