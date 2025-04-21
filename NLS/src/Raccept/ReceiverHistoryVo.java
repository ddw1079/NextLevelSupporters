package Raccept;

import java.sql.Date;

public class ReceiverHistoryVo {
    private int giverID;
    private String giverName;
    private int amount;
    private Date createDate;
    private String message;
    private String account;
    
    public ReceiverHistoryVo() {
    	
    }

    public int getGiverId() {
        return giverID;
    }

    public void setGiverId(int giverID) {
        this.giverID = giverID;
    }

    public String getGiverName() {
        return giverName;
    }

    public void setGiverName(String giverName) {
        this.giverName = giverName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    //계좌번호 Getter/Setter
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "후원자: " + giverName + ", 금액: " + amount 
             + "원, 날짜: " + createDate + ", 메세지: " + message; 
    }
}
