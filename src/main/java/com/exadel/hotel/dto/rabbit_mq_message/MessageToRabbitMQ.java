package com.exadel.hotel.dto.rabbit_mq_message;

import com.exadel.hotel.dto.EmailDto;
import lombok.ToString;

@ToString
public class MessageToRabbitMQ {
    private Boolean isToUser;

    private EmailDto emailDto;

    public MessageToRabbitMQ(){
    }

    public MessageToRabbitMQ(Boolean isToUser, EmailDto emailDto) {
        this.isToUser = isToUser;
        this.emailDto = emailDto;
    }

    public Boolean getToUser() {
        return isToUser;
    }

    public void setToUser(Boolean toUser) {
        isToUser = toUser;
    }

    public EmailDto getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(EmailDto emailDto) {
        this.emailDto = emailDto;
    }
}
