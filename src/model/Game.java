package model;

import gui.Map;

import java.util.ArrayList;

public class Game {
    private static Game game = new Game();
    private ArrayList<Player> players = new ArrayList<Player>();
    private int numberOfPlayers;
    private Map map;

    public Game(){ //game starts with zero players
        numberOfPlayers = 0;
    }

    public void joinGame(int id){
        players.add(new Player(id,true));
        numberOfPlayers++;
    }

    public void leaveGame(){
        players.clear();
        numberOfPlayers = 0;
    }

    public void addEnemy(int id){
        players.add(new Player(id,false));
        numberOfPlayers++;
    }

    public void addEnemy(int id, int x, int y){
        players.add(new Player(id,false,x, y));
        numberOfPlayers++;
    }

    public void removeEnemy(int id){
        for (Player player : players){
            if(player.getId() == id){
                players.remove(player);
                numberOfPlayers--;
            }
        }
    }

    public void updateMyPosition(int x, int y){
        for (Player player : players) {
            if (player.getMe()) {
                player.updatePlayerPosition(player.getX() + x, player.getY() + y); // previous x-pos + x and previus Y-pos + y
            }
        }
    }

    public void updateEnemyPosition(int id, int x, int y){
        for (Player player : players) {
            if(player.getId() == id){
                player.updatePlayerPosition(player.getX() + x, player.getY() + y);
            }
        }
    }

    public Player getMe(){
        for(Player player : players){
            if(player.getMe()){
                return player;
            }
        }
        return null;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public static Game getGame(){
        return game;
    }
    public Map getMap(){
        return map;
    }

    public void updateMap(Map map){
        this.map = map;
    }
    public void repaint(){
        map.repaint();
    }



}
