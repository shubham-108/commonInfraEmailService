package com.quinbay.commonInfraEmailService.controller;

import com.quinbay.commonInfraEmailService.dto.requests.RecipientAndContentRequest;
import com.quinbay.commonInfraEmailService.dto.responses.EmailSuccessReponse;
import com.quinbay.commonInfraEmailService.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping(value = "/send")
    public EmailSuccessReponse sendEmail(@RequestBody RecipientAndContentRequest request){

        Map<String, Object> data = new HashMap<>();
        try{
            sendEmailService.sendEmail(request.getRecipient(), request.getContent(), request.getTopic());

            data.put("message", "Mail Sent Successfully");

            return new EmailSuccessReponse(true, "",data);
        }
        catch(Exception e){
            data.put("message", e.getMessage());
            return new EmailSuccessReponse(false, "Failed to send mail",data);
        }

    }
}
