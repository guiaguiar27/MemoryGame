package memorygame.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import memorygame.Controller.CardControllerMachine;
import memorygame.Controller.GameControllerMachine;
import memorygame.model.CardSecond;
import memorygame.model.Machine;
import memorygame.model.UserMachine;


public class MainViewMachine implements ActionListener {

    private final JFrame mainframe;
    private final Container mainContentPane;
    private final ImageIcon cardIcon[]; //0-7 faces, 8 trás
    private final String ImageDirectory = "default";
    private UserMachine Player;
    private Machine Jarvis;
    
    public MainViewMachine() {

        this.mainframe = new JFrame("Jogo da Memória - Player Vs Computador");
        this.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainframe.setSize(800, 900);
        this.mainframe.setLocationRelativeTo(null);
        this.mainContentPane = this.mainframe.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));

        JMenuBar menuBar = new JMenuBar();
        this.mainframe.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Opções");
        menuBar.add(gameMenu);

        newMenuItem("Novo Jogo", gameMenu, this);
        newMenuItem("Sair", gameMenu, this);
        newMenuItem("Placar", gameMenu, this);

        JMenu aboutMenu = new JMenu("Sobre");
        menuBar.add(aboutMenu);
        newMenuItem("Como Jogar", aboutMenu, this);

        this.cardIcon = loadCardIcons();
    }

    private ImageIcon[] loadCardIcons() {
        ImageIcon[] icon = new ImageIcon[9];

        for (int i = 0; i < 9; i++) {
            String fileName = "/memorygame/assets/card-" + i + ".png";
            icon[i] = new ImageIcon(getClass().getResource(fileName));

            if (icon[i].getImageLoadStatus() == MediaTracker.ERRORED) {
                JOptionPane.showMessageDialog(this.mainframe,
                        "The image " + fileName + " could not be loaded.",
                        "Matching Game Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

        return icon;
    }

    public static void randomizeIntArray(int[] a) {
        Random randomizer = new Random();

        for (int i = 0; i < a.length; i++) {

            int d = randomizer.nextInt(a.length);

            int t = a[d];
            a[d] = a[i];
            a[i] = t;
        }
    }

    public JPanel makeCards() {
        this.Jarvis = new Machine();
        this.Player = new UserMachine();
        JPanel panel = new JPanel(new GridLayout(4, 2));

        // define a face de trás
        ImageIcon backIcon = this.cardIcon[8];
        GameControllerMachine Gcontroller = new GameControllerMachine(Jarvis);
        CardControllerMachine controller = new CardControllerMachine(Gcontroller, Player);
        int cardsToAdd[] = new int[8];
        for (int i = 0; i < 4; i++) {
            cardsToAdd[2 * i] = i;
            cardsToAdd[2 * i + 1] = i;

        }

        randomizeIntArray(cardsToAdd);
        for (int i = 0; i < cardsToAdd.length; i++) {

            int num = cardsToAdd[i];

            CardSecond newCard = new CardSecond(controller, this.cardIcon[num], backIcon, num);

            Gcontroller.pass_to_machine(newCard);
            panel.add(newCard);

        }

        return panel;
    }

    private void newMenuItem(String string, JMenu gameMenu, ActionListener listener) {
        JMenuItem newItem = new JMenuItem(string);
        newItem.setActionCommand(string);
        newItem.addActionListener(listener);
        gameMenu.add(newItem);

    }

    public JPanel aboutGameInit() {

        String text = "O jogo da memória pode ser jogado por um único jogador ou um jogador contra a máquina. O jogo começa colocando todas as cartas viradas para baixo."
                + " A jogada é quando um jogador vira duas cartas e coloca-as para cima.\n Se o jogador virar duas cartas que não correspondem, ambos as cartas devem ser "
                + "virados para baixo novamente no mesmo local. Se o jogador vira um par de cartas que coincidem em uma jogada, o jogador ganha o par de cartas, aumentando "
                + "seu score.\n O objetivo do jogo é virar o maior número de pares de cartas possível. O vencedor é o jogador que reúne o maior número de pares,ou seja, com maior "
                + "score. No modo de único jogador, o objetivo é identificar todos os pares no menor período de tempo possível";
        JTextArea multi = new JTextArea(text);
        multi.setWrapStyleWord(true);
        multi.setLineWrap(true);
        multi.setEditable(false);
        multi.setFont(new Font("Dialog", Font.BOLD, 20));

        JPanel textpanel = new JPanel(new GridLayout(2, 1));
        textpanel.add(multi);

        textpanel.setBackground(Color.white);

        String fileName = "/memorygame/assets/good-game.png";

        JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource(fileName)));

        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBorder(new EmptyBorder(-30, 0, 0, 0));

        textpanel.add(label1);
        textpanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        return textpanel;
    }

    public void newGame() {
        this.mainContentPane.removeAll();

        this.mainContentPane.add(makeCards());

        this.mainframe.setVisible(true);

    }

    public void aboutGame() {
        this.mainContentPane.removeAll();

        this.mainContentPane.add(aboutGameInit());

        this.mainframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Novo Jogo")) {
            newGame();
        }
        if (ae.getActionCommand().equals("Sair")) {
            System.exit(0);
        }

        if (ae.getActionCommand().equals("Como Jogar")) {
            aboutGame();
        }
        if (ae.getActionCommand().equals("Placar")) {
            if(Player.win() == 1) {
                JOptionPane.showMessageDialog(null, "Parabéns Você Ganhou");
            }
            else if(Player.win() == 2) {
                JOptionPane.showMessageDialog(null, "Empatou");
            }
            else if(Jarvis.verify_win_machine() == true) {
                JOptionPane.showMessageDialog(null, "Jarvis Ganhou");
            }
            else {
                JOptionPane.showMessageDialog(null, "Sem ganhador - Jogo em processo!");
            }
        }
    }
}
