<%@tag description="Product preview template" pageEncoding="UTF-8" %>
<%@attribute name="product" required="true" type="com.epam.alexandrli.paintballshop.entity.Product" %>

<div class="thumbnail">
    <img style="height: 200px;" src="http://images.kz.prom.st/32176938_w640_h640_pga_backwoods_hunter.png"/>
    <h4 align="center"><b>${product.name}</b></h4>
    <p align="justify">${product.description}</p>
    <p align="right"><span style="color: red;font-size: 12px;margin-right: 20px;">${product.price}</span><a
            class="btn btn-default" href="#" role="button">View
        details »</a></p>
</div>