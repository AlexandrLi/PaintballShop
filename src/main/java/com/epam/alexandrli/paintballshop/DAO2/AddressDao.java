package com.epam.alexandrli.paintballshop.DAO2;

import com.epam.alexandrli.paintballshop.entity.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddressDao extends AbstractJdbcDao<Address> {


    @Override
    public void insert(Address address) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO address(country, city, street, building_number, apartment_number) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, address.getCountry());
        ps.setString(2, address.getCity());
        ps.setString(3, address.getStreet());
        ps.setString(4, address.getBuildingNumber());
        ps.setString(5, address.getApartmentNumber());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
            address.setId(rs.getInt(1));
        }

    }

    @Override
    public Address read(int id) {
        return null;
    }

    @Override
    public List<Address> readAll() {
        return null;
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void delete(Address address) {

    }
}
