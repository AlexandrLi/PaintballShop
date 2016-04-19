package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Image;
import com.epam.alexandrli.paintballshop.entity.Product;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcImageDao extends AbstractJdbcDao<Image> {

    public static final String INSERT_IMAGE = "INSERT INTO shopdb.image(name, content, product_id, modified, content_type, deleted) VALUES (?,?,?,?,?)";
    public static final String UPDATE_IMAGE_BY_ID = "UPDATE shopdb.image SET name=?, content=?, product_id=?, modified=?, content_type=? WHERE id=?";

    @Override
    protected Image getObjectFromResultSet(ResultSet rs) throws DaoException {
        Image image = new Image();
        try {
            image.setId(rs.getInt("id"));
            image.setName(rs.getString("name"));
            image.setContent(rs.getBinaryStream("content"));
            Product product = new Product(rs.getInt("product_id"));
            image.setProduct(product);
            image.setModified(new DateTime(rs.getTimestamp("modified")));
            image.setContentType(rs.getString("content_type"));
            image.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return image;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_IMAGE;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Image image, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, image.getName());
            ps.setBlob(2, image.getContent());
            ps.setInt(3, image.getProduct().getId());
            ps.setTimestamp(4, new Timestamp(image.getModified().getMillis()));
            ps.setString(5, image.getContentType());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_IMAGE_BY_ID;
    }

    @Override
    protected String getTableName() {
        return "image";
    }

}
