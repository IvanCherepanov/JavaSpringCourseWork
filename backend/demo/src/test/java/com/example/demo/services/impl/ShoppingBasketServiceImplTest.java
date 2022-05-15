package com.example.demo.services.impl;

import com.example.demo.model.dao.IShoppingBasketRepository;
import com.example.demo.model.dao.IUserRepository;
import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.model.entity.User;
import com.example.demo.services.IItemService;
import com.example.demo.services.IShoppingBasketService;
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
class ShoppingBasketServiceImplTest {
    @Mock
    private IShoppingBasketRepository groupsRepository;
    @Mock
    private IItemService iItemService;
    @Mock
    private IUserService iUserService;
    private IShoppingBasketService underTest;

    //перед каждым тестом получаем чистые данные
    @BeforeEach
    public void setUp() {
        underTest = new ShoppingBasketServiceImpl(groupsRepository, iItemService, iUserService);
    }

    @Test
    void createEntity() {
        // given
        ShoppingBasket groups = new ShoppingBasket();
        groups.setAmount(5);

        //when
        underTest.create(groups);

        //then

        //ArgumentCaptor хранит и предоставляет все значения соответствующего параметра,
        // с которыми метод был вызван до того, как данный
        ArgumentCaptor<ShoppingBasket> groupsArgumentCaptor =
                ArgumentCaptor.forClass(ShoppingBasket.class);

        //С помощью Mockito разработчик создает имитатор — мок, указывает библиотеке,
        // что делать при вызове определенных методов, а затем использует экземпляр имитатора в своем тесте
        // вместо реального объекта.
        Mockito.verify(groupsRepository).save(groupsArgumentCaptor.capture());

        ShoppingBasket groupsArgumentCaptorValue = groupsArgumentCaptor.getValue();
        Assertions.assertThat(groupsArgumentCaptorValue).isEqualTo(groups);
    }
}