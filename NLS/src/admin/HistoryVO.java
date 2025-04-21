package admin;

import java.util.Date;

public class HistoryVO{
	private int giver_id;
	private String giver_logid;
	private int receiver_id;
	private String receiver_logid;
	private int amount;
	private Date date;
	private String is_received;
	
	public int getGiver_id() {
		return giver_id;
	}

	public void setGiver_id(int giver_id) {
		this.giver_id = giver_id;
	}

	public int getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIs_received() {
		return is_received;
	}

	public void setIs_received(String is_received) {
		this.is_received = is_received;
	}

	public String getGiver_logid() {
		return giver_logid;
	}

	public void setGived_logid(String giver_logid) {
		this.giver_logid = giver_logid;
	}

	public String getReceiver_logid() {
		return receiver_logid;
	}

	public void setReceiver_logid(String receiver_logid) {
		this.receiver_logid = receiver_logid;
	}

	public HistoryVO(int giver_id,String giver_logid, int receiver_id,String receiver_logid, int amount, Date date, String is_received) {
		super();
		this.giver_id = giver_id;
		this.giver_logid = giver_logid;
		this.receiver_id = receiver_id;
		this.receiver_logid = receiver_logid;
		this.amount = amount;
		this.date = date;
		this.is_received = is_received;
	}
	
	
}
