package com.onerivet.deskbook.services;

import com.onerivet.deskbook.models.payload.EmailDto;

import jakarta.mail.MessagingException;

public interface EmailService { 
	  
    public void sendMailRequest(EmailDto emaiDto) throws  MessagingException; 

    public void sendMailApprove(EmailDto emaiDto) throws  MessagingException; 

    public void sendMailReject(EmailDto emaiDto) throws  MessagingException; 

    public void sendMailCancel(EmailDto emaiDto) throws  MessagingException;
}
