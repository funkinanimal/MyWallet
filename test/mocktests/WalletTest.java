package mocktests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WalletTest extends Assert {

    private Wallet wallet;

    @Before
    public void setUp() {
        wallet = new Wallet();
    }

    @Test
    public void addMoney() {
        wallet.addMoney("RUB", 600);
        wallet.addMoney("USD", 100);
        wallet.addMoney("RUB", 600);
        assertEquals(1200, wallet.getMoney("RUB"));
        assertEquals(100, wallet.getMoney("USD"));
    }

    @Test
    public void removeMoney() {
        wallet.addMoney("RUB", 1200);
        try {
            wallet.removeMoney("RUB", 1200);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        assertEquals(0, wallet.getMoney("RUB"));
        try {
            wallet.removeMoney("RUB", 1200);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        assertEquals(0, wallet.getMoney("RUB"));
    }

    @Test
    public void getCurrencyCount() throws Exception {
        wallet.addMoney("EUR", 100);
        wallet.removeMoney("EUR", 100);
        wallet.addMoney("USD", 50);
        assertEquals(1, wallet.getCurrencyCount());
    }

    @Test
    public void toStringTest() {
        assertEquals("{ }", wallet.toString());
        wallet.addMoney("RUB", 100);
        wallet.addMoney("USD", 200);
        assertEquals("{ 200 USD, 100 RUB }", wallet.toString());
    }

    @Test
    public void getTotalMoney() throws Exception {
        Bank bank = mock(Bank.class);
        when(bank.convert(200,"USD","RUB")).thenReturn(11274d);
        when(bank.convert(300,"EUR", "RUB")).thenReturn(18915d);

        when(bank.convert(100, "RUB", "USD")).thenReturn(1.7638);
        when(bank.convert(300, "EUR", "USD")).thenReturn(267d);

        wallet = new Wallet(bank);
        wallet.addMoney("RUB", 100);
        wallet.addMoney("USD", 200);
        wallet.addMoney("EUR", 300);
        assertEquals(30289, wallet.getTotalMoney("RUB"));
        assertEquals(468, wallet.getTotalMoney("USD"));
    }

}