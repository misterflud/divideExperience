package com.divide.experience.auth;

import org.junit.Test;

/**
 * @author Anton Oleynikov {@literal <antonleynikov@gmail.com>}
 */

public class ClassTest {

    @Test
    public void classTest() {
        A a = new A("Privet A");
        System.out.println(a);
        A b = a;
        System.out.println(b);
        change(b);
        System.out.println(b);
        System.out.println(a);
    }

    private A change(A a) {
        a.value = "Poka";
        return a;
    }

    public class A {
        public String value;
        public A (String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

    }
}
