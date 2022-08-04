package com.example.GermanPhoneParser.controller;

import com.example.GermanPhoneParser.dto.ParserDto;
import com.example.GermanPhoneParser.service.ParserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/parser")
@CrossOrigin(origins = "http://localhost:4200")
public class ParserController {


 	private final ParserService parserService;
	@Autowired
	public ParserController(ParserService parserService) {
		this.parserService = parserService;
	}


	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity <ParserDto> parseTextFile(@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity( parserService.DefaultParserMethod(file), HttpStatus.OK);
	}

	@GetMapping("getTaskNumbers/{id}")
	public ResponseEntity <List<ParserDto>> GetNumbersById (@PathVariable("id") Long id) {
		if(parserService.GetNumbersById(id) == null) {
			return new ResponseEntity( HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity( parserService.GetNumbersById(id), HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteByTaskId/{id}")
	public ResponseEntity <String> DeleteNumbersById (@PathVariable("id") Long id) {
		if(parserService.DeleteNumbersById(id) == null) {
			return new ResponseEntity( HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity( parserService.DeleteNumbersById(id), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity <List<Long>> GetAllNumbersById () {
		if(parserService.GetAllNumbersById() == null) {
			return new ResponseEntity( HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity( parserService.GetAllNumbersById(), HttpStatus.OK);
	}

}
