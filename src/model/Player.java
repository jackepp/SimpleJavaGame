package model;

public class Player {
    private int id, x ,y;
    private boolean me;

    public Player(int id, boolean me){ //player spawns at pos 50,50
        this.id = id;
        this.me = me;
        x = 50;
        y = 50;
    }

    public Player(int id, boolean me, int x, int y){
        this.id = id;
        this.me = me;
        this.x = x;
        this.y = y;
    }

    public void updatePlayerPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getMe() {
        return me;
    }


}
