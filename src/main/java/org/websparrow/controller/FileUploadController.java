package org.websparrow.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.websparrow.entity.Person;
import org.websparrow.repository.FileUploadRepository;

@RestController
@RequestMapping("/upload/file")
public class FileUploadController {

	@Autowired
	private FileUploadRepository fileUploadRepository;

	private static final String DIR_TO_UPLOAD = "/root";

	@PostMapping(value = "/database")
	public String uploadToDatabase(@RequestParam String name, @RequestParam MultipartFile file)
			throws IOException {

		// Set the form data into entity
		Person person = new Person();
		person.setName(name);
		person.setPhoto(file.getBytes());

		// Save the records into the database
		fileUploadRepository.save(person);

		return "Records saved into database.";
	}

	@PostMapping("/directory")
	public String uploadToDirectory(@RequestParam MultipartFile file) throws IOException {

		byte[] bytes = file.getBytes();
		Path path = Paths.get(DIR_TO_UPLOAD + file.getOriginalFilename());
		Files.write(path, bytes);

		return "File uploaded";
	}
}
