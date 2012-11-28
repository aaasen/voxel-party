package main;

public class Main {
    public static void main(String[] argv) {
    	Model world = new Model();
    	Player p1 = new Player(50, 50);
    	Player p2 = new Player(100, 100);
    	world.addPlayer(p1);
    	world.addPlayer(p2);
    	
        View view = new View(world, 800, 600);
        view.start();
    }
}
