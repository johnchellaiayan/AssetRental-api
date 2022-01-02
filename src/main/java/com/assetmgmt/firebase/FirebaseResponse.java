package com.assetmgmt.firebase;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseResponse {

	/* Fields */
	@JsonProperty(value ="multicast_id")
    private long multicastId;
    private Integer success;
    private Integer failure;
    @JsonProperty(value ="canonical_ids")
    private Object canonicalIds;
    private Object results;

    @Override
    public String toString() {
        return "FirebaseResponse{" +
                "multicast_id=" + multicastId +
                ", success=" + success +
                ", failure=" + failure +
                ", canonical_ids=" + canonicalIds +
                ", results=" + results +
                '}';
    }

    /* getters and setters */
	public long getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(long multicastId) {
		this.multicastId = multicastId;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getFailure() {
		return failure;
	}

	public void setFailure(Integer failure) {
		this.failure = failure;
	}

	public Object getCanonicalIds() {
		return canonicalIds;
	}

	public void setCanonicalIds(Object canonicalIds) {
		this.canonicalIds = canonicalIds;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}
}
