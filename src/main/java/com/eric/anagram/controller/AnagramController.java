package com.eric.anagram.controller;

import com.eric.anagram.service.AnagramGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class AnagramController {

    AnagramGenerator anagramGenerator;

    @GetMapping("/anagram")
    public List<String> generateAnagrams(@RequestBody String letters) {

        List<String> anagrams;
        anagrams = (List<String>) anagramGenerator.generateAnagrams(letters);
        return anagrams;
    }
}
