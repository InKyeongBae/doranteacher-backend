package org.triathlongirls.springboot.domain.diaries;

import lombok.Getter;

@Getter
public enum DiaryType {
    SCIENCE("SCIENCE_DIARY"),
    THOUGHTS("THINKING_DIARY"),
    INFERENCE("INFERENCE_DIARY"),
    ENVIRONMENT("ENVIRONMENTAL_DIARY"),
    COMPLIMENT("COMPLIMENT_DIARY"),
    FILIAL("FILIAL_DIARY"),
    COOKING("COOKING_DIARY"),
    COMIC("COMIC_DIARY"),
    MOVIE("MOVIE_DIARY"),
    NEWSPAPER("NEWSPAPER_DIARY"),
    OBSERVATION("OBSERVATION_DIARY"),
    EXPERIENCE("EXPERIENCE_DIARY"),
    FIELD_TRIP("FIELD_TRIP_DIARY"),
    TRAVEL("TRAVEL_DIARY"),
    OBJECT("OBJECT_DIARY"),
    TOPIC("TOPIC_DIARY"),
    FREE("FREE_DIARY");

    DiaryType(String key) {
        this.key=key;
    }
    private final String key;
}
