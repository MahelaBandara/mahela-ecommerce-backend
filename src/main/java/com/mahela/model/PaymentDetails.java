package com.mahela.model;


public class PaymentDetails {
	
	private String paymentMehtod;
	
	private String status;
	
	private String PaymentId;
	
	private String razopayPaymentLinkId;
	
	private String razopayPaymentLinkReferenceId;
	
	private String razopayPaymentLinkStatus;
	
	private String razopayPaymentId;
	
	public PaymentDetails() {
		// TODO Auto-generated constructor stub
	}

	public PaymentDetails(String paymentMehtod, String status, String paymentId, String razopayPaymentLinkId,
			String razopayPaymentLinkReferenceId, String razopayPaymentLinkStatus, String razopayPaymentId) {
		super();
		this.paymentMehtod = paymentMehtod;
		this.status = status;
		this.PaymentId = paymentId;
		this.razopayPaymentLinkId = razopayPaymentLinkId;
		this.razopayPaymentLinkReferenceId = razopayPaymentLinkReferenceId;
		this.razopayPaymentLinkStatus = razopayPaymentLinkStatus;
		this.razopayPaymentId = razopayPaymentId;
	}

	public String getPaymentMehtod() {
		return paymentMehtod;
	}

	public void setPaymentMehtod(String paymentMehtod) {
		this.paymentMehtod = paymentMehtod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentId() {
		return PaymentId;
	}

	public void setPaymentId(String paymentId) {
		PaymentId = paymentId;
	}

	public String getRazopayPaymentLinkId() {
		return razopayPaymentLinkId;
	}

	public void setRazopayPaymentLinkId(String razopayPaymentLinkId) {
		this.razopayPaymentLinkId = razopayPaymentLinkId;
	}

	public String getRazopayPaymentLinkReferenceId() {
		return razopayPaymentLinkReferenceId;
	}

	public void setRazopayPaymentLinkReferenceId(String razopayPaymentLinkReferenceId) {
		this.razopayPaymentLinkReferenceId = razopayPaymentLinkReferenceId;
	}

	public String getRazopayPaymentLinkStatus() {
		return razopayPaymentLinkStatus;
	}

	public void setRazopayPaymentLinkStatus(String razopayPaymentLinkStatus) {
		this.razopayPaymentLinkStatus = razopayPaymentLinkStatus;
	}

	public String getRazopayPaymentId() {
		return razopayPaymentId;
	}

	public void setRazopayPaymentId(String razopayPaymentId) {
		this.razopayPaymentId = razopayPaymentId;
	}
	
}
