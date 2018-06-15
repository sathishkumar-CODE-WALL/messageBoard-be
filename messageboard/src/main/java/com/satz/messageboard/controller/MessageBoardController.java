package com.satz.messageboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satz.messageboard.model.Message;
import com.satz.messageboard.repository.MessageBoardRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MessageBoardController {

	@Autowired
	MessageBoardRepo messageBoardRepo;

	@PostMapping("/messages")
    public Message createTodo(@Valid @RequestBody Message message) {
        return messageBoardRepo.save(message);
    }
	
	@GetMapping("/messages")
	public List<Message> getMessages() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return messageBoardRepo.findAll(sortByCreatedAtDesc);
	}
	
	@GetMapping(value="/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") String id) {
        return messageBoardRepo.findById(id)
                .map(message -> ResponseEntity.ok().body(message))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") String id) {
        return messageBoardRepo.findById(id)
                .map(message -> {
                	messageBoardRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
