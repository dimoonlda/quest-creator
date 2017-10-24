package ua.kiev.dimoon.questcreator.front.base.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestJpaEntity;

import java.util.List;
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
                .map(questJpaEntity -> modelMapper.map(questJpaEntity, QuestDto.class))
                .collect(Collectors.toList());
    }

}
