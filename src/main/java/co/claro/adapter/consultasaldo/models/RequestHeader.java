package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * adapter-consulta-saldo
 * RequestHeader.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class RequestHeader implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 7656371320496443722L;
 
	// All this information comes from channel as part of the full payload
	@NotBlank(message = "messageID cannot be null, empty or blank")
    protected String messageID;
    protected String conversationID;
	@Pattern(message="featureCode must be 3 digits", regexp="\\d{3}")
	protected String featureCode;
	@NotBlank(message = "featureName cannot be null, empty or blank")
    protected String featureName;
    protected String serviceCode;
    protected String processingCode;
	@NotBlank(message = "serviceName cannot be null, empty or blank")
    protected String serviceName;
	@Pattern(message="serviceSubCategory must contain only uppercase letters", regexp="[A-Z]+")
    protected String serviceSubCategory;
	@Pattern(message="minorServiceVersion must be digits dot digit format", regexp="\\d\\.\\d")
    protected String minorServiceVersion;
	@Pattern(message="channelCode must be 3 digits", regexp="\\d{3}")
    protected String channelCode;
	@NotBlank(message = "channelName cannot be null, empty or blank")
    protected String channelName;
	@Pattern(message="routeCode must be 3 digits", regexp="\\d{3}")
    protected String routeCode;
	@NotBlank(message = "routeName cannot be null, empty or blank")
    protected String routeName;
	@NotBlank(message = "timeStamp cannot be null, empty or blank")
    protected String timeStamp;
	@Pattern(message="serviceMode must be Sync or Async", regexp="Sync|Async")
    protected String serviceMode;
	@Pattern(message="subscribeEvents must be 0 or 1", regexp="0|1")
    protected String subscribeEvents;
    protected String callBackURL;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestHeader [messageID=");
		builder.append(messageID);
		builder.append(", featureCode=");
		builder.append(featureCode);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", serviceCode=");
		builder.append(serviceCode);
		builder.append(", processingCode=");
		builder.append(processingCode);
		builder.append(", serviceName=");
		builder.append(serviceName);
		builder.append(", serviceSubCategory=");
		builder.append(serviceSubCategory);
		builder.append(", minorServiceVersion=");
		builder.append(minorServiceVersion);
		builder.append(", channelCode=");
		builder.append(channelCode);
		builder.append(", channelName=");
		builder.append(channelName);
		builder.append(", routeCode=");
		builder.append(routeCode);
		builder.append(", routeName=");
		builder.append(routeName);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", serviceMode=");
		builder.append(serviceMode);
		builder.append(", subscribeEvents=");
		builder.append(subscribeEvents);
		builder.append(", callBackURL=");
		builder.append(callBackURL);
		builder.append("]");
		return builder.toString();
	}
}
