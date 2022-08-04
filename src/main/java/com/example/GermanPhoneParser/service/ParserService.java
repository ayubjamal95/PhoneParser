package com.example.GermanPhoneParser.service;



import com.example.GermanPhoneParser.dto.ParserDto;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ParserService {
   ParserDto DefaultParserMethod(MultipartFile file) throws IOException;
   List<ParserDto> GetNumbersById (Long id);
   List<Long> GetAllNumbersById ();
   String DeleteNumbersById(Long Id);
}
