import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordCountList = calculateWordFrequency(sentence);

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

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));

        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
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
