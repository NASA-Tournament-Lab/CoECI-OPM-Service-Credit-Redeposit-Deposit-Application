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

package gov.opm.scrd.entities.common;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.type.StandardBasicTypes;


/**
 * Used to register Long type for BIGINT column.
 *
 * @author liuliquan
 * @version 1.0
 */
public class OPMPostgreSQLDialect extends PostgreSQL9Dialect {

    /**
     * Default constructor.
     */
    public OPMPostgreSQLDialect() {
        super();
        registerHibernateType(Types.BIGINT, StandardBasicTypes.LONG.getName());
    }

}
