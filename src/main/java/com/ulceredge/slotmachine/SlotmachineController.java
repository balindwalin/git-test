package com.ulceredge.slotmachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlotmachineController {
   
    @Autowired Account account;
    @Autowired Slotmachine slotmachine;
    
    @RequestMapping("/")
    public String index() {
        return "*** Wellcome to Slot Machine! ***\n\n" + 
                "Use:\n/account -to check your account's balance\n" +
                "/account/deposit?amount=X -to change the balance (where X ist amount of credits)\n\n" +
                "/spin -to pull the lever (default stake is 3 credits)\n" +
                "/spin?increase_stake=X -to increase your stake (where X is the amount of credits to increase)\n\n" +
                "/ -to view this help\n";
    }
    
    @RequestMapping("/account")
    public String account() {
        return "Your account's balance is " + account.getBalance() + " credits.\n";
    }
    
    @RequestMapping("/account/deposit")
    public String deposit(@RequestParam(value = "amount", required = true) final int amount) {
        if (account.deposit(amount)) {
            return "You successfully deposited " + amount + " credits. Now your account's balance is " + account.getBalance() + " credits.\n";
        } else {
            return "Unacceptable deposit: " + amount + " credits. Your account's balance is " + account.getBalance() + " credits.\n";
        }
    }
    
    @RequestMapping("/spin")
    public String spin(@RequestParam(value = "increase_stake", defaultValue = "0") final int increaseStake) {
        slotmachine.spin(increaseStake);
        return slotmachine.getMessage();
    }
}
