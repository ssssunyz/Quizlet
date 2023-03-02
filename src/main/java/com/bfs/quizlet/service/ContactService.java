package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.ContactDao;
import com.bfs.quizlet.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactDao contactDao;

    @Autowired
    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public void storeContactMsg(int user_id, String subject, String msg) {
        contactDao.storeContactMsg(user_id, subject, msg);
    }
}