package mocktests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.*;

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

    @Test
    public void getTotalMoney() throws Exception {
        Bank bank = mock(Bank.class);
        when(bank.convert(200,"USD","RUB")).thenReturn(11274d);
        when(bank.convert(300,"EUR", "RUB")).thenReturn(18915d);
        wallet = new Wallet(bank);
        wallet.addMoney("RUB", 100);
        wallet.addMoney("USD", 200);
        wallet.addMoney("EUR", 300);
        assertEquals(30289, wallet.getTotalMoney("RUB"));
    }

}