package com.journaldev.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import com.journaldev.hibernate.data.Message;
import com.journaldev.spring.service.MessageService;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;


import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;



@ManagedBean
@ViewScoped
public class RegisterMessage implements Serializable{
      @ManagedProperty(value="#{messageService}") 
      MessageService messageService;  
    
    
    private LazyDataModel<Message> lazyModel;
    private LazyDataModel<Message> lazyModelUpdate;
    private List<Message> messageList;
    private List<Message> messageUpdateList;
    private Message selectedMessage;
    private Message message = new Message();
    private String facesMessage;
   

  
    @PostConstruct
    public void init() {
        
        lazyModel = new LazyMessageDataModel(messageService);
        
    }

    public String getFacesMessage() {
        return facesMessage;
    }

    public void setFacesMessage(String facesMessage) {
        this.facesMessage = facesMessage;
    }

   
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    
    public MessageService getMessageService(){
        return messageService;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public LazyDataModel<Message> getLazyModel() {
        return lazyModel;
    }

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }
    
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Message Selected", ((Message) event.getObject()).getSubject());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   
    
    public String register() {
		// Calling Business Service
               
		messageService.register(message);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("The Message  Is Registered Successfully"));
		return "";
    }
    
    public String delete(Message message){
        messageService.delete(message.getMessageId());
        
        //send successfully deleted message to the user
        FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("The Message  Is Deleted Successfully"));
		return "";
    }
    
  
    
  
    
   /*  <p:poll interval="3"   
            listener="#{registerMessage.update}" update="form"       d
    /> */
	
}
