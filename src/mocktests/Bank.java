package mocktests;

import java.util.Hashtable;

class Bank {

    private Hashtable<String, Double> rate;

    Bank() {
        rate = new Hashtable<>();
        rate.put("USDRUB", 56.37);
        rate.put("RUBUSD", 0.017638);
        rate.put("EURRUB", 63.05);
        rate.put("RUBEUR", 0.015688);
        rate.put("USDEUR", 1.12);
        rate.put("EURUSD", 0.89);
    }

    double convert(int amount, String inCurr, String outCurr) throws Exception{

        String rateNow = inCurr + outCurr;

        if (rate.containsKey(rateNow)) {
            double rateValue = rate.get(rateNow);
            double rand = (-0.2 + Math.random() * 0.4);
            rateValue *= rand;
            return (int) rateValue * amount;
        }
        else {
            throw new Exception("Bank doesn't have this currency!");
        }

    }
}
