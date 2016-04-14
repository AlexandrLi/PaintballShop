package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.Characteristic;

import java.sql.SQLException;

public class CharacteristicService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<Characteristic> characteristicDao;
    private ProductTypeService productTypeService;


    public CharacteristicService() throws SQLException {
        jdbcDaoFactory = new JdbcDaoFactory();
        this.characteristicDao = jdbcDaoFactory.getDao(Characteristic.class);
        this.productTypeService = new ProductTypeService();
        jdbcDaoFactory.close();
    }

    public Characteristic getAllCharacteristic(Integer id) throws SQLException {
        jdbcDaoFactory.getConnection();
        Characteristic characteristic = characteristicDao.findByPK(id);
        characteristic.setType(productTypeService.getProductTypeById(characteristicDao.findColumnByPK("product_type_id", id)));
        jdbcDaoFactory.close();
        return characteristic;
    }

}
