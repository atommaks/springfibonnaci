package com.example.springfibonnaci;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.math.BigInteger;

@org.springframework.stereotype.Service
@ConditionalOnProperty(
        value="text.mode",
        havingValue = "prod",
        matchIfMissing = true
)
public class FibonacciProdService implements com.example.springfibonnaci.Service {

    public String countNthFib(int n) {
        if (n == 0) {
            return BigInteger.ZERO.toString();
        }

        BigInteger[][] a = {
                {BigInteger.ONE, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        };
        BigInteger[][] powerOfA = matrixPowerFast(a, n - 1);

        return powerOfA[0][0].toString();
    }


    private static BigInteger[][] matrixPowerFast(BigInteger[][] a, int n) {
        if (n == 0) {
            // любая матрица в нулевой степени равна единичной матрице
            return new BigInteger[][]{
                    {BigInteger.ONE, BigInteger.ZERO},
                    {BigInteger.ZERO, BigInteger.ONE}
            };
        } else if (n % 2 == 0) {
            // a ^ (2k) = (a ^ 2) ^ k
            return matrixPowerFast(matrixMultiplication(a, a), n / 2);
        } else {
            // a ^ (2k + 1) = (a) * (a ^ 2k)
            return matrixMultiplication(matrixPowerFast(a, n - 1), a);
        }
    }

    private static BigInteger[][] matrixMultiplication(BigInteger[][] a, BigInteger[][] b) {
        // [a00 * b00 + a01 * b10, a00 * b01 + a01 * b11]
        // [a10 * b00 + a11 * b10, a10 * b01 + a11 * b11]
        return new BigInteger[][]{
                {a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0])), a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]))},
                {a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0])), a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]))},
        };
    }
}
