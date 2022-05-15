package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemTypeRepository;
import com.example.demo.model.dao.IPetRepository;
import com.example.demo.model.entity.ItemType;
import com.example.demo.model.entity.Pet;
import com.example.demo.services.IItemService;
import com.example.demo.services.IItemTypeService;
import com.example.demo.services.IPetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ItemTypeServiceImplTest {
    @Mock
    private IItemTypeRepository groupsRepository;
    private IItemTypeService underTest;

    //перед каждым тестом получаем чистые данные
    @BeforeEach
    public void setUp() {
        underTest = new ItemTypeServiceImpl(groupsRepository);
    }

    @Test
    void createEntity() {
        // given
        ItemType groups = new  ItemType();
        groups.setItemTypeName("Собака");

        //when
        underTest.create(groups);

        //then

        //ArgumentCaptor хранит и предоставляет все значения соответствующего параметра,
        // с которыми метод был вызван до того, как данный
        ArgumentCaptor<ItemType> groupsArgumentCaptor =
                ArgumentCaptor.forClass(ItemType.class);

        //С помощью Mockito разработчик создает имитатор — мок, указывает библиотеке,
        // что делать при вызове определенных методов, а затем использует экземпляр имитатора в своем тесте
        // вместо реального объекта.
        Mockito.verify(groupsRepository).save(groupsArgumentCaptor.capture());

        ItemType groupsArgumentCaptorValue = groupsArgumentCaptor.getValue();
        Assertions.assertThat(groupsArgumentCaptorValue).isEqualTo(groups);
    }
}