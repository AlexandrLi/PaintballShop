package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAddressDao extends AbstractJdbcDao<Address> {

    public static final String INSERT_ADDRESS = "INSERT address(country, city, street, building_number, apartment_number) VALUES (?,?,?,?,?)";
    public static final String UPDATE_ADDRESS_BY_ID = "UPDATE address SET country=?, city=?, street=?, building_number=?, apartment_number=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_ADDRESS;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ADDRESS_BY_ID;
    }

    @Override
    protected Address getObjectFromResultSet(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCountry(rs.getString("country"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setBuildingNumber(rs.getString("building_number"));
        address.setApartmentNumber(rs.getString("apartment_number"));
        return address;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Address address, PreparedStatement ps) throws SQLException {
        ps.setString(1, address.getCountry());
        ps.setString(2, address.getCity());
        ps.setString(3, address.getStreet());
        ps.setString(4, address.getBuildingNumber());
        ps.setString(5, address.getApartmentNumber());
    }

    @Override
    protected String getTableName() {
        return "address";
    }

    public void setFKById(Integer userId, Integer id) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE address SET user_id=? WHERE id=" + id)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Couldn't update Object in db", e);
        }
    }
}
