package com.qgy.decorator;

/**
 * @Author: qgy
 * @Date: 2018/3/21 17:26
 * @Description:
 */
public  abstract class Decorator implements Person {
    protected Person  person;
    public void setPerson(Person person){
        this.person=person;
    }

    @Override
    public void eat() {
        person.eat();
    }
}
