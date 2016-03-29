package com.epam.alexandrli.paintballshop.DAO2;

import com.epam.alexandrli.paintballshop.entity.Gender;

import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JdbcDaoFactory jdbcDaoFactory = new JdbcDaoFactory();
        GenericDao dao = jdbcDaoFactory.getDAO(Gender.class);
//        Address address = new Address();
//        address.setCountry("Kazakhstan");
//        address.setCity("Karaganda");
//        address.setStreet("Alihanova");
//        address.setBuildingNumber("34/2");
//        address.setApartmentNumber("37");
//        Gender gender = new Gender();
//        gender.setName("Мужчина");
//        DAO2.insert(gender);
//        gender.setName("Женщина");
//        DAO2.insert(gender);
//        System.out.println(gender.getId());

        Gender gender1 = (Gender) dao.read(3);
        System.out.println(gender1.getName() + " " + gender1.getId());
    }
}
