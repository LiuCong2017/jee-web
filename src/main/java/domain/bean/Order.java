package domain.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Integer id;
    private String customerNumber;
    private Integer age;
    private BigDecimal amount;
    private LocalDateTime orderTime;

    public Order(){
        super();
    }

    public Order(Integer id, String customerNumber, Integer age, BigDecimal amount, LocalDateTime orderTime) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.age = age;
        this.amount = amount;
        this.orderTime = orderTime;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
