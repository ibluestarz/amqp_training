package fr.lernejo.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageListener {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void onMessage(String message) {
        chatMessageRepository.addChatMessage(message);
    }
}
