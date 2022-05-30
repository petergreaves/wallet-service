package com.demo.wallet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WalletApp {

    public WalletApp(List<Integer> _notesRequired){

        notesRequired = _notesRequired;

    }


    public List<Integer> getNotes(){
        return notesRequired;
    }
    private List<Integer> notesRequired = null;
    // m is size of notes array (number of different notes)
    private void minNotes(List<Integer> wallet, int value)
    {

        if (value>0){
            Integer thisNote=wallet.get(0);
            if (thisNote>value){
                wallet.remove(0);
            }
            else{
                value -= thisNote;
                wallet.remove(0);
                notesRequired.add(thisNote);
            }

            minNotes(wallet, value);
        }

    }
    public static void main(String args[])
    {
        int notesInWallet[] =  {10, 1, 5, 5, 5,1,1,1};

        int value = 11;

        List<Integer> notesRequired = new ArrayList<>();

        WalletApp walletApp =new WalletApp(notesRequired);
        List<Integer> wallet=new ArrayList<>();
        Arrays.stream(notesInWallet).forEach(k -> wallet.add(k));

        Integer sum = wallet.stream()
                .reduce(0, Integer::sum);

        if (value > sum){
            System.out.println("Not enough money");
        }
        else {
            walletApp.minNotes(wallet, value);
            System.out.println("Minimum notes required is " + walletApp.getNotes());
            System.out.println(notesRequired.size());
        }
    }
}
