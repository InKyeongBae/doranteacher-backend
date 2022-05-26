//package org.triathlongirls.doranssam.domain.diaries;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//import org.triathlongirls.doranssam.constant.DiaryType;
//import org.triathlongirls.doranssam.domain.user.User;
//import org.triathlongirls.doranssam.dto.SignupRequestDto;
//import org.triathlongirls.doranssam.exception.EntityNotFoundException;
//import org.triathlongirls.doranssam.repository.DiaryRepository;
//import org.triathlongirls.doranssam.repository.UserRepository;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest
//@Transactional
//@ActiveProfiles(profiles = {"test"})
//class DiaryTest {
//
//    @Autowired
//    DiaryRepository diaryRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Test
//    @DisplayName("일기 일기글 엔티티 매핑 조회 테스트")
//    public void findDiaryAndTextTest() {
//        User user = new SignupRequestDto(
//                "username",
//                "1234",
//                "1234",
//                "nickname"
//        ).toUser(passwordEncoder);
//        userRepository.save(user);
//
//        Text text = Text.builder()
//                .originalText("originalText")
//                .correctText("correctText")
//                .highlightedText("highlightedText")
//                .hasSynonym(true)
//                .build();
//
//        Diary diary = Diary.builder()
//                .title("title")
//                .date(LocalDate.now())
//                .weather("맑음")
//                .keywords("keywords")
//                .diaryType(DiaryType.FREE)
//                .text(text)
//                .isPrivate(false)
//                .wantToCorrect(true)
//                .hasImage(false)
//                .build();
//
//        diaryRepository.save(diary);
//
//        Diary savedDiary = diaryRepository.findById(diary.getId())
//                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + diary.getId()));
//        assertThat(savedDiary.getText().getId()).isEqualTo(text.getId());
//    }
//
//}