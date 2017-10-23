package ua.kiev.dimoon.questcreator.services.quest.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestServiceImpl implements QuestService {

    ConcurrentMap<String, QuestStepJpaEntity> userToCurrentQuestStep = new ConcurrentHashMap<>();
    final QuestJpaEntity quest = new QuestJpaEntity()
            .setId(1)
            .setTittle("Test tittle")
            .setQuestSteps(
                    Stream.of(
                            new QuestStepJpaEntity()
                                    .setId(1)
                                    .setOrder(1)
                                    .setStepType(StepType.START),
                            new QuestStepJpaEntity()
                                    .setId(2)
                                    .setOrder(2)
                                    .setStepType(StepType.COMMON_STEP)
                                    .setFields(
                                            Stream.of(
                                                    new QuestStepFieldJpaEntity()
                                                            .setId(1)
                                                            .setFieldType(FieldType.QUESTION)
                                                            .setTittle("Ключ тут")
                                                            .setValue("a[3]a[7]a[0]a[12]a[7]")
                                                            .setFieldDataType(FieldDataType.ARRAY),
                                                    new QuestStepFieldJpaEntity()
                                                            .setId(2)
                                                            .setFieldType(FieldType.AUXILIARY_DATA)
                                                            .setTittle("вхідний масив")
                                                            .setValue("a[] = 'ЗТ1БАМРГФОУЯЛК'")
                                                            .setFieldDataType(FieldDataType.ARRAY),
                                                    new QuestStepFieldJpaEntity()
                                                            .setId(3)
                                                            .setFieldType(FieldType.AUXILIARY_DATA)
                                                            .setTittle("підказка")
                                                            .setValue("пароль на листку")
                                                            .setFieldDataType(FieldDataType.ARRAY)
                                            ).collect(Collectors.toSet())
                                    ),
                            new QuestStepJpaEntity()
                                    .setId(3)
                                    .setOrder(3)
                                    .setStepType(StepType.END)
                    ).collect(Collectors.toSet())
            );

    @Override
    public List<QuestJpaEntity> getQuestsForCurrentUser() {
        //anonymousUser
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userName)) {
            return Collections.singletonList(quest);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void startQuestForCurrentUser(int questId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        userToCurrentQuestStep.remove(userName);
        userToCurrentQuestStep.put(userName, quest.getQuestSteps().stream().findFirst().get());
    }

    @Override
    public Boolean checkCurrentStepAnswer(String keyWord) {
        return keyWord.equals("1");
    }

    @Override
    public Optional<QuestStepJpaEntity> getNextQuestStepForCurrentUser() {
        return null;
    }

    @Override
    public void finishQuestForCurrentUser() {

    }
}
