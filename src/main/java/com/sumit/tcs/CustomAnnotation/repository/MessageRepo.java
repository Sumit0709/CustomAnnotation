package com.sumit.tcs.CustomAnnotation.repository;

import com.sumit.tcs.CustomAnnotation.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, String> {

}
