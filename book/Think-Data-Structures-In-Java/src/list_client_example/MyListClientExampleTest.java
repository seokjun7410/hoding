package list_client_example;

import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author downey
 *
 */
public class MyListClientExampleTest {

    /**
     * Test method for {@link MyListClientExample}.
     */
    @Test
    public void testListClientExample() {
        ListClientExample lce = new ListClientExample();
        @SuppressWarnings("rawtypes")
        List list = lce.getList();
        assertThat(list, instanceOf(ArrayList.class) );
    }

}