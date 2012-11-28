package main;

public class Main {
    public static void main(String[] argv) {
    	Model world = new Model();
    	Player hero = new Player(50, 50);
    	world.addPlayer(hero);
    	
        View view = new View(world);
        view.start();
    }
}
