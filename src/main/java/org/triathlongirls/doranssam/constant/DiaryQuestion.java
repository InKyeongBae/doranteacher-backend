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

    private static final List<String> ENVIRONMENTAL_DIARY2 = List.of(
            "무심코 지나쳤던 주변 환경을 다시 돌아보아요.\n많은 음식은 어디서 왔을까요?\n청소시간, 넘쳐나는 쓰레기들은 어떻게 될까요?\n날씨는 왜 점점 더 더워지고 추워지는 걸까요?"
    );
    private static final List<List<String>> ENVIRONMENTAL_DIARY1 = List.of(
            List.of(
                    "오늘 날씨는 어땠나요?",
                    "작년, 또는 더 오래전과 비교했을 때 어떤 날씨였나요?",
                    "날씨가 달라진다면 이유가 무엇일까요?",
                    "우리는 "
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


    private static final List<String> APPRECIATION_DIARY2 = List.of(
            "오늘 무엇을 감상했나요?\n가장 기억이 남았던 것이 무엇이었나요?\n감상 후 어떤 생각이 들었나요?\n",
            "오늘 무엇을 감상했나요?\n가장 기억이 남았던 것이 무엇이었나요?\n다른 사람에게도 감상한 것을 알려준다면 어떻게 설명할 수 있을까요?\n"
    );

    private static final List<List<String>> APPRECIATION_DIARY1 = List.of(
            List.of(
                    "오늘 무엇을 감상했나요?",
                    "감상하게된 이유가 무엇인가요?",
                    "감상하면서 가장 기억에 남는 것이 무엇이었나요?",
                    "감상 후 어떤 마음이 들었나요?",
                    "주변 사람들에게 설명해준다면 어떻게 말할 수 있을까요?"
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

    private static final List<String> LETTER_DIARY2 = List.of(
            "주변사람에게 하고 싶은 이야기를 담아 편지 일기를 적어보아요.\n상대가 편지를 받는다면 어떤 마음일까요?\n편지를 적고나니 어떤 마음이 들었나요?"
    );

    private static final List<List<String>> LETTER_DIARY1 = List.of(
            List.of(
                    "편지를 보내고 싶은 사람이 있다면 누구인가요?",
                    "편지에 어떤 이야기를 적고 싶나요?",
                    "일기에 편지를 적어봅시다.",
                    "상대가 편지를 받는다면 어떤 마음일까요?",
                    "편지를 적고나니 어떤 마음이 들었나요?"
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


    private static final List<String> OBJECT_DIARY2 = List.of(
            "주변의 사물과 대화를 나누어 보아요.\n물건이 마음이 있다면 어떤 생각을 할까요?\n사물에게 느낀 점이 무엇인가요?",
            "내가 사물이라면 어땠을까 상상해보아요\n그 사물의 역할이 되면 어떨까요?\n사물이 되면 사람들을 어떻게 생각할까요?"
    );
    private static final List<List<String>> OBJECT_DIARY1 = List.of(
            List.of(
                    "내가 주변의 사물이 된다면 어떤 사물이 되고 싶나요?",
                    "그 사물의 역할은 무언인가요?",
                    "사물이 되면 사람들을 어떻게 생각할까요?",
                    "앞으로 그 사물을 어떻게 다룰 것 인가요?",
                    "사물의 입장에서 생각해보니 어떤 마음이 들었나요?"
            )
    );


    public static String getStep2Question(DiaryType type) {
        String key = type.getKey();
        List<String> questions = new ArrayList<>();
        switch (key) {
            case "SCIENCE_DIARY":
                questions = SCIENCE_DIARY2;
                break;
            case "STUDY_DIARY":
                questions = STUDY_DIARY2;
                break;
            case "EVENT_DIARY":
                questions = EVENT_DIARY2;
                break;
            case "ENVIRONMENTAL_DIARY":
                questions = ENVIRONMENTAL_DIARY2;
                break;
            case "COMPLIMENT_DIARY":
                questions = COMPLIMENT_DIARY2;
                break;
            case "FILIAL_DIARY":
                questions = FILIAL_DIARY2;
                break;
            case "COOKING_DIARY":
                questions = COOKING_DIARY2;
                break;
            case "APPRECIATION_DIARY":
                questions = APPRECIATION_DIARY2;
                break;
            case "WATCH_DIARY":
                questions = WATCH_DIARY2;
                break;
            case "NEWS_DIARY":
                questions = NEWS_DIARY2;
                break;
            case "OBSERVATION_DIARY":
                questions = OBSERVATION_DIARY2;
                break;
            case "READING_DIARY":
                questions = READING_DIARY2;
                break;
            case "TRAVEL_DIARY":
                questions = TRAVEL_DIARY2;
                break;
            case "OBJECT_DIARY":
                questions = OBJECT_DIARY2;
                break;
            case "LETTER_DIARY":
                questions = LETTER_DIARY2;
                break;
            case "FREE_DIARY":
                questions = FREE_DIARY2;
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
            case "SCIENCE_DIARY":
                questions_list = SCIENCE_DIARY1;
                break;
            case "STUDY_DIARY":
                questions_list = STUDY_DIARY1;
                break;
            case "EVENT_DIARY":
                questions_list = EVENT_DIARY1;
                break;
            case "ENVIRONMENTAL_DIARY":
                questions_list = ENVIRONMENTAL_DIARY1;
                break;
            case "COMPLIMENT_DIARY":
                questions_list = COMPLIMENT_DIARY1;
                break;
            case "FILIAL_DIARY":
                questions_list = FILIAL_DIARY1;
                break;
            case "COOKING_DIARY":
                questions_list = COOKING_DIARY1;
                break;
            case "APPRECIATION_DIARY":
                questions_list = APPRECIATION_DIARY1;
                break;
            case "WATCH_DIARY":
                questions_list = WATCH_DIARY1;
                break;
            case "NEWS_DIARY":
                questions_list = NEWS_DIARY1;
                break;
            case "OBSERVATION_DIARY":
                questions_list = OBSERVATION_DIARY1;
                break;
            case "READING_DIARY":
                questions_list = READING_DIARY1;
                break;
            case "TRAVEL_DIARY":
                questions_list = TRAVEL_DIARY1;
                break;
            case "OBJECT_DIARY":
                questions_list = OBJECT_DIARY1;
                break;
            case "LETTER_DIARY":
                questions_list = LETTER_DIARY1;
                break;
            case "FREE_DIARY":
                questions_list = FREE_DIARY1;
                break;
            default:
                throw new DoranssamException("invalid diary key", DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        }
        Random rand = new Random();
        return questions_list.get(rand.nextInt(questions_list.size()));
    }
}
