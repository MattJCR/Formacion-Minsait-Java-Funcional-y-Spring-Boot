package com.example.demo.trivial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class PreguntaRestController {
    @Autowired
    private PreguntaRepository preguntaRepository;

    @GetMapping("/trivial")
    public ResponseEntity<ResultResponse> getAll() {
        List<PreguntaResponse> response = new ArrayList<PreguntaResponse>();
        ResultResponse result = new ResultResponse();
        preguntaRepository.findAll().forEach(e -> response.add(e.converToResponse()));
        result.setResponse_code(0);
        result.setResults(response);
        return new ResponseEntity<ResultResponse>(result, HttpStatus.OK);
    }

    @GetMapping("/trivial/{id}")
    public ResponseEntity<ResultResponse> getPub(@PathVariable Long id) {
        List<PreguntaResponse> response = new ArrayList<PreguntaResponse>();
        ResultResponse result = new ResultResponse();
        try {
            response.add(preguntaRepository.findById(id).get().converToResponse());
            result.setResponse_code(0);
            result.setResults(response);
            return new ResponseEntity<ResultResponse>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResponse_code(-1);
            return new ResponseEntity<ResultResponse>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/trivial")
    public ResponseEntity<PreguntaEntity> createPub(@RequestBody PreguntaResponse pregunta) {
        try {
            if (!pregunta.getIncorrect_answers().stream().anyMatch(p -> p.contains(";"))) {
                return new ResponseEntity<PreguntaEntity>(
                        preguntaRepository.save(pregunta.convertToEntity()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<PreguntaEntity>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<PreguntaEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/trivial/{id}")
    public ResponseEntity<PreguntaEntity> updatePub(@PathVariable Long id, @RequestBody PreguntaResponse response) {
        try {
            if (!response.getIncorrect_answers().stream().anyMatch(p -> p.contains(";"))) {
                PreguntaEntity entity = preguntaRepository.findById(id).get();
                PreguntaEntity responseToSave = response.convertToEntity();
                entity.setCategory(responseToSave.getCategory());
                entity.setCorrect_answer(responseToSave.getCorrect_answer());
                entity.setDifficulty(responseToSave.getDifficulty());
                entity.setIncorrect_answers(responseToSave.getIncorrect_answers());
                entity.setQuestion(responseToSave.getQuestion());
                entity.setType(responseToSave.getType());
                return new ResponseEntity<PreguntaEntity>(preguntaRepository.save(entity),
                        HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<PreguntaEntity>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<PreguntaEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/trivial/{id}")
    public ResponseEntity<PreguntaResponse> deletePub(@PathVariable Long id) {
        PreguntaResponse pregunta = new PreguntaResponse();
        try {
            preguntaRepository.deleteById(id);
            return new ResponseEntity<PreguntaResponse>(pregunta, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PreguntaResponse>(pregunta, HttpStatus.NOT_FOUND);
        }
    }
}
