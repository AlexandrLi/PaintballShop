package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Image;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcImageDao extends AbstractJdbcDao<Image> {

    public static final String INSERT_IMAGE = "INSERT INTO image(name, content, product_id, modified, content_type) VALUES (?,?,?,?,?)";
    public static final String UPDATE_IMAGE_BY_ID = "UPDATE image SET name=?, content=?, modified=?, content_type=? WHERE id=?";

    @Override
    protected Image getObjectFromResultSet(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt("id"));
        image.setName(rs.getString("name"));
        image.setModified(new DateTime(rs.getTimestamp("modified")));
        image.setContentType(rs.getString("content_type"));
        return image;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_IMAGE;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Image image, PreparedStatement ps) throws SQLException {
        ps.setString(1, image.getName());
        // TODO: 03.04.2016 write realisation for update content column(ps.setBlob)
        ps.setTimestamp(2, new Timestamp(image.getModified().getMillis()));
        ps.setString(3, image.getContentType());
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
