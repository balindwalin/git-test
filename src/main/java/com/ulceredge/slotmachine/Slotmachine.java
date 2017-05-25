package com.ulceredge.slotmachine;

import java.util.Arrays;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;

public class Slotmachine {
    
    private final int MIN_COST = 3;
    private final int NUM_REELS = 3;
    private final int NUM_FRUITS = 3;
    
    private final String[] FRUITS = {"apple", "banana", "citrus"};
    private final double[] REWARD_MULT = {3.3, 5, 6.7};

    private String message;
    
    @Autowired Account account;

    public Slotmachine() {
        this.message = "Slot machine is initialized.";
    }
    
    public boolean spin(final int increaseStake) {
        int stake = MIN_COST + increaseStake;
        
        if (stake < MIN_COST) {
            this.message = "Wrong stake value: " + stake + " credits!\n";
            return false;
        }
        
        if (stake > account.getBalance()) {
            this.message = "You have not enough money on your balance to play!\n";
            return false;
        }
        
        int[] reelsInt = new Random().ints(NUM_REELS, 0, NUM_FRUITS).toArray();
        
        this.message = "The slot machine shows: " + 
                Arrays.stream(reelsInt).mapToObj(i -> FRUITS[i]).reduce("", (s, c) -> s + " " + c) + ".\n";
        
        if (Arrays.stream(reelsInt).distinct().count() == 1) {
            int reward = (int) Math.round(REWARD_MULT[reelsInt[0]] * stake);
            this.message += "You won! Your reward is " + reward + " credits!\n";
            account.deposit(reward - stake);
        } else {
            this.message += "You lose!\n";
            account.deposit(-stake);
        }
        this.message += "Your actual balance is " + account.getBalance() + " credits.\n";
        return true;
    }
    
    public String getMessage() {
        return this.message;
    }
}
