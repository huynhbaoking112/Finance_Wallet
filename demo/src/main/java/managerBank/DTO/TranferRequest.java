package managerBank.DTO;

public class TranferRequest {
    private String senderAccount = "";      // số tài khoản người chuyển
    private String receiverAccount = "";    // số tài khoản người nhận
    private int amount = 0;                 // số tiền cần chuyển
    private String tranferMessage;          // nội dung chuyển tiền
    private String senderPhone;             // số điện thoại người gửi
    private String receiverPhone;           // số điện thoại người nhận


    public TranferRequest(String senderAccount, String receiverAccount, int amount, String tranferMessage) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.tranferMessage = tranferMessage;
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
    public String getTranferMessage() {
        return tranferMessage;
    }
    public void setTranferMessage(String tranferMessage) {
        this.tranferMessage = tranferMessage;
    }
    public TranferRequest(String senderAccount, String receiverAccount, int amount) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
    }
    public String getSenderAccount() {
        return senderAccount;
    }
    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }
    public String getReceiverAccount() {
        return receiverAccount;
    }
    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }


}
