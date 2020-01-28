package com.springboot.service;
public class Game {

    private Player player;

    public Game(Player player) {
        this.player = player;
    }

    public String attack() {
        return "Player attack with: " + player.getWeapon();
    }

}

class Player {

    private String weapon;

    public Player(String weapon) {
        this.weapon = weapon;
    }

    String getWeapon() {
        return weapon;
    }
}