package com.oikos.models.enums;
/*
 * Verifica se a mensagem foi enviada ou não enviada.
 */
public enum MessageStatus {
	SENT(0, "Sent"), NOTSENT(1, "Not sent");

	private Integer messageStatusId;
	private String description;

	private MessageStatus(Integer messageStatusId, String description) {
		this.messageStatusId = messageStatusId;
		this.description = description;
	}

	public Integer getMessageStatusId() {
		return messageStatusId;
	}

	public String getDescription() {
		return description;
	}

	public static MessageStatus toEnum(Integer messageStatusId) {
		if (messageStatusId == null) {
			return null;
		}
		for (MessageStatus x : MessageStatus.values()) {
			if (messageStatusId.equals(x.getMessageStatusId())) {
				return x;
			}

		}
		throw new IllegalArgumentException("Status inválido: " + messageStatusId);
	}
}