package DonationDAO;

import java.util.Date;

public class DonationDTO {

    private String donorName;
    private Date donationDate;
    private double amount;
    private String message;

    // 생성자
    public DonationDTO(String donorName, Date donationDate, double amount, String message) {
        this.donorName = donorName;
        this.donationDate = donationDate;
        this.amount = amount;
        this.message = message;
    }

    // Getter 및 Setter
    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
