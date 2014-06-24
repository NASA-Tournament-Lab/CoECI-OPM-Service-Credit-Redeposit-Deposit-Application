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

package gov.opm.scrd;

import gov.opm.scrd.entities.application.ServicePeriod;

import java.util.Comparator;

/**
 * Comparator implementation to compare ServicePeriod objects by their begin dates.
 * @author yedtoss
 * @version 1.0
 */
public final class ServicePeriodComparator implements Comparator<ServicePeriod> {
    /**
     * Constructor.
     */
    public ServicePeriodComparator() {
    }
    /**
     * Compare two ServicePeriod objects.
     * @param esp1 the first period to compare
     * @param esp2 the second period to compare
     * @return the comparison result
     */
    @Override
    public int compare(ServicePeriod esp1, ServicePeriod esp2) {
        return esp1.getBeginDate().compareTo(esp2.getBeginDate());
    }
}
