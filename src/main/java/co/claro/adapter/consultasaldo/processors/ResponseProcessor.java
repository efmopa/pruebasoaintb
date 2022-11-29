package co.claro.adapter.consultasaldo.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.springframework.http.MediaType.TEXT_XML_VALUE;

/**
 * adapter-consulta-saldo
 * ResponseProcessor.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Component
public class ResponseProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(ResponseProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

    	Boolean mockSuccessResponseFlag = exchange.getIn().getHeader("mockSuccessResponseFlag", Boolean.class);
    	
    	if(null == mockSuccessResponseFlag) {
    		mockSuccessResponseFlag = true;
    	}

        logger.info("Mock Success Response Flag [{}]", mockSuccessResponseFlag);
        
        if(mockSuccessResponseFlag){
            exchange.getIn().setBody(getSuccessResponse());
        } else {
            exchange.getIn().setBody(getErrorResponse());
        }

        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, TEXT_XML_VALUE);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
    }

    private String getSuccessResponse() {

        StringBuilder xmlBuilder = new StringBuilder();

        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        		+ "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
        		+ "   <S:Body>\n"
        		+ "      <ns6:KCBEnterpriseDispatcherServiceResponse xmlns:ns2=\"http://temenos.com/KCBRWINTIBACCTDETS\" xmlns:ns3=\"http://temenos.com/KCBRWINTIBACCTSTAT\" xmlns:ns4=\"http://temenos.com/KCBRWINTNOFCUSACCTS\" xmlns:ns5=\"http://temenos.com/KCBRWINTIBCUSTDETS\" xmlns:ns6=\"http://temenos.com/KCBRWINTIBANK\">\n"
        		+ "         <Status>\n"
        		+ "            <successIndicator>Success</successIndicator>\n"
        		+ "         </Status>\n"
        		+ "         <KCBRWINTIBCUSTDETSType>\n"
        		+ "            <ns5:gKCBRWINTIBCUSTDETSDetailType>\n"
        		+ "               <ns5:mKCBRWINTIBCUSTDETSDetailType>\n"
        		+ "                  <ns5:subject>Test Message</ns5:subject>\n"
        		+ "                  <ns5:message>This is test message</ns5:message>\n"
        		+ "                  <ns5:accountNumber/>\n"
        		+ "                  <ns5:accountTitle/>\n"
        		+ "                  <ns5:transactionDateTime/>\n"
        		+ "                  <ns5:transactionAmount/>\n"
        		+ "                  <ns5:transactionCurrency/>\n"
        		+ "                  <ns5:transactionType/>\n"
        		+ "                  <ns5:transactionReference/>\n"
        		+ "                  <ns5:transactionNarrative/>\n"
        		+ "                  <ns5:transactingBranch/>\n"
        		+ "                  <ns5:actualBalance/>\n"
        		+ "                  <ns5:availableBalance/>\n"
        		+ "               </ns5:mKCBRWINTIBCUSTDETSDetailType>\n"
        		+ "            </ns5:gKCBRWINTIBCUSTDETSDetailType>\n"
        		+ "         </KCBRWINTIBCUSTDETSType>\n"
        		+ "      </ns6:KCBEnterpriseDispatcherServiceResponse>\n"
        		+ "   </S:Body>\n"
        		+ "</S:Envelope>");

        logger.info("Response Payload[{}]", xmlBuilder);

        return xmlBuilder.toString();
    }

    private String getErrorResponse() {

        StringBuilder xmlBuilder = new StringBuilder();

        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        		+ "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
        		+ "   <S:Body>\n"
        		+ "      <ns6:KCBCustomerDetailsEnquiryResponse xmlns:ns2=\"http://temenos.com/KCBRWINTIBACCTDETS\" xmlns:ns3=\"http://temenos.com/KCBRWINTIBACCTSTAT\" xmlns:ns4=\"http://temenos.com/KCBRWINTNOFCUSACCTS\" xmlns:ns5=\"http://temenos.com/KCBRWINTIBCUSTDETS\" xmlns:ns6=\"http://temenos.com/KCBRWINTIBANK\">\n"
        		+ "         <Status>\n"
        		+ "            <successIndicator>T24Error</successIndicator>\n"
	    		+ "            <messages>DEBIT.ACCT.NO:1:1=Bucket Error E-117432</messages>\r\n" 
	    		+ "            <messages>DEBIT.ACCT.NO:1:1=Bucket Error E-117434</messages>\r\n"
	    		+ "         </Status>\n"
        		+ "      </ns6:KCBCustomerDetailsEnquiryResponse>\n"
        		+ "   </S:Body>\n"
        		+ "</S:Envelope>");

        logger.info("Response Payload[{}]", xmlBuilder);

        return xmlBuilder.toString();
    }
}