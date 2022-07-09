package org.websparrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.websparrow.entity.Person;

@Repository
public interface FileUploadRepository extends 
	JpaRepository<Person, Integer> {

}
