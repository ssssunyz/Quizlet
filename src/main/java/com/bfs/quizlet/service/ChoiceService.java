package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.ChoiceDao;
import com.bfs.quizlet.domain.Choice;
import com.bfs.quizlet.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChoiceService {
    private final ChoiceDao choiceDao;

    @Autowired
    public ChoiceService(ChoiceDao choiceDao) {
        this.choiceDao = choiceDao;
    }

    public List<Choice> getChoicesByQuestion(int question_id) {
        return choiceDao.getChoicesByQuestion(question_id);
    }

    public Optional<Choice> getChoiceById(Integer selectedChoiceId) {
        return Optional.ofNullable(choiceDao.getChoiceById(selectedChoiceId));
//        return choiceDao.getAllChoices().stream()
//                                .filter(choice -> choice.getChoice_id() == selectedChoiceId)
//                                .findFirst();
    }

    public int getQuestionIdByChoiceId(int choice_id) {
        return choiceDao.getQuestionIdByChoiceId(choice_id);
    }

//    public String checkAnswer(Choice selectedChoice) {
//        return selectedChoice.is_correct();
//    }
}
