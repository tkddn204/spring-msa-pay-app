package net.rightpair.exception;

public class MembershipNotFoundException extends RuntimeException {
    public MembershipNotFoundException() {
        super("해당 멤버쉽이 존재하지 않습니다.");
    }
}
