package org.hepl;

import jms.Fournisseur;

import java.util.concurrent.CountDownLatch;

public class AppFourniseur {
    static CountDownLatch latch = new CountDownLatch(1);
    private Fournisseur fournisseur;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Fournisseur fournisseur = new Fournisseur("tcp://localhost:61616", latch);
                    System.out.println(fournisseur);
                    fournisseur.ReceiveMsg();
                }
            }).start();
        }
    }


    public void latchCountDown() {
        latch.countDown();
    }
}
