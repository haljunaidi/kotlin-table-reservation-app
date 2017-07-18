package pt.joaocruz.myreservationschallenge.managers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import pt.joaocruz.myreservationschallenge.data.DataManagerImpl;
import pt.joaocruz.myreservationschallenge.data.LocalDataManager;
import pt.joaocruz.myreservationschallenge.data.NetworkServices;
import pt.joaocruz.myreservationschallenge.data.OnlineDataManager;
import pt.joaocruz.myreservationschallenge.model.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestDataManager {

    DataManagerImpl dataManager;
    NetworkServices ns;
    LocalDataManager ldm;
    OnlineDataManager odm;

    @Before
    public void setup() {
        odm = mock(OnlineDataManager.class);
        ldm = mock(LocalDataManager.class);
        ns = mock(NetworkServices.class);
        dataManager = new DataManagerImpl(odm, ns, ldm);
    }


    @Test
    public void testGetLocalCustomers() throws Exception {
        prepareForLocalTest();
        List<ArrayList<Customer>> list = dataManager.getCustomersList().toList().blockingGet();
        verify(dataManager.getLocal(), times(1)).getCustomers();
        assertNotNull(list);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).size(), 1);
        assertNotNull(list.get(0).get(0).getId());
        assertEquals(123L, list.get(0).get(0).getId().longValue());

    }

    @Test
    public void testGetOnlineCustomers() throws Exception {
        prepareForOnlineTest();
        List<ArrayList<Customer>> list = dataManager.getCustomersList().toList().blockingGet();
        verify(dataManager.getRemote(), times(1)).getCustomers();
        assertNotNull(list);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).size(), 1);
        assertNotNull(list.get(0).get(0).getId());
        assertEquals(123L, list.get(0).get(0).getId().longValue());

    }



    // Helpers

    private void prepareForLocalTest() {
        when(ns.hasInternet()).thenReturn(false);
        when(ldm.getCustomers()).thenReturn(Observable.just(getValidCustomersList()));
    }

    private void prepareForOnlineTest() {
        when(ns.hasInternet()).thenReturn(true);
        when(odm.getCustomers()).thenReturn(Observable.just(getValidCustomersList()));
    }

    private ArrayList<Customer> getValidCustomersList() {
        Customer c1 = new Customer(); c1.setId(123L);
        ArrayList<Customer> list = new ArrayList();
        list.add(c1);
        return list;
    }
}