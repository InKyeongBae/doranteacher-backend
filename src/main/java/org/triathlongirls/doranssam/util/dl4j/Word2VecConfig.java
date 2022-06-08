package org.triathlongirls.doranssam.util.dl4j;

import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.ui.standalone.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Slf4j
@Component
public class Word2VecConfig {

    private int batchSize = 1000;
    private int iterations = 3;
    private int layerSize = 150;

    private SentenceIterator loadData() throws FileNotFoundException {
        log.info("Load data....");
        ClassPathResource resource = new ClassPathResource("word2vec_sentence.txt");
        SentenceIterator iter = new LineSentenceIterator(resource.getFile());
        iter.setPreProcessor(new SentencePreProcessor() {
            @Override
            public String preProcess(String sentence) {
                //전처리 : 데이터 토큰화

                return sentence;
            }
        });
        return iter;
    }

    private TokenizerFactory createTokenizer() {
        TokenizerFactory tokenizer = new DefaultTokenizerFactory();
        tokenizer.setTokenPreProcessor(new CommonPreprocessor());
        return tokenizer;
    }

    public void  trainModel() throws FileNotFoundException {
        log.info("Build model....");

        Word2Vec vec = new Word2Vec.Builder()
                .batchSize(batchSize)
                .minWordFrequency(5)
                .useAdaGrad(false)
                .layerSize(layerSize)
                .iterations(iterations)
                .learningRate(0.025)
                .minLearningRate(1e-3)
                .negativeSample(10)
                .iterate(loadData())
                .tokenizerFactory(createTokenizer())
                .build();
        vec.fit();
    }
}
