package ua.kiev.dimoon.questcreator.front.base.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.FieldType;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepFieldJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoBuilder {

    private ModelMapper modelMapper;

    public DtoBuilder(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestDto getQuestDto(QuestJpaEntity questJpaEntity) {
        return modelMapper.map(questJpaEntity, QuestDto.class);
    }

    public List<QuestDto> getQuestDtos(List<QuestJpaEntity> questJpaEntitys) {
        return questJpaEntitys
                .stream()
                .map(this::getQuestDto)
                .collect(Collectors.toList());
    }

    public QuestStepFieldDto getQuestStepFieldDto(QuestStepFieldJpaEntity questStepFieldEntity) {
        return modelMapper.map(questStepFieldEntity, QuestStepFieldDto.class);
    }

    public List<QuestStepFieldDto> getQuestStepFieldDtos(Collection<QuestStepFieldJpaEntity> questStepFieldEntities) {
        return questStepFieldEntities
                .stream()
                .map(this::getQuestStepFieldDto)
                .collect(Collectors.toList());
    }

    public QuestStepDto getQuestStepDto(QuestStepJpaEntity questStepEntity) {
        QuestStepDto questStepDto = modelMapper.map(questStepEntity, QuestStepDto.class);
        Set<QuestStepFieldJpaEntity> fields = questStepEntity.getFields();
        questStepDto.setNeedAnswer(
                fields.stream()
                        .anyMatch(field -> field.getFieldType().equals(FieldType.ANSWER))
        );
        questStepDto.setAuxiliaryDatas(
                fields.stream()
                        .filter(field -> field.getFieldType().equals(FieldType.AUXILIARY_DATA))
                        .map(this::getQuestStepFieldDto)
                        .collect(Collectors.toList())
        );
        fields.stream()
                .filter(field -> field.getFieldType().equals(FieldType.QUESTION))
                .findFirst()
                .map(this::getQuestStepFieldDto)
                .ifPresent(questStepDto::setQuestion);
        return questStepDto;
    }

}
