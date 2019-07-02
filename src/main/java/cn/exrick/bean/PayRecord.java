package cn.exrick.bean;

import java.math.BigDecimal;
import java.util.Date;

public class PayRecord implements java.io.Serializable{
      private Integer id;
  private Integer receiveAccountId;
  private String receiveAccount;
  private Integer receiveAccountType;
  private String payAccount;
  private Integer state;
            private Date createTime;
            private String channel;
            private String receiveQrImg;
private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiveAccountId() {
        return receiveAccountId;
    }

    public void setReceiveAccountId(Integer receiveAccountId) {
        this.receiveAccountId = receiveAccountId;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public Integer getReceiveAccountType() {
        return receiveAccountType;
    }

    public void setReceiveAccountType(Integer receiveAccountType) {
        this.receiveAccountType = receiveAccountType;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReceiveQrImg() {
        return receiveQrImg;
    }

    public void setReceiveQrImg(String receiveQrImg) {
        this.receiveQrImg = receiveQrImg;
    }
}
