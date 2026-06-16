package com.opencart.utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "taxGroupData")
    public Object[][] taxGroupData() {
        return new Object[][]{
                {"june2026", "june2026", "06/01/2026", "06/30/2026"},
                {"july2026", "july2026",
                        "07/01/2026", "07/31/2026"},

                {"aug2026", "aug2026",
                        "08/01/2026", "08/31/2026"}

        };
    }
}
