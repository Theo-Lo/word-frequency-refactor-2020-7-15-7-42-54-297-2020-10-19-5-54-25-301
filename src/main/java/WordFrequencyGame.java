import java.util.*;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordCountList = calculateWordFrequencies(sentence);

            wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            StringJoiner wordFrequencyResult = buildWorldFrequencyResult(wordCountList);
            return wordFrequencyResult.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private StringJoiner buildWorldFrequencyResult(List<WordFrequency> wordCountList) {
        StringJoiner wordFrequencyResult = new StringJoiner(LINE_FEED);
        for (WordFrequency word : wordCountList) {
            wordFrequencyResult.add(buildWordFrequencyLine(word));
        }
        return wordFrequencyResult;
    }

    private List<WordFrequency> calculateWordFrequencies(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(WHITE_SPACE_REGEX);

        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        for (String word : words) {
            wordFrequencyList.add(new WordFrequency(word, 1));
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordCountMap = getWordCountMap(wordFrequencyList);

        List<WordFrequency> wordCountList = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
            wordCountList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
        }
        return wordCountList;
    }

    private String buildWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

    private Map<String, List<WordFrequency>> getWordCountMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordCountMap.containsKey(wordFrequency.getWord())) {
                ArrayList words = new ArrayList<>();
                words.add(wordFrequency);
                wordCountMap.put(wordFrequency.getWord(), words);
            } else {
                wordCountMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordCountMap;
    }
}
