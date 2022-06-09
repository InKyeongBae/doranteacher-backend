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
            "어떤 음식을 만들었나요?\n왜 요리를 하게 되었나요?\n요리 과정에서 있었던 일을 이야기해주세요.\n맛은 어땠나요? 요리를 하면서 느낀점은 무엇인가요?",
            "요리를 하게 된 이유가 무엇인가요?\n어떤 음식을 만들었나요?\n어떤 재료로 어떻게 만들었나요?\n도란쌤도 음식을 맛보고 싶네요. 어떤 맛일지 정말 궁금해요."
    );

    private static final List<List<String>> COOKING_DIARY1 = List.of(
            List.of(
                    "오늘 어떤 요리를 했나요?",
                    "요리를 하게 된 이유가 무엇인가요?",
                    "요리 과정을 설명해주세요. 어떻게 요리했나요?",
                    "요리는 어떻게 완성되었나요?",
                    "다음에는 어떤 요리를 하고 싶나요?"
            ),
            List.of(
                    "오늘 만든 요리는 무엇인가요?",
                    "요리하면서 재미있었던 일이 있었나요?",
                    "반면에 요리를 하면서 어려웠던 일이 있었나요?",
                    "요리는 어떻게 완성되었나요?",
                    "요리를 해보니 재미있었나요? 느낀점이 무엇인가요?"
            )
    );

    private static final List<String> SCIENCE_DIARY2 = List.of(
            "오늘한 실험에 대해 이야기 해보아요. 어떤 방법으로 실험했나요?\n실험이 끝나고 무엇을 알게 되었나요?\n느낀 점을 적어보아요."
    );

    private static final List<List<String>> SCIENCE_DIARY1 = List.of(
            List.of(
                    "오늘 어떤 실험을 해보았나요?",
                    "실험 과정에서 무엇을 했나요?",
                    "실험 결과 어떤걸을 알게 되었나요?",
                    "실험을 해보고 느낀 점이 무엇인가요?",
                    "또 궁금해진 점이나 알아보고 싶은 점이 있나요?"
            )
    );

    private static final List<String> STUDY_DIARY2 = List.of(
            "오늘 새롭게 알게 된 것이 있나요?\n어떻게 알게 되었나요?\n더 궁금하거나 알고 싶은 것은 무엇인가요?"
    );

    private static final List<List<String>> STUDY_DIARY1 = List.of(
            List.of(
                    "오늘 무엇을 배웠나요?",
                    "배우는 과정에서 가장 기억에 남는 것이 무엇인가요?",
                    "배우는 과정에서 어떤 생각이 들었나요?",
                    "또 궁금해진 점이나 알아보고 싶은 점이 있나요?",
                    "오늘 배운 것을 적어보니 어떤 기분이 드나요?"
            )
    );

    private static final List<String> EVENT_DIARY2 = List.of(
            "기억에 남는 사건이 있었나요?\n왜 그 사건이 일어났나요?\n또 어떤 마음이 들었는 지 적어보아요."
    );

    private static final List<List<String>> EVENT_DIARY1 = List.of(
            List.of(
                    "오늘 어떤 사건을 겪었나요?",
                    "왜 그 사건이 일어났나요?",
                    "가장 기억에 남는 순간은 무엇인가요?",
                    "사건은 어떻게 마무리되었나요?",
                    "사건을 겪고 어떤 마음이 들었나요?"
            )
    );

    private static final List<String> COMPLIMENT_DIARY2 = List.of(
            "칭찬의 말을 남겨보아요\n누구를 가장 칭찬하고 싶나요?\n어떤 점에서 그렇게 생각했나요?\n칭찬하는 글을 남기고 나니 어떤 마음이 드나요?"
    );

    private static final List<List<String>> COMPLIMENT_DIARY1 = List.of(
            List.of(
                    "오늘 가장 칭찬하고 싶은 사람이 누구인가요?",
                    "어떤 점을 칭찬하고 싶나요?",
                    "그렇게 생각한 이유가 무엇인가요?",
                    "칭찬의 말을 남겨봅시다.",
                    "칭찬하면서 어떤 기분이 들었나요?"
            )
    );

    private static final List<String> FILIAL_DIARY2 = List.of(
            "누구에게 어떤 효도를 했나요?\n사소한 일도 효도가 될 수 있어요.\n효도를 하고 난 후 어떤 마음이 들었나요?\n효도를 받은 분은 어떤 기분이었을까요?",
            "효도를 했다니 정말 멋져요. 누구에게 어떤 효도를 했나요?\n효도를 하고 난 후 어떤 마음이 들었나요?\n다음 효도 계획이 있나요?\n어떤 효도를 할 지 생각해볼까요?"
    );

    private static final List<List<String>> FILIAL_DIARY1 = List.of(
            List.of(
                    "오늘 누구에게 효도를 했나요?",
                    "어떤 효도를 하였나요?",
                    "효도를 받은 분은 어떤 반응을 하셨나요?",
                    "효도를 하고 어떤 기분이 들었나요?",
                    "앞으로는 어떤 효도 계획이 있나요?"
            )
    );

    private static final List<String> READING_DIARY2 = List.of(
            "오늘 어떤 글을 읽었나요?\n가장 기억에 남는 내용이 무엇이었는지 알려주세요.\n그 내용은 왜 기억에 남았나요?\n어떤 생각이 들었는지 이야기해봅시다."
    );

    private static final List<List<String>> READING_DIARY1 = List.of(
            List.of(
                    "글을 읽게 된 이유가 무엇인가요?",
                    "오늘 읽은 글을 소개해주세요.",
                    "인상 깊은 내용이 있나요? 이유가 무엇인가요?",
                    "글을 읽으면서 배운점이 무엇인가요?",
                    "글을 읽고 난후 어떤 마음이 들었나요? 느낀 점이 무엇인가요?"
            )
    );

    private static final List<String> NEWS_DIARY2 = List.of(
            "오늘 어떤 소식을 들었나요?\n뉴스, 동영상, 또는 주변 친구들에게 들은 이야기도 좋아요.\n자세하게 이야기 해 주세요.\n소식을 듣고 어떤 생각이 들었나요?"
    );

    private static final List<List<String>> NEWS_DIARY1 = List.of(
            List.of(
                    "오늘 알게된 소식은 어떤 이야기를 담고 있었어?",
                    "소식을 알고 어떤 생각이 들었어?",
                    "뉴스와 관련된 사건을 겪은 적이 있어?",
                    "이 뉴스를 주변 사람에게 이야기해준다면 누구에게 해주고 싶어?",
                    "왜 그 사람에게 이야기하고 싶어?"
            )
    );

    private static final List<String> OBSERVATION_DIARY2 = List.of(
            "오늘 무엇을 왜 관찰했나요?\n관찰했던 과정에 대해 자세히 알려주세요.\n관찰하면서 알게 된 점은 무엇인가요?"
    );

    private static final List<List<String>> OBSERVATION_DIARY1 = List.of(
            List.of(
                    "오늘 관찰했던 것이 무엇인가요?",
                    "왜 관찰하게 되었나요? 이유가 무엇인가요?",
                    "어떤 방법으로 관찰했나요?",
                    "관찰했던 과정에 어떤 일이 있었는지 알려주세요.",
                    "관찰하면서 무엇을 알게 되었나요?"
            )
    );

    private static final List<String> WATCH_DIARY2 = List.of(
            "오늘 본 영상에는 누가 등장했나요?\n가장 기억에 남는 장면에 대해 이야기 해주세요.\n영상을 보고 어떤 생각이 들었나요?\n주변 사람들에게 이 영상을 어떻게 소개할 수 있을까요?"
    );

    private static final List<List<String>> WATCH_DIARY1 = List.of(
            List.of(
                    "오늘 본 영상의 제목이 무엇인가요?",
                    "누가 등장하고 어떤 사건이 있었는 지 알려주세요.",
                    "가장 기억에 남는 장면이 무엇인가요? 왜 기억이 남았나요?",
                    "영상을 보고 어떤 생각이 들었나요?",
                    "오늘 본 영상을 주변 사람들에게 소개한다면 어떻게 말할 수 있을까요?"
            )
    );

    private static final List<String> TRAVEL_DIARY2 = List.of(
            "오늘 간 곳은 어떤 장소였나요?\n가서 보고 느낀 점을 알려주세요.\n그 곳에서 기억에 남는 일과 그 때의 기분을 이야기 해 보아요!"
    );

    private static final List<List<String>> TRAVEL_DIARY1 = List.of(
            List.of(
                    "오늘 간 곳 은 어떤 장소였나요?",
                    "가서 무엇을 보았나요?",
                    "가장 기억에 남았던 건 무엇이었나요?",
                    "왜 가장 기억에 남았나요?",
                    "여행을 갔다오니 기분이 어떤가요?"
            )
    );

    private static final List<String> FREE_DIARY2 = List.of(
            "오늘 있었던 일을 자유롭게 적어보아요.\n그 일을 일기에 기록하고 싶은 이유는 무엇인가요?\n다음에도 비슷한 일이 생기면, 그 때는 어떻게 달라질 수 있을까요?"
    );

    private static final List<List<String>> FREE_DIARY1 = List.of(
            List.of(
                    "오늘 있었던 일을 자유롭게 적어봅시다.",
                    "일기에 기록하고 싶은 이유가 무엇인가요?",
                    "아침에 일어난 일인가요? 언제 일어난 일인 지 알려주세요",
                    "다음에도 비슷한 일이 생기면, 그 때는 어떻게 달라질 수 있을까요?",
                    "오늘은 스스로에게 어떤 날이었나요?"
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
