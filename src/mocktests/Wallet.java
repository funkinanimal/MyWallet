package mocktests;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Wallet{

    private Map<String, Integer> money;
    private Bank bank;
    private MoneyPrinter moneyPrinter;

    Wallet() {
        money = new HashMap<>();
        bank = new Bank();
        moneyPrinter = new MoneyPrinter();
    }

    Wallet(Bank bank) {
        money = new HashMap<>();
        moneyPrinter = new MoneyPrinter();
        this.bank = bank;
    }

    void addMoney(String currency, int amount) {
        if (money.containsKey(currency))
            money.put(currency, money.get(currency) + amount);
        else
            money.put(currency, amount);
        moneyPrinter.print("add", currency, amount);
    }

    void removeMoney(String currency, int amount) throws Exception {
        if (money.containsKey(currency)) {
            if (money.get(currency) >= amount) {
                money.put(currency, money.get(currency) - amount);
                moneyPrinter.print("remove", currency, amount);
            }
            else {
                throw new Exception("No Money!");
            }
        }
        else {
            throw new Exception("No money!");
        }
    }

    int getMoney(String currency) {
        return money.getOrDefault(currency, 0);
    }

    int getCurrencyCount() {
        int q = 0;
        for (String curr: money.keySet()){
            if (money.get(curr) != 0)
                q++;
        }
        return q;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("{ ");
        for (Map.Entry<String, Integer> curr : money.entrySet() ) {
            out.append(curr.getValue()).append(" ").append(curr.getKey()).append(", ");
        }
        if (money.values().size() != 0)
            out.deleteCharAt(out.length() - 2);
        out.append("}");
        return out.toString();
    }

    int getTotalMoney(String currency) {
        int q = 0;
        for (Map.Entry<String, Integer> curr : money.entrySet() ) {
            if (Objects.equals(curr.getKey(), currency)) {
                q += curr.getValue();
            }
            else {
                try {
                    q += bank.convert(curr.getValue(), curr.getKey(), currency);
                }
                catch (Exception ex) {
                    System.out.print(ex.getMessage());
                }
            }
        }
        return q;
    }
}