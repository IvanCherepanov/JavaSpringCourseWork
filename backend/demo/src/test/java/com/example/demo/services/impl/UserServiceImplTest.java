package com.example.demo.services.impl;

import com.example.demo.model.dao.IPetRepository;
import com.example.demo.model.dao.IUserRepository;
import com.example.demo.model.entity.Pet;
import com.example.demo.model.entity.User;
import com.example.demo.services.IPetService;
import com.example.demo.services.IUserService;
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
class UserServiceImplTest {
    @Mock
    private IUserRepository groupsRepository;
    private IUserService underTest;

    //перед каждым тестом получаем чистые данные
    @BeforeEach
    public void setUp() {
        underTest = new UserServiceImpl(groupsRepository);
    }

    @Test
    void createEntity() {
        // given
        com.example.demo.model.entity.User groups = new User();
        groups.setUsername("Василий");

        //when
        underTest.create(groups);

        //then

        //ArgumentCaptor хранит и предоставляет все значения соответствующего параметра,
        // с которыми метод был вызван до того, как данный
        ArgumentCaptor<User> groupsArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        //С помощью Mockito разработчик создает имитатор — мок, указывает библиотеке,
        // что делать при вызове определенных методов, а затем использует экземпляр имитатора в своем тесте
        // вместо реального объекта.
        Mockito.verify(groupsRepository).save(groupsArgumentCaptor.capture());

        User groupsArgumentCaptorValue = groupsArgumentCaptor.getValue();
        Assertions.assertThat(groupsArgumentCaptorValue).isEqualTo(groups);
    }
}