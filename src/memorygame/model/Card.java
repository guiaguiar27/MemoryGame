package memorygame.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JLabel;

public class Card extends JLabel implements MouseListener {
    
    Icon faceIcon;
    Icon backIcon;
    int num;
    int iconWithHalf;
    int iconHeightHalf;
    boolean faceUp = false;
    boolean mousePressOnMe = false;
    
    public Card(Icon face, Icon back, int num) {
        super(back);
        this.faceIcon = face;
        this.backIcon = back;
        this.num = num;

        //pegar cliques do mouse
        this.addMouseListener(this);
        this.iconHeightHalf = back.getIconHeight() / 2;
        this.iconWithHalf = face.getIconWidth() / 2;
    }
    
    public int getNum() {
        return num;
    }
    
    private boolean overIcon(int x, int y) {
        int distanceX = Math.abs(x - (this.getWidth() / 2));
        int distanceY = Math.abs(y - (this.getHeight() / 2));
        
        return !(distanceX > this.iconHeightHalf || distanceY > this.iconWithHalf);
    }

    //é chamado uma vez que há um movimento do mouse dentro do quadro.
    @Override
    public void mouseClicked(MouseEvent me) {
        if (overIcon(me.getX(), me.getY())) {
            this.turnUp();
        }
    }

    //é chamado quando o mouse é pressionado
    @Override
    public void mousePressed(MouseEvent me) {
        if (overIcon(me.getX(), me.getY())) {
            this.mousePressOnMe = true;
        }
    }

    //é chamado quando o botão do mouse é liberado
    @Override
    public void mouseReleased(MouseEvent me) {
        if (this.mousePressOnMe) {
            this.mousePressOnMe = false;
            this.mouseClicked(me);
        }
    }

    //é chamado quando o mouse vem de uma janela exterior ao quadro.
    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //é chamado quando o mouse passa fora do quadro i.e. JFrame
    @Override
    public void mouseExited(MouseEvent me) {
        this.mousePressOnMe = false;
    }
    
    private void turnUp() {
        if (this.faceUp) return;
        if (this.faceUp) {
            this.setIcon(this.faceIcon);
        }
        
    }

    private void turnDown() {
        if (this.faceUp) {
            this.setIcon(this.backIcon);
            this.faceUp = false;
        }
        
    }
}
