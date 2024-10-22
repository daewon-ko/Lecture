package collection.map.test.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    private Map<String, Member> members = new HashMap<>();

    public void save(Member member) {
        members.put(member.getId(), member);
    }

    public Member findById(String id) {
        return members.get(id);
    }

    public void remove(String id) {
        members.remove(id);
    }

    public Member findByName(String name) {
        for (Map.Entry<String, Member> entry : members.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
