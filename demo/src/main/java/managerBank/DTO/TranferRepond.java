package managerBank.DTO;


/**
 * TranferRepond
 */
public class TranferRepond {

    private boolean result;
    private String erorMesage;
    private int idSender;
    private int idReceiver;
    
    private int amount;
    private int idTranferBill;
    private String tranferBillDate;
    private String tranferMessage;
    public TranferRepond() {
    }
    public TranferRepond(boolean result, String erorMesage, String senderName, String receiverName, String senderPhone,
    String receiverPhone, int amount, String tranferMessage) {
        this.result = result;
        this.erorMesage = erorMesage;
      
        this.amount = amount;
        this.tranferMessage = tranferMessage;
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
    public int getIdTranferBill() {
        return idTranferBill;
    }
    public void setIdTranferBill(int idTranferBill) {
        this.idTranferBill = idTranferBill;
    }
    public String getTranferBillDate() {
        return tranferBillDate;
    }
    public void setTranferBillDate(String tranferBillDate) {
        this.tranferBillDate = tranferBillDate;
    }
    public boolean getIsResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getErorMesage() {
        return erorMesage;
    }
    public void setErorMesage(String erorMesage) {
        this.erorMesage = erorMesage;
    }
  
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getTranferMessage() {
        return tranferMessage;
    }
    public void setTranferMessage(String tranferMessage) {
        this.tranferMessage = tranferMessage;
    }




}