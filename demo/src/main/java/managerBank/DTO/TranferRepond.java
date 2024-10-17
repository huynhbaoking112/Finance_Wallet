package managerBank.DTO;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

/**
 * TranferRepond
 */
public class TranferRepond {

    private boolean result;
    private String erorMesage;
    private String senderName;
    private String receiverName;
    private String senderPhone;
    private String receiverPhone;
    private int amount;
    private String tranferMessage;
    public TranferRepond() {
    }
    public TranferRepond(boolean result, String erorMesage, String senderName, String receiverName, String senderPhone,
            String receiverPhone, int amount, String tranferMessage) {
        this.result = result;
        this.erorMesage = erorMesage;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderPhone = senderPhone;
        this.receiverPhone = receiverPhone;
        this.amount = amount;
        this.tranferMessage = tranferMessage;
    }
    public boolean isResult() {
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
    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public String getSenderPhone() {
        return senderPhone;
    }
    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }
    public String getReceiverPhone() {
        return receiverPhone;
    }
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
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