package score4.view.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import score4.controller.Controller;
//import score4.model.Colour;
import score4.view.gameboy.borderpanel.BottomPanel;
import score4.view.gameboy.borderpanel.LeftPanel;
import score4.view.gameboy.borderpanel.RightPanel;
import score4.view.gameboy.borderpanel.TitlePanel;
import score4.view.gameboy.gamepanel.*;

public class GameboyPanel extends JPanel{
    
    private BottomPanel bp = new BottomPanel();
    private TitlePanel tp = new TitlePanel();
    private LeftPanel lp = new LeftPanel();
    private RightPanel rp = new RightPanel();
    private GamePanel gp = new GamePanel();
    private ImageIcon image2;
    private boolean player1 = true;

    public GameboyPanel(){

        this.setLayout(new BorderLayout());
        this.initialize(gp);
    }

    public void initialize(GamePanel gp){

        //create bottom bar with enter and input field
        add(bp, BorderLayout.SOUTH);

        //create title bar
        add(tp, BorderLayout.NORTH); 

        //create left side frame
        add(lp, BorderLayout.WEST);

        //create panel4 right side frame
        add(rp, BorderLayout.EAST);

        // create game screen
        add(gp, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        bp.add(textField);
        textField.setBackground(new Color(159,146,189));
        textField.setFont(new java.awt.Font(TOOL_TIP_TEXT_KEY, ABORT, 
            32));

        JButton button = new JButton();
        bp.add(button);
        button.addActionListener(new  Controller(gp){
        
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(textField.getText().equals("")){
                    System.out.println("please input move...");
                }else {
                    if(player1 == true){    // white player

                    String input = textField.getText();
                    String realInput = input.substring(18, 20);
                    String beadColour = input.substring(5, 10);
                    beadColour.toLowerCase();
                    System.out.println(realInput); // gives a parsable play
                    getGameBoard().realMove(realInput); //gets location of peg to play on
                    getGameBoard().getPeg(getGameBoard().getX(), getGameBoard().getY()).setBead(player1); //sets bead at location
                    //need to call repaint
                    player1 = false;
                    } else {    // black player

                        String input = textField.getText();
                        String realInput = input.substring(18, 20);
                        String beadColour = input.substring(5, 10);
                        beadColour.toLowerCase();
                        System.out.println(realInput); // gives a parsable play
                        getGameBoard().realMove(realInput);
                        getGameBoard().getPeg(getGameBoard().getX(), getGameBoard().getY()).setBead(player1); //sets bead at location
                        //need to call repaint
                        player1 = true;
                    }
                    //how do i get this repaint to repaint gp
                    /* System.out.println(getPeg().getX()+"  "+getPeg().getY());
                    update(getPeg().getXLocation(),getPeg().getYLocation()); */
                }
            } 
        });

        //gets image icon "enter"
        try{
            image2 = new ImageIcon(ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 "+
                "project/score4/resources/misc./enter3.png")));
        } catch (Exception e) {
            System.out.println("This should never happen so what did you do!!!!");
        }
        button.setBorder(BorderFactory.createLineBorder(new Color(73,71,134)));
        button.setIcon(image2);
        button.setSize(64, 32);

        setVisible(true); 
    }

    public void updatePanel(GamePanel gap,int x, int y) {

        /* gap.passComponentManager().update(x, y); */
        gap.repaint();
    }
}
