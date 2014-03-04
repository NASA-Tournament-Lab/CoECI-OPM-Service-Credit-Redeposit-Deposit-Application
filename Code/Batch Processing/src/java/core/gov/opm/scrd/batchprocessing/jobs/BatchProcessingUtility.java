/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package gov.opm.scrd.batchprocessing.jobs;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is the command line utility to start the scheduler for executing batch processing job.
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is stateless and thread safe.
 * </p>
 *
 * @author faeton, liuliquan
 * @version 1.0
 */
public class BatchProcessingUtility {

    /**
     * Private constructor to prevent class from being instantiated.
     */
    private BatchProcessingUtility() {
    }

    /**
     * Starts the scheduler from Spring context.
     *
     * @param args The command line arguments, not used.
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context
            = new ClassPathXmlApplicationContext("batchProcessApplicationContext.xml");

        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
                context.close();
                return;
            }
        }
    }
}

