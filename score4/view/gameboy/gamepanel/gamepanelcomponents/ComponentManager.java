package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import score4.view.gameboy.gamepanel.*;
import score4.view.gameboy.tile.Tile;

// THIS IS GAME BOARD!!!!!
public class ComponentManager {
    GamePanel gp;
    Tile[] tile;
    PegComponent pC;
    WhiteBeadComponent[] wBead;
    BlackBeadComponent[] bBead;
    int x = 0;
    int y = 0;

    //this ones x and y need to get updated

    /**
     * 
     * @param gp
     */
    public ComponentManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[3];
        getTileImage();

        pC = new PegComponent();

        wBead = new WhiteBeadComponent[32];
        setWBead();
        
        bBead = new BlackBeadComponent[32];
        setBBead();
    }
             
    //puts a bbc in the bc[]
    private void setBBead() {

        for (int i = 0; i < 32; i++) {
            bBead[i] = new BlackBeadComponent();
        }
    }
        
    //puts a wbc in the wb[]
    private void setWBead() {
                
        for (int i = 0; i < 32; i++) {
            wBead[i] = new WhiteBeadComponent();
        }
    }

    /**
     * 
     */
    public void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/tile/Grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File(/* "/score4/resources/board/board.png" */
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/board/board.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("/Users/tristensandhu/Desktop/CPSC101 project/score4/resources/misc./AIbuddy.png"
                /* "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/misc./AIbuddy.png" */));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param g2
     */
    public void draw(Graphics2D g2){

        //paint the grass background
        for (int index = 0; index < 768; index+=48) {
            for (int index2 = 0; index2 < 576; index2+=48) {
                g2.drawImage(tile[0].image,index,index2,
                    gp.tileSize,gp.tileSize,null);//this is grass (0)
            }
            
        }

        //paint board
        g2.drawImage(tile[1].image,25,96,10*gp.tileSize,
            4*gp.tileSize,null);

        //build the pegs
        for (int index = 0; index < 480; index+=120) {

            g2.drawImage(pC.getPeg(),index,192,gp.tileSize,
                2*gp.tileSize,null);//this is a peg (2)
        }

        for (int index = 40; index < 450; index+=120) {
            
            g2.drawImage(pC.getPeg(),index,128,gp.tileSize,
                2*gp.tileSize,null);//this is a peg (2)
            
        }
        
        for (int index = 80; index < 550; index+=120) {
            
            g2.drawImage(pC.getPeg(),index,64,gp.tileSize,
                2*gp.tileSize,null);//this is a peg (2)
            
        }
        
        for (int index = 120; index < 660; index+=120) {
            
            g2.drawImage(pC.getPeg(),index,4,gp.tileSize,
                2*gp.tileSize,null);//this is a peg (2)
            
        }

        //ai buddy for when you select vs computer
        g2.drawImage(tile[2].image,523,201,-2*gp.tileSize+5,
            2*gp.tileSize-5,null); 
        
        //just some test beads to see how it all fits
        
        g2.translate(45,200);
        g2.rotate(90*(Math.PI/180));

        g2.drawImage(bBead[1].getBead(), /*image */
            x, /* int x */
            y,  /* int y */
            bBead[1].getBeadSize(),  /* int hight */
        bBead[1].getBeadSize(),null);   /* int width / img observer */

        g2.dispose();
    }

    public void setX(int number){
        x = number;
    }

    public void setY(int number){
        y = number;
    }
}
