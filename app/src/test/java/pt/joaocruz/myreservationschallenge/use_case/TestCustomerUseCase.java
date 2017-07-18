package pt.joaocruz.myreservationschallenge.use_case;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.schedulers.Schedulers;
import pt.joaocruz.myreservationschallenge.data.DataManager;
import pt.joaocruz.myreservationschallenge.model.Customer;
import pt.joaocruz.myreservationschallenge.usecase.GetCustomerUseCase;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestCustomerUseCase {

    GetCustomerUseCase useCase;

    @Before
    public void setup() {
        DataManager dataManager = mock(DataManager.class);
        when(dataManager.getCustomerWithID(1L)).thenReturn(validCustomer());
        when(dataManager.getCustomerWithID(-1L)).thenReturn(invalidCustomer());
        useCase = new GetCustomerUseCase(dataManager, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void testValidCustomer() throws Exception {
        List<Customer> customers = useCase.withID(1L).build().toList().blockingGet();
        assertNotNull(customers);
        assertEquals(customers.size(), 1);
        assertNotNull(customers.get(0).getId());
        assertEquals(1L, customers.get(0).getId().longValue());
    }

    @Test
    public void testInvalidCustomer() throws Exception {
        List<Customer> customers = useCase.withID(-1L).build().toList().blockingGet();
        assertNotNull(customers);
        assertEquals(customers.size(), 1);
        assertNull(customers.get(0).getId());
    }


    // Helpers

    private Customer validCustomer() {
        Customer validCustomer = new Customer();
        validCustomer.setId(1L);
        return validCustomer;
    }

    private Customer invalidCustomer() {
        Customer customer = new Customer();
        return customer;
    }
}