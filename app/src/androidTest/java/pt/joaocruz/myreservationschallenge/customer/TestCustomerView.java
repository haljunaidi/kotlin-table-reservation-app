package pt.joaocruz.myreservationschallenge.customer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import pt.joaocruz.myreservationschallenge.R;
import pt.joaocruz.myreservationschallenge.model.Customer;
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenterImpl;
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersViewImpl;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TestCustomerView {

    @Rule
    public ActivityTestRule<CustomersViewImpl> rule  = new ActivityTestRule<>(CustomersViewImpl.class);

    CustomersViewImpl activity;

    @Before
    public void setup() {
        activity = rule.getActivity();
    }

    @Test
    public void testView() throws Exception {
        View view = activity.findViewById(R.id.list);
        assertNotNull(view);
        assertThat(view, instanceOf(ListView.class));
    }

    @Test
    @UiThreadTest
    public void testCustomerUpdate() {
        ArrayList<Customer> customers = validCustomerList();
        int size = customers.size();
        activity.updateCustomerList(customers);
        assertEquals(activity.getAdapter().getCount(), size);
    }

    @Test
    @UiThreadTest
    public void testCustomerClick() {
        ArrayList<Customer> customers = validCustomerList();
        activity.updateCustomerList(customers);
        ListView list = (ListView) activity.findViewById(R.id.list);
        assertNotNull(list);
        assertThat(list, instanceOf(ListView.class));

        // Selecting customer at position 0 of the list
        int clickedIndex = 0;
        list.performItemClick(list.getChildAt(clickedIndex), clickedIndex, list.getAdapter().getItemId(clickedIndex));

        // The customer selected (in the presenter) should be the same as the one selected in the view
        Customer selectedCustomerInPresenter = ((CustomersPresenterImpl)activity.presenter).getSelectedCustomer();
        assertEquals(selectedCustomerInPresenter.getId(), customers.get(clickedIndex).getId());

    }



    private ArrayList<Customer> validCustomerList() {
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        c1.setId(1L);
        c2.setId(2L);
        ArrayList<Customer> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        return list;
    }
}
