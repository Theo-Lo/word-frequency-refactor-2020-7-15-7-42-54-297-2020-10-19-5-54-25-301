import java.util.*;

public class WordFrequencyGame {
    public String getResult(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<Input> inputList = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> wordCountMap = getWordCountMap(inputList);

                List<Input> wordCountList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordCountMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordCountList.add(input);
                }
                inputList = wordCountList;

                inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner wordFrequencyResult = new StringJoiner("\n");
                for (Input word : inputList) {
                    String wordFrequencyLine = word.getValue() + " " + word.getWordCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }
                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getWordCountMap(List<Input> inputList) {
        Map<String, List<Input>> wordCountMap = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordCountMap.containsKey(input.getValue())) {
                ArrayList words = new ArrayList<>();
                words.add(input);
                wordCountMap.put(input.getValue(), words);
            } else {
                wordCountMap.get(input.getValue()).add(input);
            }
        }
        return wordCountMap;
    }
}
