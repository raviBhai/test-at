package com.costExplorer;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CostServiceImplTest {

    CostService toBeTested;

    @Test
    public void when_customerIdGiven_then_returnMonthlyCosts() {

        DataProvider dataProvider = new DataProvider();
        toBeTested = new CostServiceImpl(dataProvider);
        List<String> monthlyCosts = toBeTested.getMonthlyCosts(1);
        int actual = monthlyCosts.size();
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void when_invalidCustomerIdGiven_then_returnEmpty() {
        DataProvider dataProvider = Mockito.mock(DataProvider.class);
        List<String> invalidCosts = new ArrayList<>();
        int customerId = 2;
        when(dataProvider.getCustomerCosts(customerId)).thenReturn(invalidCosts);

        toBeTested = new CostServiceImpl(dataProvider);
        List<String> monthlyCosts = toBeTested.getMonthlyCosts(customerId);
        int actual = monthlyCosts.size();
        int expected = 0;

        assertEquals(expected, actual);
    }
}
