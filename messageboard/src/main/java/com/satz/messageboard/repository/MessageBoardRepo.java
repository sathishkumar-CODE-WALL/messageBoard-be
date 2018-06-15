package com.satz.messageboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.satz.messageboard.model.Message;

@Repository
public interface MessageBoardRepo extends MongoRepository<Message, String> {
}
