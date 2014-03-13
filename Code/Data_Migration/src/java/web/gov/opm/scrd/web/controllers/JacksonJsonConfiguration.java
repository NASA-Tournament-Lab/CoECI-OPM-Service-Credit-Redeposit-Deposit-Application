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

package gov.opm.scrd.web.controllers;

import gov.opm.scrd.entities.common.Helper.HibernateLazyLoadModule;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;


/**
 * <p>
 * Configure the Jackson JSON converter to serialize Hibernate lazy loaded objects.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author liuliquan
 * @version 1.0
 * @since OPM - Frontend - Miscellaneous Module Assembly
 */
@Component
public class JacksonJsonConfiguration {

    /**
     * The request mapping handler adapter.
     */
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * Configure Jackson JSON converter.
     */
    @PostConstruct
    public void init() {
        List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                // Register Hibernate module to serialize lazy-loaded objects
                ((MappingJackson2HttpMessageConverter) messageConverter).getObjectMapper()
                    .registerModule(new HibernateLazyLoadModule());
            }
        }
    }
}
