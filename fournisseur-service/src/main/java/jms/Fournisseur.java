package jms;

import model.Item;

import javax.jms.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Fournisseur {
    private CountDownLatch latch;
    static int currentId = 0;
    int id;
    Receiver receiver;
    Sender sender;
//    static float MIN_PRICE = 1f;
//    static float MAX_PRICE = 999f;

    static int MIN_STOCK = 5;
    static int MAX_STOCk = 50;

    public Fournisseur(String brokerAddress, CountDownLatch latch){
        this.receiver = new Receiver(brokerAddress);
        this.sender = new Sender(brokerAddress);
        this.id = ++currentId;
        this.latch = latch;
    }

    public void Start(){
        receiver.Start();
        sender.Start();
    }
    public void Stop(){
        receiver.Close();
        sender.Close();
    }

    public Item CreateItem(Item old){
        Item newItem = new Item();
        newItem.setId(old.getId());
        newItem.setName(old.getName());
        newItem.setDescription(old.getDescription());
        newItem.setType(old.getType());
        newItem.setStock(RandomStock());
        newItem.setPrice(RandomFloat());
        return newItem;
    }

    public int RandomStock(){
        Random random = new Random();
        return MIN_STOCK + random.nextInt(MAX_STOCk - MIN_STOCK);
    }
    public float RandomFloat(){
        Random random = new Random();
        return 10 * random.nextFloat();
    }

    public void ReceiveMsg() {
        try{
            //this.receiver.Init();
            this.receiver.getConsumer().setMessageListener(new ConsumerMessageListener("Fournisseur " + id, this));
            this.receiver.Start();
            this.latch.await();
        } catch (InterruptedException | JMSException e) {
            e.printStackTrace();
        } finally {
            this.receiver.Close();
        }
    }

    public void SendMsg(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Fournisseur " + id + " - " + String.format("%1$tY-%1$tm-%1$td,%1$tH:%1$tM:%1$tS", new Date()) + "\n\tEnvoi du TextMessage : " + textMessage.getText());
                this.sender.Send(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else if(message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                Item oldItem = (Item) objectMessage.getObject();
                Item newItem = this.CreateItem(oldItem);
                System.out.println("Fournisseur " + id + " - " + String.format("%1$tY-%1$tm-%1$td,%1$tH:%1$tM:%1$tS", new Date()) + "\n\tEnvoi du ObjectMessage : " + newItem);
                this.sender.Send(newItem);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "latch=" + latch +
                ", id=" + id +
                ", receiver=" + receiver +
                ", sender=" + sender +
                '}';
    }
}
