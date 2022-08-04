package com.example.GermanPhoneParser.service.impl;

import com.example.GermanPhoneParser.domain.ParserDomain;
import com.example.GermanPhoneParser.dto.ParserDto;
import com.example.GermanPhoneParser.repository.ParserRepository;
import com.example.GermanPhoneParser.service.ParserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class DefaultParserService implements ParserService {

    private final ParserRepository parserRepository;

    @Autowired
    public DefaultParserService(ParserRepository parserRepository) {
        this.parserRepository = parserRepository;
    }

    @Override
    public ParserDto DefaultParserMethod(MultipartFile file) throws IOException {

        Integer newTaskId;
        newTaskId = parserRepository.findMaxTaskId();
        if (newTaskId != null) {
            newTaskId++;
        } else {
            newTaskId = new Integer(100);
        }
        ParserDto parserDto = new ParserDto();
        List<ParserDomain> parserDomains = new ArrayList<>();
        InputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = file.getInputStream();
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.replaceAll("\\s+", "");
                boolean isFound = line.indexOf("0049") == 0 || line.indexOf("+49") == 0;
                boolean isLengthOk = line.length() == 14 || line.length() == 15 ? true : false;
                if (isFound && isLengthOk) {
                    ParserDomain parserDomain = new ParserDomain();
                    parserDomain.setPhoneNumber(line);
                    parserDomain.setUploadedDate(new java.util.Date());
                    parserDomain.setTaskId(Long.valueOf(newTaskId));
                    parserDomains.add(parserDomain);
                }
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
            }
            parserRepository.saveAll(parserDomains);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        parserDto.setTaskId(Long.valueOf(newTaskId));
        return parserDto;
    }
    @Override
    public List<ParserDto> GetNumbersById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        List<ParserDto> parserDtos = new ArrayList<>();
        List<ParserDomain> parserDomains = parserRepository.findAllByTaskId(id);
        for (ParserDomain parserDomain : parserDomains) {
            ParserDto parserDto = modelMapper.map(parserDomain, ParserDto.class);
            parserDtos.add(parserDto);
        }
        return parserDtos;
    }
    @Override
    public List<Long> GetAllNumbersById() {
        List<Long> taskIds = new ArrayList<>();
        taskIds = parserRepository.findDistinctTasks();
        return taskIds;
    }
    @Override
    @Transactional
    public String DeleteNumbersById(Long Id) {
        String success = null;
        try {
            parserRepository.deleteByTaskId(Id);
            success = "Successful";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

}
