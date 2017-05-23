package mocktests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WalletTest extends Assert {

    private Wallet wallet;

    @Before
    public void setUp() {
        wallet = new Wallet();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addMoney() {
        wallet.addMoney("RUB", 600);
        wallet.addMoney("USD", 100);
        wallet.addMoney("RUB", 600);
        try {
            wallet.removeMoney("RUB", 1200);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        assertEquals(0, wallet.getMoney("RUB"));
        assertEquals(1, wallet.getCurrencyCount());
        System.out.print(wallet.toString());
    }

}