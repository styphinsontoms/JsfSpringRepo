
package com.example.chart.pojo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * ACDVF data schema
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "chart",
    "gauge"
})
public class ChartJson {

    @JsonProperty("chart")
    private Chart chart;
    @JsonProperty("gauge")
    private Gauge gauge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The chart
     */
    @JsonProperty("chart")
    public Chart getChart() {
        return chart;
    }

    /**
     * 
     * @param chart
     *     The chart
     */
    @JsonProperty("chart")
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * 
     * @return
     *     The gauge
     */
    @JsonProperty("gauge")
    public Gauge getGauge() {
        return gauge;
    }

    /**
     * 
     * @param gauge
     *     The gauge
     */
    @JsonProperty("gauge")
    public void setGauge(Gauge gauge) {
        this.gauge = gauge;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(chart).append(gauge).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChartJson) == false) {
            return false;
        }
        ChartJson rhs = ((ChartJson) other);
        return new EqualsBuilder().append(chart, rhs.chart).append(gauge, rhs.gauge).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
