package service;

import exception.DivideNumberByZeroException;

class Calculator {

    int sum(int a, int b) {
        return a + b;
    }

    int sub(int a, int b) {
        return a - b;
    }

    int divide(int a, int b) throws DivideNumberByZeroException {
        if(b == 0) {
            throw new DivideNumberByZeroException();
        }

        return a / b;
    }
}
