package com.example.chart.pojo.style;

public class Scale {
	
	//Type can be "log" or "dateTime" , Linear", "Ordinal" 
	private String type;
	private String inverted="false";
	private String maximum;
	private String minimum;
	private String minimumGap="0.1";
	private String maximumGap="0.1";
	private String softMinimum;
	private String softMaximum;
	
	//stackMode can be Normal or Stacked or PercentStacked 
	private String stackMode= "none";
	private String stickToZero="true";
	private String logBase="10";
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInverted() {
		return inverted;
	}
	public void setInverted(String inverted) {
		this.inverted = inverted;
	}
	public String getMaximum() {
		return maximum;
	}
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	public String getMinimum() {
		return minimum;
	}
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}
	public String getMinimumGap() {
		return minimumGap;
	}
	public void setMinimumGap(String minimumGap) {
		this.minimumGap = minimumGap;
	}
	public String getMaximumGap() {
		return maximumGap;
	}
	public void setMaximumGap(String maximumGap) {
		this.maximumGap = maximumGap;
	}
	public String getSoftMinimum() {
		return softMinimum;
	}
	public void setSoftMinimum(String softMinimum) {
		this.softMinimum = softMinimum;
	}
	public String getSoftMaximum() {
		return softMaximum;
	}
	public void setSoftMaximum(String softMaximum) {
		this.softMaximum = softMaximum;
	}
	public String getStackMode() {
		return stackMode;
	}
	public void setStackMode(String stackMode) {
		this.stackMode = stackMode;
	}
	public String getStickToZero() {
		return stickToZero;
	}
	public void setStickToZero(String stickToZero) {
		this.stickToZero = stickToZero;
	}
	public String getLogBase() {
		return logBase;
	}
	public void setLogBase(String logBase) {
		this.logBase = logBase;
	}
	
	
	
}
