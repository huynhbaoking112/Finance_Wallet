package managerBank.DTO;


public class TranferRequest {
  
    private int idSender;
    private int idReceiver;
    private int amount;
    private String message;


    public TranferRequest(int idSender, int idReceiver, int amount, String message) {
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.amount = amount;
        this.message = message;
    }


    public TranferRequest() {
    }


    public int getIdSender() {
        return idSender;
    }


    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }


    public int getIdReceiver() {
        return idReceiver;
    }


    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
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
   
}
