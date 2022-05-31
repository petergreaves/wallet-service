package com.demo.wallet.controller;

import com.demo.wallet.config.WalletServiceTestConfiguration;
import com.demo.wallet.model.Wallet;
import com.demo.wallet.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(WalletServiceTestConfiguration.class)
class WalletControllerTest {

    @Autowired
    Wallet testWallet;

    @Mock
    WalletService walletService;

    @InjectMocks
    WalletController controller;

    private MockMvc mockMvc;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void checkFundsReturnsEmpty() throws Exception {

        when(walletService.getNotesForAmount(anyInt())).thenReturn(new ArrayList<Integer>());

        mockMvc.perform(get(controller.BASE_URL + "/check-funds/333")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void checkFundsReturnsACollectionForAdequateFunds() throws Exception {

        List<Integer> result = new ArrayList<Integer>();
        result.add(10);
        result.add(3);
        result.add(2);

        when(walletService.getNotesForAmount(anyInt())).thenReturn(result);

        mockMvc.perform(get(controller.BASE_URL + "/check-funds/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void checkFundsReturns400ForNegativeArg() throws Exception {

        mockMvc.perform(get(controller.BASE_URL + "/check-funds/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void checkFundsReturns400ForZero() throws Exception {

        mockMvc.perform(get(controller.BASE_URL + "/check-funds/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
}