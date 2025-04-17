package admin;

import java.util.Date;

public class HistoryVO{
	private int giver_id;
	private int receiver_id;
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

	public HistoryVO(int giver_id, int receiver_id, int amount, Date date, String is_received) {
		super();
		this.giver_id = giver_id;
		this.receiver_id = receiver_id;
		this.amount = amount;
		this.date = date;
		this.is_received = is_received;
	}
	
	
}
