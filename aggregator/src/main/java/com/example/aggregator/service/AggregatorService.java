package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    private AggregatorRestClient restClient;

    public AggregatorService(AggregatorRestClient restClient) {
        this.restClient = restClient;
    }

    public Entry getDefinitionFor(String word) {
        return restClient.getDefinitionFor(word);
    }

    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(String chars) {

        List<Entry> wordsThatStartWith = restClient.getWordsStartingWith(chars);
        List<Entry> wordsThatContainSuccessiveLetters = restClient.getWordsThatContainConsecutiveLetters();

        List<Entry> common = new ArrayList<>(wordsThatStartWith);
        common.retainAll(wordsThatContainSuccessiveLetters);

        return common;
    }

    public List<Entry> getWordsThatContainSuccessiveLettersAndContains(String chars) {

        List<Entry> wordsThatContain = restClient.getWordsThatContain(chars);
        List<Entry> wordsThatContainSuccessiveLetters = restClient.getWordsThatContainConsecutiveLetters();

        List<Entry> common = new ArrayList<>(wordsThatContain);
        common.retainAll(wordsThatContainSuccessiveLetters);

        return common;


    }
    public List<Entry> getWordsEndingWithSuccessiveLetters(String chars) {

        // Get words that end with the specified character
        List<Entry> wordsThatEndWith = restClient.getWordsEndingWith(chars);

        // Get words that contain successive letters
        List<Entry> wordsThatContainSuccessiveLetters = restClient.getWordsThatContainConsecutiveLetters();

        // Find common entries in both lists
        List<Entry> common = new ArrayList<>(wordsThatEndWith);
        common.retainAll(wordsThatContainSuccessiveLetters);

        return common;
    }
}
