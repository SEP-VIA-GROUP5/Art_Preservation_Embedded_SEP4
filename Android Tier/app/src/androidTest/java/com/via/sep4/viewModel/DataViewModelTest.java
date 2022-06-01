package com.via.sep4.viewModel;

import static org.junit.Assert.*;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataViewModelTest {

    @Before
    public void setUp() throws Exception {
        ViewModel viewModel = new DataViewModel();
        Log.v("test start", "start test");
    }

    @After
    public void tearDown() throws Exception {
        Log.v("test end", "end est");
    }

    @Test
    public void addTempNorm() {
    }

    @Test
    public void addHumNorm() {
    }

    @Test
    public void addCO2Norm() {
    }
}