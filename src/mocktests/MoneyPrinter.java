package mocktests;

class MoneyPrinter {

    void print(String operation, String currency, int amount) {
        System.out.print("Операция: " + operation + " валюта: " + currency + " сумма: " + amount + "\n");
    }
}
