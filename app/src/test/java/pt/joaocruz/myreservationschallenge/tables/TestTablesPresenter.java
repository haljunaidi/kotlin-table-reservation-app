package pt.joaocruz.myreservationschallenge.tables;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import pt.joaocruz.myreservationschallenge.data.DataManager;
import pt.joaocruz.myreservationschallenge.data.DataManagerImpl;
import pt.joaocruz.myreservationschallenge.model.TablesMap;
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenter;
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenterImpl;
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesView;
import pt.joaocruz.myreservationschallenge.usecase.GetCustomerUseCase;
import pt.joaocruz.myreservationschallenge.usecase.GetTablesMapUseCase;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestTablesPresenter {

    private TablesPresenterImpl presenter;

    @Before
    public void setup() {
        GetTablesMapUseCase tuc = mock(GetTablesMapUseCase.class);
        GetCustomerUseCase cuc = mock(GetCustomerUseCase.class);
        DataManager dm = mock(DataManager.class);
        TablesView view = mock(TablesView.class);
        presenter = new TablesPresenterImpl(tuc, cuc, dm);
        presenter.registerView(view);
    }

    @Test
    public void testSubmitWithNullTables() throws Exception {
        TablesMap map = nullTablesMap();
        presenter.clickedSubmitWithSelectedPosition(1, map);
        verify(presenter.getDm(), times(0)).saveTablesMap(any(TablesMap.class));
        verify(presenter.getView(), times(1)).goBackToCustomers();
    }

    @Test
    public void testSubmitWithFreeTable() throws Exception {
        TablesMap map = populatedFreeTablesMap();
        assertEquals(map.getTables().get(1), true);
        presenter.clickedSubmitWithSelectedPosition(1, map);
        assertEquals(map.getTables().get(1), false);
        verify(presenter.getDm(), times(1)).saveTablesMap(any(TablesMap.class));
        verify(presenter.getView(), times(1)).goBackToCustomers();
    }

    @Test
    public void testSubmitWithOccupiedTable() throws Exception {
        TablesMap map = populatedOccupiedTablesMap();
        assertEquals(map.getTables().get(1), false);
        presenter.clickedSubmitWithSelectedPosition(1, map);
        assertEquals(map.getTables().get(1), false);
        verify(presenter.getView(), times(1)).showError(any(String.class));
        verify(presenter.getDm(), times(0)).saveTablesMap(any(TablesMap.class));
        verify(presenter.getView(), times(1)).goBackToCustomers();
    }


    // Helpers

    public TablesMap populatedFreeTablesMap() {
        TablesMap map = new TablesMap();
        map.setTables(new ArrayList<Boolean>());
        for (int i=0; i<10; i++)
            map.getTables().add(true);
        return map;
    }

    public TablesMap populatedOccupiedTablesMap() {
        TablesMap map = new TablesMap();
        map.setTables(new ArrayList<Boolean>());
        for (int i=0; i<10; i++)
            map.getTables().add(false);
        return map;
    }

    public TablesMap nullTablesMap() {
        TablesMap map = new TablesMap();
        return map;
    }


}