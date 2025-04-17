package Raccept;

import java.sql.Date;

import vo.BaseVO;


/* **
 * 파일 설명: 수혜자 수혜내역에서 사용할 VO
 * 파일 상세: 수혜자의 수혜내역에서 사용할 VO
 *  - 수혜자 DAO에서 데이터를 사용할 것
 *  - 아래 데이터는 History DB와 같음
 * 
 * */

//수혜자가 받은 후원 내역을 담는 VO 클래스
public class ReceiverHistoryVo {
	
	
	// 필드 정의 (DB에서 받아오는 항목들)
	private int giverID;	//후원자 ID
	private String giverName;	//후원자 이름
	private int amount;	//후원 금액
	private Date createDate;	//후원일자
	private String message;	//후원메세지
	

    // 기본 생성자
    public ReceiverHistoryVo() {}

    // setter 메서드 	
	public void setGiverId(int int1) {
		// TODO Auto-generated method stub
		this.giverID = giverID;
		
	}

	public void setGiverName(String string) {
		// TODO Auto-generated method stub
		this.giverName = giverName;
		
	}

	public void setAmount(int int1) {
		// TODO Auto-generated method stub
		this.amount = amount;
	}

	public void setCreateDate(Date date) {
		// TODO Auto-generated method stub
		this.createDate = createDate;
	}

	public void setMessage(String string) {
		// TODO Auto-generated method stub
		this.message = message;
	}
    // getter 메서드 	
	public int getGiverID() {
		return giverID;
	}
	
	public String getGiverName() {
		return giverName;
	}

	public char[] getAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCreateDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "후원자: " + giverName + ", 금액: " + amount 
				+ "원, 날짜: " + createDate + ", 메세지: " + message; 
	
	}

	public Object getCreate_date() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
