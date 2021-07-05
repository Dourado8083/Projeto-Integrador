package com.oikos.models.enums;

public enum MessageType {
	PUBLIC(0, "Public"), PRIVATE(1, "Private"), ONCOMMUNITY(2, "On Community");

	private Integer messageTypeId;
	private String description;

	private MessageType(Integer messageTypeId, String description) {
		this.messageTypeId = messageTypeId;
		this.description = description;
	}

	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	public String getDescription() {
		return description;
	}

	public static MessageType toEnum(Integer messageTypeId) {
		if (messageTypeId == null) {
			return null;
		}
		for (MessageType x : MessageType.values()) {
			if (messageTypeId.equals(x.getMessageTypeId())) {
				return x;
			}

		}
		throw new IllegalArgumentException("Tipo inválido: " + messageTypeId);
	}

}
