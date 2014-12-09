package proj.snowflake.src;

import java.awt.Canvas;
import javax.swing.*;

import proj.snowflake.src.buildings.*;
import proj.snowflake.src.guimanagers.BuildingMenu;
import proj.snowflake.src.guimanagers.BuildingUpgradeMenu;
import proj.snowflake.src.guimanagers.GUIComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;

public class Main extends Canvas implements Runnable, KeyListener, ActionListener, MouseListener, MouseMotionListener {

    public static final long serialVersionUID = 1L;
    public boolean isRunning = false;
    Font font = new Font("Arial", Font.PLAIN, 20);
    Toolkit tk = Toolkit.getDefaultToolkit();
    public int WIDTH = ((int) tk.getScreenSize().getWidth());
    public int HEIGHT = ((int) tk.getScreenSize().getHeight());
    //public static final int WIDTH = 800;
    //public static final int HEIGHT = 600;
    public double HEIGHTSCALE = HEIGHT/768;
    public int VIRTUALHEIGHT;
    public int VIRTUALWIDTH;
    public double MAXZOOM = 2;
    public double MINZOOM = 0.2;
    public static final String NAME = "Game";
    DecimalFormat df = new DecimalFormat("#.##");
    ArrayList<Chunk> chunks = new ArrayList<Chunk>();
    
    ArrayList<Building> buildings = new ArrayList<Building>();
    
    boolean drawBuildingGUI = false;
    
    GUIComponent guiCompo;
    
    Building mousedBuilding;
    
    Ticker ticker = new Ticker(this);
    
    ArrayList<ArrayList<Chunk>> chunkRow = new ArrayList<ArrayList<Chunk>>();
    
    AquiferStartingPoint test001;
    
    boolean rightHeld = false;
    boolean leftHeld = false;
    boolean upHeld = false;
    boolean downHeld = false;
    
    boolean zoomingIn = false;
    boolean zoomingOut = false;
    
    int mouseX = 0;
    int mouseY = 0;

    int movementSpeed = 2;
    
    private JFrame jFrame;
    
    public enum GameState {
    	MAINMENU, GAMEROOM
    }

    public Main() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        jFrame = new JFrame(NAME);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(this, BorderLayout.CENTER);
        jFrame.setResizable(true);
        jFrame.setLocation(1366/2 - WIDTH/2,768/2 - HEIGHT/2);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
        addKeyListener(this);
        jFrame.addMouseListener(this);
        addMouseListener(this);
        jFrame.addMouseMotionListener(this);
        addMouseMotionListener(this);
        jFrame.pack();
        
        //chunks.add(new Chunk(0,0));
        //Only this chunk because I'm stretching it to 
        //take up the space of three
        chunks.add(new Chunk(-100, 0));
        //chunks.add(new Chunk(100, 0));
        //chunks.add(new DeepChunk(0, 100));
        //chunks.add(new DeepChunk(-100, 100));
        //chunks.add(new DeepChunk(100, 100));
        
        //test001 = new AquiferStartingPoint(chunks.get(1));
        
        (new Thread(ticker)).start();
    }

    public int getRealX(int coordX) {
    	return (int) ((coordX + chunks.get(0).x) * 16 * HEIGHTSCALE) + WIDTH/2;
    }
    
    public int getRealY(int coordY) {
    	return (int) ((coordY + chunks.get(0).y) * 16 * HEIGHTSCALE) + HEIGHT/2;
    }
    
    public synchronized void start() {
        isRunning = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        isRunning = false;
    }

    public void run() {
        while (isRunning) {
            //System.out.println("Hej");
        	tick();
            render();
            /*try {
                Thread.sleep(0);
            } catch (Exception e) {
            }*/
        }
    }
    
    public void tick() {
        if(rightHeld) {
        	for(int i = 0; i < chunks.size(); i++) {
        		chunks.get(i).x -= movementSpeed;
        		if(mousedBuilding != null)
        			mousedBuilding.x += movementSpeed;
        	}
        }
        if(leftHeld) {
        	for(int i = 0; i < chunks.size(); i++) {
        		chunks.get(i).x += movementSpeed;
        		if(mousedBuilding != null)
        			mousedBuilding.x -= movementSpeed;
        	}
        }
        if(upHeld) {
        	for(int i = 0; i < chunks.size(); i++) {
        		chunks.get(i).y += movementSpeed;
        		if(mousedBuilding != null)
        			mousedBuilding.y -= movementSpeed;
        	}
        }
        if(downHeld) {
        	for(int i = 0; i < chunks.size(); i++) {
        		chunks.get(i).y -= movementSpeed;
        		if(mousedBuilding != null)
        			mousedBuilding.y += movementSpeed;
        	}
        }
        if(zoomingIn) {
        	if(HEIGHTSCALE < MAXZOOM)
        		HEIGHTSCALE += 0.02;
        }
        if(zoomingOut) {
        	if(HEIGHTSCALE > MINZOOM)
        		HEIGHTSCALE -= 0.02;
        }
    }
    
    public void render() {
         BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(new Color(32, 128, 255));
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        for(int i = 0; i < chunks.size(); i++) {
        	for(int j = 0; j < 300; j++) {
        		for(int k = 0; k < 100; k++) {
        			if(chunks.get(i).blocks[j][k] != null){
        				if(chunks.get(i).blocks[j][k] instanceof LimestoneBlock) {
        					g.setColor(new Color(0,255,0));
        				}
        				else if(chunks.get(i).blocks[j][k] instanceof DirtBlock) {
        					g.setColor(new Color(128, 64, 0));
        				}
        				else if(chunks.get(i).blocks[j][k] instanceof StoneBlock) {
        					g.setColor(new Color(128,128,128));
        				}
        				else if(chunks.get(i).blocks[j][k] instanceof AquiferBlock) {
        					g.setColor(new Color(0, 0, 255));
        				}
        				else if(chunks.get(i).blocks[j][k] instanceof CoalBlock) {
        					g.setColor(Color.BLACK);
        				}
        				//g.fillRect((int) ((j + chunks.get(i).x)*16*HEIGHTSCALE) - 1 + WIDTH/2,(int) ((k + chunks.get(i).y)*16*HEIGHTSCALE) - 1 + HEIGHT/2,(int) (16*HEIGHTSCALE) + 2,(int) (16*HEIGHTSCALE) + 2);
        			}
        			else {
        				g.setColor(new Color(32, 128, 255));
        				//g.fillRect((int) ((j + chunks.get(i).x)*16*HEIGHTSCALE)- 1 + WIDTH/2,(int) ((k + chunks.get(i).y)*16*HEIGHTSCALE) - 1 + HEIGHT/2,(int) (16*HEIGHTSCALE) + 2,(int) (16*HEIGHTSCALE) + 2);
        			}
        			g.fillRect((int) ((j + chunks.get(i).x)*16*HEIGHTSCALE)- 1 + WIDTH/2,(int) ((k + chunks.get(i).y)*16*HEIGHTSCALE) - 1 + HEIGHT/2,(int) (16*HEIGHTSCALE) + 2,(int) (16*HEIGHTSCALE) + 2);
        			//g.fillRect((int) ((j + chunks.get(i).x)*16)- 1 + WIDTH/2,(int) ((k + chunks.get(i).y)*16) - 1 + HEIGHT/2,(int) (16) + 2,(int) (16) + 2);
        		}
        	}
        }
        
        for(int i = 0; i < buildings.size(); i++) {
        	g.fillRect((int) ((buildings.get(i).x + chunks.get(0).x) * 16 * HEIGHTSCALE) + WIDTH/2, (int) ((buildings.get(i).y + chunks.get(0).y) * 16 * HEIGHTSCALE) + HEIGHT/2, (int) (5 * 16 * HEIGHTSCALE), (int) (5 * 16 * HEIGHTSCALE));
        }
        
        //Post-chunk-drawing-loop
        
        if(guiCompo != null) {
        	guiCompo.draw(g,  WIDTH, HEIGHT);
        }
        
        if(mousedBuilding != null) {
        	g.setColor(Color.RED);
        	g.fillRect((int) ((mousedBuilding.x + chunks.get(0).x) * 16 * HEIGHTSCALE) + WIDTH/2, (int) ((mousedBuilding.y + chunks.get(0).y) * 16 * HEIGHTSCALE) + HEIGHT/2, (int) (5 * 16 * HEIGHTSCALE), (int) (5 * 16 * HEIGHTSCALE));
        	//g.fillRect((int) ((mousedBuilding.x + 2*chunks.get(0).x) * 16 * HEIGHTSCALE) + WIDTH/2, (int) ((mousedBuilding.y + 2*chunks.get(0).y) * 16 * HEIGHTSCALE) + HEIGHT/2, (int) (5 * 16 * HEIGHTSCALE), (int) (5 * 16 * HEIGHTSCALE));
        	//g.fillRect(mousedBuilding.x, mousedBuilding.y, 100, 100);
        }
        
        g.dispose();
        bs.show();
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	System.exit(0);
        }
    	if(e.getKeyChar() == 'd') {
    		for(int i = 0; i < chunks.size(); i++) {
    			rightHeld = true;
    		}
    	}
    	if(e.getKeyChar() == 'a') {
    		for(int i = 0; i < chunks.size(); i++) {
    			leftHeld = true;
    		}
    	}
    	if(e.getKeyChar() == 'w') {
    		for(int i = 0; i < chunks.size(); i++) {
    			upHeld = true;
    		}
    	}
    	if(e.getKeyChar() == 's') {
    		for(int i = 0; i < chunks.size(); i++) {
    			downHeld = true;
    		}
    	}
    	if(e.getKeyChar() == 't') {
    		zoomingIn = true;
    	}
    	if(e.getKeyChar() == 'g') {
    		zoomingOut = true;
    	}
    }

    public void keyReleased(KeyEvent e) {
    	if(e.getKeyChar() == 'd') {
    		for(int i = 0; i < chunks.size(); i++) {
    			rightHeld = false;
    		}
    	}
    	if(e.getKeyChar() == 'a') {
    		for(int i = 0; i < chunks.size(); i++) {
    			leftHeld = false;
    		}
    	}
    	if(e.getKeyChar() == 'w') {
    		for(int i = 0; i < chunks.size(); i++) {
    			upHeld = false;
    		}
    	}
    	if(e.getKeyChar() == 's') {
    		for(int i = 0; i < chunks.size(); i++) {
    			downHeld = false;
    		}
    	}
    	if(e.getKeyChar() == 't') {
    		zoomingIn = false;
    	}
    	if(e.getKeyChar() == 'g') {
    		zoomingOut = false;
    	}
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Mouse Clicked");
    }
    public void mousePressed(MouseEvent e) {
    	mouseX = e.getX();
		mouseY = e.getY();
		
		for(int i = 0; i < buildings.size(); i++) {
			if(mouseX >= getRealX(buildings.get(i).x) && mouseX <= getRealX(buildings.get(i).x) + ((int) (5 * 16 * HEIGHTSCALE))) {
				if(mouseY >= getRealY(buildings.get(i).y) && mouseY <= getRealY(buildings.get(i).y) + ((int) (5 * 16 * HEIGHTSCALE))) {
					guiCompo = new BuildingUpgradeMenu(buildings.get(i));
					return;
				}
			}
		}
		if(guiCompo instanceof BuildingUpgradeMenu) {
			if(mouseX <= WIDTH - guiCompo.y) {
				guiCompo = null;
			}
		}
    }
    public void mouseReleased(MouseEvent e) {
    	if(mousedBuilding != null) {
    		if(mousedBuilding instanceof Mine) {
    			((Mine) mousedBuilding).lastMine = System.currentTimeMillis();
    			
    		}
        	buildings.add(mousedBuilding);
        	mousedBuilding = null;
        	return;
        }
        if(guiCompo == null && e.getX() >= WIDTH - 100) {
        	guiCompo = new BuildingMenu();
        }
        else if(guiCompo instanceof BuildingMenu) {
        	if(e.getX() <= 90 && e.getX() >= 10) {
        		if(e.getY() <= HEIGHT - 10 && e.getY() >= HEIGHT - 90) {
        			guiCompo = null;
        			mousedBuilding = new House();
        		}
        		
        	}
        	if(e.getX() <= 190 && e.getX() >= 110) {
        		if(e.getY() <= HEIGHT - 10 && e.getY() >= HEIGHT - 90) {
        			guiCompo = null;
        			mousedBuilding = new Mine();
        		}
        		
        	}
        	if(e.getX() <= 290 && e.getX() >= 210) {
        		if(e.getY() <= HEIGHT - 10 && e.getY() >= HEIGHT - 90) {
        			guiCompo = null;
        			mousedBuilding = new PowerPlant();
        		}
        		
        	}
        	else if(e.getY() < HEIGHT - 100){
        		guiCompo = null;
        	}
        }
    }
    public void mouseClicked(MouseEvent e) {
        
    }
    public void mouseEntered(MouseEvent e) {
        
    }
    public void mouseExited(MouseEvent e) {
        
    }

    public static void main(String args[]) {
        new Main().start();
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(mousedBuilding != null) {
			mousedBuilding.x = mouseX;
			mousedBuilding.y = mouseY;
			mousedBuilding.x = (int) (((mouseX - WIDTH/2) / (16 * HEIGHTSCALE)) - (chunks.get(0).x));
			mousedBuilding.y = (int) (((mouseY - HEIGHT/2) / (16 * HEIGHTSCALE)) - (chunks.get(0).y));
		}
	}
}