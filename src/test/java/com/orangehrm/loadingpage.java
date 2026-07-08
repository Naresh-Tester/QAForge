package com.orangehrm;

import com.orangehrm.base.BaseClass;
import org.testng.annotations.Test;

public class loadingpage extends BaseClass {

    @Test
    public void title(){
        System.out.println("Loading Page");
        String title = driver.getTitle();
        System.out.println(title);

    }
}
