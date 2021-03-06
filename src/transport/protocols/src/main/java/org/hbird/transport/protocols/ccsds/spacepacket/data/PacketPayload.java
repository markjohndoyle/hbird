package org.hbird.transport.protocols.ccsds.spacepacket.data;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;


public class PacketPayload implements Serializable {
	private static final long serialVersionUID = -1837235324860411156L;

	public int payloadIdentifier;
	public byte[] payload;
	public long timeStamp;

	public PacketPayload(int payloadIdentifier, byte[] payload, long timeStamp) {
		this.payloadIdentifier = payloadIdentifier;
		this.payload = ArrayUtils.clone(payload);
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "packet apId: " + payloadIdentifier + ", payload: " + new String(payload) + ", timeStamp: " + timeStamp;
	}

}
