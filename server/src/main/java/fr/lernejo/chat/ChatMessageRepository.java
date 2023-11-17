package fr.lernejo.chat;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatMessageRepository {

    private final List<String> messages = new ArrayList<>();

    public void addChatMessage(String message) {
        messages.add(message);
        if (messages.size() > 10) {
            messages.remove(0);
        }
    }

    public List<String> getLastTenMessages() {
        return new ArrayList<>(messages);
    }
}
