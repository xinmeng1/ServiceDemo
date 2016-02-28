/*
 *  Copyright (C) 2015 The IDigiSign Android Project
 *
 *  Licensed under the Deepnet Security (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.deepnetsecurity.com/legal/
 *
 *  Under the international copyright law, neither the Deepnet
 *  Security software or documentation may be copied, reproduced,
 *  translated or reduced to any electronic medium or machine
 *  readable form, in whole or in part, without the prior written
 *  consent of Deepnet Security.
 *
 *  Please read your licence agreement with Deepnet carefully
 *  and make sure you understand the exact terms of usage.
 *  In particular, for which projects, on which platforms and
 *  at which sites, you are allowed to use the product.
 *  You are not allowed to make any modifications to the
 *  product. If you feel the need for any modifications, please
 *  contact Deepnet Security.
 */

package android.deepnetsecurity.com.servicedemo;

import com.logentries.logger.AndroidLogger;

import java.io.IOException;

/**
 * Created by Android Studio.
 * User: xin
 * Date: 26/02/2016 0026
 * Time: 05:55:41 PM
 * Version: V 1.0
 */

/**
 *
 * Logentries help class
 *
 * 1. Use HTTP
 * 2. Use the application context
 *
 * Usage:
 *
 * 1. Define a AndroidLogger logger = null as member of the class
 * 2. logger = createLogger() in the initial of the class, such as onCreate()...
 * 3. logger.log(String msg) send
 *
 */
public class LogentriesUtil {

    public static AndroidLogger createLogger() {
        try {
            return AndroidLogger.createInstance( MyApplication.getContext(),
                    true, false, false, null, 0, "64a28ed6-6c15-49e5-860e-dd59d172238a" , false) ;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AndroidLogger getLogger(){
        AndroidLogger logger = null;
        try{
            logger = AndroidLogger.getInstance();
            if (logger == null) {
                logger = AndroidLogger.createInstance(MyApplication.getContext(),
                        false, false, false, null, 0, "64a28ed6-6c15-49e5-860e-dd59d172238a", false);
            } else {
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return logger;
    }
}
