package www.melody.chat.bus;

import java.util.List;

import www.melody.chat.domain.TestPerson;

/**
 * Created by zhengsheng on 16/7/9.
 */
public class TestPersonBus {

    private List<TestPerson> persons;

    public TestPersonBus(List<TestPerson> persons) {
        this.persons = persons;
    }

    public List<TestPerson> getPersons() {
        return persons;
    }
}
