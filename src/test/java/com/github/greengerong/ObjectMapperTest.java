package com.github.greengerong;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2015                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class ObjectMapperTest {

    @Test
    public void should_Name() throws Exception {
        final String json = "{\"/order/item[0]\":\"item1\",\"/order/item[1]\":\"item2\"}";

        final QueryHandler queryHandler = new QueryHandler();
        queryHandler.response(json);

        assertThat(queryHandler.get("/order/item[0]"), is("item1"));
        assertThat(queryHandler.get("/order/item[1]"), is("item2"));

        System.out.println(queryHandler);

    }

    private abstract class HandlerBase<T> {

        public void response(String json) throws IOException {
            final Type type = ReflectionUtil.getParameterizedTypes(this)[0];
            final ObjectMapper mapper = new ObjectMapper();
            final Object model = mapper.readValue(json, TypeFactory.type(type));
            innerResponse((T) model);
        }

        protected abstract void innerResponse(T model);
    }

    private class QueryHandler extends HandlerBase<Map<String, String>> {

        private Map<String, String> model;

        @Override
        protected void innerResponse(Map<String, String> model) {
            this.model = model;
        }

        public String get(String key) {
            return model.get(key);
        }

        @Override
        public String toString() {
            return this.model.toString();
        }
    }
}
