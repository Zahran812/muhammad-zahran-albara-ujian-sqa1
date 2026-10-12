package com.juaracoding;

public class AssertionHelper {
    public static boolean assertEquals(String actual, String expected, String message)
    {
        boolean pass = actual.equals(expected);
        System.out.printf("[%s] Expected: '%s' | Actual: '%s'\n", (pass ? "PASS" : "FAIL"), expected, actual);
        return pass;
    } //
    
    // Overload 2: Memvalidasi Bilangan Bulat (int)
    public static boolean assertEquals(int actual, int expected, String message) {
        boolean pass = (actual == expected);
        System.out.printf("[%s] Expected: %d | Actual: %d\n", (pass ? "PASS" : "FAIL"), expected, actual);
        return pass;
    } //
    
    //  Overload 3: Memvalidasi Desimal dengan Toleransi Delta (double)
    public static boolean assertEquals(double actual, double expected, double delta, String message) {
        boolean pass = Math.abs(actual - expected) <= delta;
        System.out.printf("[%s] Expected: %.2f | Actual: %.2f (Delta: %.3f)\n", (pass ? "PASS" : "FAIL"), expected, actual, delta);
        return pass;
    }

    public static void main(String[] args) {
        assertEquals("Login Sukses", "Login Sukses", "Pesan UI");
        assertEquals(200, 200, "HTTP Status Code");
        assertEquals(1450000.50, 1450000.50, 0.001, "Total Saldo");
    }
}
