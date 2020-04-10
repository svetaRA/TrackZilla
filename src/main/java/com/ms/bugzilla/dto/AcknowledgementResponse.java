package com.ms.bugzilla.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.ms.bugzilla.loaneligibility.Acknowledgement;


@XmlRootElement(name = "Acknowledgement", 
namespace = "http://www.javatechie.com/spring/soap/api/loanEligibility")
public class AcknowledgementResponse {
	
	/*
	 * @XmlValue public boolean isEligible() { return isEligible; }
	 * 
	 * public void setEligible(boolean isEligible) { this.isEligible = isEligible; }
	 * 
	 * @XmlValue public long getApprovedAmount() { return approvedAmount; }
	 * 
	 * public void setApprovedAmount(long approvedAmount) { this.approvedAmount =
	 * approvedAmount; }
	 * 
	 * @XmlValue public List<String> getCriteriaMismatch() { return
	 * criteriaMismatch; }
	 * 
	 * public void setCriteriaMismatch(List<String> criteriaMismatch) {
	 * this.criteriaMismatch = criteriaMismatch; }
	 * 
	 * private boolean isEligible; private long approvedAmount; private List<String>
	 * criteriaMismatch;
	 */
	@XmlValue
	private String status;

	 public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private Acknowledgement ack;
	 @XmlElement
	public Acknowledgement getAck() {
		return ack;
	}

	public void setAck(Acknowledgement ack) {
		this.ack = ack;
	}
	
	

}
