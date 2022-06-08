package org.triathlongirls.doranssam.constant;

import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class DiaryQuestion {

    private static final List<String> COOKING_DIARY2 = List.of(
            "오늘 어떤 요리를 했나요? 요리 과정을 설명해주세요. 완성된 요리는 맛있었나요? 다음에 요리할 음식은 무엇인가요?"
    );

    private static final List<List<String>> COOKING_DIARY1 = List.of(
            List.of(
                    "오늘 어떤 요리를 했나요?",
                    "요리를 하게 된 이유가 무엇인가요?",
                    "요리 과정을 설명해주세요. 어떻게 요리했나요?",
                    "요리는 어떻게 완성되었나요?",
                    "다음에는 어떤 요리를 하고 싶나요?"
            )
    );

    public static String getStep2Question(DiaryType type) {
        String key = type.getKey();
        List<String> questions = new ArrayList<>();
        switch (key) {
            case "COOKING_DIARY":
                questions = COOKING_DIARY2;
                break;
            default:
                throw new DoranssamException("invalid diary key", DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        }

        Random rand = new Random();
        return questions.get(rand.nextInt(questions.size()));
    }

    public static List<String> getStep1Question(DiaryType type) {
        String key = type.getKey();
        List<List<String>> questions_list = new ArrayList<>();
        switch (key) {
            case "COOKING_DIARY":
                questions_list = COOKING_DIARY1;
                break;
            default:
                throw new DoranssamException("invalid diary key", DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        }
        Random rand = new Random();
        return questions_list.get(rand.nextInt(questions_list.size()));
    }
}
