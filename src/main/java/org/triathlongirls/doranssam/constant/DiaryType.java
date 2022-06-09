package org.triathlongirls.doranssam.constant;

import lombok.Getter;

@Getter
public enum DiaryType {
    과학일기("SCIENCE_DIARY"),
    배움일기("STUDY_DIARY"),
    사건일기("EVENT_DIARY"),
    환경일기("ENVIRONMENTAL_DIARY"),
    칭찬일기("COMPLIMENT_DIARY"),
    효도일기("FILIAL_DIARY"),
    요리일기("COOKING_DIARY"),
    감상일기("APPRECIATION_DIARY"),
    시청일기("WATCH_DIARY"),
    소식일기("NEWS_DIARY"),
    관찰일기("OBSERVATION_DIARY"),
    독서일기("READING_DIARY"),
    여행일기("TRAVEL_DIARY"),
    사물일기("OBJECT_DIARY"),
    편지일기("LETTER_DIARY"),
    자유일기("FREE_DIARY");

    DiaryType(String key) {
        this.key=key;
    }
    private final String key;
}
