/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xl.study.presto.sql.parser;

import java.util.Optional;

import com.facebook.presto.sql.parser.SqlParser;
import com.facebook.presto.sql.tree.Expression;
import com.facebook.presto.sql.tree.Query;
import com.facebook.presto.sql.tree.QuerySpecification;
import com.facebook.presto.sql.tree.Select;

public class TestSqlParser
{
    private static final SqlParser SQL_PARSER = new SqlParser();
    
    public static void main(String[] args) {
    	testSQL();
    	testJoin();

    }
    
    private static void testSQL() {
        SqlParser parser = new SqlParser();
        String sql = "select len(id),* from xyz where x=y group by x order by y limit 10";
        Query query = (Query)parser.createStatement(sql);
        QuerySpecification body = (QuerySpecification)query.getQueryBody();
        
        System.out.println("SQL = " + sql);
        System.out.println("-------------------");
        Select select = body.getSelect();
        System.out.println("Columns = " + select.getSelectItems());
        System.out.println("From = " + body.getFrom().get());
        Optional<Expression> where = body.getWhere();
        System.out.println("Where = " + where.get());
        System.out.println("Group by = " + body.getGroupBy());
        System.out.println("Order by = " + body.getOrderBy());
        System.out.println("Limit = " + body.getLimit().get());
    }
    
    private static void testJoin() {
        SqlParser parser = new SqlParser();
        String sql = "select len(id),* from xyz x , abc a where x.c1=a.c1 group by x.c1 order by a.c2 limit 10";
        Query query = (Query)parser.createStatement(sql);
        QuerySpecification body = (QuerySpecification)query.getQueryBody();
        
        System.out.println("SQL = " + sql);
        System.out.println("-------------------");
        Select select = body.getSelect();
        System.out.println("Columns = " + select.getSelectItems());
        System.out.println("From = " + body.getFrom().get());
        Optional<Expression> where = body.getWhere();
        System.out.println("Where = " + where.get());
        System.out.println("Group by = " + body.getGroupBy());
        System.out.println("Order by = " + body.getOrderBy());
        System.out.println("Limit = " + body.getLimit().get());
    }
}
