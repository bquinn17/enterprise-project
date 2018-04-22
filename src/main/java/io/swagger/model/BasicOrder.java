package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.RetailOrder.StatusEnum;

import javax.validation.Valid;

/*
   POJO to represent the information needed from an order to be sent to inventory. There is a list
       of these returned by the /orders/all endpoint.

   This will not be explicitly mapped to any data in the database. It is simply to format the data
       in a different way.
 */
public class BasicOrder {

        private Long id;


        private StatusEnum status = null;


        private List<Product> products = null;


    /**
     * Get ID
     * @return id of order
     **/
    public Long getID() { return id; }

    public void setID(Long id) { this.id = id; }


    public BasicOrder status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     * @return status
     **/
    @ApiModelProperty(value = "")


    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    public BasicOrder products(List<Product> products) {
        this.products = products;
        return this;
    }

    public BasicOrder addProductsItem(Product productsItem) {
        if (this.products == null) {
            this.products = new ArrayList<Product>();
        }
        this.products.add(productsItem);
        return this;
    }

    /**
     * Get products
     * @return products
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicOrder BasicOrder = (BasicOrder) o;
        return Objects.equals(this.id, BasicOrder.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, products);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BasicOrder {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    products: ").append(toIndentedString(products)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
