package memorygame.model;

import java.awt.event.*;
import javax.swing.*;

public class Card extends JLabel implements MouseListener {

    Icon faceIcon;
    Icon backIcon;
    boolean faceUp = false;
    int num;
    int iconWidthHalf, iconHeightHalf;
    boolean mousePressedOnMe = false;

    public Card(Icon face, Icon back, int num) {
        // inicialmente mostrar a face para baixo
        super(back);
        this.faceIcon = face;
        this.backIcon = back;
        this.num = num;
        // capturar cliques do mouse e eventos
        this.addMouseListener(this);
      
        this.iconWidthHalf = back.getIconWidth() / 2;
        this.iconHeightHalf = back.getIconHeight() / 2;
    }

    public void turnUp() {
        if (this.faceUp) {
            return;
        }

        this.faceUp = true;

        if (this.faceUp) {
            this.setIcon(this.faceIcon);
        }
    }

    public void turnDown() {

        if (!this.faceUp) {
            return;
        }

        this.setIcon(this.backIcon);

        this.faceUp = false;
    }

    /**
     * devolve o número que identifica o tipo de cartão (qual o lado)
     *
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * Verifique se as coordenadas estão sobre o ícone
     *
     * @param x coordanada X
     * @param y coordenada Y
     * @return verdadeiro se as coordenadas estiverem sobre o ícone, caso
     * contrário, falso
     */
    private boolean overIcon(int x, int y) {
        // calcula a distância do centro do Card
        int distX = Math.abs(x - (this.getWidth() / 2));
        int distY = Math.abs(y - (this.getHeight() / 2));

        return !(distX > this.iconWidthHalf || distY > this.iconHeightHalf);
    }

    /**
     * /é chamado uma vez que há um movimento do mouse dentro do quadro.
     *
     * @param e objeto que contém informações sobre o clique do botão
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // over icon, so try to turn up the card
        if (overIcon(e.getX(), e.getY())) {
            this.turnUp();
        }
    }

    /**
     * é chamado quando o mouse é pressionado
     *
     * @param e objeto que contém informações sobre o clique do botão
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // over icon, so remember this is a mouse press
        if (overIcon(e.getX(), e.getY())) {
            this.mousePressedOnMe = true;
        }
    }

    /**
     * é chamado quando o botão do mouse é liberado
     *
     * @param e objeto que contém informações sobre o clique do botão
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // previous press was over icon
        if (this.mousePressedOnMe) {
            // mouse is no longer pressed
            this.mousePressedOnMe = false;
            // it was a click, so treat it as one
            this.mouseClicked(e);
        }
    }

    /**
     * é chamado quando o mouse vem de uma janela exterior ao quadro.
     *
     * @param e objeto que contém informações sobre o clique do botão
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * é chamado quando o mouse passa fora do quadro i.e. JFrame
     *
     * @param e objeto que contém informações sobre o clique do botão
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // forget any previous mouse press
        this.mousePressedOnMe = false;
    }

}
