package ua.kiev.dimoon.questcreator.services.quest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.*;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.QuestJpaRepository;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.QuestStepJpaRepository;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.UserQuestJpaRepository;
import ua.kiev.dimoon.questcreator.common.utils.security.SecuritiUtils;

import java.util.*;

import static ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity.SORT_BY_ODER;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestJpaRepository questRepository;
    @Autowired
    private UserQuestJpaRepository userQuestRepository;
    @Autowired
    private QuestStepJpaRepository questStepRepository;

    @Override
    public List<UserQuestJpaEntity> getQuestsForCurrentUser() {
        if (SecuritiUtils.isAuthenticated()) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return userQuestRepository.findByUser_UserLoginAndCompleted(userName, false);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void startQuestForCurrentUser(int questId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserQuestJpaEntity userQuest = userQuestRepository.findFirstByUser_UserLoginAndId(userName, questId);
        Set<QuestStepJpaEntity> questSteps = userQuest.getQuest().getQuestSteps();
        if (null != questSteps && !questSteps.isEmpty()) {
            questSteps.stream()
                    .sorted(SORT_BY_ODER)
                    .findFirst()
                    .ifPresent(questStepEntity -> {
                        userQuest.setQuestStep(questStepEntity);
                        userQuestRepository.save(userQuest);
                    });
        }
    }

    @Override
    public Boolean checkCurrentStepAnswer(String keyWord) {
        Boolean checkResult = false;
        Optional<UserQuestJpaEntity> currentUserQuestOptional = getCurrentUserQuest();
        Optional<QuestStepJpaEntity> currentQuestStepOptional =
                currentUserQuestOptional.map(UserQuestJpaEntity::getQuestStep);

        if (currentQuestStepOptional.isPresent()) {
            QuestStepJpaEntity currentQuestStep = currentQuestStepOptional.get();
            QuestStepFieldJpaEntity answerQuestStepField = currentQuestStep.getFields()
                    .stream()
                    .filter(questStepField -> questStepField.getFieldType().equals(FieldType.ANSWER))
                    .findFirst()
                    .orElse(new QuestStepFieldJpaEntity());
            if (isKeyWordCorrect(answerQuestStepField, keyWord) || currentQuestStep.isStartStep()) {
                UserQuestJpaEntity userQuestEntity = currentUserQuestOptional.get();
                Optional<QuestStepJpaEntity> nextQuestStep = getNextQuestStep(userQuestEntity.getQuest(), currentQuestStep);
                if (nextQuestStep.isPresent()) {
                    userQuestEntity.setQuestStep(nextQuestStep.get());
                    userQuestRepository.save(userQuestEntity);
                    checkResult = true;
                }
            }
        }
        return checkResult;
    }

    private boolean isKeyWordCorrect(QuestStepFieldJpaEntity answerQuestStepField, String keyWord) {
        return null != keyWord && keyWord.equals(answerQuestStepField.getValue());
    }

    private Optional<QuestStepJpaEntity> getNextQuestStep(QuestJpaEntity questEntity, QuestStepJpaEntity currentQuestStep) {
        return questEntity.getQuestSteps().stream()
                .sorted(SORT_BY_ODER)
                .filter(questStep -> questStep.getOrder() > currentQuestStep.getOrder())
                .findFirst();
    }

    private Optional<UserQuestJpaEntity> getCurrentUserQuest() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserQuestJpaEntity> userQuests = userQuestRepository.findByUser_UserLoginAndCompleted(userName, false);
        return userQuests.stream()
                .filter(userQuestEntity -> null != userQuestEntity.getQuestStep())
                .findFirst();
    }

    @Override
    public Optional<QuestStepJpaEntity> getCurrentQuestStepForCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserQuestJpaEntity> userQuests = userQuestRepository.findByUser_UserLoginAndCompleted(userName, false);
        return userQuests.stream()
                .filter(userQuestEntity -> null != userQuestEntity.getQuestStep())
                .findFirst()
                .map(UserQuestJpaEntity::getQuestStep);
    }

    @Override
    public void finishQuestForCurrentUser() {
        Optional<UserQuestJpaEntity> currentUserQuestOptional = getCurrentUserQuest();
        UserQuestJpaEntity currentUserQuest;
        if (currentUserQuestOptional.isPresent()
                && (currentUserQuest = currentUserQuestOptional.get()).getQuestStep() != null
                && !currentUserQuest.getCompleted()
                && StepType.END.equals(currentUserQuest.getQuestStep().getStepType())) {
            currentUserQuest.setCompleted(true);
            userQuestRepository.save(currentUserQuest);
        }
    }
}
