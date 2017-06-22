/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pasdelor
 */
public abstract class Observateur {
    
    Lock lock;
    private Condition condition;
    
    
    public Observateur() {
        lock =  new ReentrantLock();
        condition = lock.newCondition();
        
    }
    
    
    public abstract void traiterMessage(Message msg);

    public abstract void traiterMessagePlateau(MessagePlateau msg);
    
    public abstract void traiterMessageAventurier(MessageAventurier msg);
    
    public void waitForInput() {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException ex) {
            
        } finally {
            lock.unlock();
        }
    }
    
    public void notifier() {
        lock.lock();
        try{
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    
}
