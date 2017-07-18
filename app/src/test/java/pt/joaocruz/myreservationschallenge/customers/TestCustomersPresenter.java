package pt.joaocruz.myreservationschallenge.customers;

import org.junit.Before;
import org.junit.Test;
import pt.joaocruz.myreservationschallenge.data.DataManager;
import pt.joaocruz.myreservationschallenge.model.Customer;
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenterImpl;
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersView;
import pt.joaocruz.myreservationschallenge.usecase.GetCustomersUseCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestCustomersPresenter {

    private CustomersPresenterImpl presenter;

    @Before
    public void setup() {
        GetCustomersUseCase cuc = mock(GetCustomersUseCase.class);
        DataManager dm = mock(DataManager.class);
        CustomersView view = mock(CustomersView.class);
        presenter = new CustomersPresenterImpl(cuc, dm);
        presenter.registerView(view);
    }

    @Test
    public void testSubmitWithEmptyCustomer() throws Exception {
        Customer customer = new Customer();
        presenter.customerSelected(customer);
        verify(presenter.getView(), times(1)).showError(any(String.class));
        verify(presenter.getView(), times(0)).showTablesScreenForCustomer(any(Customer.class));
    }

    @Test
    public void testSubmitWithInvalidCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(-1L);
        presenter.customerSelected(customer);
        verify(presenter.getView(), times(1)).showError(any(String.class));
        verify(presenter.getView(), times(0)).showTablesScreenForCustomer(any(Customer.class));
    }

    @Test
    public void testSubmitWithValidCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        presenter.customerSelected(customer);
        verify(presenter.getView(), times(0)).showError(any(String.class));
        verify(presenter.getView(), times(1)).showTablesScreenForCustomer(any(Customer.class));
    }



}