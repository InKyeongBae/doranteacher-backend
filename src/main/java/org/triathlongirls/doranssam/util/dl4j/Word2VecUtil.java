package org.triathlongirls.doranssam.util.dl4j;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.ui.standalone.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Word2VecUtil {

    public static HashMap<String, Double> calculateSimilarity(List<String> targetList, List<String> keywordList) throws FileNotFoundException, UnsupportedEncodingException {
        WordVectors vec = loadTrainedModel();
        Collection<String> nearestResult = vec.wordsNearestSum(keywordList, null, 1);
        String nearestWord = nearestResult.stream().findFirst().get(); //get first item

        HashMap<String, Double> wordMap = new HashMap<>();
        for (String target : targetList) {
            wordMap.put(target, vec.similarity(target, nearestWord));
        };

        return wordMap;
    }

    private static WordVectors loadTrainedModel() throws FileNotFoundException, UnsupportedEncodingException {
        ClassPathResource resource = new ClassPathResource("word2vec_sentence.txt");
        File model = resource.getFile();
        return WordVectorSerializer.loadStaticModel(model);
    }

}
