package org.loopingdoge.acme;

import org.camunda.bpm.engine.ProcessEngine;
import org.loopingdoge.acme.model.House;
import org.loopingdoge.acme.utils.AcmeVariables;
import org.loopingdoge.acme.utils.BuyerOfferMessage;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("restriction")
@WebService
public class SellerWebService {
	@Resource(mappedName = "java:global/camunda-bpm-platform/process-engine/default")
	private ProcessEngine processEngine;

    public SellerWebService() {}

    @WebMethod
    public String proposeHouse(@WebParam(name = "house") House house) {
        System.out.println(house);
        if (house != null && house.toComplete()) {
            String message = "MSG";
            Map<String, Object> vars = new HashMap<String, Object>();
            /* Process initial variables */
            vars.put("newHouse", house);
			/* Start a process in Camunda, which is waiting on the specified messageId */
			String startMessageId = "houseAddition";
			System.out.println(startMessageId);
			processEngine.getRuntimeService().startProcessInstanceByMessage(startMessageId, vars);
			return "Proposal accepted";
		} else
			return "Proposal refused: data is missing";
	}
	
	
	@WebMethod
	public String sendAvailability(
			@WebParam(name="processId") String processId,
			@WebParam(name="dateList") List<String> dateList) {
		
		processEngine.getRuntimeService().setVariable(
				processId, 
				"meetingDateList", 
				dateList);
		
		// Unlock process using message
        processEngine.getRuntimeService().createMessageCorrelation(AcmeVariables.SELLER_AVAILABILITY_MESSAGE)
		  .processInstanceId(processId)
		  .correlate();

		return "Ok";
	}
	
	@SuppressWarnings("unchecked")
	@WebMethod
	public List<String> getBuyerMeetingDateList (@WebParam(name="processId") String processId) {
		List<String> dateList = (List<String>) processEngine.getRuntimeService().getVariable(
				processId, 
				"meetingDateList");
		return dateList;
	}
	
	@WebMethod
	public String confirmMeeting (
			@WebParam(name="processId") String processId,
			@WebParam(name="accept") boolean accept,
			@WebParam(name="accepedDate") String acceptedDate) {
		
		// Seller has accepted a date
		if (accept) {
			// set meeting accepted
			processEngine.getRuntimeService().setVariable(
					processId, 
					AcmeVariables.BUYER_MEETING_REPLY, 
					"accept");
			
			// set meeting accepted
			processEngine.getRuntimeService().setVariable(
					processId, 
					AcmeVariables.MEETING_DATE, 
					acceptedDate);
		}
		
		else {
			// set meeting refused
			processEngine.getRuntimeService().setVariable(
					processId, 
					AcmeVariables.BUYER_MEETING_REPLY, 
					"stop");
		}
		
		// Unlock process using message
        processEngine.getRuntimeService().createMessageCorrelation(AcmeVariables.SELLER_MEETING_REPLY_MESSAGE)
                .processInstanceId(processId)
                .correlate();

        return "Ok";
    }
	
	@WebMethod
	public BuyerOfferMessage getOffer (
			@WebParam(name="processId") String processId) {
		
		String buyerName = (String) processEngine.getRuntimeService().getVariable(processId, AcmeVariables.BUYER_NAME);
		Double buyerOffer = (Double) processEngine.getRuntimeService().getVariable(processId, AcmeVariables.BUYER_OFFER);
		House chosenHouse = (House) processEngine.getRuntimeService().getVariable(processId, AcmeVariables.CHOSEN_HOUSE);
		
		BuyerOfferMessage offerMessage = new BuyerOfferMessage(buyerName, buyerOffer, chosenHouse);
		
		return offerMessage;
	}
	
	@WebMethod
	public String offerReply (
			@WebParam(name="processId") String processId,
			@WebParam(name="accept") boolean accept) {	
		
			processEngine.getRuntimeService().setVariable(
					processId, 
					AcmeVariables.OFFER_REPLY, 
					accept);
			
			// Unlock process using message
	        processEngine.getRuntimeService().createMessageCorrelation(AcmeVariables.SELLER_OFFER_REPLY_MESSAGE)
	                .processInstanceId(processId)
	                .correlate();		
		
		return "Ok";
		
	}
}
