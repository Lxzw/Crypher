package com.l.mk.obj;

public class Message {
	private MessageHeader messageHeader;
	private MessageContent messageContent;
	
	public MessageHeader getMessageHeader() {
		return messageHeader;
	}
	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}
	
	public MessageContent getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(MessageContent messageContent) {
		this.messageContent = messageContent;
	}
	
}
