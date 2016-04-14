package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.CharacteristicItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharacteristicItemService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<CharacteristicItem> characteristicItemDao;
    private CharacteristicService characteristicService;

    public CharacteristicItemService() throws SQLException {
        jdbcDaoFactory = new JdbcDaoFactory();
        this.characteristicItemDao = jdbcDaoFactory.getDao(CharacteristicItem.class);
        this.characteristicService = new CharacteristicService();
        jdbcDaoFactory.close();
    }

    public CharacteristicItem getAllCharacteristicItem(String id) throws SQLException {
        jdbcDaoFactory.getConnection();
        int itemId = Integer.parseInt(id);
        CharacteristicItem characteristicItem = characteristicItemDao.findByPK(itemId);
        Integer characteristic_id = characteristicItemDao.findColumnByPK("characteristic_id", itemId);
        characteristicItem.setCharacteristic(characteristicService.getAllCharacteristic(characteristic_id));
        jdbcDaoFactory.close();
        return characteristicItem;
    }

    private CharacteristicItem fillCharacteristicItem(CharacteristicItem item) throws SQLException {
        Integer characteristic_id = characteristicItemDao.findColumnByPK("characteristic_id", item.getId());
        item.setCharacteristic(characteristicService.getAllCharacteristic(characteristic_id));
        return item;
    }

    public List<CharacteristicItem> getAllItemsByParams(Map<String, String> params) throws SQLException {
        jdbcDaoFactory.getConnection();
        List<CharacteristicItem> filledItems = new ArrayList<>();
        List<CharacteristicItem> characteristicItems = characteristicItemDao.findAllByParams(params);
        for (CharacteristicItem characteristicItem : characteristicItems) {
            filledItems.add(fillCharacteristicItem(characteristicItem));
        }
        jdbcDaoFactory.close();
        return filledItems;
    }
}
