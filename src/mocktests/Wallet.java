package mocktests;

import java.util.HashMap;
import java.util.Map;

class Wallet{

    private Map<String, Integer> money;
    private Bank bank;
    private MoneyPrinter moneyPrinter;

    Wallet() {
        money = new HashMap<>();
        bank = new Bank();
        moneyPrinter = new MoneyPrinter();
    }

    void addMoney(String currency, int amount) {
        if (money.containsKey(currency))
            money.put(currency, money.get(currency) + amount);
        else
            money.put(currency, amount);
    }

    void removeMoney(String currency, int amount) throws Exception {
        if (money.containsKey(currency)) {
            if (money.get(currency) >= amount)
                money.put(currency, money.get(currency) - amount);
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
        out.deleteCharAt(out.length() - 2);
        out.append("}");
        return out.toString();
    }

    int getTotalMoney(String currency) {
        //bank.convert();
        return 0;
    }
}