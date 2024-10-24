package managerBank.Model;

import java.sql.Date;

public class UserTransaction {

    private int id;
    private int idSender;
    private int idRecevier;
    private String nameSender;
    private String nameReceiver;
    private int amount;
    private String message;
    private Date time;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdRecevier() {
        return idRecevier;
    }

    public void setIdRecevier(int idRecevier) {
        this.idRecevier = idRecevier;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UserTransaction(int id, int idSender, int idRecevier, String nameSender, String nameReceiver, int amount,
            String message, Date time) {
        
        this.id = id;
        this.idSender = idSender;
        this.idRecevier = idRecevier;
        this.nameSender = nameSender;
        this.nameReceiver = nameReceiver;
        this.amount = amount;
        this.message = message;
        this.time = time;
    }


    
}
